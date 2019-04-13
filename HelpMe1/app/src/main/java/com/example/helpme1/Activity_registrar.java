package com.example.helpme1;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Activity_registrar extends AppCompatActivity {
    Button btnGuardar, btnBuscar, btnActualizar, btnBorrar;
    EditText etId, etNombre, etTelefono,etEmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);

        btnGuardar = (Button)findViewById(R.id.btnGuardar);
        btnBuscar = (Button)findViewById(R.id.btnBuscar);
        btnBorrar = (Button)findViewById(R.id.btnBorrar);
        btnActualizar  = (Button)findViewById(R.id.btnActulizar);

        etId = (EditText)findViewById(R.id.etId);
        etNombre = (EditText)findViewById(R.id.etNombre);
        etEmail=(EditText)findViewById(R.id.etEmail);
        etTelefono = (EditText)findViewById(R.id.etTelefono);

        final AdminSQLiteOpenHelper adminbd = new AdminSQLiteOpenHelper(getApplicationContext());

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SQLiteDatabase db = adminbd.getWritableDatabase();
                ContentValues valores = new ContentValues();
                valores.put(AdminSQLiteOpenHelper.DatosTabla.COLUMNA_ID,etId.getText().toString());
                valores.put(AdminSQLiteOpenHelper.DatosTabla.COLUMNA_NOMBRE, etNombre.getText().toString());
                valores.put(AdminSQLiteOpenHelper.DatosTabla.COLUMNA_EMAIL,etEmail.getText().toString());
                valores.put(AdminSQLiteOpenHelper.DatosTabla.COLUMNA_TELEFONO, etTelefono.getText().toString());

                Long IdGuardado = db.insert(AdminSQLiteOpenHelper.DatosTabla.NOMBRE_TABLA, AdminSQLiteOpenHelper.DatosTabla.COLUMNA_ID, valores);
                Toast.makeText(getApplicationContext(), "Se guardo el dato: "+IdGuardado, Toast.LENGTH_LONG).show();

            }
        });

        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SQLiteDatabase db = adminbd.getReadableDatabase();
                String[] argsel = {etId.getText().toString()};
                String[] projection = {AdminSQLiteOpenHelper.DatosTabla.COLUMNA_NOMBRE, AdminSQLiteOpenHelper.DatosTabla.COLUMNA_EMAIL,AdminSQLiteOpenHelper.DatosTabla.COLUMNA_TELEFONO};
                Cursor c = db.query(AdminSQLiteOpenHelper.DatosTabla.NOMBRE_TABLA, projection, AdminSQLiteOpenHelper.DatosTabla.COLUMNA_ID+"=?",argsel,null,null,null);

                c.moveToFirst();
                etNombre.setText(c.getString(0));
                etEmail.setText(c.getString(1));
                etTelefono.setText(c.getString(2));

            }
        });

        btnBorrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SQLiteDatabase db = adminbd.getWritableDatabase();
                String Selection = AdminSQLiteOpenHelper.DatosTabla.COLUMNA_ID+"=?";
                String[] argsel = {etId.getText().toString()};

                db.delete(AdminSQLiteOpenHelper.DatosTabla.NOMBRE_TABLA,Selection,argsel);
                Toast.makeText(getApplicationContext(), "Dato eliminado con éxito: ", Toast.LENGTH_LONG).show();

            }
        });

        btnActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = adminbd.getWritableDatabase();
                ContentValues valores = new ContentValues();
                valores.put(AdminSQLiteOpenHelper.DatosTabla.COLUMNA_NOMBRE, etNombre.getText().toString());
                valores.put(AdminSQLiteOpenHelper.DatosTabla.COLUMNA_EMAIL,etEmail.getText().toString());
                valores.put(AdminSQLiteOpenHelper.DatosTabla.COLUMNA_TELEFONO, etTelefono.getText().toString());
                String[] argsel = {etId.getText().toString()};
                String Selection = AdminSQLiteOpenHelper.DatosTabla.COLUMNA_ID+"=?";

                int count = db.update(AdminSQLiteOpenHelper.DatosTabla.NOMBRE_TABLA,valores,Selection,argsel);
                Toast.makeText(getApplicationContext(), "Dato actualizado con éxito: ", Toast.LENGTH_LONG).show();

            }
        });
    }
}

