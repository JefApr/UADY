package Interfaces;

import view.Event;

public interface Controller {

    boolean validateEvent(Event event);

    void sendRequestToModel(Event event) throws Exception;
}
