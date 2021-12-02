package com.bluestonemill.base.v4.core.extension.mp3.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import org.apache.ibatis.annotations.Param;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @author wuzl
 * @date 2021/10/9 10:07
 */
public interface ExtraMapper<T, V> extends DefaultMapper<T> {

    /**
     * 根据id查询对象 忽略删除标记
     * @param id id
     * @return
     */
    public V selectVoByIdIgnoreDeleteFlag(Serializable id);

    /**
     * 根据ids查询对象
     * @param ids ids
     * @return
     */
    public List<V> selectVoByIdsIgnoreDeleteFlag(@Param(Constants.COLLECTION) Collection<? extends Serializable> ids);

    /**
     * 根据查询条件查询对象数据
     * @param page 分页
     * @param queryWrapper 查询参数
     * @param <E> 分析类型
     * @return
     */
    <E extends IPage<V>> E selectVoPage(E page, @Param(Constants.WRAPPER) Wrapper<T> queryWrapper);


    /**
     * 根据条件查询数据
     * @param queryWrapper 查询参数
     * @return
     */
    List<V> selectVoList(@Param(Constants.WRAPPER) Wrapper<T> queryWrapper);


    List<V> selectVoListIgnoreDeleteFlag(@Param(Constants.WRAPPER) Wrapper<T> queryWrapper);
}
