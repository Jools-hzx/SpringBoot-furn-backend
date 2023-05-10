package com.hspedu.furn.controller;

import com.hspedu.furn.bean.Furn;
import com.hspedu.furn.service.FurnService;
import com.hspedu.furn.util.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Zexi He.
 * @date 2023/5/10 0:01
 * @description:
 */

@RestController
@Slf4j
public class FurnController {

    @Resource
    private FurnService furnService;

    @PostMapping("/save")
    public Result save(@RequestBody Furn furn) {
        furnService.save(furn);
        return Result.success();
    }

    //显示数据库中的所有数据;后面再考虑去使用分页
    @RequestMapping("/list")
    @ResponseBody
    public Result<List<Furn>> listFurns() {
        List<Furn> list = furnService.list();
        return Result.success(list);
    }
}
