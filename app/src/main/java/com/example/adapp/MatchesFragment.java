package com.example.adapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.adapp.model.Match;
import com.example.adapp.viewModels.MatchesViewModel;

import java.util.ArrayList;


public class MatchesFragment extends Fragment {

    public ArrayList matchesList = new ArrayList();
    private MatchesViewModel viewModel = new MatchesViewModel();
    private RecyclerView recyclerView;

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
        int largePadding = getResources().getDimensionPixelSize(R.dimen.matches_grid_spacing);
        int smallPadding = getResources().getDimensionPixelSize(R.dimen.matches_grid_spacing_small);
        recyclerView = view.findViewById(R.id.matches_txt);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2, GridLayoutManager.VERTICAL, false));
        MatchesCardRecyclerViewAdapter adapter = new MatchesCardRecyclerViewAdapter(matchesList);
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new MatchesGridDecoration(largePadding, smallPadding));

        viewModel.getMatches(
                (ArrayList<Match> matches) -> {
                    adapter.setMatchesList(matches);
                    adapter.notifyDataSetChanged();
                });

        return view;
    }

    @Override
    public void onPause() {
        viewModel.clear();
        super.onPause();
    }
}