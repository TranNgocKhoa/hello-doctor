package com.hellodoctor.auth.services;

import com.hellodoctor.common.models.vo.HDToken;
import com.hellodoctor.common.models.vo.SnsUser;
import com.hellodoctor.common.models.vo.UserVo;

public interface UserService {
    HDToken auth(UserVo userVo);
    HDToken ggLogin(SnsUser user);
    HDToken faceLogin(SnsUser user);
}
