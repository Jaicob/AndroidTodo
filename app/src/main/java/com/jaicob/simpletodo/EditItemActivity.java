package com.jaicob.simpletodo;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

public class EditItemActivity extends AppCompatActivity {

    private String itemText;
    private int position;
    private EditText etUpdateItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        itemText = getIntent().getStringExtra("itemText");
        position = getIntent().getIntExtra("position", 0);
        etUpdateItem = (EditText) findViewById(R.id.etUpdateItem);
        etUpdateItem.setHint(itemText);
    }

    // fired when the save button is clicked
    public void onSave(View view) {
        Intent data = new Intent();
        data.putExtra("updatedItemText",etUpdateItem.getText().toString());
        data.putExtra("position",position);
        setResult(RESULT_OK,data);
        finish();
    }

}
