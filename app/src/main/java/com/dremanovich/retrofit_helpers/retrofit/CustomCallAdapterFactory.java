package com.dremanovich.retrofit_helpers.retrofit;

import com.dremanovich.retrofit_helpers.RequestAnnotationsContainer;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import retrofit2.CallAdapter;
import retrofit2.Retrofit;

public class CustomCallAdapterFactory extends CallAdapter.Factory {

    private final List<CallAdapter.Factory> callAdapterFactories;
    private final RequestAnnotationsContainer container;

    public CustomCallAdapterFactory(List<CallAdapter.Factory> callAdapterFactories,
                                    RequestAnnotationsContainer container) {
        this.callAdapterFactories = callAdapterFactories;
        this.container = container;
    }

    @Override
    public CallAdapter<?,?> get(Type returnType, Annotation[] annotations, Retrofit retrofit) {
            for (int i = 0; i < callAdapterFactories.size(); i++) {
                CallAdapter<?,?> adapter = callAdapterFactories.get(i).get(returnType, annotations, retrofit);
                if (adapter != null) {
                    if (annotations != null) {
                        // get whatever info you need from your annotation
                        return new CustomWrappingCallAdapter<>(adapter, container, annotations);
                    }
                    return adapter;
                }
            }

        return null;
    }
}
