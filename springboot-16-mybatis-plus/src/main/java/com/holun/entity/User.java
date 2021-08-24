package com.holun.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
//@TableName("user") //使用@TableName注解将实体类与数据库中的某个表绑定（如果数据库中存在和当前实体类同名的表，默认就会绑定）
public class User {
    private Integer id;
    private String name;
    private Integer age;
    private String email;

    @TableField(exist = false) //使用@TableField注解来标注非数据库表字段
    private String pwd;
}
