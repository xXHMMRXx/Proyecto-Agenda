package com.mx.cwvm.agenda;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
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

public class ActEliminar extends AppCompatActivity {

    private final String nomArch = "xXHMMRXx-agenda.txt";
    private ArrayList<String> data0 = new ArrayList<String>();


    EditText nombre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_eliminar);

        Button btn = (Button) findViewById(R.id.btnVolver2);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(v.getContext(), MainActivity.class);
                startActivity(intent);
                finish();

            }
        });

        nombre = findViewById(R.id.txtEliminar);

    }

    public void Eliminar(View v)
    {

        try {

            Gson gson = new Gson();

            InputStreamReader file = new InputStreamReader(this.openFileInput(nomArch));
            BufferedReader br = new BufferedReader(file);
            String st = br.readLine();
            String t = " ";
            String j = nombre.getText().toString();

            while (st != null)
            {

                Datos dto = gson.fromJson(st, Datos.class);
                t = dto.getNombre();
                if(t.equalsIgnoreCase(j))
                {

                    //Toast.makeText(this, "¡Name!", Toast.LENGTH_LONG).show();
                    st = br.readLine();

                }else
                {

                    Datos d = new Datos(dto.getNombre(), dto.getTelefono(), dto.getDireccion(), dto.getCorreo());
                    Gson g = new Gson();
                    String st7 = g.toJson(d);
                    data0.add(st7);
                    st = br.readLine();

                }

            }

            EliminarArch();

            String tmp = "";

            OutputStreamWriter Archivo2 = new OutputStreamWriter(this.openFileOutput(nomArch, Activity.MODE_PRIVATE));

            for(String st2 : data0)
            {

                tmp += st2;
                Archivo2.write(tmp+"\n");

                //contenido.setText(tmp);
            }

            Archivo2.flush();
            Archivo2.close();
            Toast.makeText(this, "¡Successful Elimination!", Toast.LENGTH_LONG).show();


        }catch (Exception e)
        {

            e.printStackTrace();

        }
    }

    public void EliminarArch()
    {

        try {

            OutputStreamWriter Archivo = new OutputStreamWriter(this.openFileOutput(nomArch, Activity.MODE_PRIVATE));
            Archivo.write("");
            Archivo.flush();
            Archivo.close();

        }catch (Exception e)
        {

            e.printStackTrace();

        }

    }

}
