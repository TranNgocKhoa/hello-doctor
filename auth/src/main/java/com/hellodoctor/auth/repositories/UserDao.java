package com.hellodoctor.auth.repositories;

import com.hellodoctor.auth.models.fb.FacebookUser;
import com.hellodoctor.common.models.user.HDUser;
import com.hellodoctor.common.models.vo.RegisterUserVo;
import com.hellodoctor.common.models.vo.UserVo;

import java.util.List;

/**
 * @author Khoa
 * @created 3/24/2019
 */
public interface UserDao {

    HDUser findByUsername(String username);

    List<HDUser> findByEmail(String email);

    HDUser findByUsernameAndPw(String username, String password);

    int failedLogin(String host);

    boolean isExistedUser(String username);

    void saveUser(RegisterUserVo user);

    HDUser createSnsUser(FacebookUser fbUser);

    HDUser getHDFacebookUser(String fbId);

    boolean updateSnsUser(RegisterUserVo userVo);

}
