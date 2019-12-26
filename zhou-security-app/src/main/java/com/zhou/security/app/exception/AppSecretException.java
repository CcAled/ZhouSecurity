package com.zhou.security.app.exception;

/**
 * @author : Sun Chuan
 * @date : 2019/10/22 22:48
 * Description：app异常
 */
public class AppSecretException extends RuntimeException {

    public AppSecretException(String msg) {
        super(msg);
    }
}