package com.bluestonemill.base.v4.core.extension.mp3.injector.methods;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.baomidou.mybatisplus.core.toolkit.sql.SqlScriptUtils;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;

/**
*
* date 2021-06-08 13:12:30
* @author 吴志林
*
**/
public class SelectByIdsIgnoreDeleteFlag extends AbstractMethod {

    private String sqlMethod = "selectByIdsIgnoreDeleteFlag";
    private String note = "根据ID 查询一条数据";
    private String sql = "<script>SELECT %s FROM %s WHERE %s IN (%s)</script>";


    @Override
    public MappedStatement injectMappedStatement(Class<?> mapperClass, Class<?> modelClass, TableInfo tableInfo) {
        SqlSource sqlSource = languageDriver.createSqlSource(configuration, String.format(sql,
                sqlSelectColumns(tableInfo, false), tableInfo.getTableName(), tableInfo.getKeyColumn(),
                SqlScriptUtils.convertForeach("#{item}", COLLECTION, null, "item", COMMA)
        ), Object.class);
        return addSelectMappedStatementForTable(mapperClass, sqlMethod, sqlSource, tableInfo);
    }
}
