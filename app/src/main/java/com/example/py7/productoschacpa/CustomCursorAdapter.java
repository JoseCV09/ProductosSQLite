package com.example.py7.productoschacpa;

import android.annotation.TargetApi;
import android.content.Context;
import android.database.Cursor;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.example.py7.notesapp.R;

public class CustomCursorAdapter extends CursorAdapter {

    private LayoutInflater layoutInflater;

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public CustomCursorAdapter(Context context, Cursor c, int flags) {
        super(context, c, flags);
        layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        View v = layoutInflater.inflate(R.layout.row_notes, viewGroup, false);
        MyHolder holder = new MyHolder();
        holder.ListID = (TextView)v.findViewById(R.id.listID);
        holder.ListNombre = (TextView)v.findViewById(R.id.listNombre);
        holder.ListPrecio = (TextView)v.findViewById(R.id.listPrecio);
        holder.ListCantidad = (TextView)v.findViewById(R.id.listCantidad);
        holder.ListCreated = (TextView)v.findViewById(R.id.listCreated);
        v.setTag(holder);
        return v;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        MyHolder holder = (MyHolder)view.getTag();
        holder.ListID.setText(cursor.getString(cursor.getColumnIndex(DBHelper.row_id)));
        holder.ListNombre.setText(cursor.getString(cursor.getColumnIndex(DBHelper.row_nombre)));
        holder.ListPrecio.setText(cursor.getString(cursor.getColumnIndex(DBHelper.row_precio)));
        holder.ListCantidad.setText(cursor.getString(cursor.getColumnIndex(DBHelper.row_cantidad)));
        holder.ListCreated.setText(cursor.getString(cursor.getColumnIndex(DBHelper.row_created)));
    }

    class MyHolder{
        TextView ListID;
        TextView ListNombre;
        TextView ListPrecio;
        TextView ListCantidad;
        TextView ListCreated;
    }
}
