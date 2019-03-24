package com.hellodoctor.auth.services.impl;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hellodoctor.auth.models.BaseSnsUser;
import com.hellodoctor.auth.repositories.UserRepository;
import com.hellodoctor.auth.services.UserService;
import com.hellodoctor.common.constants.ReturnCode;
import com.hellodoctor.common.exceptions.ApiRuntimeException;
import com.hellodoctor.common.exceptions.RegisterException;
import com.hellodoctor.common.models.UserType;
import com.hellodoctor.common.models.user.HDUser;
import com.hellodoctor.common.models.vo.RegisterUserVo;
import com.hellodoctor.common.models.vo.SnsUser;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

public abstract class BaseSnsService {

    @Autowired
    protected RestTemplate restTemplate;

    @Autowired
    protected UserRepository userRepository;

    @Autowired
    protected UserService userService;

    protected String url;

    private Logger logger;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    protected String buildRequestUrl(String api, String snsToken) {
        return getUrl() + api + "?access_token=" + snsToken;
    }

    <T> T fetchObject(String api, String snsToken, Class<T> clazz) {
        String apiUrl = buildRequestUrl(api, snsToken);
        String json = restTemplate.getForObject(apiUrl, String.class);

        T profile;
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            profile = mapper.readValue(json, clazz);
        } catch (Exception e) {
            throw new ApiRuntimeException(ReturnCode.INVALID_SNS_TOKEN, ReturnCode.INVALID_SNS_TOKEN.name());
        }

        if (profile == null) {
            throw new ApiRuntimeException(ReturnCode.INVALID_SNS_TOKEN, ReturnCode.INVALID_SNS_TOKEN.name());
        }

        return profile;
    }

    abstract <T> T curlSnsUser(String snsToken, Class<T> clazz);

    abstract HDUser getHdUserFromSnsId(String snsId);

    abstract boolean createSnsUser(BaseSnsUser snsUser);

    public BaseSnsService(String url, Logger logger) {
        this.logger = logger;
        this.url = url;
    }


    public <T extends BaseSnsUser> T getSnsUser(UserType type, String snsToken, Class<T> clazz) {
        return curlSnsUser(snsToken, clazz);
    }

    public HDUser updateSnsUser(RegisterUserVo userVo) {

        HDUser user = getHdUserFromSnsId(userVo.getSnsInfo().getToken());
        userVo.setUserId(user.getId());

        if (userRepository.updateSnsUser(userVo)) {
            user.setEmail(userVo.getEmail());
            user.setName(userVo.getUsername());
            return user;
        }

        throw new ApiRuntimeException(ReturnCode.USER_SNS_LOGIN_FAIL, ReturnCode.USER_SNS_LOGIN_FAIL.name());

    }

    <T extends BaseSnsUser> HDUser authentication(SnsUser snsUser, Class<T> clazz, UserType type) {

        String snsToken = snsUser.getToken();
        logger.info("Sns type : {}", type);
        logger.info("Sns token : {}", snsToken);

        if (StringUtils.isEmpty(snsToken)) {
            throw new ApiRuntimeException(ReturnCode.INVALID_SNS_TOKEN, ReturnCode.INVALID_SNS_TOKEN.name());
        }

        // Get Sns User info
        T sns;
        try {
            sns = getSnsUser(type, snsToken, clazz);
        } catch (Exception ex) {
            logger.warn("Error when getting profile by token. ", ex.getMessage());
            throw new ApiRuntimeException(ReturnCode.INVALID_SNS_TOKEN, ReturnCode.INVALID_SNS_TOKEN.name());
        }

        HDUser hdUser = getHdUserFromSnsId(sns.getId());

        if (hdUser != null) {
            hdUser.setType(type);
            return hdUser;
        } else {
            logger.info("Generate new {} SNS user : {}", type.getName(), sns.getId());
            if (createSnsUser(sns)) {
                logger.error("Generate new {} SNS user error : {} ", type.getName(), sns.getId());
                throw new RegisterException(ReturnCode.USER_SNS_LOGIN_FAIL);
            }
            throw new RegisterException(ReturnCode.USER_SNS_NOT_AVAILABLE);
        }
    }

}
