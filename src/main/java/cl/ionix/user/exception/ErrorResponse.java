package cl.ionix.user.exception;

import lombok.*;

import java.util.List;

@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor
public class ErrorResponse {
  @Getter
  @Setter
  String message;
  @Getter
  @Setter
  List<String> details;
}
