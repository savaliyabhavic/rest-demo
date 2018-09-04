package com.ikhodal.empdeptrest.datamanager;

import com.ikhodal.empdeptrest.models.DeptModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class DeptDataService
{
    // Database login


    @Autowired
//    @Qualifier("customJDBCTemplate")
            JdbcTemplate jdbcTemplate;

    public List<Map<String, Object>> getAllDept()
    {
        String query = "SELECT DEPTNO, DNAME, Location FROM DEPARTMENTDETAILS";
        List<Map<String, Object>> resultList = jdbcTemplate.queryForList(query);
        return resultList;
    }

    public DeptModel getOneDept(int id)
    {
        String query = "SELECT DEPTNO, DNAME, Location FROM DEPARTMENTDETAILS WHERE DEPTNO = ?";

        try
        {
            return jdbcTemplate.queryForObject(query, new Object[]{id}, (resultSet, i) ->
            {
                DeptModel model = new DeptModel();
                model.setDeptNo(resultSet.getInt("DEPTNO"));
                model.setDeptName(resultSet.getString("DNAME"));
                model.setDeptLocaltion(resultSet.getString("Location"));

                return model;
            });
        } catch (EmptyResultDataAccessException empty)
        {
            return null;
        }
    }

    public int getLastDepartmentId()
    {
        String query = "SELECT DEPTNO FROM DEPARTMENTDETAILS ORDER BY DEPTNO DESC LIMIT 1";
        return jdbcTemplate.queryForObject(query, ((resultSet, i) -> resultSet.getInt("DEPTNO")));
    }

    public DeptModel createNewDepartment(DeptModel deptModel)
    {
        String query = "INSERT INTO DEPARTMENTDETAILS(DEPTNO, DNAME, Location) VALUES(?, ?, ?)";
        int update = jdbcTemplate.update(query, preparedStatement ->
        {
            int newDepartmentId = this.getLastDepartmentId() + 10;
            deptModel.setDeptNo(newDepartmentId);

            preparedStatement.setInt(1, deptModel.getDeptNo());
            preparedStatement.setString(2, deptModel.getDeptName());
            preparedStatement.setString(3, deptModel.getDeptLocaltion());
        });

        if (update == 1)
        {
            return deptModel;
        } else
        {
            return null;
        }
    }

    public DeptModel updateDepartment(DeptModel newDeptModel)
    {
        String query = "UPDATE DEPARTMENTDETAILS SET DNAME = ?, Location = ? WHERE DEPTNO = ?";
        int updatedRows = jdbcTemplate.update(query, (preparedStatement ->
        {
            preparedStatement.setString(1, newDeptModel.getDeptName());
            preparedStatement.setString(2, newDeptModel.getDeptLocaltion());
            preparedStatement.setInt(3, newDeptModel.getDeptNo());
        }));

        return updatedRows == 1 ? newDeptModel : null;
    }

    public DeptModel deleteDepartment(int id)
    {
        DeptModel deptModel = this.getOneDept(id);

        String query = "DELETE FROM DEPARTMENTDETAILS WHERE DEPTNO = ?";
        int updatedRows = jdbcTemplate.update(query, (preparedStatement ->
        {
            preparedStatement.setInt(1, id);
        }));

        return updatedRows == 1 ? deptModel : null;
    }
}
