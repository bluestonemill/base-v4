package com.bluestonemill.base.v4.core.base.utils;

import cn.hutool.core.collection.CollUtil;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ColUtils {

    public static <K,V,C extends Collection<V>> void multipleMap(Map<K, C> listMap, K k, V v, Function<? super K, ? extends C> mappingFunction){
        listMap.computeIfAbsent(k,mappingFunction);
        listMap.computeIfPresent(k, (tk, tv) -> tv).add(v);
    }

    /**
     * list 分组
     * @param listMap
     * @param k
     * @param v
     * @param <K>
     * @param <V>
     */
    public static <K,V> void multipleMapLinkList(Map<K, List<V>> listMap, K k, V v){
        multipleMap(listMap,k,v,tmp->new LinkedList<>());
    }

    /**
     * list 转map
     * @param list
     * @param key
     * @param val
     * @param <K>
     * @param <V>
     * @param <E>
     * @return
     */
    public static <K,V,E> Map<K,V> simpleMapToList(Collection<E> list,Function<E,K> key,Function<E,V> val){
        return list.stream().collect(Collectors.toMap(key,val,(o,n)->o));
    }

    public static <K,E> Map<K,E> simpleMapToList(Collection<E> list,Function<E,K> key){
        return list.stream().collect(Collectors.toMap(key,Function.identity(),(o,n)->o));
    }

    public static <T,E>  T simpleMap(E e,Function<List<E>,List<T>> map,Predicate<E> check){
        if (check.test(e)){
            return null;
        }
        Collection<T> apply = map.apply(Collections.singletonList(e));
        return CollUtil.getFirst(apply);
    }

    public static <T,E>  T simpleMapCheckNull(E e,Function<List<E>,List<T>> map){
        return simpleMap(e,map,Objects::isNull);
    }


    /**
     * 适合listId转为name
     */
    public static <K, V> void listMultiMapStrSetVal(
            Collection<K> list,
            Map<K, V> map,
            Function<V, String> getMapEntityVal,
            Consumer<String> setResVal) {
        String rs = listMultiMapStr(list, map, getMapEntityVal);
        setResVal.accept(rs);
    }


    public static <K, V> String listMultiMapStr(
            Collection<K> list,
            Map<K, V> map,
            Function<V, String> getMapEntityVal) {
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }
        return list.stream().map(e -> {
                    V v = map.get(e);
                    if (v == null) {
                        return null;
                    } else {
                        return getMapEntityVal.apply(v);
                    }
                }).filter(Objects::nonNull)
                .collect(Collectors.joining(","));
    }

    public static <K,V> Map<K,V> mergeMap(Map<K,V> map1,Map<K,V> map2){
        Assert.notNull(map1,"参数错误");
        Assert.notNull(map2,"参数错误");

        Map<K,V> map= Maps.newHashMapWithExpectedSize(map1.size()+map2.size());
        if (CollUtil.isNotEmpty(map1)){
            map.putAll(map1);
        }
        if (CollUtil.isNotEmpty(map2)){
            map.putAll(map2);
        }
        return map;
    }

    public static <T> List<T> mergeList(List<T> t1,List<T> t2){
        Assert.notNull(t1,"参数错误");
        Assert.notNull(t2,"参数错误");

        List<T> list= Lists.newArrayListWithCapacity(t1.size()+t2.size());
        CollUtil.addAll(list,t1);
        CollUtil.addAll(list,t2);
        return list;
    }
}
