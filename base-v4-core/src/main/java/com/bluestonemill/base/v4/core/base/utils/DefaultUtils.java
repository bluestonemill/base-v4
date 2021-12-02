package com.bluestonemill.base.v4.core.base.utils;

import cn.hutool.core.collection.CollUtil;

import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

/**
 * date 2021-06-23 09:04
 *
 * @author 吴志林
 **/
public class DefaultUtils {

    public static <T>  T getDefault(boolean check,Supplier<T> normal,Supplier<T> df){
        if (check){
            return normal.get();
        }else {
            return df.get();
        }
    }

    public static <K,V> Map<K,V> getDefaultMap(Map<K,V> map,Supplier<Map<K,V>> mapCreate){
        return getDefault(map!=null,()->map,mapCreate);
    }

    public static <T> List<T> getDefaultList(List<T> list,Supplier<List<T>> listSupplier){
        return getDefault(CollUtil.isNotEmpty(list),()->list, listSupplier);
    }
}
