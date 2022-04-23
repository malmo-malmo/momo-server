package com.momo.aws.util;

public class GenerateUploadPathUtil {

    public static String getUserImagePath(Long userId) {
        return String.format("user/%s/profile", userId);
    }

    public static String getGroupImagePath(Long groupId) {
        return String.format("group/%s/profile", groupId);
    }

    public static String getPostImagePath(Long groupId, Long postId) {
        return String.format("group/%s/posts/%s", groupId, postId);
    }
}
