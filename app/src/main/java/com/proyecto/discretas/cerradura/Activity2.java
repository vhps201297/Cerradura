package com.proyecto.discretas.cerradura;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Activity2 extends AppCompatActivity {
    Button setPwd;
    Button btnJoin;
    Button setConnection;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        setConnection=findViewById(R.id.connect);
        setPwd=findViewById(R.id.btnConfigPwd);
        btnJoin=findViewById(R.id.btnIngreso);


        setPwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Activity2.this,ActivityConfigPwd.class);
                startActivity(intent);
            }
        });

        btnJoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Activity2.this,Ingresar.class);
                startActivity(intent);
            }
        });

        setConnection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Activity2.this,ListBluetooth.class);
            }
        });




    }
}
