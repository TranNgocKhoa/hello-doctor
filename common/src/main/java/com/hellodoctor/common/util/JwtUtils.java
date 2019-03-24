package com.hellodoctor.common.util;

import com.hellodoctor.common.constants.HDConstant;
import com.hellodoctor.common.exceptions.InvalidTokenException;
import com.hellodoctor.common.models.UserType;
import com.hellodoctor.common.models.user.HDUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.SignatureException;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Khoa
 * @created 3/24/2019
 */
public class JwtUtils {

    public String encode(HDUser hdUser) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("id", hdUser.getId());
        params.put("name", hdUser.getName());
        params.put("email", hdUser.getEmail());
        params.put("type", hdUser.getType().name());

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, HDConstant.TOKEN_EXPIRE);
        Date expire = calendar.getTime();

        String compact = Jwts.builder().setClaims(params)
                .setIssuedAt(calendar.getTime())
                .setExpiration(expire)
                .setExpiration(calendar.getTime())
                .signWith(SignatureAlgorithm.HS512, HDConstant.SECRET_KEY)
                .compact();

        return compact;
    }

    public HDUser decode(String jwt) throws InvalidTokenException {
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
        String name = body.get("name", String.class);
        String email = body.get("email", String.class);
        String type = body.get("type", String.class);

        HDUser hdUser = new HDUser();
        hdUser.setId(id);
        hdUser.setName(name);
        hdUser.setEmail(email);
        hdUser.setType(UserType.valueOf(type));

        return hdUser;
    }
}
