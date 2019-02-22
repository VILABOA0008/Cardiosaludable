package com.example.a16pablovc.cardiosaludable;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by mi on 17/02/2019.
 */

public class DatabaseAcces {
    private SQLiteOpenHelper open;
    private SQLiteDatabase bd;
    private static DatabaseAcces aces;
    Cursor c = null;
    String tabla="\"Cereais e derivados\"";
    String[]tablas={"\"Cereais e derivados\"","bebidas","\"Froita\"","  Legumes","\"Verduras e hortalizas\"","\"Sementes e froitos secos\"",  "Ovos","Lacteos"};
    private DatabaseAcces(Context context){
this.open=new BDAyuda(context);

    }

    public static DatabaseAcces getInstance(Context context){
    if(aces==null) {
        aces = new DatabaseAcces(context);
    }
        return aces;
    }

    public void open(){
        this.bd=open.getWritableDatabase();}

public void close(){
if(bd!=null){

    this.bd.close();
}

}

    public boolean isliquid(String nombre){

        Cursor c = bd.rawQuery("SELECT * FROM bebidas WHERE ALIMENTO= \""+nombre+"\""  ,null);
        System.out.println(c.getCount());
        boolean a;
        if(c.getCount()>0){a=true;}else{a=false;}
        return a;
    }

    public boolean isfruta(String nombre){

        c = bd.rawQuery("SELECT * FROM Froita WHERE ALIMENTO= \""+nombre+"\""  ,null);
        System.out.println(c.getCount());
        boolean a;
        if(c.getCount()>0){a=true;}else{a=false;}
        return a;
    }
/*
    public String[] datos(String nombre){

        c = bd.rawQuery("SELECT * FROM "+tabla+" WHERE ALIMENTO= \""+nombre+"\""  ,null);
        String[] a=new String[3];
        if (c.moveToFirst()) {
            a[0] = c.getString(1);
            a[1] = c.getString(2);
            a[2] = c.getString(3);
        }
        return a;
    }

    public ArrayList consultars(){

        Cursor c = bd.rawQuery("SELECT * FROM "+tabla+" ", null);
        ArrayList <String> a=new ArrayList<>();
        if (c.moveToFirst()) {
            while(!c.isAfterLast()){
                String n = c.getString(0);
                  a.add(n);
                c.moveToNext();

            }}
    return a;
    }
*/
    public ArrayList consultars(){
        ArrayList <String> a=new ArrayList<>();
    for(int i=0;i<tablas.length;i++){
        Cursor c = bd.rawQuery("SELECT * FROM "+tablas[i]+" ", null);

        if (c.moveToFirst()) {
            while(!c.isAfterLast()){
                String n = c.getString(0);
                a.add(n);
                c.moveToNext();

            }}}
        return a;
    }


    public String[] datos(String nombre){
        String[]  a=new String[3];
        for(int i=0;i<tablas.length;i++){
        c = bd.rawQuery("SELECT * FROM "+tablas[i]+" WHERE ALIMENTO= \""+nombre+"\""  ,null);

        if (c.moveToFirst()) {
            try{
            a[0]=String.format("%.2f",Float.valueOf(c.getString(1)));
            }catch(java.lang.NumberFormatException e){  a[0]="0";}
            try{
                a[1]=String.format("%.2f",Float.valueOf(c.getString(2)));
            }catch(java.lang.NumberFormatException e){  a[1]="0";}
            try{
                a[2]=String.format("%.2f",Float.valueOf(c.getString(3)));
            }catch(java.lang.NumberFormatException e){  a[2]="0";}
       /*     a[0] = c.getString(1);
            a[1] = c.getString(2);
            a[2] = c.getString(3);*/
        }   }
        return a;
    }

}
