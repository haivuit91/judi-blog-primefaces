/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package service;

import entity.Post;
import java.util.List;


/**
 *
 * @author Tuanka
 */
public interface PostDAO {
     /**
     * get list of post
     * @return list
     * @throws Exception 
     */
    List<Post> getListPost() throws Exception;
    /**
     * get list of post
     * @param c
     * @return list post by categories
     * @throws Exception 
     */
    List<Post> getListPostByCategories(int c) throws Exception;
    /**
     * get sum post
     * @return
     * @throws Exception 
     */
    int getTotalPost() throws Exception;
    /**
     * delete post by postID
     * @param postID 
     * @return true if delete success
     * @throws Exception 
     */
    boolean deletePost(int postID) throws Exception;
    /**
     * update post
     * @param post object Post 
     * @return true if update success
     * @throws Exception 
     */
    boolean updatePost(Post post) throws Exception;
    /**
     * add new post
     * @param post object Post
     * @return true if insert success
     * @throws Exception 
     */
    boolean insertPost(Post post) throws Exception;
    /**
     * enable or disable post
     * @param isActive true if enable post
     * @param postID 
     * @return true if active success
     * @throws Exception 
     */
    boolean activePost(boolean isActive,int postID) throws Exception;
    /**
     * search by title
     * @param searchType search for title, user, fullName, content 
     * @param searchKey search value
     * @return
     * @throws Exception 
     */
    List<Post> searchPost(String searchType,String searchKey) throws Exception;
    /**
     * get post by PostID
     * @param postID
     * @return post object
     */
    Post getPostByID(int postID) throws Exception;
    /**
     * get new Post ID
     * @return
     * @throws Exception 
     */
    int getNewPostID() throws Exception;
    /**
     * check exit post
     * @param post
     * @return true if post exit
     * @throws Exception 
     */
    boolean checkExitPost(Post post) throws Exception;
}
