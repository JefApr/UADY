package view;

import Interfaces.Observer;
import controller.Controller;
import view.Event;

import javax.swing.*;

public abstract class View implements Observer {

    protected JFrame GUI;
    protected JPanel container;


    private final Controller controller;

    public abstract void startGUI(String title, String[] items);

    public View(Controller controller) {
        this.controller = controller;
    }

    public final void sendRequestToController(String header, Object body) {
        try {
            controller.sendRequestToModel(new Event(header, body));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
