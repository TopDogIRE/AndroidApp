package com.example.adapp;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.adapp.model.Match;
import com.example.adapp.viewModels.MatchesViewModel;
import com.example.adapp.viewModels.SettingsViewModel;

import java.util.ArrayList;
import java.util.List;


public class MatchesFragment extends Fragment {

    public ArrayList matchesList = new ArrayList();
    private MatchesViewModel viewModel = new MatchesViewModel();
    private RecyclerView recyclerView;

    LocationManager locationManager;
    Location userLoc;
    MatchesCardRecyclerViewAdapter adapter;
    SettingsViewModel settingsViewModel;
    Float maxDist = 16093.44f;  // 10 miles at first but is set by settings in the getDistanceSettings function
    MatchesCardRecyclerViewAdapter adapter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(getArguments() != null){
            matchesList = getArguments().getParcelableArrayList(Constants.KEY_MATCHES);
        }
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_matches, container, false);

        locationManager = (LocationManager) this.getActivity().getSystemService(Context.LOCATION_SERVICE);
        userLoc = new Location(LocationManager.GPS_PROVIDER);

        int largePadding = getResources().getDimensionPixelSize(R.dimen.matches_grid_spacing);
        int smallPadding = getResources().getDimensionPixelSize(R.dimen.matches_grid_spacing_small);
        recyclerView = view.findViewById(R.id.matches_txt);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2, GridLayoutManager.VERTICAL, false));
        adapter = new MatchesCardRecyclerViewAdapter(matchesList);
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new MatchesGridDecoration(largePadding, smallPadding));

        gpsUpdates(view);
        getDistanceSetting();
        getMatches();


        return view;

        getMatches();

        return view;
    }

    public void getMatches(){
        viewModel.getMatches(
                (ArrayList<Match> matches) -> {
                    adapter.setMatchesList(matches);
                    adapter.notifyDataSetChanged();
                });

    }


    @Override
    public void onPause() {
        viewModel.clear();
        super.onPause();
    }

    private boolean checkLocation() {
        if(!isLocationEnabled()) {
            showAlert();
        }
        return isLocationEnabled();
    }

    private boolean isLocationEnabled() {
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
    }

    private void showAlert() {
        final AlertDialog.Builder dialog = new AlertDialog.Builder(this.getContext());
        dialog.setTitle(R.string.enable_location)
                .setMessage(getString(R.string.location_message))
                .setPositiveButton(R.string.location_settings, (paramDialogInterface, paramInt) -> {
                    Intent myIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                    startActivity(myIntent);
                })
                .setNegativeButton(R.string.location_cancel, (paramDialogInterface, paramInt) -> {
                });
        dialog.show();
    }

    public void gpsUpdates(View view) {
        if(!checkLocation()) {
            return;
        }
        if (ActivityCompat.checkSelfPermission(this.getContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED ||
                ActivityCompat.checkSelfPermission(this.getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 60 * 1000, 10, locationListener);
            Toast.makeText(this.getContext(), R.string.provider_started_running, Toast.LENGTH_LONG).show();
        }

    }


    private final LocationListener locationListener = new LocationListener() {
        public void onLocationChanged(Location location) {
            userLoc.setLatitude(location.getLatitude());
            userLoc.setLongitude(location.getLongitude());
            getMatches();
        }

        @Override
        public void onStatusChanged(String s, int i, Bundle bundle) {}

        @Override
        public void onProviderEnabled(String s) {}

        @Override
        public void onProviderDisabled(String s) {}
    };

    public void getMatches(){
        viewModel.getMatches(
                (ArrayList<Match> matches) -> {
                    ArrayList<Match> tooFar = new ArrayList<>();
                    float[] dist = new float[1];
                    for( Match match : matches){
                        Location.distanceBetween(Double.parseDouble(match.lat), Double.parseDouble(match.longitude), userLoc.getLatitude(), userLoc.getLongitude(), dist);
                        if(dist[0] > maxDist ){
                            tooFar.add(match);
                        }
                    }
                    matches.removeAll(tooFar);
                    adapter.setMatchesList(matches);
                    adapter.notifyDataSetChanged();
                });
    }

    public void getDistanceSetting() {
        settingsViewModel = new ViewModelProvider(this).get(SettingsViewModel.class);
        Float five = new Float(8046.72f);
        Float ten = new Float(16093.44f);
        Float twentyfive = new Float(40233.6f);
        Float fifty = new Float(80467.2f);
        Float oneHundred = new Float(160934.4f);

        final Observer<List<com.example.adapp.entity.Settings>> getSettingsObserver = (new Observer<List<com.example.adapp.entity.Settings>>() {
            @Override
            public void onChanged(List<com.example.adapp.entity.Settings> settings) {
                ;
                if (settings == null || settings.size() <= 0) {
                    return;
                }

                com.example.adapp.entity.Settings set = settings.get(settings.size() - 1);

                if (set == null) {
                    return;
                }
                Float dist = 0f;
                switch (set.getMaxDist()) {
                    case 0:
                        dist = five;
                        break;
                    case 1:
                        dist = ten;
                        break;
                    case 2:
                        dist = twentyfive;
                        break;
                    case 3:
                        dist = fifty;
                        break;
                    case 4:
                        dist = oneHundred;
                        break;
                }
                setDistance(dist);
            }
        });
        settingsViewModel.loadSettings(this.getContext()).observe(this.getViewLifecycleOwner(),getSettingsObserver);
    }

    public void setDistance(Float f){
        this.maxDist = f;
    }
}