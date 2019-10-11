package model;

import java.util.HashMap;
import java.util.Map;

public class VoteModel extends  Model{
    private Map<String, Integer> votes;

    public VoteModel() {
        votes= new HashMap<>();
    }

    public void setCandidates(String[] candidates) throws Exception {
        for(String candidate : candidates){
            if(!votes.containsKey(candidate)){
                votes.put(candidate,0);
            }else{
                throw new Exception("Nombres de candidatos repetidos: "+ candidate);
            }
        }
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
