package com.hellodoctor.auth.services;


import com.hellodoctor.common.models.user.HDUser;
import com.hellodoctor.common.models.vo.RegisterUserVo;
import com.hellodoctor.common.models.vo.SnsUser;


/**
 * @author Khoa
 * @created 3/23/2019
 */
public interface SnsService {
    HDUser authSns(SnsUser user);

    HDUser updateSnsUser(RegisterUserVo userVo);
}
