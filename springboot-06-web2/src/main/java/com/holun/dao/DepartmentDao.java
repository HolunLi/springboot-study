package com.holun.dao;

import com.holun.pojo.Department;
import org.springframework.stereotype.Repository;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
public class DepartmentDao {

    private static Map<Integer, Department> departments = null;

    //这里只是模拟数据库表中的数据
    static {
        departments = new HashMap<>();
        departments.put(1, new Department(1, "市场部"));
        departments.put(2, new Department(2, "开发部"));
        departments.put(3, new Department(3, "运营部"));
        departments.put(4, new Department(4, "教研部"));
    }

    //查询所有的部门
    public Collection<Department> getDepartments() {
        return departments.values();
    }

    //根据部门id，查找部门
    public Department queryDepartmentById(Integer id) {
        return departments.get(id);
    }
}
