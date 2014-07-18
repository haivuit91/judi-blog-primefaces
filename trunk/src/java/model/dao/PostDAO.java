/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.dao.service.PostDAOService;
import model.entities.Post;

/**
 *
 * @author Admin
 */
public class PostDAO implements PostDAOService {

    private static PostDAO postDAO;

    public static PostDAO getInstance() {
        if (postDAO == null) {
            postDAO = new PostDAO();
        }
        return postDAO;
    }

    private Post createPostItem(ResultSet rs) throws Exception {
        Post item = new Post();
        item.setPostID(rs.getInt("postID"));
        item.setTitle(rs.getString("title"));
        item.setContent(rs.getString("content"));
        item.setImagePath(rs.getString("imagePath"));
        item.setPostDate(rs.getDate("postDate"));
        item.setUser(UserDAO.getInstance().getUserByID(rs.getInt("userID")));
        item.setCategory(CategoryDAO.getInstance().getCategoryByID(rs.getInt("catID")));
        item.setIsActive(rs.getBoolean("isActive"));
        return item;
    }

    @Override
    public List<Post> getListPost() throws Exception {
        List<Post> listPost = new ArrayList<>();
        String sql = "select * from tbl_post";
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement sm = conn.prepareStatement(sql);
        ResultSet rs = sm.executeQuery();
        while (rs.next()) {
            listPost.add(createPostItem(rs));
        }
        return listPost;
    }

    @Override
    public List<Post> getListPostByCategories(int c) throws Exception {
        List<Post> listPost = new ArrayList<>();
        String sql = "select * from tbl_post where catID = " + c + ";";
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement sm = conn.prepareStatement(sql);
        ResultSet rs = sm.executeQuery();
        while (rs.next()) {
            listPost.add(createPostItem(rs));
        }
        return listPost;
    }

    @Override
    public int getTotalPost() throws Exception {
        String sql = "select count(*) as sum from tbl_post";
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement sm = conn.prepareStatement(sql);
        ResultSet rs = sm.executeQuery();
        if (rs.next()) {
            return rs.getInt("sum");
        }
        return 0;
    }

    @Override
    public boolean deletePost(int postID) throws Exception {
        String sql = "delete from tbl_post where postID =?";
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement sm = conn.prepareStatement(sql);
        sm.setInt(1, postID);
        return sm.executeUpdate() == 1;

    }

    @Override
    public boolean updatePost(Post post) throws Exception {
        String sql = "update tbl_post set title=?,content=?,imagePath=?,postDate=?,userID=?,catID=?,isActive=? where postID = ?";
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement sm = conn.prepareStatement(sql);
        sm.setString(1, post.getTitle());
        sm.setString(2, post.getContent());
        sm.setString(3, post.getImagePath());
        sm.setDate(4, (Date) post.getPostDate());
        sm.setInt(5, post.getUser().getUserID());
        sm.setInt(6, post.getCategory().getCatID());
        sm.setBoolean(7, post.isIsActive());
        sm.setInt(8, post.getPostID());
        return sm.executeUpdate() == 1;
    }

    @Override
    public boolean insertPost(Post post) throws Exception {
        String sql = "insert into tbl_post (title,content,imagePath,postDate,userID,catID,isActive) values(?,?,?,?,?,?,?)";
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement sm = conn.prepareStatement(sql);
        sm.setString(1, post.getTitle());
        sm.setString(2, post.getContent());
        sm.setString(3, post.getImagePath());
        sm.setDate(4, (Date) post.getPostDate());
        sm.setInt(5, post.getUser().getUserID());
        sm.setInt(6, post.getCategory().getCatID());
        sm.setBoolean(7, post.isIsActive());
        return sm.executeUpdate() == 1;
    }

    @Override
    public boolean activePost(boolean isActive, int postID) throws Exception {
        String sql = "update tbl_post set isActive =? where postID = ?";
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement sm = conn.prepareStatement(sql);
        sm.setBoolean(1, isActive);
        sm.setInt(2, postID);
        return sm.executeUpdate() == 1;
    }

    @Override
    public List<Post> searchPost(String searchType, String searchKey) throws Exception {
        String sql = "";
        Connection conn = ConnectionFactory.getConnection();
        List<Post> listPost = new ArrayList<>();
        switch (searchType) {
            case "title":
                sql = "select * from tbl_post where title like '%" + searchKey + "%' ";
                break;
            case "user":
                sql = "select postID,title,content,tbl_post.imagePath,postDate,tbl_post.userID,catID,tbl_post.isActive from tbl_post inner join tblUser on tbl_post.userID = tblUser.userID where userName like '%" + searchKey + "%'";
                break;
            case "fullName":
                sql = "select postID,title,content,tbl_post.imagePath,postDate,tbl_post.userID,catID,tbl_post.isActive from tbl_post inner join tblUser on tbl_post.userID = tblUser.userID where fullName like '%" + searchKey + "%'";
                break;
            case "content":
                sql = "select postID,title,content,tbl_post.imagePath,postDate,tbl_post.userID,catID,tbl_post.isActive from tbl_post inner join tblUser on tbl_post.userID = tblUser.userID where content like '%" + searchKey + "%'";
                break;
        }
        PreparedStatement sm = conn.prepareStatement(sql);
        ResultSet rs = sm.executeQuery();
        while (rs.next()) {
            listPost.add(createPostItem(rs));
        }
        return listPost;
    }

    @Override
    public Post getPostByID(int postID) throws Exception {
        String sql = "select * from tbl_post where postID =? ";
        Post post = null;
        Connection connection = ConnectionFactory.getConnection();
        PreparedStatement sm = connection.prepareStatement(sql);
        sm.setInt(1, postID);
        ResultSet rs = sm.executeQuery();
        if (rs.next()) {
            post = createPostItem(rs);
        }
        return post;
    }

    @Override
    public int getNewPostID() throws Exception {
        String sql = "SELECT IDENT_CURRENT('tbl_post') as maxid ";
        Connection connection = ConnectionFactory.getConnection();
        PreparedStatement sm = connection.prepareStatement(sql);
        ResultSet rs = sm.executeQuery();
        if (rs.next()) {
            return rs.getInt("maxID") + 1;
        }
        return 0;
    }

    @Override
    public boolean checkExitPost(Post post) throws Exception {
        String sql = "select postID from tbl_post where title = ? ";
        Connection connection = ConnectionFactory.getConnection();
        PreparedStatement sm = connection.prepareStatement(sql);
        sm.setString(1, post.getTitle());
        ResultSet rs = sm.executeQuery();
        return rs.next();
    }
}
