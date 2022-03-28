package com.momo.config.model;

import com.momo.domain.user.domain.User;
import java.security.Principal;

public class SocketPrincipal implements Principal {

    private User user;

    public SocketPrincipal(User user) {
        this.user = user;
    }

    @Override
    public String getName() {
        return this.user.getNickname();
    }

    public User getUser() {
        return this.user;
    }
}
