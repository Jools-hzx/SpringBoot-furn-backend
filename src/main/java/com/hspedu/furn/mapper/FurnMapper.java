package com.hspedu.furn.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hspedu.furn.bean.Furn;

/**
 * @author Zexi He.
 * @date 2023/5/9 23:16
 * @description:    该接口可以扩展 Furn bean 类其他额外的方法
 */
public interface FurnMapper extends BaseMapper<Furn> {

    //可以在该接口声明，并在对应的 Mapper.xml 进行配置
}
