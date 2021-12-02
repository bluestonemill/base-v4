package com.bluestonemill.base.v4.core.extension.mp3.injector.methods;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;

/**
*
* date 2021-06-08 13:12:30
* @author 吴志林
*
**/
public class SelectCountDistinct extends AbstractMethod {


    private String sqlMethod = "selectCountDistinct";
    private String note = "select count(distinct(val))";
    private String sql = "<script>%s SELECT COUNT(%s) FROM %s %s %s\n</script>";

    public static final String CELL = "cell";
    private static final String DISTINCT_CELL = " count(distinct(${cell})) ";


    @Override
    public MappedStatement injectMappedStatement(Class<?> mapperClass, Class<?> modelClass, TableInfo tableInfo) {
        String sqlVal = String.format(sql, sqlFirst(), sqlCount(), tableInfo.getTableName(),
                sqlWhereEntityWrapper(true, tableInfo), sqlComment());
        SqlSource sqlSource = languageDriver.createSqlSource(configuration, sqlVal, modelClass);
        return this.addSelectMappedStatementForOther(mapperClass, sqlMethod, sqlSource, Integer.class);
    }
}
