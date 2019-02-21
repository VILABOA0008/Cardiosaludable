package com.example.a16pablovc.cardiosaludable;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by a16pablovc on 21/02/2019.
 */

public class Adaptador extends ArrayAdapter {
    private Activity context;
    private ArrayList<String>nombre;

    private ArrayList<String>medida;


    public Adaptador(Activity context, ArrayList<String>nombre,  ArrayList<String> medida){
        super(context, R.layout.lista ,nombre);
        this.context=context;
        this.nombre=nombre;

        this.medida=medida;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        System.out.println("asdfdsfdsf");
        LayoutInflater inflater=context.getLayoutInflater();
        View fila = inflater.inflate(R.layout.lista,null);
        TextView nom=(TextView)fila.findViewById(R.id.nomb);

        TextView medi=(TextView)fila.findViewById(R.id.medida);
        nom.setText(nombre.get(position));

        medi.setText(medida.get(position));



        return fila;
        //return super.getView(position, convertView, parent);
    }
}
