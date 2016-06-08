package com.jaicob.simpletodo;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;
import com.activeandroid.query.Select;
import com.jaicob.simpletodo.models.Task;
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
        lvItems = (ListView) findViewById(R.id.lvItems);
        items = new ArrayList<>();
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
        Task newTask = new Task("Enter a description", new Date(), false);
        newTask.save();
        ItemDialogFragment itemDialog = ItemDialogFragment.newInstance(newTask.getId(),-1);
        itemDialog.show(manager, "fragment_item");
    }

    // Click listener used for editing items in the lvItems list
    private void setupClickListener() {
        lvItems.setOnItemClickListener(
            new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Long taskId = items.get(position).getId();
                    launchEditView(taskId, position);
                }
            }
        );
    }

    // Intent to launch the edit view for item when clicked
    public void launchEditView(Long taskId, int position) {
        FragmentManager manager = getSupportFragmentManager();
        ItemDialogFragment itemDialog = ItemDialogFragment.newInstance(taskId,position);
        itemDialog.show(manager, "fragment_item");
    }

    @Override
    public void onFragmentUpdate(Long taskId, int position) {
        if (position == -1) {
            Task newTask =  Task.load(Task.class,taskId);
            itemsAdapter.add(newTask);
        }
        itemsAdapter.notifyDataSetChanged();
    }

    @Override
    public void onFragmentDelete(int position) {
        if (position != -1) {
            items.remove(position);
            itemsAdapter.notifyDataSetChanged();
        }
    }
}
