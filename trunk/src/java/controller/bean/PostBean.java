/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.bean;

import java.io.IOException;
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

    public String insertPost() throws Exception {
        boolean isSuccess = false;
        try {

            post.setUser(Support.getCurrentUser());
            post.setImagePath(saveImage());
            post.setPostDate(Support.getCurrentDate());
            post.setActive(true);

//                System.out.println(post.getTitle());
//                System.out.println(post.getCategory().getCatID());
//                System.out.println(post.getContent());
//                System.out.println(post.getImagePath());
            if (POST_SERVICE.insertPost(post)) {
                return "/Judi-PrimeFaces/index.jsf";
            } else {
                return "/Judi-PrimeFaces/member/user-post.jsf";
            }

        } catch (Exception ex) {
            Logger.getLogger(PostManagementBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
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
            msg = "Deleted user successfully!";
        } else {
            msg = "Delete user failed!";
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

    public String editPost(Post post) {
        this.post = post;
        this.action = "Save";
        return "/Judi-PrimeFaces/member/createpost.jsf";
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
