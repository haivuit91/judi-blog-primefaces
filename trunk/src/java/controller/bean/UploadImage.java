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
import java.io.Serializable;
import java.util.StringTokenizer;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import model.dao.UserDAO;
import model.dao.service.UserDAOService;
import model.entities.User;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author cong0_000
 */
@ManagedBean
@RequestScoped
public class UploadImage implements Serializable {

    private final UserDAOService USER_SERVICE = UserDAO.getInstance();
    private static final long serialVersionUID = 1L;
    private UploadedFile file;

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public void upload() {
//        System.out.println("sssss");
        String msg;
        if (file.getSize()>0) {
            try {
                String fileName = file.getFileName();
                InputStream fin2 = file.getInputstream();

                StringTokenizer token = new StringTokenizer(fileName, ".");
                String fileNameExtension = "";
                while (token.hasMoreElements()) {
                    fileNameExtension = token.nextElement().toString();
                }

                String imagePath = "images/avatar/" + util.Support.getCurrentUser().getUserName() + "." + fileNameExtension;
                User user = USER_SERVICE.getUserByID(util.Support.getCurrentUser().getUserID());
                user.setImagePath(imagePath);
                if (USER_SERVICE.updateAvatar(user)) {
                    String realPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/");
                    String filePath = realPath.replace("\\build\\web", "\\web\\resources\\images\\avatar\\");
                    File f = new File(filePath + util.Support.getCurrentUser().getUserName() + "." + fileNameExtension);
                    // if file doesnt exists, then create it
                    if (!f.exists()) {
                        f.createNewFile();
                    }

                    try (FileOutputStream fos = new FileOutputStream(f)) {
                        int x = 0;
                        byte[] b = new byte[1024];
                        while ((x = fin2.read(b)) != -1) {
                            fos.write(b, 0, x);
                        }
                        fos.flush();
                    }
                    msg = "Avatar updated successfully!";
                } else {
                    msg = "Avatar updated failed!";
                }

            } catch (IOException e) {
                msg = "Avatar updated failed!";
                System.out.println("Exception-File Upload." + e.getMessage());
            }
        } else {
            msg = "Please select image!!";
        }
        this.addMessages(msg);
    }

    public void addMessages(String msg) {
        FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, "Message!");
        FacesContext.getCurrentInstance()
                .addMessage(null, fm);
    }

}
