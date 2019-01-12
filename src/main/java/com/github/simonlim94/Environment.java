package com.github.simonlim94;

import com.github.simonlim94.env.env;

public class Environment {
    public void setEnvironment(String clientId,String clientSecret,String environment){
        env.clientId = clientId;
        env.clientSecret = clientSecret;
        env.environment = environment;
    }

    public void setAccessToken(String accessToken){
        env.accessToken = accessToken;
    }

    public void setRefreshToken(String refreshToken){
        env.refreshToken = refreshToken;
    }

    public void setPrivateKey(String privateKey){
        env.privateKey = privateKey;
    }
}
