package com.ikhodal.empdeptrest.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ikhodal.empdeptrest.datamanager.DeptDataService;
import com.ikhodal.empdeptrest.models.DeptModel;
import com.ikhodal.empdeptrest.utils.JsonProcessingUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class DeptService
{

    @Autowired
    DeptDataService deptDataService;

    @Autowired
    JsonProcessingUtils jsonProcessingUtils;

    public String getDeptJson() throws JsonProcessingException
    {
        List<Map<String, Object>> resultset = deptDataService.getAllDept();
        List<DeptModel> deptModelsList = new ArrayList<>();

        for (Map<String, Object> result : resultset)
        {
            int deptId = (int) result.get("DEPTNO");
            String deptName = (String) result.get("DNAME");
            String deptLocation = (String) result.get("Location");

            DeptModel deptModel = new DeptModel();
            deptModel.setDeptNo(deptId);
            deptModel.setDeptName(deptName);
            deptModel.setDeptLocaltion(deptLocation);

            deptModelsList.add(deptModel);
        }

        return jsonProcessingUtils.convertObjectToJSONString(deptModelsList);

    }

    public DeptModel getDeptment(int id){
        return deptDataService.getOneDept(id);
    }

    public DeptModel insertDepartment(DeptModel deptModel){
        return this.deptDataService.createNewDepartment(deptModel);
    }

    public DeptModel updateDepartment(DeptModel newDeptModel)
    {
        return this.deptDataService.updateDepartment(newDeptModel);
    }

    public DeptModel deleteDepartment(int id)
    {
        return this.deptDataService.deleteDepartment(id);
    }
}
