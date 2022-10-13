package cl.ionix.user.controller.dto;

import lombok.*;

import javax.xml.transform.Result;

@AllArgsConstructor(access = AccessLevel.PUBLIC)
  @NoArgsConstructor
  public class ResponseExternalApiDto {
    @Getter
    @Setter
    int responseCode;
    @Getter
    @Setter
    String description;
    @Getter
    @Setter
    long elapsedTime;
    @Getter
    @Setter
    Result result;
  }

