package com.dremanovich.retrofit_helpers.converters;

import android.support.annotation.NonNull;

import com.dremanovich.retrofit_helpers.ApiException;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Converter;


public class WrappedResponseBodyConverter<T> implements Converter<ResponseBody, T> {
    private Converter<ResponseBody, WrappedResponse<T>> converter;

    public WrappedResponseBodyConverter(Converter<ResponseBody,
            WrappedResponse<T>> converter) {
        this.converter = converter;
    }

    @Override
    public T convert(@NonNull ResponseBody value) throws IOException {
//        Log.e("response", value.string());
        WrappedResponse<T> response = converter.convert(value);
        if (response.getResponse() != null) {
            return response.getResponse();
        }


        // RxJava will call onError with this exception
        if (response.getError() != null){
            throw new ApiException(response.getError().getMessage(), response.getError().getCode());
        } else {
            throw new ApiException("Json parsing error", ApiException.PARSE_ERROR);
        }
    }
}

