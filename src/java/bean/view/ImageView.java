/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bean.view;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author monkeydluffy
 */
@ManagedBean(name = "imageView")
@RequestScoped
public class ImageView {
    

    private List<String> images;
    private List<String> judiPhotos;
    private List<String> photo_JUDI;
   
    @PostConstruct
    public void init() {
        images = new ArrayList<String>();
        for (int i = 1; i <= 5; i++) {
            images.add("slide0" + i + ".jpg");
        }
        judiPhotos = new ArrayList<String>();
        for (int i = 1; i < 16; i++) {
            judiPhotos.add("judi" + i + ".jpg");
        }
        photo_JUDI = new ArrayList<String>();
        for(int i=1; i<=19 ; i++){
            photo_JUDI.add(i+".jpg");
        }

    }

    public List<String> getPhoto_JUDI() {
        return photo_JUDI;
    }

    public List<String> getImages() {
        return images;
    }

    public List<String> getJudiPhotos() {
        return judiPhotos;
    }

}