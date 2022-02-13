package com.momo.domain.aws.util;

public class GenerateUploadPathUtil {

    public static String getUserImage(Long userId) {
        return String.format("user/%s/profile", userId);
    }

    public static String getGroupImage(Long groupId) {
        return String.format("group/%s/profile", groupId);
    }

    public static String getPostImage(Long groupId, Long postId) {
        return String.format("group/%s/posts/%s", groupId, postId);
    }
}