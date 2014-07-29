/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.oldentities;

import java.util.List;

/**
 *
 * @author Admin
 */
public class Category {

    private int catID;
    private String catName;
    private boolean isActive;
    private List<Post> postList = null;

    public Category() {

    }

    public Category(int catID, String catName, boolean isActive) {
        this.catID = catID;
        this.catName = catName;
        this.isActive = isActive;
    }

    public List<Post> getPostList() {
        return postList;
    }

    public void setPostList(List<Post> postList) {
        this.postList = postList;
    }
    
    public int getCatID() {
        return catID;
    }

    public void setCatID(int catID) {
        this.catID = catID;
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Category){
            Category cat =(Category)obj;
            return this.catID == cat.getCatID();
        }
        return false;
    }
    
}
