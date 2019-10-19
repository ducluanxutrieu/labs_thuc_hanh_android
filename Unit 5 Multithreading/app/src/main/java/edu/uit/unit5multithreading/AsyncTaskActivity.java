package edu.uit.unit5multithreading;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class AsyncTaskActivity extends AppCompatActivity {
    private AppCompatButton btnQuickJob, btnSlowJob;
    private TextView tvStatus;
    private SlowTask slowTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async_task);

        findViewByIds();

        //init showTask
        slowTask = new SlowTask(AsyncTaskActivity.this, tvStatus);

        btnQuickJob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SimpleDateFormat sdf = new SimpleDateFormat(getString(R.string.date_format), Locale.US);

                tvStatus.setText(sdf.format(new Date()));
            }
        });

        btnSlowJob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                slowTask.execute();
            }
        });
    }

    private void findViewByIds() {
        btnQuickJob = findViewById(R.id.btn_quick_job);
        btnSlowJob = findViewById(R.id.btn_slow_job);
        tvStatus = findViewById(R.id.tv_status);
    }


}
