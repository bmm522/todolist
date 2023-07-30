package com.jiinkim.todolist.user.jwt;

import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;


@Getter
@Setter
public class JwtProperties {

  public String secret;

  public String tokenPrefix;


  public String refreshPrefix;


  public String headerJwt;

  public String headerRefresh;

  public String iss;





}
