package com.dremanovich.retrofit_helpers;

import android.util.SparseArray;

import java.lang.annotation.Annotation;

import okhttp3.Request;

public class RequestAnnotationsContainer {
    private final SparseArray<Annotation[]> registeredAnnotations = new SparseArray<>();

    public void register(Request request, Annotation[] annotations){
        registeredAnnotations.put(identity(request), annotations);
    }

    public boolean isAnnotated(Request request, Class findAnnotation) {
        Annotation[] annotations = registeredAnnotations.get(identity(request));

        if (annotations != null){
            for (Annotation annotation : annotations){
                if (annotation.annotationType() == findAnnotation){
                    return true;
                }
            }
        }

        return false;
    }

    private int identity(Request request){
        //Todo: Вынести механизм идентификации запроса в стратегию, т.к. в некоторых API могут использоваться разные очень спицифичные методики формирования url
        return (request.url() + request.method()).hashCode();
    }
}
