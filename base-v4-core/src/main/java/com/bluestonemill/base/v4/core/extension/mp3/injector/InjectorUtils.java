package com.bluestonemill.base.v4.core.extension.mp3.injector;

import org.springframework.core.ResolvableType;

/**
*
* date 2021-06-08 13:12:30
* @author 吴志林
*
**/
public class InjectorUtils {


    public static Class<?> voClass(Class<?> modelClass) {
        ResolvableType resolvableType = ResolvableType.forClass(modelClass);
        ResolvableType resolvableTypeSuperType = resolvableType.getSuperType();
        Class<?> voClassName = resolvableTypeSuperType.getGeneric(1).resolve();
        return voClassName;
    }
}
