package com.driver.ridev.ui.slideshow;

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

public class EarnFragment extends Fragment {

    private EarnViewModel earnViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        earnViewModel =
                ViewModelProviders.of(this).get(EarnViewModel.class);
        View root = inflater.inflate(R.layout.fragment_earn, container, false);
        final TextView textView = root.findViewById(R.id.text_slideshow);
        earnViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}