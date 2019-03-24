package com.hellodoctor.auth.services.impl;

import com.hellodoctor.auth.services.SnsService;
import com.hellodoctor.auth.services.UserService;
import com.hellodoctor.common.models.user.HDUser;
import com.hellodoctor.common.models.vo.HDToken;
import com.hellodoctor.common.models.vo.SnsUser;
import com.hellodoctor.common.models.vo.UserVo;
import com.hellodoctor.common.util.JwtUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Base64;

/**
 * @author Khoa
 * @created 3/23/2019
 */
@Service
public class UserServiceImpl implements UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    @Qualifier("faceSns")
    private SnsService faceService;
    @Override
    public HDToken auth(UserVo userVo) {
        return null;
    }

    @Override
    public HDToken ggLogin(SnsUser user) {
        return null;
    }

    @Override
    public HDToken faceLogin(SnsUser user) {
        HDUser hdUser = faceService.authSns(user);
        return generateJwt(hdUser);
    }

    private HDToken generateJwt(HDUser hdUser) {
        String jwt = jwtUtils.encode(hdUser);
        String encoded = Base64.getEncoder().encodeToString(jwt.getBytes());

        logger.info("Token: {}", encoded);

        HDToken token = new HDToken(encoded);
        return token;
    }
}
