package com.driver.ridev.ui.share;

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

public class ReferAndEarnFragment extends Fragment {

    private ReferAndEarnViewModel referAndEarnViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        referAndEarnViewModel =
                ViewModelProviders.of(this).get(ReferAndEarnViewModel.class);
        View root = inflater.inflate(R.layout.fragment_refer_earn, container, false);
        final TextView textView = root.findViewById(R.id.text_share);
        referAndEarnViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}