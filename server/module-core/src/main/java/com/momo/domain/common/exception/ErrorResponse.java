package com.momo.domain.common.exception;

import java.util.List;
import java.util.stream.Collectors;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.validation.BindingResult;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ErrorResponse {

  private int status;

  private String message;

  private String code;

  private List<FieldError> errors;

  private ErrorResponse(ErrorCode code) {
    this.status = code.getStatus();
    this.message = code.getMessage();
  }

  private ErrorResponse(ErrorCode code, List<FieldError> errors) {
    this.status = code.getStatus();
    this.message = code.getMessage();
    this.errors = errors;
  }

  public static ErrorResponse of(ErrorCode code) {
    return new ErrorResponse(code);
  }

  public static ErrorResponse of(ErrorCode code, BindingResult bindingResult) {
    return new ErrorResponse(code, FieldError.of(bindingResult));
  }

  @Getter
  @NoArgsConstructor(access = AccessLevel.PRIVATE)
  @AllArgsConstructor(access = AccessLevel.PRIVATE)
  public static class FieldError {

    private String field;

    private String value;

    private String reason;


    public static List<FieldError> of(BindingResult bindingResult) {
      List<org.springframework.validation.FieldError> fieldErrors = bindingResult.getFieldErrors();
      return fieldErrors.stream()
          .map(error -> new FieldError(
              error.getField(),
              error.getRejectedValue() == null ? "" : error.getRejectedValue().toString(),
              error.getDefaultMessage()))
          .collect(Collectors.toList());
    }
  }
}
