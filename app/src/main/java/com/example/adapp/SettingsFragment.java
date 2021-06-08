package com.example.adapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.adapp.entity.Settings;
import com.example.adapp.viewModels.SettingsViewModel;
import com.google.android.material.slider.RangeSlider;

import java.util.List;


public class SettingsFragment extends Fragment implements View.OnClickListener {

    private Spinner reminderTime;
    private Spinner maxSearch;
    private Spinner gender;
    private CheckBox privateAcct;
    private Spinner minAge;
    private Spinner maxAge;
    private Button butt;
    private RangeSlider rangeSlider;

    private SettingsViewModel settingsViewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment with the ProductGrid theme
        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        butt = (Button) view.findViewById(R.id.save_settings);
        butt.setOnClickListener(this);

        reminderTime = view.findViewById(R.id.time_spinner);
        maxSearch = view.findViewById(R.id.max_dist);
        gender = view.findViewById(R.id.gender);
        privateAcct = view.findViewById(R.id.checkbox1);
        minAge = view.findViewById(R.id.minAge);
        maxAge = view.findViewById((R.id.maxAge));

        settingsViewModel = new ViewModelProvider(this).get(SettingsViewModel.class);

        // Create the observer which updates the UI.
        final Observer<List<Settings>> getSettingsObserver = settings -> { ;
            if (settings == null || settings.size() <= 0) {
                return;
            }

            Settings set = settings.get(settings.size()-1);

            if (set == null) {
                return;
            }

            reminderTime.setSelection(set.getReminderTime());
            maxSearch.setSelection(set.getMaxDist());
            gender.setSelection(getIndex(gender,set.getGender()));
            privateAcct.setChecked(set.isPrivateAcct());
            minAge.setSelection(set.getMinAge());
            maxAge.setSelection(set.getMaxAge());

        };
        settingsViewModel.loadSettings(this.getContext()).observe(this.getViewLifecycleOwner(), getSettingsObserver);
        return view;
    }

    public void saveSettings(View view) {
        Settings set = new Settings();
        set.setReminderTime(reminderTime.getSelectedItemPosition());
        set.setMaxDist(maxSearch.getSelectedItemPosition());
        set.setGender(gender.getSelectedItem().toString());
        set.setPrivateAcct(privateAcct.isChecked());
        set.setMinAge(minAge.getSelectedItemPosition());
        set.setMaxAge(maxAge.getSelectedItemPosition());

        settingsViewModel.saveSettings(this.getContext(), set);
        Toast.makeText(getActivity(), "Settings Saved", Toast.LENGTH_SHORT).show();
    }

    //private method of your class
    private int getIndex(Spinner spinner, String myString){
        for (int i=0;i<spinner.getCount();i++){
            if (spinner.getItemAtPosition(i).toString().equalsIgnoreCase(myString)){
                return i;
            }
        }
        return 0;
    }

    @Override
    public void onClick(View v) {
        saveSettings(v);
    }
}