package Interfaces;

import javax.swing.*;

public abstract class View extends JFrame {
     protected Controller controller;

     public View(Controller controller){
         this.controller = controller;
     }

     protected abstract void sendRequestToController(String request, Object content);
}
