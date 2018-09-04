package com.ikhodal.empdeptrest.datamanager;

import com.ikhodal.empdeptrest.models.EmpModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class EmpDataService
{
    // It Contains Business Login!

    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<Map<String, Object>> getAllEmp()
    {
        String query = "SELECT EMID, ENAME, DEPTNO FROM EMPLOYEE";
        List<Map<String, Object>> resultset = jdbcTemplate.queryForList(query);
        return resultset;

    }

    public EmpModel getOneEmp(int id)
    {
        String query = "SELECT EMID, ENAME, DEPTNO FROM EMPLOYEE WHERE EMID = ?";
        return jdbcTemplate.queryForObject(query, new Object[]{id}, (resultSet, i) ->
        {
            EmpModel model = new EmpModel();
            model.setEmpId(resultSet.getInt("EMID"));
            model.setEmpName(resultSet.getString("ENAME"));
            model.setDeptNo(resultSet.getInt("DEPTNO"));
            return model;
        });
    }

    public int getValidDeptNum(int deptNo)
    {
        String query = "SELECT DEPTNO FROM DEPARTMENTDETAILS WHERE DEPTNO = ?";
        try
        {
            return jdbcTemplate.queryForObject(query,
                    new Object[]{deptNo},
                    (resultSet, i) -> resultSet.getInt("DEPTNO"));
        } catch (EmptyResultDataAccessException e)
        {
            return -1;
        }

    }

    public EmpModel addEmp(EmpModel empModel)
    {
        int deptNo = getValidDeptNum(empModel.getDeptNo());

        if (deptNo == empModel.getDeptNo())
        {
            String query = "INSERT INTO EMPLOYEE (EMID, ENAME, DEPTNO) VALUES(NULL, ?, ?)";
            int i = jdbcTemplate.update(query, preparedStatement ->
            {
                preparedStatement.setString(1, empModel.getEmpName());
                preparedStatement.setInt(2, empModel.getDeptNo());
            });

            if (i == 1)
            {
                return empModel;
            }

        }

        return null;
    }

    public EmpModel updateEmp(EmpModel empModel)
    {
        String query = "UPDATE EMPLOYEE SET ENAME = ?, DEPTNO = ? WHERE EMID = ?";
        int result = jdbcTemplate.update(query, (preparedStatement ->
        {
            preparedStatement.setString(1, empModel.getEmpName());
            preparedStatement.setInt(2, empModel.getDeptNo());
            preparedStatement.setInt(3, empModel.getEmpId());
        }));

        return result == 1 ? empModel : null;
    }

    public EmpModel deleteEmp(int id)
    {
        EmpModel oldmodel = getOneEmp(id);

        String query = "DELETE FROM EMPLOYEE WHERE EMID = ?";
        int result = jdbcTemplate.update(query,
                preparedStatement -> preparedStatement.setInt(1, id));

        return result == 1 ? oldmodel : null;
    }
}