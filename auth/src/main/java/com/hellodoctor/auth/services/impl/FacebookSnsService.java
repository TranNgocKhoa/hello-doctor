package com.hellodoctor.auth.services.impl;


import com.hellodoctor.auth.models.BaseSnsUser;
import com.hellodoctor.auth.models.fb.FacebookUser;
import com.hellodoctor.auth.models.fb.Thumbnail;
import com.hellodoctor.auth.services.SnsService;
import com.hellodoctor.common.models.UserType;
import com.hellodoctor.common.models.user.HDUser;
import com.hellodoctor.common.models.vo.SnsUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @author Khoa
 * @created 3/23/2019
 */

@Service("faceSns")
public class FacebookSnsService extends BaseSnsService implements SnsService {

    private static final Logger logger = LoggerFactory.getLogger(FacebookSnsService.class);

    public FacebookSnsService() {
        super("https://graph.facebook.com/v3.2", logger);
    }

    @Override
    public HDUser authSns(SnsUser user) {
        return authentication(user, FacebookUser.class, UserType.FACEBOOK);
    }

    @Override
    public <T> T curlSnsUser(String snsToken, Class<T> clazz) {
        T fbUser = fetchObject("/me?fields=id,name,email,first_name,last_name", snsToken, clazz);
        Thumbnail thumbnail = fetchObject("/me/picture", snsToken, Thumbnail.class);
        ((FacebookUser) fbUser).setThumbnail(thumbnail);
        return fbUser;
    }

    @Override
    public HDUser getHdUserFromSnsId(String snsId) {
        return userRepository.getHDFacebookUser(snsId);
    }

    @Override
    public boolean createSnsUser(BaseSnsUser snsUser) {
        return userRepository.createSnsUser((FacebookUser) snsUser) != null;
    }
}
