package com.hellodoctor.auth.repositories;

import com.hellodoctor.auth.models.SnsParameter;
import com.hellodoctor.common.models.UserType;
import com.hellodoctor.common.models.vo.RegisterUserVo;
import com.hellodoctor.auth.models.fb.FacebookUser;
import com.hellodoctor.common.models.user.HDUser;
import com.hellodoctor.common.models.vo.UserVo;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Khoa
 * @created 3/24/2019
 */
@Repository
public class UserRepository implements UserDao{

    private static final String PACKAGE = "user.";

    @Autowired
    private SqlSessionTemplate sqlSession;
    @Override
    public HDUser findByUsername(String username) {
        return null;
    }

    @Override
    public List<HDUser> findByEmail(String email) {
        return null;
    }

    @Override
    public HDUser findByUsernameAndPw(String username, String password) {
        return null;
    }

    @Override
    public int failedLogin(String host) {
        return 0;
    }

    @Override
    public boolean isExistedUser(String username) {
        return false;
    }

    @Override
    public void saveUser(RegisterUserVo user) {

    }

    @Override
    @Transactional
    public HDUser createSnsUser(FacebookUser fbUser) {
        if (sqlSession.insert(PACKAGE + "saveUsersFacebook", fbUser) > 0) {
            long userRefId = fbUser.getUserRefId();
            String displayName = createDisplayName(fbUser.getName(), fbUser.getId());
            return generateHDUser(userRefId, displayName, fbUser.getEmail(), UserType.FACEBOOK);

        }
        return null;
    }

    @Override
    public HDUser getHDFacebookUser(String fbId) {
        return null;
    }

    @Override
    public boolean updateSnsUser(RegisterUserVo userVo) {
        return false;
    }

    private HDUser generateHDUser(long userRefId, String displayName, String email, UserType type) {
        SnsParameter params = new SnsParameter(userRefId, displayName, type);
        sqlSession.insert(PACKAGE + "saveSnsUser", params);
        sqlSession.insert(PACKAGE + "mappingSnsUser", params);

        HDUser hdUser = new HDUser();
        hdUser.setId(params.getUserId());
        hdUser.setEmail(email);
        hdUser.setName(displayName);
        hdUser.setType(type);

        return hdUser;

    }

    private String createDisplayName(String type, String id) {
        return type + "." + id;
    }

}
