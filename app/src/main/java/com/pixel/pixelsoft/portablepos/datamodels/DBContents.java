package com.pixel.pixelsoft.portablepos.datamodels;

public enum DBContents {

    DATABASE("pixelsoft.portablepos.db"),
    TBL_ITEM("_item"),
    TBL_ITEM_CATEGORY("_item_category"),
    TBL_STOCK("_item_stock"),
    TBL_RECEIPT("_receipt"),
    TBL_RECEIPT_DETAIL("_receipt_detail"),
    TBL_RECEIPT_DETAIL_TEMP("_receipt_detail_temp"),
    TBL_PROFILE("_profile");

    private String name;

    private DBContents(String name) {
        this.name = name;
    }
 }
