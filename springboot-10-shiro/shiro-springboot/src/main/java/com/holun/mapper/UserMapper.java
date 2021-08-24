package com.holun.mapper;

import com.holun.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    User queryUserByName(String name);
}
