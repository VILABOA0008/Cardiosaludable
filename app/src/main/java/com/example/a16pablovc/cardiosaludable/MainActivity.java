package com.example.a16pablovc.cardiosaludable;




import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView lv;
    AutoCompleteTextView av;
    ArrayList <String> nombres=new ArrayList<>();
    ArrayList <String> cantidades=new ArrayList<>();
    ArrayList <String> medidas=new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        av=(AutoCompleteTextView)findViewById(R.id.sv);
        lv=(ListView)findViewById(R.id.lv);

        DatabaseAcces aces= DatabaseAcces.getInstance(getApplicationContext());
        aces.open();
        ArrayList<String >a=   aces.consultars();
        for(int i=0;i<a.size();i++){
            System.out.println(a.get(i));

        }
        autocomplete(a);


    }
    public void on_cardiosaludable(View v){
        String nombre=av.getText().toString();
        System.out.println(nombre);
        DatabaseAcces aces= DatabaseAcces.getInstance(getApplicationContext());
        aces.open();
        String []a=   aces.datos(nombre);
        String nom=nombre+"  ";
        nom+=a[0];
        nom+="    "+a[1]+"    ";
        nom+=a[2];
        Toast.makeText(this,nom,Toast.LENGTH_SHORT).show();

        Intent i=new Intent(this,Main2Activity.class);
        if(a[0]!=null) {
            i.putExtra("nom", nombre);
            i.putExtra("azu", a[0]);
            i.putExtra("gra", a[1]);
            i.putExtra("sod", a[2]);

            startActivity(i);
        }else{Toast.makeText(this,"error",Toast.LENGTH_SHORT).show();;}
    }




    public void autocomplete( ArrayList<String >a){
        ArrayList<String >arr=a;
        final ArrayAdapter<String> array=new ArrayAdapter<>(this,android.R.layout.simple_dropdown_item_1line,arr);
        av.setAdapter(array);
        av.setThreshold(1);

    }
    public void on_añadir(View v){

        if(av.getText().toString().isEmpty()){
         Toast.makeText(this,"Selecione un alimento",Toast.LENGTH_SHORT).show();
        }else {
            DatabaseAcces aces= DatabaseAcces.getInstance(getApplicationContext());
            boolean liquido=aces.isliquid(av.getText().toString());
            nombres.add(av.getText().toString());
            if (liquido==false){medidas.add("g");}else{medidas.add("ml");}


            Adaptador adaptador = new Adaptador(this, nombres,  medidas);
            lv.setAdapter(adaptador);
        }
    }

}