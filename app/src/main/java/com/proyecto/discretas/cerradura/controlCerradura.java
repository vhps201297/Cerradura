package com.proyecto.discretas.cerradura;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class controlCerradura extends AppCompatActivity {

    Button abrir;
    Button cerrar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_control_cerradura);

        abrir=(Button) findViewById(R.id.open);
        cerrar=(Button) findViewById(R.id.close);

        abrir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        cerrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }



}
