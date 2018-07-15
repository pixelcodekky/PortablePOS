package com.pixel.pixelsoft.portablepos.datamodels;

public class ItemModel {
    public int _id;
    public String name;
    public String category_id;
    public double unit_price;
    public String barcode;
    public int status;
    public String photo;
    public int isActive;

    public ItemModel(int id,String category_id,String name,double unit_price,String barcode,int status,String photo,int  isActive){
        this._id=id;
        this.category_id = category_id;
        this.name = name;
        this.unit_price = unit_price;
        this.barcode = barcode;
        this.status = status;
        this.photo = photo;
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

    public String getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }

    public double getUnit_price() {
        return unit_price;
    }

    public void setUnit_price(double unit_price) {
        this.unit_price = unit_price;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public int getIsActive() {
        return isActive;
    }

    public void setIsActive(int isActive) {
        this.isActive = isActive;
    }
}
