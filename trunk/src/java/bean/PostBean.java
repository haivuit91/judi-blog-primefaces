/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.Part;
import model.dao.CategoryDAO;
import model.dao.PostDAO;
import model.dao.UserDAO;
import model.dao.service.CategoryDAOService;
import model.dao.service.PostDAOService;
import model.dao.service.UserDAOService;
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

    private final PostDAOService POST_SERVICE = PostDAO.getInstance();
    private final CategoryDAOService CATEGORY_SERVICE = CategoryDAO.getInstance();
    private final UserDAOService userService = UserDAO.getInstance();
    private final FacesContext facesContext;
    private Post post;
    private Part image;
    private String action = "Publish";

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
            post.setIsActive(true);

//                System.out.println(post.getTitle());
//                System.out.println(post.getCategory().getCatID());
//                System.out.println(post.getContent());
//                System.out.println(post.getImagePath());
            if (POST_SERVICE.insertPost(post)) {
                return "/Judi-PrimeFaces/index.jsf";
            } else {
                return "/Judi-PrimeFaces/member/create-post.jsf";
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

    public String detete(int postID) {

        try {
            if (POST_SERVICE.deletePost(postID)) {
                return "post-management";
            } else {
                return "/Judi-PrimeFaces/index.jsf";
            }

        } catch (Exception ex) {
            Logger.getLogger(PostBean.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public String editPost(Post post) {
        this.post = post;
        this.action = "Save";
        return "/Judi-PrimeFaces/member/createpost.jsf";
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

}
