package com.theironyard.androidcontacts;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemLongClickListener {


    EditText name;
    EditText phone;
    Button addButton;
    ListView list;
    ArrayAdapter<String>items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = (EditText) findViewById(R.id.name);
        phone = (EditText) findViewById(R.id.phone);
        addButton = (Button) findViewById(R.id.addButton);
        list = (ListView) findViewById(R.id.list);

//        addButton.setOnClickListener(this);
        items = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
        list.setAdapter(items);

        list.setOnItemLongClickListener(this);

    }



    @Override
    public void onClick(View v) {
        String item = (name.getText().toString() +" ("+ phone.getText().toString()+")");
        items.add(item);
        name.setText("");
        phone.setText("");
        Toast.makeText(this, "Contact Added", Toast.LENGTH_LONG).show();

    }



    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        final String item = items.getItem(position);
        AlertDialog.Builder removeAlert = new AlertDialog.Builder(this);
        removeAlert.setMessage("You are about to remove a contact. Are you sure that you would like to proceed?")
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                })
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        items.remove(item);
                    }
                })
                .create();
        removeAlert.show();

        return true;
    }
}
