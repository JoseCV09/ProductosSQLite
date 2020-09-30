package com.example.py7.productoschacpa;

import android.content.ContentValues;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.py7.notesapp.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class AddActivity extends AppCompatActivity {

    DBHelper helper;
    EditText txtNomProducto, txtPrecio, txtCantidad;
    Button btn_registrar_producto;

    long id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        helper = new DBHelper(this);

        id = getIntent().getLongExtra(DBHelper.row_id, 0);

        txtNomProducto = (EditText)findViewById(R.id.txNombreProducto);
        txtPrecio = (EditText)findViewById(R.id.txPrecio);
        txtCantidad = (EditText)findViewById(R.id.txCantidad);

        btn_registrar_producto = findViewById(R.id.button_registrar);

        btn_registrar_producto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nomproducto = txtNomProducto.getText().toString().trim();
                String precio = txtPrecio.getText().toString().trim();
                String cantidad = txtCantidad.getText().toString().trim();

                //Get Date
                Calendar calendar = Calendar.getInstance();
                SimpleDateFormat simpleDate = new SimpleDateFormat("MMM dd, yyyy");
                String created = simpleDate.format(calendar.getTime());


                //Create Condition if Title and Detail is empty
                if (nomproducto.equals("") || precio.equals("") || cantidad.equals("")){
                    Toast.makeText(AddActivity.this, "Ingrese los datos requeridos!", Toast.LENGTH_SHORT).show();
                }else{
                    ContentValues values = new ContentValues();
                    values.put(DBHelper.row_nombre, nomproducto);
                    values.put(DBHelper.row_precio, Double.parseDouble(precio));
                    values.put(DBHelper.row_cantidad, Integer.parseInt(cantidad));
                    values.put(DBHelper.row_created, created);
                    helper.insertData(values);
                    Toast.makeText(AddActivity.this, "Registro Guardado Correctamente!", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });
    }


}
