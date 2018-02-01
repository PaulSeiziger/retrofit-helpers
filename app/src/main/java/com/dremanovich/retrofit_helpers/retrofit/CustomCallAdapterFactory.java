package com.dremanovich.retrofit_helpers.retrofit;

import android.support.annotation.NonNull;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import retrofit2.CallAdapter;
import retrofit2.Retrofit;

public class CustomCallAdapterFactory extends CallAdapter.Factory {

    private final List<CallAdapter.Factory> callAdapterFactories;
    private final Map<Integer, Annotation[]> registration;

    public CustomCallAdapterFactory(List<CallAdapter.Factory> callAdapterFactories,
                                    Map<Integer, Annotation[]> reg) {
        this.callAdapterFactories = callAdapterFactories;
        this.registration = reg;
    }

    @Override
    public CallAdapter<?,?> get(@NonNull Type returnType, Annotation[] annotations, @NonNull Retrofit retrofit) {
            for (int i = 0; i < callAdapterFactories.size(); i++) {
                CallAdapter<?,?> adapter = callAdapterFactories.get(i).get(returnType, annotations, retrofit);
                if (adapter != null) {
                    if (annotations != null) {
                        // get whatever info you need from your annotation
                        return new CustomWrappingCallAdapter<>(adapter, registration, annotations);
                    }
                    return adapter;
                }
            }

        return null;
    }
}
