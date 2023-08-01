package com.jiinkim.todolist.user.service.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ReissueTokenResponse {


  private final String accessToken;

  public static ReissueTokenResponse create(final String accessToken) {
    return new ReissueTokenResponse(accessToken);
  }

}
