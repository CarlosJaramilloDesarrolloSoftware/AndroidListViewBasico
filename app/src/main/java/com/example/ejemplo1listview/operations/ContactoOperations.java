package com.example.ejemplo1listview.operations;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.ejemplo1listview.database.SQLHelper;
import com.example.ejemplo1listview.models.ContactoModel;

import java.util.ArrayList;

public class ContactoOperations {
    private static final String NOMBRE_BASE_DE_DATOS = "chat.db";
    private static final String NOMBRE_TABLA = "contacto";
    private static final int VERSION_DB = 1;
    public static SQLiteDatabase database;
    public final Context context;
    private static SQLHelper helper;
    private ContactoModel model;

    public ContactoOperations(Context context) {
        this.context = context;
        helper = new SQLHelper(context, NOMBRE_BASE_DE_DATOS, null, VERSION_DB);
    }

    public void openRead() {
        database = helper.getReadableDatabase();
    }

    public void openWrite(){
        database = helper.getWritableDatabase();
    }

    public void close(){
        database.close();
    }

    public int insertModel(ContactoModel model){
        try {
            ContentValues values = new ContentValues();
            values.put("nombre", model.get_nombre());
            values.put("numero", model.get_numero());
            values.put("mensaje", model.get_mensaje());
            values.put("estado", model.get_estado());
            values.put("activo", (model.is_activo() ) ? 1 : 0);

            openWrite();
            return (int) database.insert(NOMBRE_TABLA, null, values);

        }catch (Exception e){
            String TAG = "MyActivity";
            Log.i(TAG, "e " + e);
            return -1;
        }
    }

    public int delete(int id){
        try {
            String idString = String.valueOf(id);
            String sqlWhere = "id = ?";
            String[] whereArgs = new String[] {idString};

            openWrite();
            return database.delete(NOMBRE_TABLA, sqlWhere, whereArgs);

        }catch (Exception e){
            return -1;
        }
    }

    public int updateModel(ContactoModel model){
        try {
            String idString = String.valueOf(model.get_id());
            String sqlWhere = "id = ?";
            String[] whereArgs = new String[] {idString};

            ContentValues values = new ContentValues();
            values.put("nombre", model.get_nombre());
            values.put("numero", model.get_numero());
            values.put("mensaje", model.get_mensaje());
            values.put("estado", model.get_estado());

            openWrite();
            return database.update(NOMBRE_TABLA, values, sqlWhere, whereArgs);

        }catch (Exception e){
            return -1;
        }
    }

    public ArrayList<ContactoModel> selectAll(){
        ArrayList<ContactoModel> list = new ArrayList<>();
        try{
            openRead();
            Cursor cursor = database.query(NOMBRE_TABLA, null, null, null, null, null, null);
            if (cursor.getCount() > 0){
                cursor.moveToFirst();
                do{
                    int id;
                    String nombre, numero, mensaje, estado;

                    id = cursor.getInt(cursor.getColumnIndex("id"));
                    nombre = cursor.getString(cursor.getColumnIndex("nombre"));
                    numero = cursor.getString(cursor.getColumnIndex("numero"));
                    mensaje = cursor.getString(cursor.getColumnIndex("mensaje"));
                    estado = cursor.getString(cursor.getColumnIndex("estado"));

                    model = new ContactoModel(id, nombre, numero, mensaje, estado);
                    list.add(model);

                }while (cursor.moveToNext());
            }
            return list;

        }catch (Exception e){
            return list;
        }
    }

    public ArrayList<String> selectAllString(){
        ArrayList<String> list = new ArrayList<>();
        try{
            openRead();
            Cursor cursor = database.query(NOMBRE_TABLA, null, null, null, null, null, null);
            if (cursor.getCount() > 0){
                cursor.moveToFirst();
                do{
                    int id;
                    String nombre, numero, mensaje, estado;

                    id = cursor.getInt(cursor.getColumnIndex("id"));
                    nombre = cursor.getString(cursor.getColumnIndex("nombre"));
                    numero = cursor.getString(cursor.getColumnIndex("numero"));
                    mensaje = cursor.getString(cursor.getColumnIndex("mensaje"));
                    estado = cursor.getString(cursor.getColumnIndex("estado"));

                    model = new ContactoModel(id, nombre, numero, mensaje, estado);
                    list.add(model.toString());

                }while (cursor.moveToNext());
            }
            return list;

        }catch (Exception e){
            return list;
        }
    }
}
