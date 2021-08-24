package com.holun.mapper;

import com.holun.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Mapper和@Repository 都是作用在dao层接口上，使得其生成代理对象bean，交给spring容器管理（注入到容器）
 * 区别：
 * @Mapper 可以单独使用，不需要配置地址扫描，通过sql映射文件中的namnspace绑定指定mapper接口的地址后，为mapper接口生成的动态代理对象才会被注入到容器
 * @Repository 不可以单独使用，需要配置地址后，为mapper接口生成的动态代理对象才会被注入到容器
 */

@Mapper
//@Repository
public interface UserMapper {

    List<User> list();

}
