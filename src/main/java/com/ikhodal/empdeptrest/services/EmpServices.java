package com.ikhodal.empdeptrest.services;

import com.ikhodal.empdeptrest.datamanager.EmpDataService;
import com.ikhodal.empdeptrest.models.EmpModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class EmpServices {

    @Autowired
    EmpDataService empDataService;

    public List<EmpModel> getAllEmp(){

        List<Map<String,Object>> mapList = empDataService.getAllEmp();
        List<EmpModel> listModel = new ArrayList<>();

        for (Map<String,Object> item: mapList) {
            EmpModel empModel = new EmpModel();
            empModel.setEmpId((int)item.get("EMID"));
            empModel.setEmpName((String) item.get("ENAME"));
            empModel.setDeptNo((int) item.get("DEPTNO"));

            listModel.add(empModel);
        }

        return listModel;
    }

    public EmpModel getOneEmp(int id)
    {
        return this.empDataService.getOneEmp(id);
    }

    public EmpModel addEmp(EmpModel empModel){
        return this.empDataService.addEmp(empModel);
    }

    public EmpModel updateEmp(EmpModel empModel)
    {
        return this.empDataService.updateEmp(empModel);
    }

    public EmpModel deleteEmp(int id) {
        return this.empDataService.deleteEmp(id);
    }
}
