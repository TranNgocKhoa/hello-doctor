package com.hellodoctor.common.util;

import com.hellodoctor.common.constants.HDConstant;
import com.hellodoctor.common.exceptions.InvalidTokenException;
import com.hellodoctor.common.models.UserType;
import com.hellodoctor.common.models.user.HDUser;
import com.hellodoctor.common.models.user.UserDTO;
import io.jsonwebtoken.*;
import io.jsonwebtoken.impl.TextCodec;

import java.security.Key;
import java.util.*;

/**
 * @author Khoa
 * @created 3/24/2019
 */
public class JwtUtils {

    public String encode(UserDTO userDTO) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("id", userDTO.getId());
        params.put("email", userDTO.getEmail());
        params.put("status", userDTO.getStatus());
        params.put("roles", userDTO.getRoles());

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, HDConstant.TOKEN_EXPIRE);
        Date expire = calendar.getTime();
        String compact = Jwts.builder().setClaims(params)
                .setIssuedAt(calendar.getTime())
                .setExpiration(expire)
                .setExpiration(calendar.getTime())
                .signWith(SignatureAlgorithm.HS512, TextCodec.BASE64.decode(HDConstant.SECRET_KEY))
                .compact();

        return compact;
    }

    public UserDTO decode(String jwt) throws InvalidTokenException {
        Claims body = null;
        try {
            body = Jwts.parser()
                    .setSigningKey(HDConstant.SECRET_KEY)
                    .parseClaimsJws(jwt).getBody();
        } catch (SignatureException ex) {
            throw new InvalidTokenException("Invalid signature");
        } catch (ExpiredJwtException ex) {
            throw new InvalidTokenException("Token expired");
        } catch (Exception ex) {
            throw new InvalidTokenException("Unknown exception");
        }

        Long id = body.get("id", Long.class);
        String email = body.get("email", String.class);
        String status = body.get("status", String.class);
        List<String> roles = body.get("roles", List.class);

        UserDTO userDTO = new UserDTO();
        userDTO.setId(id);
        userDTO.setEmail(email);
        userDTO.setStatus(status);
        userDTO.setRoles(roles);

        return userDTO;
    }
}
