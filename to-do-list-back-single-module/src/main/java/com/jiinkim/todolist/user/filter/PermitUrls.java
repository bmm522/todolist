package com.jiinkim.todolist.user.filter;

import java.util.ArrayList;
import java.util.List;

public class PermitUrls {

    private static final List<String> permitUrls = new ArrayList<>();

    static {
        permitUrls.addAll(List.of("/login", "/user/check-duplicate", "/user/register",  "/favicon.ico"));
    }

    public static boolean isPermitted(String url) {
        return  permitUrls.contains(url);
    }

}
