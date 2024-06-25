package edu.gatech.seclass.jobcompare6300;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class EditCurrentJobDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_current_job_details);
    }

    public void handleClickBackToMainMenu(View view) {
        startActivity(new Intent(EditCurrentJobDetails.this, MainActivity.class));
    }
}