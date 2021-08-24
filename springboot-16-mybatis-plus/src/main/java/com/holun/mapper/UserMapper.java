package com.holun.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.holun.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper  //在BaseMapper<User>接口中，为user表提供了一套CRUD的操作，因此我们编写UserMapper只需要继承它即可获得CRUD功能，无需编写 mapper.xml 文件
public interface UserMapper extends BaseMapper<User> {
}
