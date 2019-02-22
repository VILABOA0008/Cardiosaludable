package com.example.a16pablovc.cardiosaludable;




import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import javax.xml.transform.sax.SAXSource;

public class MainActivity extends AppCompatActivity {
    ListView lv;
    AutoCompleteTextView av;
    Adaptador adaptador=null;
    ArrayList <String> nombres=new ArrayList<>();
    ArrayList <Integer> cantidades=new ArrayList<>();
    ArrayList <String> medidas=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        av = (AutoCompleteTextView) findViewById(R.id.sv);
        lv = (ListView) findViewById(R.id.lv);

        DatabaseAcces aces = DatabaseAcces.getInstance(getApplicationContext());
        aces.open();
        ArrayList<String> a = aces.consultars();
        for (int i = 0; i < a.size(); i++) {
            System.out.println(a.get(i));

        }
        autocomplete(a);

    }
    public void on_cardiosaludable(View v){
        float sodio=0,azucar=0,grasa=0;
        String[]nombre=new String[lv.getCount()];
        for(int i=0;i<lv.getCount();i++){
            nombre[i]=lv.getItemAtPosition(i).toString();
            System.out.println(lv.getItemAtPosition(i));
        }


        DatabaseAcces aces= DatabaseAcces.getInstance(getApplicationContext());
        aces.open();

        for(int i=0;i<lv.getCount();i++) {
            cantidades.set(i, adaptador.getCantidad(i));
            String[] a = aces.datos(nombre[i]);
            String nom = nombre + "  ";
            nom += a[0];
            nom += "    " + a[1] + "    ";
            nom += a[2];
            if(aces.isfruta(nombre[i])){azucar=0;}else{
            azucar+=(Float.valueOf(a[0])*cantidades.get(i)*0.01);}
            grasa+=(Float.valueOf(a[1])*cantidades.get(i)*0.01);
            sodio+=(Float.valueOf(a[2])*cantidades.get(i)*0.01);
            Toast.makeText(this, nom, Toast.LENGTH_SHORT).show();
        }

       // if(azucar!=null) {
            Intent i=new Intent(this,Main2Activity.class);

            i.putExtra("azu", azucar);
            i.putExtra("gra", grasa);
            i.putExtra("sod", sodio);
        System.out.println(nombre+"   eses    "+azucar);
            startActivity(i);
      //  }else{Toast.makeText(this,"error",Toast.LENGTH_SHORT).show();}
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
            cantidades.add(100);
            if(adaptador!=null){
            for(int i=0;i<cantidades.size();i++) {
                cantidades.set(i, adaptador.getCantidad(i));
            }
            }
            if (liquido==false){medidas.add("g");}else{medidas.add("ml");}


            adaptador= new Adaptador(this, nombres,cantidades,  medidas);
            lv.setAdapter(adaptador);
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    System.out.println("DHAKSDNA");
                }
            });

        }
        av.setText("");
    }
    public void on_borrar(View v){
        int p=v.getId();
        medidas.remove(p);
        nombres.remove(p);
        cantidades.remove(p);
        for(int i=0;i<cantidades.size();i++) {
            cantidades.set(i, adaptador.getCantidad(i));
        }
        adaptador= new Adaptador(this, nombres,cantidades,medidas);
        lv.setAdapter(adaptador);




    }

}