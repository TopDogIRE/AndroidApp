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

    public void getMatches(Consumer<ArrayList<Match>> responseCallback) {
        matchesModel.getMatches(
                (QuerySnapshot querySnapshot) -> {
                    if (querySnapshot != null) {
                        ArrayList<Match> matches = new ArrayList<>();
                        for (DocumentSnapshot matchesSnap : querySnapshot.getDocuments()) {
                            Match m = matchesSnap.toObject(Match.class);
                            assert m != null;
                            m.uid = matchesSnap.getId();
                            matches.add(m);
                        }
                        responseCallback.accept(matches);
                    }
                },
                (databaseError -> System.out.println("Error reading Todo Items: " + databaseError))
        );
    }

    public void updateMatch(Match m) {
        matchesModel.updateMatchById(m);
    }

    public void clear() {
        matchesModel.clear();
    }
}