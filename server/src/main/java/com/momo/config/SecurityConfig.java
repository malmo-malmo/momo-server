package com.momo.config;

import com.momo.security.CustomAuthenticationEntryPoint;
import com.momo.security.CustomUserDetailsService;
import com.momo.security.TokenAuthenticationFilter;
import com.momo.security.TokenProvider;
import com.momo.security.oauth.CustomOAuth2UserService;
import com.momo.security.oauth.HttpCookieOAuth2AuthorizationRequestRepository;
import com.momo.security.oauth.OAuth2AuthenticationFailureHandler;
import com.momo.security.oauth.OAuth2AuthenticationSuccessHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  private static final String USER = "USER";

  private final CustomUserDetailsService customUserDetailsService;

  private final CustomOAuth2UserService customOAuth2UserService;

  private final OAuth2AuthenticationSuccessHandler oAuth2AuthenticationSuccessHandler;

  private final OAuth2AuthenticationFailureHandler oAuth2AuthenticationFailureHandler;

  private final TokenProvider tokenProvider;

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public TokenAuthenticationFilter tokenAuthenticationFilter() {
    return new TokenAuthenticationFilter();
  }

  @Bean
  public HttpCookieOAuth2AuthorizationRequestRepository cookieAuthorizationRequestRepository() {
    return new HttpCookieOAuth2AuthorizationRequestRepository();
  }

  @Override
  public void configure(AuthenticationManagerBuilder authenticationManagerBuilder)
      throws Exception {
    authenticationManagerBuilder
        .userDetailsService(customUserDetailsService)
        .passwordEncoder(passwordEncoder());
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
        .headers().frameOptions().disable().and()
        .csrf().disable()
        .formLogin().disable()
        .httpBasic().disable()
        .cors().and()

        .exceptionHandling()
        .authenticationEntryPoint(new CustomAuthenticationEntryPoint())
        .and()

        .sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()

        .authorizeRequests()
        .antMatchers("/api/oauth2/**").permitAll()
        .antMatchers("/api/**").hasRole(USER)
        .and()

        .oauth2Login()
        .authorizationEndpoint()
        .baseUri("/api/oauth2/authorization")
        .authorizationRequestRepository(cookieAuthorizationRequestRepository())
        .and()

        .redirectionEndpoint()
        .baseUri("/*/oauth2/code/*")
        .and()

        .userInfoEndpoint()
        .userService(customOAuth2UserService)
        .and()

        .successHandler(oAuth2AuthenticationSuccessHandler)
        .failureHandler(oAuth2AuthenticationFailureHandler);

    http.addFilterBefore(tokenAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
  }

  @Override
  public void configure(WebSecurity web) {
    web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
  }
}
