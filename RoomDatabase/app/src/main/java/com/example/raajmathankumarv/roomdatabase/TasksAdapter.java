package com.example.raajmathankumarv.roomdatabase;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import static com.example.raajmathankumarv.roomdatabase.Constants.INTENT_TASK;

public class TasksAdapter extends RecyclerView.Adapter<TasksAdapter.TasksViewHolder>  {
    private final Context mCtx;
    private final List<Task> taskList;
//..........................
    public TasksAdapter(Context mCtx, List<Task> taskList) {
        this.mCtx = mCtx;
        this.taskList = taskList;
    }
//.............................
    @NonNull
    @Override
    public TasksViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.recyclerview_tasks, parent, false);
        return new TasksViewHolder(view);
    }
    //......................................
    private void updateTask(Task task) {
        {
            class UpdateTask extends AsyncTask<Void, Void, Void> {

                @Override
                protected Void doInBackground(Void... voids) {
                    DatabaseClient.getInstance(mCtx.getApplicationContext()).getAppDatabase()
                            .taskDao()
                            .update(task);
                    return null;
                }
                @Override
                protected void onPostExecute(Void aVoid) {
                    super.onPostExecute(aVoid);
                }
            }
            UpdateTask ut = new UpdateTask();
            ut.execute();
        }
    }
//............................

    @Override
    public void onBindViewHolder(TasksViewHolder holder, int position) {
        Task task = taskList.get(position);
        holder.TaskNametextView.setText(task.getTask());
        holder.DescriptiontextView.setText(task.getDesc());
        holder.FinishBytextView.setText(task.getFinishBy());


//........................................
        holder.mcheckBoxFinished.setChecked(task.isFinished());

        if (task.isFinished()){
            holder.textViewStatus.setText(R.string.Completed_tv);
        }
        else {
            holder.textViewStatus.setText(R.string.Not_Completed_tv);
        }
    }

//...........................
    @Override
    public int getItemCount()
    {
        return taskList.size();
    }
//..............

    class TasksViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView textViewStatus, TaskNametextView, DescriptiontextView, FinishBytextView;
        private CheckBox mcheckBoxFinished;
//............................

        public TasksViewHolder(View itemView) {
            super(itemView);

            textViewStatus = itemView.findViewById(R.id.textViewStatus);
            TaskNametextView = itemView.findViewById(R.id.textViewTask);
            DescriptiontextView = itemView.findViewById(R.id.textViewDesc);
            FinishBytextView = itemView.findViewById(R.id.textViewFinishBy);
           mcheckBoxFinished = itemView.findViewById(R.id.checkBoxFinished);
//.......................................

            mcheckBoxFinished.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    Task task = taskList.get(getAdapterPosition());
                    task.setFinished(mcheckBoxFinished.isChecked());
                    updateTask(task);

                    if(task.isFinished()){
                        mcheckBoxFinished.setChecked(true);
                        textViewStatus.setText(R.string.Completed_tv);
                    }else{
                        mcheckBoxFinished.setChecked(false);
                        textViewStatus.setText(R.string.Not_Completed_tv);
                    }
                }
            });
            itemView.setOnClickListener(this);
        }

//...............................................
        @RequiresApi(api = Build.VERSION_CODES.P)
        @Override
        public void onClick(View view) {
                Constants.goToUpdateTask(mCtx,taskList.get(getAdapterPosition()));
        }

    }

}

