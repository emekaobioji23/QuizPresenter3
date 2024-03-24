/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.quizpresenter3;

import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
/**
 *
 * @author emeka
 */
public class AnswerTimerManager extends Thread {
    final FXMLController instance;
    AnswerTimerManager(FXMLController instance){
        this.instance = instance;
    }
    @Override
    public void run() {
        instance.answerTimer.fireEvent(new MouseEvent(MouseEvent.MOUSE_ENTERED,
                            instance.getStage().getScene().getX(), instance.getStage().getScene().getY(), instance.getStage().getX(), instance.getStage().getY(), MouseButton.PRIMARY, 1, true, true, true, true, true, true, true, true, true, true, null));
    }
}
