package com.dremanovich.retrofit_helpers.retrofit;

import com.dremanovich.retrofit_helpers.RequestAnnotationsContainer;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Map;

import okhttp3.Request;
import retrofit2.Call;
import retrofit2.CallAdapter;

public class CustomWrappingCallAdapter<R, T> implements CallAdapter<R, T> {

    private final CallAdapter<R, T> adapter;
    private final RequestAnnotationsContainer container;
    private final Annotation[] info;

    CustomWrappingCallAdapter(CallAdapter<R,T> adapter, RequestAnnotationsContainer container, Annotation[] info) {
        this.adapter = adapter;
        this.container = container;
        this.info = info;
    }

    @Override
    public Type responseType() {
        return adapter.responseType();
    }

    @Override
    public T adapt(Call<R> call) {
        Request request = call.request();
        container.register(request, info);
        return adapter.adapt(call);
    }
}
