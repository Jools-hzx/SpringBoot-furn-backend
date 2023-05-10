package com.hspedu.furn.service.impl;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hspedu.furn.bean.Furn;
import com.hspedu.furn.mapper.FurnMapper;
import com.hspedu.furn.service.FurnService;
import org.springframework.stereotype.Service;

/**
 * @author Zexi He.
 * @date 2023/5/9 23:32
 * @description:
 */
@Service
public class FurnServiceImpl
        extends ServiceImpl<FurnMapper, Furn>
        implements FurnService {
}
