package com.pixel.pixelsoft.portablepos.datamodels;

public class ItemCategoryModel {

    public int _id;
    public String name;
    public int isActive;

    public ItemCategoryModel(int id,String name,int isActive){
        this._id = id;
        this.name = name;
        this.isActive = isActive;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIsActive() {
        return isActive;
    }

    public void setIsActive(int isActive) {
        this.isActive = isActive;
    }


}
