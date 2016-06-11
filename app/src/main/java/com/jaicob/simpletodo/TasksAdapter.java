package com.jaicob.simpletodo;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.jaicob.simpletodo.models.Task;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Jaicob on 6/8/16.
 */
public class TasksAdapter extends ArrayAdapter<Task>{

    private final int PAST_DUE_COLOR = Color.parseColor("#01579B"); //Red
    private final int SIX_HOURS_COLOR = Color.parseColor("#0288D1"); //Deep Orange
    private final int ONE_DAY_COLOR = Color.parseColor("#29B6F6"); //Amber
    private final int WEEK_COLOR = Color.parseColor("#81D4FA"); //Green
    private final int WEEK_PLUS_COLOR = Color.parseColor("#E1F5FE"); //Green

    private final int SIX_HOURS = 21600000;
    private final int ONE_DAY = 86400000;
    private final int WEEK = 86400000*6;

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

        TextView tvRecur =  (TextView) convertView.findViewById(R.id.tvRecur);
        if (task.recurring) {
            tvRecur.setText("Recurs Weekly");
        } else {
            tvRecur.setText("");
        }

        TextView tvDuedate = (TextView) convertView.findViewById(R.id.tvDuedate);
        SimpleDateFormat format = new SimpleDateFormat("MMM dd yyyy");
        String dueDate = format.format(task.dueDate);
        tvDuedate.setText("Due: " + (CharSequence) dueDate);
        double timeLeft = (task.dueDate.getTime() - (new Date().getTime()));

        if (timeLeft < 0) {
            convertView.setBackgroundColor(PAST_DUE_COLOR);
            tvDescription.setTextColor(Color.parseColor("#FFFFFF"));
            tvDuedate.setTextColor(Color.parseColor("#FFFFFF"));
        }
        if (timeLeft > 0 && timeLeft < SIX_HOURS) {
            convertView.setBackgroundColor(SIX_HOURS_COLOR);
            tvDescription.setTextColor(Color.parseColor("#FFFFFF"));
            tvDuedate.setTextColor(Color.parseColor("#FFFFFF"));
        }
        if (timeLeft > SIX_HOURS && timeLeft < ONE_DAY) {
            convertView.setBackgroundColor(ONE_DAY_COLOR);
            tvDescription.setTextColor(Color.parseColor("#FFFFFF"));
            tvDuedate.setTextColor(Color.parseColor("#FFFFFF"));
        }
        if (timeLeft > ONE_DAY && timeLeft < WEEK) {
            convertView.setBackgroundColor(WEEK_COLOR);
            tvDescription.setTextColor(Color.parseColor("#727272"));
            tvDuedate.setTextColor(Color.parseColor("#727272"));
        }
        if (timeLeft > WEEK) {
            convertView.setBackgroundColor(WEEK_PLUS_COLOR);
            tvDescription.setTextColor(Color.parseColor("#727272"));
            tvDuedate.setTextColor(Color.parseColor("#727272"));
        }

        return convertView;
    }
}
