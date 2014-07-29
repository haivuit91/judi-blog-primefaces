/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.bean;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.ServletContext;
import model.olddao.CategoryDAO;
import model.olddao.PostDAO;
import model.olddao.UserDAO;
import model.olddao.service.CategoryDAOService;
import model.olddao.service.PostDAOService;
import model.olddao.service.UserDAOService;
import model.oldentities.Category;
import model.oldentities.Post;
import model.oldentities.User;
import org.primefaces.model.UploadedFile;
import util.Support;

/**
 *
 * @author Tuanka
 */
@ManagedBean
@RequestScoped
public class PostManagementBean {

    private final int CODE_SIZE = 8;
    private final PostDAOService POST_SERVICE = PostDAO.getInstance();
    private final CategoryDAOService CATEGORY_SERVICE = CategoryDAO.getInstance();
    private final UserDAOService USER_SERVICE = UserDAO.getInstance();
    private final FacesContext facesContext;
    private List<Post> listPost;
    private List<Category> listCategory;
    private List<User> listUser;
    private Post selectedPost;
    private UploadedFile image;

    /**
     * Creates a new instance of PostManagementBean
     */
    public PostManagementBean() {
        selectedPost = new Post();
        this.facesContext = FacesContext.getCurrentInstance();
    }

    public void delete(ActionEvent event) {
        FacesMessage mess;
        try {
            if (POST_SERVICE.deletePost(selectedPost.getPostID())) {
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

    public void active(ActionEvent event) {
        FacesMessage mes;
        String strMess;
        try {
            selectedPost = POST_SERVICE.getPostByID(selectedPost.getPostID());
            if (POST_SERVICE.activePost(!selectedPost.isIsActive(), selectedPost.getPostID())) {
                if (selectedPost.isIsActive()) {
                    strMess = "Disable success !";
                } else {
                    strMess = "Enable success !";
                }
            } else {
                if (selectedPost.isIsActive()) {
                    strMess = "Disable faile !";
                } else {
                    strMess = "Enable faile !";
                }
            }
            mes = new FacesMessage("Message", strMess);
            facesContext.addMessage("result", mes);

        } catch (Exception ex) {
            Logger.getLogger(PostManagementBean.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void newPost(ActionEvent event) {
        FacesMessage mes = null;
        try {
            selectedPost.setUser(Support.getCurrentUser());
            selectedPost.setPostDate(Support.getCurrentDate());
            selectedPost.setImagePath(saveImage());
            if(POST_SERVICE.insertPost(selectedPost))
                  mes = new FacesMessage("Message","Success !");
            else
                  mes = new FacesMessage("Message","Faile !");
        } catch (Exception e) {
              Logger.getLogger(PostManagementBean.class
                    .getName()).log(Level.SEVERE, null, e);
        }
        facesContext.addMessage("result", mes);
    }
    public void update(ActionEvent event){
         FacesMessage mes = null;
        try {
            Post post = POST_SERVICE.getPostByID(selectedPost.getPostID());
            post.setContent(selectedPost.getContent());
            post.setTitle(selectedPost.getTitle());
            post.setUser(selectedPost.getUser());
            post.setCategory(selectedPost.getCategory());
            if(image != null)
                post.setImagePath(saveImage());
            if(POST_SERVICE.updatePost(post))
                  mes = new FacesMessage("Message","Success !");
            else
                  mes = new FacesMessage("Message","Faile !");
        } catch (Exception e) {
            System.out.println("err:" + e.getMessage());
              Logger.getLogger(PostManagementBean.class
                    .getName()).log(Level.SEVERE, null, e);
        }
        facesContext.addMessage("result", mes);
    }
 
    private String saveImage() {
        String fileName = "default.png";
        if (image == null) {
            return fileName;
        }
        try {
            String arr[] = image.getFileName().split("\\.");
            String fileExtention = arr[arr.length - 1];
            fileName = Support.randomCode(CODE_SIZE) + "." + fileExtention;
            ServletContext context = (ServletContext) facesContext.getExternalContext().getContext();
            String folder = context.getRealPath("").replace("\\build\\web", "\\web\\resources\\images\\post\\");
            if(processImage(folder, fileName, image.getInputstream()))
                return fileName;
        } catch (IOException e) {
            System.out.println("err:" + e.getMessage());
        }
        return fileName;
    }

    private boolean processImage(String folder, String fileName, InputStream inStream) {
        try {
            String path = folder + "\\" + fileName;
            File file = new File(path);
            if (!file.exists()) {
                file.createNewFile();
            }
            FileOutputStream outStream = new FileOutputStream(file);
            int x = 0;
            byte[] b = new byte[1024];
            while ((x = inStream.read(b)) != -1) {
                outStream.write(b, 0, x);
            }
            return true;
        } catch (IOException e) { }
        return false;
    }

    public List<Category> getListCategory() {
        return CATEGORY_SERVICE.getList();
    }

    public void setListCategory(List<Category> listCategory) {
        this.listCategory = listCategory;
    }

    public List<Post> getListPost() throws Exception {
        return POST_SERVICE.getListPost();
    }

    public void setListPost(List<Post> listPost) {
        this.listPost = listPost;
    }

    public Post getSeletedPost() {
        return selectedPost;
    }

    public void setSeletedPost(Post seletedPost) {
        this.selectedPost = seletedPost;
    }

    public UploadedFile getImage() {
        return image;
    }

    public void setImage(UploadedFile image) {
        this.image = image;
    }

    public List<User> getListUser() {
        return USER_SERVICE.getAllUser();
    }

    public void setListUser(List<User> listUser) {
        this.listUser = listUser;
    }
    
}
