package org.hua.tracker.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import org.hua.tracker.dao.UserLocationDBHelper;
import org.hua.tracker.databinding.ActivityMainBinding;
import org.hua.tracker.entity.UserLocation;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.binding = ActivityMainBinding.inflate(getLayoutInflater());

        this.binding.buttonAddLocation.setOnClickListener(listener -> {
            if(validateForm()){
                if(saveLocation() != -1)
                    Toast.makeText(this,"Location Successfully saved.",Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(this,"An unexpected error occurred.",Toast.LENGTH_SHORT).show();
            }

        });

        this.binding.buttonSearchLocations.setOnClickListener(listener -> {
            Intent intent = new Intent(this,SearchActivity.class);
            startActivity(intent);
        });

        View view = binding.getRoot();
        setContentView(view);

    }

    private long saveLocation(){
        String userId = binding.editTextUserId.getText().toString();
        float longitude = Float.parseFloat(binding.editTextLongitude.getText().toString());
        float latitude = Float.parseFloat(binding.editTextLatitude.getText().toString());

        UserLocation location = new UserLocation(userId,longitude,latitude);

        UserLocationDBHelper dbHelper = new UserLocationDBHelper(getApplicationContext());
        return dbHelper.saveLocation(location);
    }

    //Validates the EditTexts. Returns false if any of the 3 fields are empty.
    private boolean validateForm(){

        boolean flag = true;

        if(this.binding.editTextUserId.getText().toString().isEmpty()){
            this.binding.editTextUserId.setError("User id cannot be empty");
            flag = false;
        }

        if(this.binding.editTextLongitude.getText().toString().isEmpty()){
            this.binding.editTextLongitude.setError("Longitude cannot be empty");
            flag = false;
        }

        if(this.binding.editTextLatitude.getText().toString().isEmpty()){
            this.binding.editTextLatitude.setError("Latitude cannot be empty");
            flag = false;
        }

        return flag;

    }

}