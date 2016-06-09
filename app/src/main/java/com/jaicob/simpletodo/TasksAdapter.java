package com.jaicob.simpletodo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.jaicob.simpletodo.models.Task;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Created by Jaicob on 6/8/16.
 */
public class TasksAdapter extends ArrayAdapter<Task>{
    public TasksAdapter(Context context, ArrayList<Task> tasks) {
        super(context, 0, tasks);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Task task = getItem(position);
        // Check if an exsiting view is being reused, otherwise inflate the vew
        if(convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_task, parent, false);
        }
        
        TextView tvDescription = (TextView) convertView.findViewById(R.id.tvDescription);
        tvDescription.setText(task.description);

        TextView tvDuedate = (TextView) convertView.findViewById(R.id.tvDuedate);
        SimpleDateFormat format = new SimpleDateFormat("MMM dd yyyy");
        String dueDate = format.format(task.dueDate);
        tvDuedate.setText("Due: " + (CharSequence) dueDate);

        return convertView;
    }
}
