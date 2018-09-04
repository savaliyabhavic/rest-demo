package com.ikhodal.empdeptrest.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ikhodal.empdeptrest.models.DeptModel;
import com.ikhodal.empdeptrest.services.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/department")
public class DeptController
{

    @Autowired
    DeptService deptService;

    @GetMapping(produces = "application/json")
    String getAllDept(@RequestParam(name = "name", defaultValue = "NULL") String name) throws JsonProcessingException
    {
        System.out.println(name);
        return deptService.getDeptJson();
    }

    @GetMapping("/{id}")
    DeptModel getDeptment(@PathVariable int id, HttpServletResponse response)
    {
        DeptModel deptment = deptService.getDeptment(id);
        if (deptment == null){
            response.setStatus(HttpStatus.BAD_REQUEST.value());
        }

        return deptment;
    }

    @PostMapping
    DeptModel createNewDepartment(@RequestBody DeptModel deptModel)
    {
        DeptModel insertedDepartment = this.deptService.insertDepartment(deptModel);
        return insertedDepartment;
    }

    @PutMapping
    DeptModel updatedDepartment(@RequestBody DeptModel newDeptModel)
    {
        DeptModel updatedDeptModel = this.deptService.updateDepartment(newDeptModel);
        return updatedDeptModel;
    }

    @DeleteMapping("/{id}")
    DeptModel deleteDepartment(@PathVariable int id) {
        return this.deptService.deleteDepartment(id);
    }
}
