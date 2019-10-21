package model;


import java.util.HashMap;
import java.util.Map;

public class VoteModel extends  Model{

    private static final String[] candidates={"Jef", "Trump", "AMLO"};


    private Map<String, Integer> votes;

    public VoteModel() {
        votes= new HashMap();
        for(String candidate : candidates){
            votes.put(candidate, 0);
        }
    }

    public String[] getCandidates(){
        return candidates;
    }


    public void addVote(String candidate){
        Integer vote= votes.get(candidate);
        vote+=1;
        votes.replace(candidate, vote);
        notifyObservers(votes);
    }

    public void substractVote(String candidate){
        Integer vote= votes.get(candidate);
        vote-=1;
        votes.replace(candidate, vote);
        notifyObservers(votes);
    }
}
