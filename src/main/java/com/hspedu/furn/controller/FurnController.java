package com.hspedu.furn.controller;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hspedu.furn.bean.Furn;
import com.hspedu.furn.service.FurnService;
import com.hspedu.furn.util.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public Result<?> save(@Validated @RequestBody Furn furn, Errors errors) {
//        try {
//            furnService.save(furn);
//            return Result.success();
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//            return Result.error("?", "添加失败");
//        }

        Map<String, String> errorsMap = new HashMap<>();

        //如果有错误，获取到所有的错误
        List<FieldError> fieldErrors = errors.getFieldErrors();
        for (FieldError error : fieldErrors) {
            errorsMap.put(error.getField(), error.getDefaultMessage());
        }

        //如果没有错误，说明验证成功，返回成功消息
        if (errorsMap.isEmpty()) {
            furnService.save(furn);
            return Result.success();
        } else {
            //否则返回错误消息
            return Result.error("5xx", "后端验证失败", errorsMap);
        }
    }

    //显示数据库中的所有数据;后面再考虑去使用分页
    @RequestMapping("/list")
    @ResponseBody
    public Result<?> listFurns() {
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
    public Result<?> updateFurn(@RequestBody Furn furn) {
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
    public Result<?> queryById(@RequestParam(value = "id") Integer id) {
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
    public Result<?> deleteById(@PathVariable(value = "id") Integer id) {
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


    @GetMapping("/pageByConditional2")
    @ResponseBody
    public Result<Page<Furn>> pageByConditional2(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                                @RequestParam(value = "pageSize", defaultValue = "8") Integer pageSize,
                                                @RequestParam(value = "search", defaultValue = "") String search) {

        //说明，关于 Lambda 表达式，我们这里使用的是 类名::实例方法
        //是 lambda 方法引用中一个不太容易理解的知识点

        //解读:
        //1. Furn::getName 通过 lambda 表达式去引用实例方法 getName
        //2. 这里就是把 Furn::getName 赋给了 SFunction<T,R> 函数式接口
        /*
        @FunctionalInterface
        public interface SFunction<T, R> extends Function<T, R>, Serializable {
        }

        @FunctionalInterface
        public interface Function<T, R> {
            R apply(T t);   //抽象方法:表示根据类型 T 的参数，获取到类型 R 的结果
            //后面有默认实现方法
        }

        4. 传入Furn::getName 相当于实现类 SFunction<T,R> 的 apply 方法
        5. 底层就会根据 Furn:getName 去得到该方法对应的属性映射的表的字段
         */

        //创建 LambdaQueryWrapper, 封装查询条件
        LambdaQueryWrapper<Furn> queryWrapper = new LambdaQueryWrapper<>();

        //判断 search 关键字
        if (StringUtils.hasText(search)) {
            //后面讲解
            queryWrapper.like(Furn::getName, search);
        }

        Page<Furn> page = furnService.page(new Page<>(pageNum, pageSize), queryWrapper);
        log.info("page:{}", page);
        return Result.success(page);
    }
}
