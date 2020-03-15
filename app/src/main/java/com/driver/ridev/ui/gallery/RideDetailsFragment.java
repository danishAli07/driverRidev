package com.driver.ridev.ui.gallery;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.driver.ridev.R;

public class RideDetailsFragment extends Fragment {

    private RideDetailsViewModel rideDetailsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        rideDetailsViewModel = ViewModelProviders.of(this).get(RideDetailsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_ride_details, container, false);
        final TextView textView = root.findViewById(R.id.text_gallery);
        rideDetailsViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}