package org.hua.tracker.activity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import org.hua.tracker.adapter.UserLocationAdapter;
import org.hua.tracker.entity.UserLocationContract;
import org.hua.tracker.dao.UserLocationDBHelper;
import org.hua.tracker.entity.UserLocation;
import org.hua.tracker.databinding.ActivityResultsBinding;

import java.util.ArrayList;
import java.util.List;


public class ResultsActivity extends AppCompatActivity {

    private ActivityResultsBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.binding = ActivityResultsBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        Intent intent = getIntent();
        //Get search criteria from the intent
        String userId = intent.getStringExtra("userId");
        String timestamp = intent.getStringExtra("dt");

        UserLocationDBHelper dbHelper = new UserLocationDBHelper(getApplicationContext());
        List<UserLocation> locations = dbHelper.searchLocations(userId,timestamp); //fetch locations

        //Set custom adapter to display the results beautifully in the ListView
        this.binding.listviewUserLocationResuts.setAdapter(
                new UserLocationAdapter(getApplicationContext(),locations)
        );

    }


}
