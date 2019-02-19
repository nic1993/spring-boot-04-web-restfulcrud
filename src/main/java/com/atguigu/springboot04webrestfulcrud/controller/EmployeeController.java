package com.atguigu.springboot04webrestfulcrud.controller;

import com.atguigu.springboot04webrestfulcrud.dao.DepartmentDao;
import com.atguigu.springboot04webrestfulcrud.dao.EmployeeDao;
import com.atguigu.springboot04webrestfulcrud.entities.Department;
import com.atguigu.springboot04webrestfulcrud.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Controller
public class EmployeeController {

    @Autowired
    EmployeeDao employeeDao;

    @Autowired
    DepartmentDao departmentDao;


    @GetMapping("/emps")
    public  String list(Model model){
        Collection<Employee> employees = employeeDao.getAll();

        //放在请求域中
        model.addAttribute("emps",employees);
        //thymeleaf 默认就会拼串
        return  "emp/list";
    }

    @ResponseBody
    @GetMapping("/test")
    public Map<String,Object> test(){
        Collection<Employee> employees = employeeDao.getAll();

        Map<String,Object> map = new HashMap<>();

        map.put("emp",employees);

        return  map;
    }

    @GetMapping("/emp")
    public  String toAddPage(Model model){
       //查出所有的部门
        Collection<Department>  departments = departmentDao.getDepartments();
        model.addAttribute("depts",departments);
        //来到添加页面
        return  "emp/add";
    }


    //员工添加
    //SpringMVC自动将请求参数和入参对象的属性进行一一绑定；
    //要求
    @PostMapping("/emp")
    public  String addEmp(Employee employee){
        System.out.println("保存的员工信息:" + employee);
        employeeDao.save(employee);
        //来到员工页面
        //redirect:表示重定向到一个地址
        //forward:表示转发到一个地址
        return  "redirect:/emps";
    }

    //来到修改页面 查出当前员工 在页面回显
    @GetMapping("/emp/{id}")
    public  String addEmp(@PathVariable("id") Integer id,Model model){

        Employee employee = employeeDao.get(id);
        model.addAttribute("emp",employee);

        //查出所有的部门
        Collection<Department>  departments = departmentDao.getDepartments();
        model.addAttribute("depts",departments);

        //回到修改页面 （修改添加二合一页面）
        return  "emp/add";
    }


    //员工修改
    @PutMapping("/emp")
    public  String updateEmployee(Employee employee){
        employeeDao.save(employee);
        return  "redirect:/emps";
    }

    //员工修改
    @DeleteMapping("/emp/{id}")
    public  String updateEmployee(@PathVariable("id") Integer id){
        employeeDao.delete(id);
        return  "redirect:/emps";
    }
}
