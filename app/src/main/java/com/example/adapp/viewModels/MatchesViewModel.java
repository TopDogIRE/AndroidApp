package com.example.adapp.viewModels;

import com.example.adapp.dataModels.MatchesModel;
import com.example.adapp.model.Match;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.function.Consumer;

public class MatchesViewModel {

    private MatchesModel matchesModel;

    public MatchesViewModel() {
        matchesModel = new MatchesModel();
    }


    public void getMatches(Consumer<ArrayList<Match>> resultCallback) {
        matchesModel.getMatches(
                (QuerySnapshot querySnapShot) -> {
                    if (querySnapShot != null) {
                        ArrayList<Match> myMatches = new ArrayList<>();
                        for (DocumentSnapshot matchSnapshot : querySnapShot.getDocuments()) {
                            Match matches = matchSnapshot.toObject(Match.class);
                            assert matches != null;
                            matches.uid = matchSnapshot.getId();
                            myMatches.add(matches);
                        }
                        resultCallback.accept(myMatches);
                    }
                },
                (databaseError -> System.out.println("An error occurred reading matches" + databaseError))
        );
    }

    public void updateMatch(Match m) {
        matchesModel.updateMatchById(m);
    }

    public void clear() {
        matchesModel.clear();
    }
}