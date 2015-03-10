package com.bearcreekapps.taskit;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;


public class TaskListActivity extends ActionBarActivity {

    private static final String TAG = "TaskListActivity";

    ListView mTaskList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_list);

        String[] myList = {"1","2","3"};

        Task[] myTasks = new Task[3];
        myTasks[0] = new Task();
        myTasks[0].setName("Task 1");
        myTasks[1] = new Task();
        myTasks[1].setName("Task 2");
        myTasks[1].setDone(true);
        myTasks[2] = new Task();
        myTasks[2].setName("Task 3");


        mTaskList = (ListView)findViewById(R.id.task_list);
        mTaskList.setAdapter(new TaskAdapter(myTasks));
        mTaskList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d(TAG,"Position Clicked:"+position);
                Intent i = new Intent(TaskListActivity.this,TaskAdapter.class);
                Task selectedTask = (Task) parent.getAdapter().getItem(position);
                i.putExtra(TaskActivity.EXTRA,selectedTask);
                startActivity(i);
            }
        });

    }

    private class TaskAdapter extends ArrayAdapter<Task>{
        TaskAdapter(Task[] tasks){
            super(TaskListActivity.this,R.layout.task_list_row, R.id.task_item_name, tasks);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            convertView = super.getView(position, convertView, parent);
            TextView itemName =  (TextView) convertView.findViewById(R.id.task_item_name);
            CheckBox itemDone = (CheckBox) convertView.findViewById(R.id.task_item_done);
            Task currentTask = getItem(position);
            itemName.setText(currentTask.getName());
            itemDone.setChecked(currentTask.isDone());


            return convertView;
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_task_list, menu);
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
