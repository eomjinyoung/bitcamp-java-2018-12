package com.eomcs.lms.context;

public class ApplicationContextException extends RuntimeException {
  private static final long serialVersionUID = 1L;

  public ApplicationContextException() {
    super();
  }

  public ApplicationContextException(String message, Throwable cause, boolean enableSuppression,
      boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }

  public ApplicationContextException(String message, Throwable cause) {
    super(message, cause);
  }

  public ApplicationContextException(String message) {
    super(message);
  }

  public ApplicationContextException(Throwable cause) {
    super(cause);
  }

}
