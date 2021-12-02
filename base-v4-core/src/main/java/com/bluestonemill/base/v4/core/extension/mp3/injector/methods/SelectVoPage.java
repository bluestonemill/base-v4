package com.bluestonemill.base.v4.core.extension.mp3.injector.methods;

import com.bluestonemill.base.v4.core.extension.mp3.injector.InjectorUtils;
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
public class SelectVoPage extends AbstractMethod {

    private final String method = "selectVoPage";
    private final String desc = "查询满足条件所有数据（并翻页）";
    private final String sqlVal = "<script>%s SELECT %s FROM %s %s %s\n</script>";

    @Override
    public MappedStatement injectMappedStatement(Class<?> mapperClass, Class<?> modelClass, TableInfo tableInfo) {
        String sql = String.format(sqlVal, sqlFirst(), sqlSelectColumns(tableInfo, true),
                tableInfo.getTableName(), sqlWhereEntityWrapper(true, tableInfo), sqlComment());
        SqlSource sqlSource = languageDriver.createSqlSource(configuration, sql, modelClass);
        return addSelectMappedStatementForOther(mapperClass, method, sqlSource, InjectorUtils.voClass(mapperClass));
    }
}
