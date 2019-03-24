package com.hellodoctor.auth.aop;

import com.hellodoctor.auth.config.sercurity.AuthenticationFacade;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogUsernameAop {
    Logger logger = LoggerFactory.getLogger(LogUsernameAop.class);
    @Autowired
    private AuthenticationFacade authenticationFacade;

    @Before("@annotation(LogUsername)")
    public void logUsername() throws Throwable {
        logger.info("The request just sent by {}", authenticationFacade.getAuthentication().getName());
        LoggedUser.logIn(authenticationFacade.getAuthentication().getName());
    }
}
