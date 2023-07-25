package com.jiinkim.todolist.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Encoder {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    public String encodeByBCryptPasswordEncoder(String strBeforeEncode) {
        return bCryptPasswordEncoder.encode(strBeforeEncode);
    }
}
