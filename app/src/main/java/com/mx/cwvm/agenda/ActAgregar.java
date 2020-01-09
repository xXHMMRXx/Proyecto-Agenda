package com.mx.cwvm.agenda;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class ActAgregar extends AppCompatActivity {

    private ArrayList<String> data0 = new ArrayList<String>();
    private final String nomArch = "xXHMMRXx-agenda.txt";

    EditText nombre, telefono, direccion, correo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_agregar);

        nombre = findViewById(R.id.txtNombre);
        telefono = findViewById(R.id.txtTelefono);
        direccion = findViewById(R.id.txtDir);
        correo = findViewById(R.id.txtCorreo);

        Button btn = (Button) findViewById(R.id.btnVolver1);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(v.getContext(), MainActivity.class);
                startActivity(intent);
                finish();

            }
        });

    }

    public void ConvertWrite(View v)
    {

        try {

            if(Verificar(this, nomArch))
            {

                if(Leer(this, nomArch))
                {

                    OutputStreamWriter Archivo = new OutputStreamWriter(this.openFileOutput(nomArch, Activity.MODE_PRIVATE));


                    data0 = getData();
                    String tmp = "";

                    for(String st : data0)
                    {

                        tmp += st;
                        Archivo.write(st+"\n");
                        //contenido.setText(tmp);
                    }

                    Datos data = new Datos(nombre.getText().toString(), telefono.getText().toString(), direccion.getText().toString(), correo.getText().toString());
                    Gson gson = new Gson();
                    String st1 = gson.toJson(data);
                    Archivo.write(st1+"\n");
                    Archivo.flush();
                    Archivo.close();


                    Toast.makeText(this, "¡Success!", Toast.LENGTH_LONG).show();


                }else
                {

                    Toast.makeText(this, "¡Error!", Toast.LENGTH_LONG).show();

                }


            }else
            {

                Datos data = new Datos(nombre.getText().toString(), telefono.getText().toString(), direccion.getText().toString(), correo.getText().toString());

                Gson gson = new Gson();
                String st = gson.toJson(data);
                //json.setText(st);

                OutputStreamWriter Archivo = new OutputStreamWriter(this.openFileOutput(nomArch, Activity.MODE_PRIVATE));

                Archivo.write(st+"\n");
                Archivo.flush();
                Archivo.close();

                Toast.makeText(this, "¡Success!", Toast.LENGTH_LONG).show();

            }


        } catch (Exception e)
        {

            e.printStackTrace();

        }

    }

    public boolean Verificar(Context context, String nomArch)
    {

        String [] files = context.fileList();
        for (String list: files)
        {

            if(list.equals(nomArch))
            {

                return true;

            }
        }

        return false;
    }

    public boolean Leer(Context context, String nomArch)
    {

        try {

            InputStreamReader file = new InputStreamReader(context.openFileInput(nomArch));
            BufferedReader br = new BufferedReader(file);
            String st = br.readLine();

            while (st != null)
            {

                data0.add(st);
                st = br.readLine();

            }

        }catch (Exception e)
        {

            e.printStackTrace();
            return false;

        }

        return true;

    }

    public ArrayList<String> getData()
    {

        return data0;

    }

}
