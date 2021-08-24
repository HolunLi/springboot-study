package com.holun.controller;

import com.holun.dao.DepartmentDao;
import com.holun.dao.EmployeeDao;
import com.holun.pojo.Department;
import com.holun.pojo.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collection;

@Controller
public class PageController {
    @Autowired
    private DepartmentDao departmentDao;
    @Autowired
    private EmployeeDao employeeDao;

    @RequestMapping("/foreToDashboardPage")
    public String toDashboardPage() {
        return "dashboard";
    }

    @RequestMapping("/foreToAddEmployeePage")
    public String toAddEmployeePage(Model model) {
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("departments", departments);

        return "employee/addEmployee";
    }

    @RequestMapping("/foreToUpdateEmployeePage/{id}")
    public String toUpdateEmployeePage(@PathVariable("id") int id, Model model) {
        Employee employee = employeeDao.queryEmployeeById(id);
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("employee", employee);
        model.addAttribute("departments", departments);

        return "employee/updateEmployee";
    }

}
