package com.example.ejemplo1listview;

import android.content.Intent;
import android.os.Bundle;

import com.example.ejemplo1listview.models.ContactoModel;
import com.example.ejemplo1listview.operations.ContactoOperations;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private FloatingActionButton fab_main_nuevo;
    private FloatingActionButton fab_main_foreach;
    private ListView lv_main_contactos;
    private ContactoOperations contactoOperations;
    private ArrayList<ContactoModel> list;
    private ArrayList<String> listString;
    private ArrayAdapter<String> itemsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fab_main_nuevo = findViewById(R.id.fab_main_nuevo);
        fab_main_foreach = findViewById(R.id.fab_main_foreach);
        lv_main_contactos = findViewById(R.id.lv_main_contactos);
        contactoOperations = new ContactoOperations(getApplicationContext());

        listString = contactoOperations.selectAllString();
        contactoOperations.close();

        /* Comentarear para que se trabaje con la DB */
        listString = new ArrayList<>();
        listString.add("Pablo");
        listString.add("German");
        listString.add("Juan");
        /* Comentarear para que se trabaje con la DB */

        itemsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listString);
        lv_main_contactos.setAdapter(itemsAdapter);

        /*
        // Metodo para ir a un activity al darle clic
        lv_main_contactos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String seleccionado = listString.get(position);
                Intent detalle = new Intent(MainActivity.this, DetalleActivity.class);
                detalle.putExtra("item", seleccionado);
                startActivity(detalle);
            }
        });
        */

        /*
        // Metodo para ir mostrar un Snackbar al darle clic
        lv_main_contactos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String seleccionado = listString.get(position);
                Snackbar.make(view, seleccionado, Snackbar.LENGTH_LONG).show();
            }
        });
        */


        lv_main_contactos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String texto = listString.get(position);
                Toast.makeText(getApplicationContext(), texto, Toast.LENGTH_LONG).show();
            }
        });


        fab_main_nuevo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show(); */
                Intent intent = new Intent(MainActivity.this, RegistroActivity.class);
                startActivity(intent);
            }
        });

        fab_main_foreach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ForeachActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
