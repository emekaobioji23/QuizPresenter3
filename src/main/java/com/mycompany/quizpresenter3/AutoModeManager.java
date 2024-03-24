/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.quizpresenter3;

import javafx.animation.Animation;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.MouseButton;
import javafx.application.Platform;


/**
 *
 * @author emeka
 */
public class AutoModeManager extends Thread {
    final FXMLController instance;
    AutoModeManager(FXMLController instance){
        this.instance = instance;
    }
    @Override
    public void run() {
        try {
            long delayTime = Long.parseLong(instance.answerTimeDuration.getText());
            System.out.println("AutoModelManager:run:  delayTime = " + delayTime);
            if (instance.questionBankArray != null) {
                instance.questionBankArrayPointer++;
                if (((instance.autoButton.getText().equalsIgnoreCase("manual")) && (instance.questionBankArrayPointer > -1) )&& 
                        (instance.questionBankArrayPointer < instance.questionBankArray.size())) {
                    String[] mcq = (String[]) instance.questionBankArray.toArray()[instance.questionBankArrayPointer];

                    instance.updateLabel(instance.questionDisplayArea, mcq[0], instance.defaultQuestionDisplayAreaFont);
                    instance.updateLabel(instance.optionADisplayArea, mcq[1], instance.defaultOptionsDisplayAreaFont);
                    instance.updateLabel(instance.optionBDisplayArea, mcq[2], instance.defaultOptionsDisplayAreaFont);
                    instance.updateLabel(instance.optionCDisplayArea, mcq[3], instance.defaultOptionsDisplayAreaFont);
                    instance.updateLabel(instance.optionDDisplayArea, mcq[4], instance.defaultOptionsDisplayAreaFont);
                    instance.updateLabel(instance.optionEDisplayArea, mcq[5], instance.defaultOptionsDisplayAreaFont);
                    //instance.questionBankArrayPointer++;
                            /*instance.answerTimer.fireEvent(new MouseEvent(MouseEvent.MOUSE_ENTERED,
                            instance.getStage().getScene().getX(), instance.getStage().getScene().getY(), instance.getStage().getX(), instance.getStage().getY(), MouseButton.PRIMARY, 1, true, true, true, true, true, true, true, true, true, true, null));*/
                    sleep(delayTime*1000);
                    Platform.runLater(new AutoModeManager(instance));
                }
            }
        } catch (Exception e) {
            System.err.println("AutoModelManager: Exception = " + e.toString());
        }
    }

}
