package com.example.py7.productoschacpa;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.py7.notesapp.R;

public class EditActivity extends AppCompatActivity {

    DBHelper helper;
    EditText TxtNombre, TxtPrecio, TxtCantidad;
    Button BtnEditar, BtnEliminar;
    long id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        helper = new DBHelper(this);

        id = getIntent().getLongExtra(DBHelper.row_id, 0);

        TxtNombre = (EditText)findViewById(R.id.txNombre_Edit);
        TxtPrecio = (EditText)findViewById(R.id.txPrecio_Edit);
        TxtCantidad = (EditText)findViewById(R.id.txCantidad_Edit);
        getData();

        BtnEditar = findViewById(R.id.btn_editar);
        BtnEliminar = findViewById(R.id.btn_eliminar);

        BtnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String nomproducto = TxtNombre.getText().toString().trim();
                String precio = TxtPrecio.getText().toString().trim();
                String cantidad = TxtCantidad.getText().toString().trim();


                if (nomproducto.equals("") || precio.equals("") || cantidad.equals("")){
                    Toast.makeText(EditActivity.this, "Error al Editar!", Toast.LENGTH_SHORT).show();
                }else {
                    ContentValues values = new ContentValues();
                    values.put(DBHelper.row_nombre, nomproducto);
                    values.put(DBHelper.row_precio, precio);
                    values.put(DBHelper.row_cantidad, cantidad);
                    helper.updateData(values, id);
                    Toast.makeText(EditActivity.this, "Registro Modificado!", Toast.LENGTH_SHORT).show();
                    finish();
                }

            }
        });

        BtnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(EditActivity.this);
                builder.setMessage("Estas seguro de eliminar?");
                builder.setCancelable(true);
                builder.setPositiveButton("Eliminar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        helper.deleteData(id);
                        Toast.makeText(EditActivity.this, "Eliminado Correctamente!", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });
                builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();

            }
        });
    }

    private void getData() {
        Cursor cursor = helper.oneData(id);
        if(cursor.moveToFirst()){
            String nomproducto = cursor.getString(cursor.getColumnIndex(DBHelper.row_nombre));
            String precio = cursor.getString(cursor.getColumnIndex(DBHelper.row_precio));
            String cantidad = cursor.getString(cursor.getColumnIndex(DBHelper.row_cantidad));

            TxtNombre.setText(nomproducto);
            TxtPrecio.setText(precio);
            TxtCantidad.setText(cantidad);
        }
    }


}
