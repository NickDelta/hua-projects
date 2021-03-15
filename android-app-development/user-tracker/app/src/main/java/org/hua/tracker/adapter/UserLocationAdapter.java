package org.hua.tracker.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.hua.tracker.R;
import org.hua.tracker.entity.UserLocation;
import org.hua.tracker.databinding.UserLocationsListItemBinding;

import java.util.List;

public class UserLocationAdapter extends ArrayAdapter<UserLocation>{

    private UserLocationsListItemBinding binding;

    private Context context;
    private List<UserLocation> locations;

    public UserLocationAdapter(@NonNull Context context, @NonNull List<UserLocation> objects) {
        super(context,0, objects);
        this.context = context;
        this.locations = objects;

        this.binding = UserLocationsListItemBinding.inflate(LayoutInflater.from(context));
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if(convertView == null){
            convertView = LayoutInflater.from(context)
                    .inflate(R.layout.user_locations_list_item,parent,false);
        }

        TextView id = (TextView) convertView.findViewById(R.id.editTextUserLocationItemId);
        id.setText(getItem(position).getId().toString());

        TextView userId = (TextView) convertView.findViewById(R.id.editTextUserLocationItemUserId);
        userId.setText(getItem(position).getUserId());

        TextView longitude = (TextView) convertView.findViewById(R.id.editTextUserLocationItemLongitude);
        longitude.setText(getItem(position).getLongitude().toString());

        TextView latitude = (TextView) convertView.findViewById(R.id.editTextUserLocationItemLatitude);
        latitude.setText(getItem(position).getLatitude().toString());

        TextView timestamp = (TextView) convertView.findViewById(R.id.editTextUserLocationItemTimestamp);
        timestamp.setText(getItem(position).getTimestamp());

        return convertView;
    }

    @Override
    public int getCount() {
        return locations.size();
    }

    @Nullable
    @Override
    public UserLocation getItem(int position) {
        return locations.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

}
