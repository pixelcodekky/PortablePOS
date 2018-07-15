package com.pixel.pixelsoft.portablepos.datacontrollers;

import android.content.ContentValues;

import com.pixel.pixelsoft.portablepos.dataaccess.IDataRepository;
import com.pixel.pixelsoft.portablepos.datamodels.DBContents;
import com.pixel.pixelsoft.portablepos.datamodels.ItemModel;

import java.util.ArrayList;
import java.util.List;

public class ItemController {

    private IDataRepository db;

    public int addItem(ItemModel itemModel) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("name",itemModel.name);
        contentValues.put("category_id",itemModel.category_id);
        contentValues.put("unit_price",itemModel.unit_price);
        contentValues.put("barcode",itemModel.barcode);
        contentValues.put("status",itemModel.status);
        contentValues.put("photo",itemModel.photo);
        contentValues.put("isActive",itemModel.isActive);
        try {
            int i = db.insert(DBContents.TBL_ITEM.toString(), contentValues);
            return i;
        }catch (Exception e){
            return 0;
        }
    }

    public boolean editItem(ItemModel itemModel) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("name",itemModel.name);
        contentValues.put("category_id",itemModel.category_id);
        contentValues.put("unit_price",itemModel.unit_price);
        contentValues.put("barcode",itemModel.barcode);
        contentValues.put("status",itemModel.status);
        contentValues.put("photo",itemModel.photo);
        contentValues.put("isActive",itemModel.isActive);
        return db.update(DBContents.TBL_ITEM.toString(),contentValues);
    }

    public boolean deleteItem(int Id) {
        return db.delete(DBContents.TBL_ITEM.toString(),Id);
    }

    private List<ItemModel> toItemList(List<Object> objectList) {
        List<ItemModel> itemModelList = new ArrayList<ItemModel>();
        for (Object object: objectList){
            ContentValues contentValues =(ContentValues) object;
            itemModelList.add(new ItemModel(
                    contentValues.getAsInteger("_id"),
                    contentValues.getAsString("category_id"),
                    contentValues.getAsString("name"),
                    contentValues.getAsDouble("unit_price"),
                    contentValues.getAsString("barcode"),
                    contentValues.getAsInteger("status"),
                    contentValues.getAsString("photo"),
                    contentValues.getAsInteger("isActive"))
            );
        }
        return itemModelList;
    }

    public List<ItemModel> getAllItem(String querycondition){
        String sql = "SELECT * FROM " + DBContents.TBL_ITEM.toString() + querycondition;
        List<ItemModel> list = toItemList(db.select(sql));
        return list;
    }

    public List<ItemModel> searchByItemName(String criteria){
        String sql = " WHERE name LIKE '%" + criteria + "%' OR barcode '%" + criteria + "'% ;";
        List<ItemModel> list  = getAllItem(sql);
        return list;
    }

    public List<ItemModel> searchByID(int id){
        String sql = " WHERE _id =" +id+" ";
        List<ItemModel> list = getAllItem(sql);
        return list;
    }

    public boolean isDuplicateName(String criteria){
        String sql = " WERE name ='" + criteria + "'";
        List<ItemModel> list = getAllItem(sql);
        return list !=null ? true :false;
    }


}
