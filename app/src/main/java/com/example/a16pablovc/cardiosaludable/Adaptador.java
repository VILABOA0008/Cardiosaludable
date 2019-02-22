package com.example.a16pablovc.cardiosaludable;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by a16pablovc on 21/02/2019.
 */
public class Adaptador extends ArrayAdapter {
    private Activity context;
    private ArrayList<String> nombre;
    private ArrayList<Integer>cantidad;
    private ArrayList<String>medida;


    public Adaptador(Activity context, ArrayList<String>nombre,   ArrayList<Integer>cantidad,ArrayList<String> medida){
        super(context, R.layout.lista ,nombre);
        if(this.cantidad!=null&&cantidad.size()>this.cantidad.size()+1){System.out.println("ERRRROROOROR");
        }
        this.context=context;
        this.nombre=nombre;
        this.cantidad=cantidad;
        this.medida=medida;
    }

public int getCantidad(int posi){
    return cantidad.get(posi);
}

    @NonNull
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater=context.getLayoutInflater();
        View fila = inflater.inflate(R.layout.lista,null);
        TextView nom=(TextView)fila.findViewById(R.id.nomb);
        EditText can=(EditText)fila.findViewById(R.id.cant);
        TextView medi=(TextView)fila.findViewById(R.id.medida);
        nom.setText(nombre.get(position));
        //System.out.println("AQUIUII"+cantidad.get(0));
        can.setText(String.valueOf(cantidad.get(position)));
       // can.setId(position);
        medi.setText(medida.get(position));

        can.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                EditText et=(EditText)v;
                System.out.println(et.getText().toString());
                try{
                cantidad.set(position,Integer.valueOf(et.getText().toString()));
            }catch (Exception e){}
            }
        });
        ImageView borrar=(ImageView)fila.findViewById(R.id.borrar);
borrar.setId(position);


        return fila;
        //return super.getView(position, convertView, parent);
    }
}