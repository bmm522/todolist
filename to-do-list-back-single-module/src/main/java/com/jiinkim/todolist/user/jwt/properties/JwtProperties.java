package com.jiinkim.todolist.user.jwt.properties;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "env")
@Component
public class JwtProperties {

  public static String SECRET;


  public static String TOKEN_PREFIX;


  public static String REFRESH_PREFIX;


  public static String HEADER_JWT;


  public static String HEADER_REFRESH;


  public static String ISS;





}
