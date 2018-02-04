package com.dremanovich.retrofit_helpers.converters;

import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class UnwrapConverterFactory extends Converter.Factory {
    private GsonConverterFactory factory;

    public UnwrapConverterFactory(GsonConverterFactory factory) {
        this.factory = factory;
    }

    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(final Type type,
                                                            Annotation[] annotations, Retrofit retrofit) {
        // e.g. WrappedResponse<Person>
        Type wrappedType = new ParameterizedType() {
            @Override
            public Type[] getActualTypeArguments() {
                // -> WrappedResponse<type>
                return new Type[] {type};
            }

            @Override
            public Type getOwnerType() {
                return null;
            }

            @Override
            public Type getRawType() {
                return WrappedResponse.class;
            }
        };
        Converter<ResponseBody, ?> gsonConverter = factory
                .responseBodyConverter(wrappedType, annotations, retrofit);
        return new WrappedResponseBodyConverter(gsonConverter);
    }

    @Override
    public Converter<?, RequestBody> requestBodyConverter(Type type, Annotation[] parameterAnnotations, Annotation[] methodAnnotations, Retrofit retrofit) {
        return factory.requestBodyConverter(type, parameterAnnotations, methodAnnotations, retrofit);
    }

    @Override
    public Converter<?, String> stringConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
        return factory.stringConverter(type, annotations, retrofit);
    }
}
