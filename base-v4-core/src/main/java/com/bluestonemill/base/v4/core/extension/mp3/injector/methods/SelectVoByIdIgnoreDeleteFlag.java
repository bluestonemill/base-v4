package com.bluestonemill.base.v4.core.extension.mp3.injector.methods;

import com.bluestonemill.base.v4.core.extension.mp3.injector.InjectorUtils;
import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.scripting.defaults.RawSqlSource;

/**
*
* date 2021-06-08 13:12:30
* @author 吴志林
*
**/
public class SelectVoByIdIgnoreDeleteFlag extends AbstractMethod {

    private String sqlMethod = "selectVoByIdIgnoreDeleteFlag";
    private String note = "根据ID 查询一条数据";
    private String sql = "SELECT %s FROM %s WHERE %s=#{%s}";

    @Override
    public MappedStatement injectMappedStatement(Class<?> mapperClass, Class<?> modelClass, TableInfo tableInfo) {
        SqlSource sqlSource = new RawSqlSource(configuration, String.format(sql,
                sqlSelectColumns(tableInfo, false),
                tableInfo.getTableName(), tableInfo.getKeyColumn(), tableInfo.getKeyProperty()), Object.class);
        return this.addSelectMappedStatementForOther(mapperClass, sqlMethod, sqlSource, InjectorUtils.voClass(mapperClass));
    }
}
