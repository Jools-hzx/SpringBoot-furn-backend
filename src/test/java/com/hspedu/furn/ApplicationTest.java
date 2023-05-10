package com.hspedu.furn;

import com.hspedu.furn.bean.Furn;
import com.hspedu.furn.mapper.FurnMapper;
import com.hspedu.furn.service.FurnService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Zexi He.
 * @date 2023/5/9 23:19
 * @description:
 */

@SpringBootTest
public class ApplicationTest {

    //装配 Mapper 对象
    @Resource
    private FurnMapper furnMapper;

    //装配Service
    @Resource
    private FurnService furnService;

    @Test
    public void mapperTest() {
        System.out.println("类型:" + furnMapper.getClass());
        Furn furn = furnMapper.selectById(3);
        System.out.println("Furniture:" + furn);
    }

    @Test
    public void serviceTest() {
        List<Furn> list = furnService.list();
        for (Furn furn : list) {
            System.out.println(furn);
        }
    }
}
