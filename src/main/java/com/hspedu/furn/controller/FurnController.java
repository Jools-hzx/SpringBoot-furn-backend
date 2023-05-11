package com.hspedu.furn.controller;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hspedu.furn.bean.Furn;
import com.hspedu.furn.service.FurnService;
import com.hspedu.furn.util.Result;
import com.sun.org.apache.bcel.internal.generic.I2F;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
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
        try {
            furnService.save(furn);
            return Result.success();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return Result.error("?", "添加失败");
        }
    }

    //显示数据库中的所有数据;后面再考虑去使用分页
    @RequestMapping("/list")
    @ResponseBody
    public Result listFurns() {
        try {
            List<Furn> list = furnService.list();
            return Result.success(list);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return Result.error("?", "请求失败");
        }
    }

    //完成数据修改
    @PutMapping("/update")
    @ResponseBody
    public Result updateFurn(@RequestBody Furn furn) {
        /*
         * 根据 ID 选择修改
         *
         * @param entity 实体对象
         *
            default boolean updateById(T entity) {
                return SqlHelper.retBool(getBaseMapper().updateById(entity));
            }
         */
        try {
            furnService.updateById(furn);
            return Result.success();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return Result.error("?", "更新失败");
        }
    }

    //根据id查询DB中的单行数据
    @GetMapping("/query")
    @ResponseBody
    public Result queryById(@RequestParam(value = "id") Integer id) {
        Furn furn = furnService.getById(id);
        if (null != furn) {
            return Result.success(furn);
        } else {
            return Result.error("4xx", "ID不存在");
        }
    }

    //删除数据
    @DeleteMapping("/del/{id}")
    @ResponseBody
    public Result deleteById(@PathVariable(value = "id") Integer id) {
        /*
        default boolean removeById(Serializable id) {
            return SqlHelper.retBool(getBaseMapper().deleteById(id));
        }
        */
        boolean isRemoved = furnService.removeById(id);
        if (isRemoved) {
            return Result.success();
        } else {
            return Result.error("5xx", "删除失败");
        }
    }

    //返回分页数据信息
    @GetMapping("/furnsByPage")
    @ResponseBody
    public Result<Page<Furn>> queryByPage(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                          @RequestParam(value = "pageSize", defaultValue = "8") Integer pageSize) {

        Page<Furn> page = furnService.page(new Page<>(pageNum, pageSize));
        return Result.success(page);
    }

    //方法: 可以支持带条件的分页检索
    @GetMapping("/pageByConditional")
    @ResponseBody
    public Result<Page<Furn>> pageByConditional(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                                @RequestParam(value = "pageSize", defaultValue = "8") Integer pageSize,
                                                @RequestParam(value = "search", defaultValue = "") String search) {

        QueryWrapper<Furn> queryWrapper = new QueryWrapper<>();

        if (StringUtils.hasText(search)) {
            // like: "name" 表示对应数据库中的字段名
            queryWrapper = queryWrapper.like("name", search);
        }
        Page<Furn> page = furnService.page(
                new Page<>(pageNum, pageSize),
                queryWrapper);
        return Result.success(page);
    }
}
