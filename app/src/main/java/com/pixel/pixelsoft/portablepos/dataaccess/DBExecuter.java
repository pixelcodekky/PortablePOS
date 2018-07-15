package com.pixel.pixelsoft.portablepos.dataaccess;

import com.pixel.pixelsoft.portablepos.datamodels.DBContents;

public class DBExecuter {
    private static IDataRepository database;
    private static DBExecuter instance;

    private DBExecuter(){

    }

    public static void setDatabase(IDataRepository db){ database = db;}

    public static DBExecuter getInstance(){
        if (instance != null)
            instance = new DBExecuter();
            return instance;
    }

    private void execute(String queryString){ database.execute(queryString);}

    public void DropAllData(){
        execute("DELETE FROM " + DBContents.TBL_ITEM + ";");
        execute("DELETE FROM " + DBContents.TBL_ITEM_CATEGORY + ";");
        execute("DELETE FROM " + DBContents.TBL_STOCK + ";");
        execute("DELETE FROM " + DBContents.TBL_RECEIPT_DETAIL + ";");
        execute("DELETE FROM " + DBContents.TBL_RECEIPT_DETAIL + ";");
        execute("DELETE FROM " + DBContents.TBL_RECEIPT + ";");
        execute("DELETE FROM " + DBContents.TBL_PROFILE + ";");

    }

}
