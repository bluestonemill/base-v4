package com.bluestonemill.base.v4.core.extension.mp3.injector;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.injector.DefaultSqlInjector;
import com.bluestonemill.base.v4.core.extension.mp3.injector.methods.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
*
* date 2021-06-08 13:12:30
* @author 吴志林
*
**/
public class ExtraSqlInjector extends DefaultSqlInjector {
    @Override
    public List<AbstractMethod> getMethodList(Class<?> mapperClass) {
        List<AbstractMethod> superList = super.getMethodList(mapperClass);

        List<AbstractMethod> thisList = Stream.of(
                new SelectCountDistinct(),
                new SelectDistinctCellAsLong(),
                new SelectByIdIgnoreDeleteFlag(),
                new SelectByIdsIgnoreDeleteFlag(),
                new SelectVoByIdIgnoreDeleteFlag(),
                new SelectVoByIdsIgnoreDeleteFlag(),
                new SelectVoList(),
                new SelectVoPage()
        ).collect(toList());

        List<AbstractMethod> rs = new ArrayList<>(superList.size() + thisList.size());
        rs.addAll(superList);
        rs.addAll(thisList);
        return rs;
    }
}
