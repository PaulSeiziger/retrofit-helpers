package com.dremanovich.retrofit_helpers;

import java.io.IOException;

/**
 * Created by PavelDremanovich on 30.01.2018.
 */

public class ApiException extends IOException {
    public final static int PARSE_ERROR = 1;
    private int code;

    public ApiException(String message, int code) {
        super(message);
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
