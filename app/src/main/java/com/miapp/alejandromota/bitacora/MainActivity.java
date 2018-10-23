package com.miapp.alejandromota.bitacora;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    private EditText et1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et1=(EditText)findViewById(R.id.editMulti);
        String [] archivos = fileList();

        if(ArchivoExiste(archivos, "bitacora.txt")){
            try{
                InputStreamReader archivo = new InputStreamReader(openFileInput("bitacora.txt"));
                BufferedReader br = new BufferedReader(archivo);
                String linea= br.readLine();
                String bitacoraCompleta="";

                while(linea != null){
                    bitacoraCompleta = bitacoraCompleta + linea + "\n";
                    linea = br.readLine();
                }
                br.close();
                archivo.close();
                et1.setText(bitacoraCompleta);

            }catch (IOException e){

            }

        }
    }

    private boolean ArchivoExiste(String cadena [],String nombreArchivo ){

        for(int i=0 ; i< cadena.length; i++)
            if(nombreArchivo.equals(cadena[i]))
                return true;
                return false;
    }

    //metodo para guardar

    public void Guardar(View view) {
        try {
            OutputStreamWriter archivo = new OutputStreamWriter(openFileOutput("bitacora.txt",Activity.MODE_PRIVATE));
            archivo.write(et1.getText().toString());
            archivo.flush();
            archivo.close();
        }catch (IOException e){

        }
        Toast.makeText(this,"BITACORA GUARDADA", Toast.LENGTH_LONG).show();



    }
}

