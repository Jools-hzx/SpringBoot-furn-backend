package com.hspedu.furn.controller;

import com.hspedu.furn.bean.Furn;
import com.hspedu.furn.service.FurnService;
import com.hspedu.furn.util.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

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
}
