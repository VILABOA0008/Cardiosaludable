package com.example.a16pablovc.cardiosaludable;

import android.content.Context;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

/**
 * Created by a16pablovc on 08/02/2019.
 */

public class BDAyuda extends SQLiteAssetHelper {
    private final static String DATABASE_NAME = "data.sqlite";
    private final static int DATABASE_VERSION =1;


  public BDAyuda(Context context){
      super(context,DATABASE_NAME,null,DATABASE_VERSION);
  }




}
