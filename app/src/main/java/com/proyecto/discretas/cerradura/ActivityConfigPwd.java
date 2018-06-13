package com.proyecto.discretas.cerradura;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

public class ActivityConfigPwd extends AppCompatActivity{

    Button sendPwd;
    private EditText pwd1;
    private EditText pwd2;


    Handler bluetoothIn;
    final int handlerState = 0;
    private BluetoothAdapter btAdapter = null;
    private BluetoothSocket btSocket = null;
    private StringBuilder DataStringIN = new StringBuilder();
    private ConnectedThread MyConexionBT;

    // Identificador unico de servicio - SPP UUID
    private static final UUID BTMODULEUUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
    // String para la direccion MAC
    private static String address = null;
    //-------------------------------------------

    @SuppressLint("HandlerLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config_pwd);

        sendPwd=(Button) findViewById(R.id.sendPwd);
        pwd1=(EditText) findViewById(R.id.pwd1);
        pwd2=(EditText) findViewById(R.id.pwd2);

        bluetoothIn = new Handler() {
            public void handleMessage(android.os.Message msg) {
                if (msg.what == handlerState) {
                    String readMessage = (String) msg.obj;
                    DataStringIN.append(readMessage);

                    int endOfLineIndex = DataStringIN.indexOf("\n");

                    if (endOfLineIndex > 0) {
                        //String dataInPrint = DataStringIN.substring(0, endOfLineIndex);
                        DataStringIN.delete(0, DataStringIN.length());
                    }
                }
            }
        };

        btAdapter = BluetoothAdapter.getDefaultAdapter(); // get Bluetooth adapter
        VerificarEstadoBT();

        sendPwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pass1=pwd1.getText().toString();
                String pass2=pwd2.getText().toString();

                if(pass1.equals(pass2)){
                    SharedPreferences prefs =
                            getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);

                    SharedPreferences.Editor editor = prefs.edit();
                    editor.putString("pass", pass2);
                    editor.commit();








                }
                else{
                    Toast.makeText(getApplicationContext(), "las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
                }
            }
        });






    }

    private BluetoothSocket createBluetoothSocket(BluetoothDevice device) throws IOException
    {
        //crea un conexion de salida segura para el dispositivo
        //usando el servicio UUID
        return device.createRfcommSocketToServiceRecord(BTMODULEUUID);
    }




    private void VerificarEstadoBT() {

        if(btAdapter==null) {
            Toast.makeText(getBaseContext(), "El dispositivo no soporta bluetooth", Toast.LENGTH_LONG).show();
        } else {
            if (btAdapter.isEnabled()) {
            } else {
                Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(enableBtIntent, 1);
            }
        }
    }







}
