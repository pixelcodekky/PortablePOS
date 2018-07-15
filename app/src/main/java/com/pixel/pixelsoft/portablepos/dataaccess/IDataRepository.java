package com.pixel.pixelsoft.portablepos.dataaccess;

import java.util.List;

public interface IDataRepository {

    public int insert(String tableName,Object content);

    boolean update(String tableName,Object content);

    boolean delete(String tableName,int id);

    List<Object> select(String queryString);

    boolean execute(String queryString);


}
