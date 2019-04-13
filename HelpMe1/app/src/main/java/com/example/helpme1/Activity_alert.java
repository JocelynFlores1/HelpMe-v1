package com.example.helpme1;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Activity_alert extends AppCompatActivity {
    Button Enviar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try{
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_alert);
            Enviar = (Button)findViewById(R.id.btnEnviar);
            if(ActivityCompat.checkSelfPermission(
                    Activity_alert.this, Manifest.permission.SEND_SMS)
                    != PackageManager.PERMISSION_GRANTED&& ActivityCompat.checkSelfPermission(
                    Activity_alert.this,Manifest
                            .permission.SEND_SMS)!= PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(Activity_alert.this,new String[]
                        { Manifest.permission.SEND_SMS,},1000);
            }else{
            };
            Enviar.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    enviarMensaje("7751898866","Hola Manuel soy HelpMe");
                }
            });

        }catch (Exception e){
            Log.e("Error1 en Activity_Alert", e.getMessage());
        }
    }
    private void enviarMensaje (String numero, String mensaje){
        try {
            SmsManager sms = SmsManager.getDefault();
            sms.sendTextMessage(numero,null,mensaje,null,null);
            Toast.makeText(getApplicationContext(), "Mensaje Enviado.", Toast.LENGTH_LONG).show();
        }
        catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Mensaje no enviado, datos incorrectos.", Toast.LENGTH_LONG).show();
            e.printStackTrace();
            Log.e("Error2 en Activity_Alert", e.getMessage());
        }
    }
}
