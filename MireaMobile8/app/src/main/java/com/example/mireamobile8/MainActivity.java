package com.example.mireamobile8;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.work.Data;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "SeqParExample";

    private TextView txtResult;
    private Button btnSequential, btnParallel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtResult = findViewById(R.id.txtResult);
        btnSequential = findViewById(R.id.btnSequential);
        btnParallel = findViewById(R.id.btnParallel);

        btnSequential.setOnClickListener(v -> {
            runSequentialTasks();
        });
        btnParallel.setOnClickListener(v -> {
            runParallelTasks();
        });
    }

    private void addResult(String message) {
        runOnUiThread(() -> txtResult.append(message + "\n"));
        Log.d(TAG, message);
    }

    private void clearResult() {
        runOnUiThread(() -> txtResult.setText(""));
    }

    private OneTimeWorkRequest createWorkRequest(String taskName, long duration) {
        Data inputData = new Data.Builder()
                .putString(GenericWorker.KEY_TASK_NAME, taskName)
                .putLong(GenericWorker.KEY_SLEEP_DURATION, duration)
                .putString(GenericWorker.KEY_LOG_TAG, TAG)
                .build();

        return new OneTimeWorkRequest.Builder(GenericWorker.class)
                .setInputData(inputData)
                .build();
    }

    private void observeWork(OneTimeWorkRequest workRequest, String taskName) {
        WorkManager.getInstance(this)
                .getWorkInfoByIdLiveData(workRequest.getId())
                .observe(this, workInfo -> {
                    if (workInfo != null) {
                        WorkInfo.State state = workInfo.getState();

                        if (state == WorkInfo.State.RUNNING) {
                            String startTime = DateTimeFormatter.ofPattern("HH:mm:ss")
                                    .format(LocalTime.now());

                            addResult(taskName + " началась в: " + startTime);
                        } else if (state == WorkInfo.State.SUCCEEDED) {
                            String result = workInfo.getOutputData().getString("result");
                            addResult(taskName + " закончилась в: " + result);
                        } else if (state == WorkInfo.State.FAILED) {
                            addResult(taskName + " провалена!");
                        }
                    }
                });
    }

    private void runSequentialTasks() {
        clearResult();
        addResult("Запуск задач последовательно. . .");

        OneTimeWorkRequest task1 = createWorkRequest("Task 1", 3000);
        OneTimeWorkRequest task2 = createWorkRequest("Task 2", 2000);
        OneTimeWorkRequest task3 = createWorkRequest("Task 3", 1000);

        WorkManager.getInstance(this)
                .beginWith(task1)
                .then(task2)
                .then(task3)
                .enqueue();

        observeWork(task1, "Task 1");
        observeWork(task2, "Task 2");
        observeWork(task3, "Task 3");
    }

    private void runParallelTasks() {
        clearResult();
        addResult("Запуск задач параллельно");

        OneTimeWorkRequest task1 = createWorkRequest("Task 1", 3000);
        OneTimeWorkRequest task2 = createWorkRequest("Task 2", 2000);

        WorkManager.getInstance(this)
                .enqueue(Arrays.asList(task1, task2));

        observeWork(task1, "Task 1");
        observeWork(task2, "Task 2");
    }
}