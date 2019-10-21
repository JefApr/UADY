package controller;

import Interfaces.Observer;
import model.Model;
import model.VoteModel;
import view.Event;

public class Controller implements Observer {
    private Model model;

    public Controller(Model model){
        this.model = model;
    }

    public void setCandidates(String[] candidates) throws Exception {
      //  model.setCandidates(candidates); CONFIGURAR CAMBIOS CREACION DE VISTA A MODELO
    }

    public void addObserver(Observer observer){
       model.addObserver(observer);
    }

    public void sendRequestToModel(Event event) throws Exception {
        if(validateEvent(event)){
            switch(event.getRequest()){
                case "addVote":
                    ((VoteModel)model).addVote((String) event.getContent());
                    break;
                case "substractVote":
                    ((VoteModel)model).substractVote((String) event.getContent());
                    break;
                default:
                    throw new Exception("No se pudo procesar la petición");
            }
        }
    }

    private boolean validateEvent(Event event){
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

    @Override
    public void update(Object o) {

    }
}
