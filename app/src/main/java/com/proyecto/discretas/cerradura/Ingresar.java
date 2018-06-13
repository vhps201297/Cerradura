package com.proyecto.discretas.cerradura;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Ingresar extends AppCompatActivity {
    EditText pass;
    Button sendPwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingresar);

        sendPwd=(Button) findViewById(R.id.btnSendPwd);
        pass=(EditText) findViewById(R.id.pwd);

        sendPwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


    }
}
