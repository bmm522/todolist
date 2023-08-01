package com.jiinkim.todolist.common.config.security.filter;

import org.springframework.util.AntPathMatcher;

import java.util.ArrayList;
import java.util.List;

public class PermitUrls {

    private static final List<String> permitUrls = new ArrayList<>();

    static {
        permitUrls.addAll(List.of("/login", "/user/check-duplicate", "/user/register", "/user/re-issue"));
    }

    public static boolean isPermitted(String url) {
        return permitUrls.contains(url);
    }

}
