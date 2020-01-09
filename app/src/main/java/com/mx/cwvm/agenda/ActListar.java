package com.mx.cwvm.agenda;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class ActListar extends AppCompatActivity {

    private final String nomArch = "xXHMMRXx-agenda.txt";
    private ArrayList<String> data0 = new ArrayList<String>();


    TextView contenido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_listar);

        contenido = findViewById(R.id.tvContenido);

        Button btn = (Button) findViewById(R.id.btnVolver3);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(v.getContext(), MainActivity.class);
                startActivity(intent);
                finish();

            }
        });

        try {

            Gson gson = new Gson();

            InputStreamReader file = new InputStreamReader(this.openFileInput(nomArch));
            BufferedReader br = new BufferedReader(file);
            String st = br.readLine();

            while (st != null)
            {

                Datos dto = gson.fromJson(st, Datos.class);
                data0.add("Nombre: "+dto.getNombre()+" Teléfono: "+dto.getTelefono()+" Correo: "+dto.getCorreo()+" Dirección: "+dto.getDireccion()+"\n");
                st = br.readLine();

            }

            String tmp = "";

            for(String st2 : data0)
            {

                tmp += st2;
                contenido.setText(tmp);
            }


        }catch (Exception e)
        {

            e.printStackTrace();

        }


    }


}
