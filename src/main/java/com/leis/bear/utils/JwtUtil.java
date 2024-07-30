package com.leis.bear.utils;

import cn.hutool.crypto.KeyUtil;
import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTValidator;
import cn.hutool.jwt.signers.AlgorithmUtil;
import cn.hutool.jwt.signers.JWTSigner;
import cn.hutool.jwt.signers.JWTSignerUtil;
import com.leis.bear.exception.UnAuthorizedException;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Date;

@Component
public class JwtUtil {

    private static final String SECRET = "adaeqwrfsfaqwd";

    private static final String id = "rs256";
    private static final JWTSigner signer = JWTSignerUtil.createSigner(id,
            // 随机生成密钥对，此处用户可自行读取`KeyPair`、公钥或私钥生成`JWTSigner`
            KeyUtil.generateKeyPair(AlgorithmUtil.getAlgorithm(id)));

    /**
     * 创建token
     *
     * @param userId
     * @param ttl
     * @return
     */
    public String createToken(Long userId, Duration ttl) {

        return JWT.create()
                .setPayload("user", userId)
                .setExpiresAt(new Date(System.currentTimeMillis() + ttl.toMillis()))
                .setSigner(signer)
                .sign();
    }


    public Long parseToken(String token) {
        if (token == null) {
            throw new UnAuthorizedException("未登录");
        }
        JWT jwt;
        try {
            jwt = JWT.of(token).setSigner(signer);
        } catch (Exception e) {
            throw new UnAuthorizedException("无效的token", e);
        }

        if (!jwt.verify()) {
            throw new UnAuthorizedException("无效的token");
        }
        try {
            JWTValidator.of(jwt).validateDate();
        } catch (Exception e) {
            throw new UnAuthorizedException("token已经过期");
        }

        Object userPayload = jwt.getPayload("user");
        if (userPayload == null) {
            throw new UnAuthorizedException("无效的token");
        }
        try {
            return Long.valueOf(userPayload.toString());
        }catch (Exception e){
            throw new UnAuthorizedException("无效的token");
        }
    }

}
