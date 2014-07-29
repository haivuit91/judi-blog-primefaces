/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.olddao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.olddao.service.ProjectTypeDAOService;
import model.oldentities.ProjectType;

/**
 *
 * @author Admin
 */
public class ProjectTypeDAO implements ProjectTypeDAOService {

    private static ProjectTypeDAO ptDAO;

    public static ProjectTypeDAO getInstance() {
        if (ptDAO == null) {
            ptDAO = new ProjectTypeDAO();
        }
        return ptDAO;
    }

    @Override
    public List<ProjectType> getTypes() {
        List<ProjectType> typeList = new ArrayList<>();
        try {
            Connection conn = ConnectionFactory.getConnection();
            String sql = "select * from tbl_type";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                ProjectType type = new ProjectType();
                type.setTypeID(rs.getInt("typeID"));
                type.setTypeName(rs.getString("typeName"));
                type.setActive(rs.getInt("isActive"));
                type.setProjectList(ProjectDAO.getInstance().getProjectByType(rs.getInt("typeID")));
                typeList.add(type);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return typeList;
    }

    @Override
    public ProjectType getTypeByID(int typeID) {
        ProjectType type = new ProjectType();
        try {
            Connection conn = ConnectionFactory.getConnection();
            String sql = "select * from tbl_type where typeID = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, typeID);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                type.setTypeID(rs.getInt("typeID"));
                type.setTypeName(rs.getString("typeName"));
                type.setActive(rs.getInt("isActive"));
                type.setProjectList(ProjectDAO.getInstance().getProjectByType(rs.getInt("typeID")));
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return type;
    }

    @Override
    public ProjectType getTypeByName(String typeName) {
        ProjectType type = new ProjectType();
        try {
            Connection conn = ConnectionFactory.getConnection();
            String sql = "select * from tbl_type where typeName = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, typeName);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                type.setTypeID(rs.getInt("typeID"));
                type.setTypeName(rs.getString("typeName"));
                type.setActive(rs.getInt("isActive"));
                type.setProjectList(ProjectDAO.getInstance().getProjectByType(rs.getInt("typeID")));
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return type;
    }

    @Override
    public boolean createProjectType(ProjectType type) {
        boolean isCheck = false;
        try {
            Connection conn = ConnectionFactory.getConnection();
            String sql = "insert into tbl_type values(?,?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, type.getTypeName());
            pstmt.setInt(2, type.getActive());
            pstmt.executeUpdate();
            isCheck = true;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return isCheck;
    }

    @Override
    public boolean updateProjectType(ProjectType type) {
        boolean isCheck = false;
        try {
            Connection conn = ConnectionFactory.getConnection();
            String sql = "update tbl_type set typeName = ?, isActive = ? where typeID = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, type.getTypeName());
            pstmt.setInt(2, type.getActive());
            pstmt.setInt(3, type.getTypeID());
            pstmt.executeUpdate();
            isCheck = true;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return isCheck;
    }

    @Override
    public boolean deleteProjectType(int typeID) {
        boolean isCheck = false;
        try {
            Connection conn = ConnectionFactory.getConnection();
            String sql = "delete from tbl_type where typeID = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, typeID);
            pstmt.executeUpdate();
            isCheck = true;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return isCheck;
    }

    @Override
    public boolean activeProjectType(ProjectType type) {
        boolean isCheck = false;
        try {
            Connection conn = ConnectionFactory.getConnection();
            String sql = "update tbl_type set isActive = '1' where typeID = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, type.getTypeID());
            pstmt.executeUpdate();
            isCheck = true;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return isCheck;
    }

    @Override
    public boolean inactiveProjectType(ProjectType type) {
        boolean isCheck = false;
        try {
            Connection conn = ConnectionFactory.getConnection();
            String sql = "update tbl_type set isActive = '0' where typeID = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, type.getTypeID());
            pstmt.executeUpdate();
            isCheck = true;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return isCheck;
    }

}
