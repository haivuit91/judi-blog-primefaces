/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.bean;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import model.dao.CategoryDAO;
import model.dao.PostDAO;
import model.dao.service.CategoryDAOService;
import model.dao.service.PostDAOService;
import model.entities.Post;

/**
 *
 * @author Tuanka
 */
@ManagedBean
@RequestScoped
public class PostManagementBean {

    private final PostDAOService POST_SERVICE = PostDAO.getInstance();
    private final CategoryDAOService CATEGORY_SERVICE = CategoryDAO.getInstance();
    private final FacesContext facesContext;
    private List<Post> listPost;
    private Post seletedPost;
    private String test;

    /**
     * Creates a new instance of PostManagementBean
     */
    public PostManagementBean() {
        seletedPost = new Post();
        this.facesContext = FacesContext.getCurrentInstance();
    }

    public void test(ActionEvent event){
        System.out.println("valefffffffffffffffffff;"+test);
    }
    
    public void delete2(Post post) {
        System.out.println("delete:" + post.getPostID());
        FacesMessage mess;
        try {
            if (POST_SERVICE.deletePost(post.getPostID())) {
                mess = new FacesMessage("Success!");
            } else {
                mess = new FacesMessage("fail!");
            }
            facesContext.addMessage("result", mess);

        } catch (Exception ex) {
            Logger.getLogger(PostManagementBean.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void active(Post post) {
        System.out.println("active:" + post.getPostID());
        FacesMessage mes;
        String strMess;
        try {
            if (POST_SERVICE.activePost(!post.isIsActive(), post.getPostID())) {
                if(post.isIsActive())
                    strMess = "Disable success !";
                else
                    strMess = "Enable success !";
            } else {
                if(post.isIsActive())
                    strMess = "Disable faile !";
                else
                    strMess = "Enable faile !";
            }
            mes = new FacesMessage("Message", strMess);
            facesContext.addMessage("result", mes);

        } catch (Exception ex) {
            Logger.getLogger(PostManagementBean.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }


    public List<Post> getListPost() throws Exception {
        return POST_SERVICE.getListPost();
    }

    public void setListPost(List<Post> listPost) {
        this.listPost = listPost;
    }

    public Post getSeletedPost() {
        return seletedPost;
    }

    public void setSeletedPost(Post seletedPost) {
        this.seletedPost = seletedPost;
    }

    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
    }

    
    
    
}
