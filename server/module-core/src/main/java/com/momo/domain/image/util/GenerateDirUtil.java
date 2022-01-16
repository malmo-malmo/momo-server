package com.momo.domain.image.util;

public class GenerateDirUtil {

    public static String userProfile(Long userId) {
        return String.format("user/%s/profile", userId);
    }

    public static String groupProfile(Long groupId) {
        return String.format("group/%s/profile", groupId);
    }

    public static String posts(Long groupId, Long postId) {
        return String.format("group/%s/posts/%s", groupId, postId);
    }
}