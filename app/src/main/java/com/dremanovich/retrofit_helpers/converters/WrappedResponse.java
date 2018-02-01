package com.dremanovich.retrofit_helpers.converters;


import com.dremanovich.retrofit_helpers.ApiError;

public class WrappedResponse<T> {
    private T response;
    private ApiError error;

    public T getResponse() {
        return response;
    }

    public void setResponse(T response) {
        this.response = response;
    }

    public ApiError getError() {
        return error;
    }

    public void setError(ApiError error) {
        this.error = error;
    }
}
