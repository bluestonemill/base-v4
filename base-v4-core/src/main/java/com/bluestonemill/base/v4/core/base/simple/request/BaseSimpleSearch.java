package com.bluestonemill.base.v4.core.base.simple.request;

import lombok.Data;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @author wuzl
 * @date 2021/11/29 15:21
 */
@Data
public class BaseSimpleSearch {


    private int page = 1;

    private int pageSize = 10;

    private String key;

    /**
     * 内存分页
     * @param allList
     * @param <T>
     * @return
     */
    public <T> List<T> moneyPage(List<T> allList){
        return moneyPage(allList,getPage(), getPageSize());
    }


    public  static <T> List<T> moneyPage(List<T> allList,int page,int pageSize){
        if (CollectionUtils.isEmpty(allList)){
            return allList;
        }
        int start = (page -1) * pageSize;
        int end=start+pageSize;
        int allSize = allList.size();
        List<T> rsList;
        if (start>allSize){
            //超越起始点
            rsList= Collections.emptyList();
        }else {
            //取小值
            end=Math.min(end,allSize);
            rsList=allList.subList(start,end);
        }
        return rsList;
    }
}
