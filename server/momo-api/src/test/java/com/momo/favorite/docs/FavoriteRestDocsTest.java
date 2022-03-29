package com.momo.favorite.docs;

import static com.momo.FavoriteFixture.getFavoriteGroupCardResponse;
import static com.momo.FavoriteFixture.getFavoriteGroupCreateRequest;
import static com.momo.GroupFixture.getGroupCardResponse;
import static com.momo.domain.group.entity.Category.HOBBY;
import static com.momo.domain.group.entity.Category.LIFE;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.delete;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.patch;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.momo.favorite.FavoriteController;
import com.momo.common.RestDocsControllerTest;
import com.momo.domain.common.dto.EnumResponse;
import com.momo.domain.favorite.dto.FavoriteCategoriesUpdateRequest;
import com.momo.domain.favorite.dto.FavoriteGroupCardResponse;
import com.momo.domain.favorite.dto.FavoriteGroupCountResponse;
import com.momo.domain.favorite.dto.FavoriteGroupCreateRequest;
import com.momo.domain.favorite.service.FavoriteService;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;

@WebMvcTest(FavoriteController.class)
@DisplayName("관심 문서화 테스트")
public class FavoriteRestDocsTest extends RestDocsControllerTest {

    @InjectMocks
    private FavoriteController favoriteController;

    @MockBean
    private FavoriteService favoriteService;

    @Test
    void 관심_모임_등록() throws Exception {
        FavoriteGroupCreateRequest request = getFavoriteGroupCreateRequest(1L);

        String content = super.objectMapper.writeValueAsString(request);
        super.mockMvc.perform(post("/api/favorite/group")
                .content(content)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
            )
            .andDo(print())
            .andExpect(status().isCreated())
            .andDo(FavoriteDocumentation.createFavoriteGroup());
    }

    @Test
    void 관심_모임_수_조회() throws Exception {
        FavoriteGroupCountResponse response = new FavoriteGroupCountResponse(10L);

        when(favoriteService.countFavoriteGroupsByUser(any())).thenReturn(response);

        super.mockMvc.perform(get("/api/favorite/group/count"))
            .andDo(print())
            .andExpect(status().isOk())
            .andDo(FavoriteDocumentation.findFavoriteGroupCount());
    }

    @Test
    void 관심_모임_목록_조회() throws Exception {
        List<FavoriteGroupCardResponse> responses = List.of(getFavoriteGroupCardResponse(getGroupCardResponse()));

        when(favoriteService.findFavoriteGroupsByUser(any())).thenReturn(responses);

        super.mockMvc.perform(get("/api/favorite/groups"))
            .andDo(print())
            .andExpect(status().isOk())
            .andDo(FavoriteDocumentation.findFavoriteGroups());
    }

    @Test
    void 관심_카테고리_목록_조회() throws Exception {
        List<EnumResponse> responses = List.of(EnumResponse.ofCategory(LIFE), EnumResponse.ofCategory(HOBBY));

        when(favoriteService.findFavoriteCategoriesByUser(any())).thenReturn(responses);

        super.mockMvc.perform(get("/api/favorite/categories"))
            .andDo(print())
            .andExpect(status().isOk())
            .andDo(FavoriteDocumentation.findFavoriteCategories());
    }

    @Test
    void 관심_카테고리_수정() throws Exception {
        FavoriteCategoriesUpdateRequest request = new FavoriteCategoriesUpdateRequest(List.of(LIFE));

        String content = super.objectMapper.writeValueAsString(request);
        super.mockMvc.perform(patch("/api/favorite/categories")
                .content(content)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
            )
            .andDo(print())
            .andExpect(status().isOk())
            .andDo(FavoriteDocumentation.updateFavoriteCategories());
    }

    @Test
    void 관심_모임_삭제() throws Exception {
        super.mockMvc.perform(delete("/api/favorite/group/{groupId}", 1L))
            .andDo(print())
            .andExpect(status().isNoContent())
            .andDo(FavoriteDocumentation.deleteFavoriteGroup());
    }
}
