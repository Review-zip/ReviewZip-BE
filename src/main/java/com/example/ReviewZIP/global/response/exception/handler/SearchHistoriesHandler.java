package com.example.ReviewZIP.global.response.exception.handler;

import com.example.ReviewZIP.global.response.code.BaseErrorCode;
import com.example.ReviewZIP.global.response.exception.GeneralException;

public class SearchHistoriesHandler extends GeneralException{
    public SearchHistoriesHandler(BaseErrorCode errorCode) {super(errorCode);}
}
