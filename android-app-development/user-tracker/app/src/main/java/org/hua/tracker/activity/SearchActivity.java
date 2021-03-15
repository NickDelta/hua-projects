package org.hua.tracker.activity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import org.hua.tracker.entity.UserLocationContract;
import org.hua.tracker.dao.UserLocationDBHelper;
import org.hua.tracker.databinding.ActivitySearchBinding;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {

    private ActivitySearchBinding binding;

    public SearchActivity() {
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.binding = ActivitySearchBinding.inflate(getLayoutInflater());

        loadTimestamps();

        this.binding.buttonSearch.setOnClickListener(listener -> {
            Intent intent = new Intent(this, ResultsActivity.class);
            //Transfer search criteria to ResultsActivity
            intent.putExtra("userId",binding.editTextSearchUserId.getText().toString());
            intent.putExtra("dt",binding.spinnerSearchTimestamp.getSelectedItem().toString());
            startActivity(intent);
        });

        View view = binding.getRoot();
        setContentView(view);
    }

    private void loadTimestamps(){

        UserLocationDBHelper dbHelper = new UserLocationDBHelper(getApplicationContext());
        List<String> timestamps = dbHelper.getTimestamps(); //Fetch all timestamps

        Spinner timestampSpinner = this.binding.spinnerSearchTimestamp;
        //Set an adapter for the spinner. I used the simple one here.
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, timestamps);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        timestampSpinner.setAdapter(adapter);

    }


}
