package com.bluestonemill.base.v4.core.base.simple.response;


import cn.hutool.core.collection.CollUtil;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
*
* date 2021-06-08 13:12:30
* @author 吴志林
* @param <K> 主类型
*
**/
@Data
public abstract class BaseSimpleTree<K> {

    /**
     * 节点id
     * @return 节点id
     */
    public abstract K readId();



    /**
     * 节点父id
     * @return
     */
    public abstract K readParentId();


    /**
     * 子节点
     */
    protected List<BaseSimpleTree<K>> children = new ArrayList<>();

    /**
     * 添加子节点
     * @param sub 节点对象
     * @param <T> 节点类型
     */
    public <T extends BaseSimpleTree<K>> void addChildren(T sub) {
        children.add(sub);
    }

    /**
     * 数字主类型
     * @param list
     * @param <T>
     * @return
     */
    public static <T extends BaseSimpleTree<Long>> List<T> longListToTree(List<T> list) {
        return listToTree(list, pid -> pid, 0L);
    }

    public static <T extends BaseSimpleTree<K>, K> List<T> listToTree(List<T> list, Function<K, K> pidParent, K root) {
        if (CollUtil.isEmpty(list)) {
            return list;
        }
        Map<K, List<T>> pidMapList = list.stream().collect(Collectors.groupingBy(t -> pidParent.apply(t.readParentId())));

        List<T> rootList = pidMapList.get(root);
        buildChild(rootList, pidMapList);
        return rootList;
    }

    public static <T extends BaseSimpleTree<K>, K> void buildChild(List<T> rootList, Map<K, List<T>> pidMapList) {
        if (CollUtil.isEmpty(rootList)) {
            return;
        }
        rootList.forEach(root -> {
            List<T> sub = pidMapList.get(root.readId());
            if (!CollUtil.isEmpty(sub)) {
                root.children.addAll(sub);
                buildChild(sub, pidMapList);
            }
        });
    }


}
