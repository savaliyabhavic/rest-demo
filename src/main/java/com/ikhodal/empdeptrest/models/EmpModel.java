package com.ikhodal.empdeptrest.models;

public class EmpModel
{
    private int empId;
    private String empName;
    private int deptNo;

    public int getEmpId()
    {
        return empId;
    }

    public void setEmpId(int empId)
    {
        this.empId = empId;
    }

    public String getEmpName()
    {
        return empName;
    }

    public void setEmpName(String empName)
    {
        this.empName = empName;
    }

    public int getDeptNo()
    {
        return deptNo;
    }

    public void setDeptNo(int deptNo)
    {
        this.deptNo = deptNo;
    }
}
