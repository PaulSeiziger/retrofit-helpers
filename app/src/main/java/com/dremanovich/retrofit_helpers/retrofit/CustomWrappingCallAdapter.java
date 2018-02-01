package com.dremanovich.retrofit_helpers.retrofit;

import android.support.annotation.NonNull;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Map;

import okhttp3.Request;
import retrofit2.Call;
import retrofit2.CallAdapter;

public class CustomWrappingCallAdapter<R, T> implements CallAdapter<R, T> {

    private final CallAdapter<R, T> adapter;
    private final Map<Integer, Annotation[]> registration;
    private final Annotation[] info;

    CustomWrappingCallAdapter(CallAdapter<R,T> adapter, Map<Integer, Annotation[]> reg, Annotation[] info) {
        this.adapter = adapter;
        this.registration = reg;
        this.info = info;
    }

    @Override
    public Type responseType() {
        return adapter.responseType();
    }

    @Override
    public T adapt(@NonNull Call<R> call) {
        Request request = call.request();
        registration.put(identify(request), info);
        return adapter.adapt(call);
    }



    private Integer identify(Request request) {
        // this is very experimental but it does the job currently
        return (request.url() + request.method()).hashCode();
    }
}
