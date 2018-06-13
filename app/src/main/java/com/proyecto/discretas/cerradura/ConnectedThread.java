package com.proyecto.discretas.cerradura;

import android.bluetooth.BluetoothSocket;
import android.os.Handler;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class ConnectedThread extends Thread{
    //Crea la clase que permite crear el evento de conexion

        private final InputStream mmInStream;
        private final OutputStream mmOutStream;
        Handler bluetoothIn;
        private final int handlerState;

        public ConnectedThread(BluetoothSocket socket,Handler bluetoothIn,int handlerState) {
            InputStream tmpIn = null;
            OutputStream tmpOut = null;
            this.handlerState=handlerState;
            this.bluetoothIn=bluetoothIn;
            try
            {
                tmpIn = socket.getInputStream();
                tmpOut = socket.getOutputStream();
            } catch (IOException e) {
                e.printStackTrace();
            }
            mmInStream = tmpIn;
            mmOutStream = tmpOut;
        }
        public void run() {
            byte[] buffer = new byte[256];
            int bytes;

            // Se mantiene en modo escucha para determinar el ingreso de datos
            while (true) {
                try {
                    bytes = mmInStream.read(buffer);
                    String readMessage = new String(buffer, 0, bytes);
                    // Envia los datos obtenidos hacia el evento via handler
                    bluetoothIn.obtainMessage(handlerState, bytes, -1, readMessage).sendToTarget();
                } catch (IOException e) {
                    break;
                }
            }
        }
        //Envio de trama
        public void write(String input) {
            try {
                mmOutStream.write(input.getBytes());
            }
            catch (IOException e)
            {
                //si no es posible enviar datos se cierra la conexión
                //Toast.makeText(getApplicationContext(), "La Conexión fallo", Toast.LENGTH_LONG).show();
                e.printStackTrace();
            }
        }



}
