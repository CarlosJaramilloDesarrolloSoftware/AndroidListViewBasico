package com.example.ejemplo1listview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.ejemplo1listview.models.ContactoModel;
import com.example.ejemplo1listview.operations.ContactoOperations;
import com.google.android.material.snackbar.Snackbar;

public class RegistroActivity extends AppCompatActivity {

    private ContactoOperations contactoOperations;
    private EditText et_registro_nombre, et_registro_numero, et_registro_mensaje, et_registro_estado;
    private Button btn_registro_guardar;
    private ContactoModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        contactoOperations = new ContactoOperations(getApplicationContext());
        et_registro_estado = findViewById(R.id.et_registro_estado);
        et_registro_nombre = findViewById(R.id.et_registro_nombre);
        et_registro_mensaje = findViewById(R.id.et_registro_mensaje);
        et_registro_numero = findViewById(R.id.et_registro_numero);
        btn_registro_guardar = findViewById(R.id.btn_registro_guardar);

        btn_registro_guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre, estado, mensaje, numero;
                nombre = et_registro_nombre.getText().toString();
                numero = et_registro_numero.getText().toString();
                mensaje = et_registro_mensaje.getText().toString();
                estado = et_registro_estado.getText().toString();

                model = new ContactoModel(nombre, numero, estado);
                int a = contactoOperations.insertModel(model);
                contactoOperations.close();
                if (a > 0){
                    Intent intent = new Intent(RegistroActivity.this, MainActivity.class);
                    startActivity(intent);
                }else{
                    Snackbar.make(v, "No se guardó", Snackbar.LENGTH_LONG).show();
                }
            }
        });
    }
}
