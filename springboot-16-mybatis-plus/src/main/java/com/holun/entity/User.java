package com.holun.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
//@TableName("user") //使用@TableName注解将实体类与数据库中的某个表绑定（如果数据库中存在和当前实体类同名的表，默认就会绑定）
public class User {
   // @TableId(type = IdType.AUTO)
    private Long id;
    private String name;
    private Integer age;
    private String email;
    @TableField(fill = FieldFill.INSERT)  //往表插入数据时，为插入对象的createTime属性填充值（MybatisPlus的自动填充功能）
    private LocalDateTime createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE) //更新表中的数据时，为更新对象的updateTime属性填充值
    private LocalDateTime updateTime;
    @TableField(exist = false) //使用@TableField注解来标注非数据库表字段
    private String pwd;

    public User(String name, Integer age) {
        this.name = name;
        this.age = age;
    }
}
