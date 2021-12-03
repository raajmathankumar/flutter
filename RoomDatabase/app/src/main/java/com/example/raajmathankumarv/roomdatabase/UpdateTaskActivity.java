package com.example.raajmathankumarv.roomdatabase;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import static com.example.raajmathankumarv.roomdatabase.Constants.INTENT_TASK;
import static com.example.raajmathankumarv.roomdatabase.Constants.UPDATED;

public class UpdateTaskActivity extends AppCompatActivity {

    private EditText TaskNameeditText, DescriptioneditText, FinishByeditText;
    private CheckBox checkBoxFinished;
    private Button mbtBackbt;

//..............................

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_task);



        mbtBackbt=findViewById(R.id.btBack);

        TaskNameeditText = findViewById(R.id.editTextTask);
        DescriptioneditText = findViewById(R.id.editTextDesc);
        FinishByeditText = findViewById(R.id.editTextFinishBy);

        checkBoxFinished = findViewById(R.id.checkBoxFinished);
//......................
        mbtBackbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { finish();
            }
        });
        //................................


        final Task task;
        task = (Task) getIntent().getSerializableExtra(INTENT_TASK);

        loadTask(task);
//.....................................
        findViewById(R.id.button_update).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), UPDATED, Toast.LENGTH_LONG).show();
                updateTask(task);
            }
        });
        //............................

        findViewById(R.id.button_delete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(UpdateTaskActivity.this);
                builder.setTitle(R.string.Are_you_sure);
                //..............
                builder.setPositiveButton(R.string.Yes, (dialogInterface, i) -> deleteTask(task));
                //...........
                builder.setNegativeButton(R.string.No, (dialogInterface, i) -> {

                });

                AlertDialog ad = builder.create();
                ad.show();
            }
        });
    }
//............................................
    private void loadTask(Task task) {
        TaskNameeditText.setText(task.getTask());
        DescriptioneditText.setText(task.getDesc());
        FinishByeditText.setText(task.getFinishBy());
        checkBoxFinished.setChecked(task.isFinished());
    }

//...................................................
    private void deleteTask(final Task task) {
        class DeleteTask extends AsyncTask<Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... voids) {
                DatabaseClient.getInstance(getApplicationContext()).getAppDatabase()
                        .taskDao()
                        .delete(task);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                Toast.makeText(getApplicationContext(), R.string.Deleted, Toast.LENGTH_LONG).show();
                finish();
                startActivity(new Intent(UpdateTaskActivity.this, MainActivity.class));
            }
        }

        DeleteTask dt = new DeleteTask();
        dt.execute();

    }

    private void setValidationError(EditText edtTxt, String errorStr) {
        edtTxt.setError(errorStr);
        edtTxt.requestFocus();
    }
//..........................................
    public boolean updateTask(final Task task) {
        final String sTask = TaskNameeditText.getText().toString().trim();
        final String sDesc = DescriptioneditText.getText().toString().trim();
        final String sFinishBy = FinishByeditText.getText().toString().trim();

        if (sTask.isEmpty()) {
            setValidationError(TaskNameeditText,getString(R.string.Task_Required));
            return false;
        }

        if (sDesc.isEmpty()) {
            setValidationError(DescriptioneditText,getString(R.string.Finish_by_required));
            return false;
        }

        if (sFinishBy.isEmpty()) {
            setValidationError(FinishByeditText,getString(R.string.Finish_by_required));
            return false;
        }

        class UpdateTask extends AsyncTask<Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... voids) {
                task.setTask(sTask);
                task.setDesc(sDesc);
                task.setFinishBy(sFinishBy);
                task.setFinished(checkBoxFinished.isChecked());
                DatabaseClient.getInstance(getApplicationContext()).getAppDatabase()
                        .taskDao()
                        .update(task);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                Toast.makeText(getApplicationContext(), R.string.Updated, Toast.LENGTH_LONG).show();
                finish();
                startActivity(new Intent(UpdateTaskActivity.this, MainActivity.class));
            }
        }

        UpdateTask updateTask = new UpdateTask();
        updateTask.execute();
        return true;
    }
}
//............................................