package com.eomcs.lms.service;

import java.util.Map;

public interface FacebookService {
  Map<String,Object> getLoginUser(String accessToken);
}
