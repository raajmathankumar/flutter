package com.example.raajmathankumarv.roomdatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddTaskActivity extends AppCompatActivity {

    private EditText TaskNameeditText, DescriptioneditText, FinishByeditText;
    private Button mbtBackBtn;
//...............................
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task2);


        TaskNameeditText = findViewById(R.id.editTextTask);
        DescriptioneditText = findViewById(R.id.editTextDesc);
        FinishByeditText = findViewById(R.id.editTextFinishBy);
        mbtBackBtn=findViewById(R.id.btBack);

  //................

        mbtBackBtn.setOnClickListener(v -> finish());
//.............................
        findViewById(R.id.button_save).setOnClickListener(view -> {
                                                                    if(validateFields()) {
                                                                        saveTask();
                                                                    }
                                                                  } );
    }

    private void setValidationError(EditText edtTxt, String errorStr) {
        edtTxt.setError(errorStr);
        edtTxt.requestFocus();
    }

    private boolean validateFields() {
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

        return true;
    }
//............................
    private void saveTask() {

        final String sTask = TaskNameeditText.getText().toString().trim();
        final String sDesc = DescriptioneditText.getText().toString().trim();
        final String sFinishBy = FinishByeditText.getText().toString().trim();

        class SaveTask extends AsyncTask<Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... voids) {

                //creating a task
                Task task = new Task();
                task.setTask(sTask);
                task.setDesc(sDesc);
                task.setFinishBy(sFinishBy);
                task.setFinished(false);

                //adding to database
                DatabaseClient.getInstance(getApplicationContext()).getAppDatabase()
                        .taskDao()
                        .insert(task);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                finish();
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                Toast.makeText(getApplicationContext(), getString(R.string.saved_txt), Toast.LENGTH_LONG).show();
            }
        }
        SaveTask saveTask = new SaveTask();
        saveTask.execute();
    }

}
//.................................