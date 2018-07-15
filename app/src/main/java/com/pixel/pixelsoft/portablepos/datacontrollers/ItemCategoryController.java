package com.pixel.pixelsoft.portablepos.datacontrollers;

import android.content.ContentValues;

import com.pixel.pixelsoft.portablepos.dataaccess.IDataRepository;
import com.pixel.pixelsoft.portablepos.datamodels.DBContents;
import com.pixel.pixelsoft.portablepos.datamodels.ItemCategoryModel;

import java.util.ArrayList;
import java.util.List;

public class ItemCategoryController {

    private IDataRepository db;

    public int addcategory(ItemCategoryModel itemCategoryModel){
        ContentValues contentValues = new ContentValues();
        contentValues.put("name",itemCategoryModel.name);
        contentValues.put("isActive",itemCategoryModel.isActive);
        int i = db.insert(DBContents.TBL_ITEM_CATEGORY.toString(),contentValues);
        return i;
    }

    public boolean editCategory(ItemCategoryModel itemCategoryModel){
        ContentValues contentValues = new ContentValues();
        contentValues.put("_id",itemCategoryModel._id);
        contentValues.put("name",itemCategoryModel.name);
        contentValues.put("isActive",itemCategoryModel.isActive);
        return db.update(DBContents.TBL_ITEM_CATEGORY.toString(),contentValues);
    }

    public boolean deleteCategory(int id){
        return db.delete(DBContents.TBL_ITEM_CATEGORY.toString(),id);
    }

    private List<ItemCategoryModel> toItemCatList(List<Object> objectList){
        List<ItemCategoryModel> itemCategoryModelList = new ArrayList<ItemCategoryModel>();
        for(Object object:objectList){
            ContentValues contentValues =(ContentValues) object;
            itemCategoryModelList.add(new ItemCategoryModel(
                    contentValues.getAsInteger("_id"),
                    contentValues.getAsString("name"),
                    contentValues.getAsShort("isActive")
            ));
        }
        return itemCategoryModelList;
    }

    private List<ItemCategoryModel> getAllItem(String querycondition){
        String sql = "SELECT * FROM " + DBContents.TBL_ITEM.toString() + querycondition;
        List<ItemCategoryModel> listall = toItemCatList(db.select(sql));
        return listall;
    }

    public List<ItemCategoryModel> searchByItemName(String criteria){
        String sql = " WHERE name LIKE '%" + criteria + "%';";
        List<ItemCategoryModel> listby  = getAllItem(sql);
        return listby;
    }

    public List<ItemCategoryModel> searchByID(int id){
        String sql = " WHERE _id =" +id+" ";
        List<ItemCategoryModel> listid = getAllItem(sql);
        return listid;
    }

    public boolean isDuplicateName(String criteria){
        String sql = " WERE name ='" + criteria + "'";
        List<ItemCategoryModel> list = getAllItem(sql);
        return list !=null ? true :false;
    }

}
