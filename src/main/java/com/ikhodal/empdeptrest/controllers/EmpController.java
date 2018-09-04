package com.ikhodal.empdeptrest.controllers;


import com.ikhodal.empdeptrest.models.EmpModel;
import com.ikhodal.empdeptrest.services.EmpServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class EmpController
{
    @Autowired
    EmpServices empServices;

    @GetMapping(produces = "application/Json")
    public List<EmpModel> getAllUsers(@RequestParam(defaultValue = "NUll") String name){
        System.out.println(":::::::::::::: " + name);
        return this.empServices.getAllEmp();
    }

    @GetMapping("/{id}")
    EmpModel getOneUser(@PathVariable int id){
        return this.empServices.getOneEmp(id);
    }

    @PostMapping
    EmpModel addEmp(@RequestBody EmpModel empModel){
        return this.empServices.addEmp(empModel);
    }

    @PutMapping
    EmpModel updateEmp(@RequestBody EmpModel empModel){
        return this.empServices.updateEmp(empModel);
    }

    @DeleteMapping("/{id}")
    EmpModel deleteEmp(@PathVariable int id){
        return this.empServices.deleteEmp(id);
    }

}
