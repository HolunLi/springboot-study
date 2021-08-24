package com.holun.dao;

import com.holun.pojo.Department;
import com.holun.pojo.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
public class EmployeeDao {

    private static Map<Integer, Employee>  employees;
    @Autowired
    private DepartmentDao departmentDao;

    //这里只是模拟数据库表中的数据
    static {
        employees = new HashMap<>();
        employees.put(1, new Employee(1, "lunge", "1545022@qq.com", 1, new Department(1, "市场部")));
        employees.put(2, new Employee(2, "ajie", "13434022@qq.com", 1, new Department(3, "运行部")));
        employees.put(3, new Employee(3, "张欣", "1541222@qq.com", 0, new Department(3, "运行部")));
        employees.put(4, new Employee(4, "独一味", "15022@qq.com", 0, new Department(2, "开发部")));
    }

    //模拟主键自增
    private Integer initId = 5;

    //增加员工
    public void addEmployee(Employee employee) {
        if (employee.getId() == null) {
            employee.setId(initId++);
        }

        employee.setDepartment(departmentDao.queryDepartmentById(employee.getDepartment().getId()));

        employees.put(employee.getId(), employee);
    }

    //查询所有的员工
    public Collection<Employee> getEmployees() {
        return employees.values();
    }

    //根据id,查询员工
    public Employee queryEmployeeById(Integer id) {
        return employees.get(id);
    }

    public void updateEmployee(Employee employee) {
        employee.setDepartment(departmentDao.queryDepartmentById(employee.getDepartment().getId()));
        employees.put(employee.getId(), employee);
    }

    //通过id，删除员工
    public Employee deleteEmployeeById(Integer id) {
        return employees.remove(id);
    }
}
