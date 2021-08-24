package com.holun;

import com.holun.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class Springboot07JdbcApplicationTests {
    @Autowired
    private DataSource dataSource;

    @Test
    void contextLoads() {
        System.out.println(dataSource.getClass());
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<User> list = new ArrayList<>();
        User user;

        try {
            //从数据源中获取数据库连接对象
            connection = dataSource.getConnection();
            String sql = "select * from user";
            //获取preparedStatement对象，并对sql进行预处理
            preparedStatement = connection.prepareStatement(sql);
            //执行sql，返回查询结果集
            resultSet = preparedStatement.executeQuery();
            //处理查询结果集
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                String pwd = resultSet.getString(3);
                user = new User(id, name, pwd);
                list.add(user);
            }

            list.forEach(System.out :: println);

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                if (connection != null) connection.close();
                if (preparedStatement != null) preparedStatement.close();
                if (resultSet != null) resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
