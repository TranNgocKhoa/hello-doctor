package com.hellodoctor.auth2.services;

import com.hellodoctor.auth2.models.LoginDTO;
import com.hellodoctor.auth2.models.Token;

/**
 * @author Khoa
 * @created 4/20/2019
 */
public interface UserService {
    Token auth(LoginDTO login);
}
