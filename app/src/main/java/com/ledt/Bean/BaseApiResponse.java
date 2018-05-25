package com.ledt.Bean;

/**
 * Created by 13126 on 2017/9/6.
 */

public class BaseApiResponse {
    private int Result;
    private String ErrorMsg="";

    public int getResult() {
        return Result;
    }

    public void setResult(int result) {
        Result = result;
    }

    public String getErrorMsg() {
        return ErrorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        ErrorMsg = errorMsg;
    }
}
