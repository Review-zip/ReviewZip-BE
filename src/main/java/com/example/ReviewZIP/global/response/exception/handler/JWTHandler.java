package com.example.ReviewZIP.global.response.exception.handler;

import com.example.ReviewZIP.global.response.code.BaseErrorCode;
import com.example.ReviewZIP.global.response.exception.GeneralException;

public class JWTHandler extends GeneralException {
    public JWTHandler(BaseErrorCode errorCode) {super(errorCode);};
}