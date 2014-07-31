/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.bean;

import java.awt.Event;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.ServletContext;
import javax.servlet.http.Part;
import model.dao.CategoryDAOImlp;
import model.dao.PostDAOImpl;
import model.dao.UserDAOImpl;
import model.dao.service.CategoryDAO;
import model.dao.service.PostDAO;
import model.dao.service.UserDAO;
import model.entities.Category;
import model.entities.Post;
import model.entities.User;
import util.Support;

/**
 *
 * @author Thanh
 */
@ManagedBean(name = "postBean")
@RequestScoped
public class PostBean {

    private final PostDAO POST_SERVICE = PostDAOImpl.getInstance();
    private final CategoryDAO CATEGORY_SERVICE = CategoryDAOImlp.getInstance();
    private final UserDAO userService = UserDAOImpl.getInstance();
    private final FacesContext facesContext;
    private Post post;
    // private Category category;
    private Part image;
    private String action = "Publish";

    private List<Post> filteredPost;
    private List<Category> category;
    private Category categorys;
    private List<Post> posts;

    /**
     * Creates a new instance of PostBean
     */
    public PostBean() {
        this.post = new Post();
        this.facesContext = FacesContext.getCurrentInstance();
    }

    public void newPost(ActionEvent event) {
        FacesMessage mes = null;
        try {
            post.setUser(Support.getCurrentUser());
            post.setPostDate(Support.getCurrentDate());
            post.setImagePath(saveImage());
            if (POST_SERVICE.insertPost(post)) {
                mes = new FacesMessage("Message", "Success !");
            } else {
                mes = new FacesMessage("Message", "Faile !");
            }
        } catch (Exception e) {
            Logger.getLogger(PostBean.class
                    .getName()).log(Level.SEVERE, null, e);
        }
        facesContext.addMessage("result", mes);
    }

    public void update(ActionEvent event) {

        String mes  = null;
        try {
            User user = util.Support.getCurrentUser();
            Category cat = this.post.getCategory();
            String title = this.post.getTitle();
            String content = this.post.getContent();
            String imagePath = this.post.getImagePath();
            Date date = new Date();
            Post p = new Post(cat, user, title, content, imagePath, date, true);
            p.setPostId(this.post.getPostId());
            if (POST_SERVICE.updatePost(p)) {
                mes = "OK";
            } else {
                mes = "Failed";
            }
        } catch (Exception e) {
            System.out.println("err:" + e.getMessage());
            Logger.getLogger(PostBean.class
                    .getName()).log(Level.SEVERE, null, e);
        }
        this.addMessages(mes);
    }

    public void addMessages(String msg) {
        FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, "Message!");
        FacesContext.getCurrentInstance()
                .addMessage(null, fm);
    }

    public List<Category> getList() {
        return CATEGORY_SERVICE.getList();
    }

    private String getFilename(Part part) {
        for (String cd : part.getHeader("content-disposition").split(";")) {
            if (cd.trim().startsWith("filename")) {
                String filename = cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
                return filename.substring(filename.lastIndexOf('/') + 1).substring(filename.lastIndexOf('\\') + 1); // MSIE fix.  
            }
        }
        return null;
    }

    private String saveImage() {
        String fileName = "default.png";
        try {
            if (image == null) {
                return fileName;
            }
            ServletContext context = (ServletContext) facesContext.getExternalContext().getContext();
            String path = context.getRealPath("").replace("build\\web", "web\\img\\post\\");
            String arr[] = getFilename(image).split("\\.");
            String type = arr[arr.length - 1];
            if (type.equals("PNG") || type.equals("png") || type.equals("jpg") || type.equals("JPG")) {
                fileName = Support.randomCode(8) + "." + type;
                image.write(path + fileName);
                post.setImagePath(fileName);
            }
        } catch (IOException ex) {
            Logger.getLogger(PostBean.class
                    .getName()).log(Level.SEVERE, null, ex);
            return "default.png";
        }
        return fileName;
    }

    public List<Post> getListPost() {
        try {
            List<Post> listPost = POST_SERVICE.getListPost();
            return listPost;

        } catch (Exception ex) {
            Logger.getLogger(PostBean.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void deleteUser(ActionEvent event) throws Exception {
        String msg;
        if (POST_SERVICE.deletePost(this.post.getPostId())) {
            msg = "Deleted  successfully!";
        } else {
            msg = "Delete  failed!";
        }
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, "Message!");
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

//    public String detete(int postID) {
//
//        try {
//            if (POST_SERVICE.deletePost(postID)) {
//                return "post-management";
//            } else {
//                return "/Judi-PrimeFaces/index.jsf";
//            }
//
//        } catch (Exception ex) {
//            Logger.getLogger(PostBean.class
//                    .getName()).log(Level.SEVERE, null, ex);
//        }
//        return null;
//    }
    public String editPost(ActionEvent event) throws Exception {

        try {
            Post p = POST_SERVICE.getPostByID(post.getPostId());
            p.setContent(post.getContent());
            p.setTitle(post.getTitle());
            p.setImagePath(post.getImagePath());
            p.setPostDate(post.getPostDate());
            p.setActive(true);
            p.setUser(post.getUser());
            p.setCategory(post.getCategory());

            if (POST_SERVICE.updatePost(p)) {
                return "/Judi-PrimeFaces/index.jsf";
            } else {
                return "/Judi-PrimeFaces/member/user-post.jsf";
            }
        } catch (Exception e) {

        }

//        int postID = getPost().getPostID();
//        String title = getPost().getTitle();
//        String content = getPost().getContent();
//        String img =getPost().getImagePath();
//        Date startDate = getPost().getPostDate();
//        java.sql.Date date = new java.sql.Date(startDate.getTime());
//        User us= getPost().getUser();
//        Category cate = this.categorys;
//       
//        
//        Post post = new Post(postID, title, content, img, startDate, us, cate, true);
//        if (POST_SERVICE.updatePost(post)) {
//            System.out.println("thanh");
//        } 
//        System.out.println("that bai");
//        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, "Message!");
//        FacesContext.getCurrentInstance().addMessage(null, message);
        return null;
    }

    /**
     * @return the categorys
     */
    public Category getCategorys() {
        return categorys;
    }

    /**
     * @param categorys the categorys to set
     */
    public void setCategorys(Category categorys) {
        this.categorys = categorys;
    }

    public class EditorView {

        private String text;

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }
    }

    public List<Category> getListCategory() {
        return CATEGORY_SERVICE.getList();
    }

    /**
     * @return the post
     */
    public Post getPost() {
        return post;
    }

    /**
     * @param post the post to set
     */
    public void setPost(Post post) {
        this.post = post;
    }

    /**
     * @return the image
     */
    public Part getImage() {
        return image;
    }

    /**
     * @param image the image to set
     */
    public void setImage(Part image) {
        this.image = image;
    }

    /**
     * @return the category
     */
    public List<Category> getCategory() {
        category = CATEGORY_SERVICE.getList();
        return category;
    }

    /**
     * @param category the category to set
     */
    public void setCategory(List<Category> category) {
        this.category = category;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public List<Post> getFilteredPost() {
        return filteredPost;
    }

    public void setFilteredPost(List<Post> filteredPost) {
        this.filteredPost = filteredPost;
    }

}
