package controller;

import Interfaces.Controller;
import Interfaces.Observer;
import model.VoteModel;
import view.Event;

import java.util.ArrayList;

public class VoteController implements Controller {
    private VoteModel voteModel;

    public VoteController(){
        voteModel = new VoteModel();
    }

    public void setCandidates(String[] candidates) throws Exception {
        voteModel.setCandidates(candidates);
    }

    public void addObserver(Observer observer){
        voteModel.addObserver(observer);
    }

    @Override
    public void sendRequestToModel(Event event) throws Exception {
        if(validateEvent(event)){
            switch(event.getRequest()){
                case "addVote":
                    voteModel.addVote((String) event.getContent());
                    break;
                case "substractVote":
                    voteModel.substractVote((String) event.getContent());
                    break;
                default:
                    throw new Exception("No se pudo procesar la petición");
            }
        }
    }

    public boolean validateEvent(Event event){
        boolean isValid=true;
        if(event.getContent()==null || event.getRequest().isEmpty()){
            isValid= false;
        }else{
            if(!(event.getContent() instanceof  String)){
                isValid=false;
            }
        }
        return isValid;
    }
}
