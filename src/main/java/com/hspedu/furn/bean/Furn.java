package com.hspedu.furn.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * @author Zexi He.
 * @date 2023/5/9 23:13
 * @description:
 *
 * `id` INT(11) PRIMARY KEY AUTO_INCREMENT, ## id
 * `name` VARCHAR(64) NOT NULL, ## 家居名
 * `maker` VARCHAR(64) NOT NULL, ## 厂商
 * `price` DECIMAL(11,2) NOT NULL, ## 价格
 * `sales` INT(11) NOT NULL, ## 销量
 * `stock` INT(11) NOT NULL ## 库存
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("furn")  //目前可以不写，如果表和bean不能形成映射，需要加上
public class Furn {

    //这里我们使用 @TableId: 表的主键标识
    //当我们在 private Integer id 上表示了 @TableId
    //说明 id 对应的就是表的 id 字段，而且是主键
    //表示主键类型是自增长: type = IdType.AUTO
    @TableId(type = IdType.AUTO)
    private Integer id;

    @NotEmpty(message = "请输入家具名")
    private String name;

    @NotEmpty(message = "请输入厂商名")
    private String maker;

    @NotNull(message = "请输入数字")
    @Range(min = 0, message = "价格不能小于0")
    private BigDecimal price;

    @NotNull(message = "请输入数字")
    @Range(min = 0, message = "销量不能小于0")
    private Integer sales;

    @NotNull(message = "请输入数字")
    @Range(min = 0, message = "库存不能小于0")
    private Integer stock;
}
