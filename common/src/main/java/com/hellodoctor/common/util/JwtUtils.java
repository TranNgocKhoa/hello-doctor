package com.hellodoctor.common.util;

import com.hellodoctor.common.constants.HDConstant;
import com.hellodoctor.common.exceptions.InvalidTokenException;
import com.hellodoctor.common.models.user.HDUser;
import io.jsonwebtoken.*;
import io.jsonwebtoken.impl.TextCodec;

import java.util.*;

/**
 * @author Khoa
 * @created 3/24/2019
 */
public class JwtUtils {

    public String encode(HDUser hdUser) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("id", hdUser.getId());
        params.put("email", hdUser.getEmail());
        params.put("name", hdUser.getName());
        params.put("status", hdUser.getStatus());
        params.put("roles", hdUser.getRoles());

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

    public HDUser decode(String jwt) throws InvalidTokenException {
        Claims body = null;
        try {
            body = Jwts.parser()
                    .setSigningKey(TextCodec.BASE64.decode(HDConstant.SECRET_KEY))
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
        String name = body.get("name", String.class);
        String status = body.get("status", String.class);
        List<String> roles = body.get("roles", List.class);

        HDUser hdUser = new HDUser();
        hdUser.setId(id);
        hdUser.setEmail(email);
        hdUser.setName(name);
        hdUser.setStatus(status);
        hdUser.setRoles(roles);

        return hdUser;
    }
}
