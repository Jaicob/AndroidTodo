package com.jaicob.simpletodo;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.activeandroid.query.Select;
import com.jaicob.simpletodo.models.Task;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOExceptionWithCause;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import layout.ItemDialogFragment;

public class MainActivity extends AppCompatActivity implements ItemDialogFragment.OnFragmentInteractionListener {

    private ArrayList<Task> items;
    private TasksAdapter itemsAdapter;
    private ListView lvItems;
    private final int REQUEST_CODE = 20;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Load data from database

        // My Content
        lvItems = (ListView) findViewById(R.id.lvItems);
        readItems();
        itemsAdapter = new TasksAdapter(this, items);
        List<Task> queryResult = new Select().from(Task.class).execute();
        itemsAdapter.addAll(queryResult);
        lvItems.setAdapter(itemsAdapter);
        setupClickListener();
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

    // Fired when pressing btnAddItem. Adds item to todo list
    public void onAddItem(View view) {
        FragmentManager manager = getSupportFragmentManager();
        ItemDialogFragment itemDialog = ItemDialogFragment.newInstance("Enter Task Description",-1);
        itemDialog.show(manager, "fragment_item");
    }

    // Click listener used for editing items in the lvItems list
    private void setupClickListener() {
        lvItems.setOnItemClickListener(
            new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    String itemText = items.get(position).toString();
                    launchEditView(itemText, position);
                }
            }
        );
    }

    // Intent to launch the edit view for item when clicked
    public void launchEditView(String itemText, int position) {
        FragmentManager manager = getSupportFragmentManager();
        ItemDialogFragment itemDialog = ItemDialogFragment.newInstance(itemText,position);
        itemDialog.show(manager, "fragment_item");
    }

    // Read saved items from a file
    private void readItems() {
//        File filesDir = getFilesDir();
//        File todoFile = new File(filesDir, "todo.txt");
//        try {
//            items = new ArrayList<String>(FileUtils.readLines(todoFile));
//        } catch (IOException e) {
//            items = new ArrayList<String>();
//        }
        items = new ArrayList<>();
//        Task t = new Task("Sample Task 1",new Date(),false);
//        t.save();
    }

    // Write items to be saved to a file
    private void writeItems() {
        File filesDir = getFilesDir();
        File todoFile = new File(filesDir, "todo.txt");
        try {
            FileUtils.writeLines(todoFile,items);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onFragmentUpdate(String description, int position) {
        System.out.println("Passed data to be saved\nDescription: " + description);
        if (position == -1) {
//            itemsAdapter.add(description);
            Task newTask = new Task(description, new Date(),false);
        } else {
//            items.set(position,description);
            itemsAdapter.notifyDataSetChanged();
        }
        writeItems();
    }

    @Override
    public void onFragmentDelete(int position) {
        if (position != -1) {
            items.remove(position);
            itemsAdapter.notifyDataSetChanged();
            writeItems();
        }
    }
}
