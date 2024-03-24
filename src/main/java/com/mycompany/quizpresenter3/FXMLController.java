package com.mycompany.quizpresenter3;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.scene.Node;
import javafx.scene.control.Tooltip;

import javafx.stage.FileChooser;
import java.io.File;
import javafx.stage.Stage;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.ArrayList;
import javafx.animation.Animation;
import javafx.application.Platform;
import org.apache.poi.ss.usermodel.CellType;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.KeyValue;
import javafx.util.Duration;
import javafx.event.EventHandler;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;
import javafx.scene.layout.AnchorPane;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Labeled;
import javafx.scene.text.Font;
import javafx.scene.control.ToggleButton;

public class FXMLController implements Initializable {
    ArrayList<String[]> questionBankArray;
    int questionBankArrayPointer = -1;
    Font defaultQuestionDisplayAreaFont;
    Font defaultOptionsDisplayAreaFont;

    
    
    private Stage stage;
    @FXML
    AnchorPane componentContainer;
    @FXML
    Label questionDisplayArea;
    @FXML
    Label optionA;
    @FXML
    Label optionB;
    @FXML
    Label optionC;
    @FXML
    Label optionD;
    @FXML
    Label optionE;
    @FXML
    Label optionADisplayArea;
    @FXML
    Label optionBDisplayArea;
    @FXML
    Label optionCDisplayArea;
    @FXML
    Label optionDDisplayArea;
    @FXML
    Label optionEDisplayArea;
    @FXML
    Label answerDisplayArea;
    @FXML
    Button forwardButton;
    @FXML
    Button backwardButton;
    @FXML
    TextField fontSizeRegulator;
     @FXML
    Label answerTimer;
     @FXML
    Button excelQuestionBankButton;
     @FXML
    TextField answerTimeDuration;
     @FXML
    ToggleButton autoButton;
      @FXML
    Label bottomBar;
     
     Thread thread = null;
    
    @FXML
    private void handleExcelQuestionBankButtonAction(ActionEvent event) {
        defaultQuestionDisplayAreaFont=questionDisplayArea.getFont();
        defaultOptionsDisplayAreaFont=optionADisplayArea.getFont();
        File excelQuestionBankFile;
        FileChooser excelQuestionBankChooser = new FileChooser();
        excelQuestionBankFile = excelQuestionBankChooser.showOpenDialog(Stage.getWindows().get(0));
        FileInputStream excelQuestionBankFIS= null;
        XSSFWorkbook excelQuestionBankWorkbook = null;
        try{
            excelQuestionBankFIS= new FileInputStream(excelQuestionBankFile);
            excelQuestionBankWorkbook = new XSSFWorkbook(excelQuestionBankFIS);
        }catch(IOException e){}
        XSSFSheet excelQuestionBankSheet = excelQuestionBankWorkbook.getSheetAt(0);
        boolean emptyAColumnCellMet = false;
        String[] mcq;
        Iterator<Row> iteratorRow = excelQuestionBankSheet.iterator();
        Row row=iteratorRow.next();
        Iterator<Cell> iteratorCell;
        Cell cell;
        iteratorCell = row.iterator();
        System.out.println("ok1");
        questionBankArray = new ArrayList<String[]>();
        int counter=0;
        while(!emptyAColumnCellMet){
            System.out.println("ok"+counter++);
            cell = iteratorCell.next();
            if(cell.getStringCellValue().trim().equals("end")){
                emptyAColumnCellMet=true;
                break;
            }
            if(cell.getStringCellValue().trim().isEmpty()){
                emptyAColumnCellMet=true;
                break;
            }
            if(cell.getStringCellValue()== null)break;
            System.out.println(cell.getStringCellValue()+"/");
            cell = iteratorCell.next();
            System.out.println(cell.getStringCellValue()+"/");
            cell = iteratorCell.next();
            System.out.println(cell.getStringCellValue()+"/");
            cell = iteratorCell.next();
            System.out.println(cell.getStringCellValue()+"/");
            cell = iteratorCell.next();
            //System.out.println(cell.getStringCellValue()+"/");
            cell = iteratorCell.next();
            System.out.println(cell.getStringCellValue()+"/");
            cell = iteratorCell.next();
           // System.out.println(cell.getStringCellValue()+"/");
            cell = iteratorCell.next();
            System.out.println(cell.getStringCellValue()+"/");
            cell = iteratorCell.next();
            //System.out.println(cell.getStringCellValue()+"/");
            mcq = new String[7];
            cell=iteratorCell.next();
            mcq[0]=cell.getStringCellValue();
            System.out.println(cell.getStringCellValue()+"/");
            cell = iteratorCell.next();
            if(cell.getCellType().equals(CellType.STRING)){
                mcq[1]=cell.getStringCellValue();
            }else{
                mcq[1]= Integer.toString((int)cell.getNumericCellValue());
            }
            System.out.println(mcq[1]+"/");
            cell = iteratorCell.next();
            if(cell.getCellType().equals(CellType.STRING)){
                mcq[2]=cell.getStringCellValue();
            }else{
                mcq[2]= Integer.toString((int)cell.getNumericCellValue());
            }
            System.out.println(mcq[2]+"/");
            cell = iteratorCell.next();
            if(cell.getCellType().equals(CellType.STRING)){
                mcq[3]=cell.getStringCellValue();
            }else{
                mcq[3]= Integer.toString((int)cell.getNumericCellValue());
            }
            System.out.println(mcq[3]+"/");
            cell = iteratorCell.next();
            if(cell.getCellType().equals(CellType.STRING)){
                mcq[4]=cell.getStringCellValue();
            }else{
                mcq[4]= Integer.toString((int)cell.getNumericCellValue());
            }
            System.out.println(mcq[4]+"/");
            cell = iteratorCell.next();
            if(cell.getCellType().equals(CellType.STRING)){
                mcq[5]=cell.getStringCellValue();
            }else{
                mcq[5]= Integer.toString((int)cell.getNumericCellValue());
            }
            System.out.println(mcq[5]+"/");
            cell = iteratorCell.next();
            if(cell.getCellType().equals(CellType.STRING)){
                mcq[6]=cell.getStringCellValue();
            }else{
                mcq[6]= Integer.toString((int)cell.getNumericCellValue());
            }
            System.out.println(mcq[6]+"/");
            questionBankArray.add(mcq);
            row=iteratorRow.next();
            iteratorCell = row.iterator();
        }
        System.out.println("ok2");
        System.out.println(questionBankArray.size());
    }
     @FXML
    private void handleForwardButtonAction(ActionEvent event) {
        questionBankArrayPointer++;
        if(questionBankArray != null){
            if((questionBankArrayPointer > -1) && (questionBankArrayPointer< questionBankArray.size())){
                String[] mcq = (String[])questionBankArray.toArray()[questionBankArrayPointer];
                String[] mcq_q= mcq[0].split(" ");
                String mcq_q_holder = new String("");
               
                updateLabel(questionDisplayArea, mcq[0], defaultQuestionDisplayAreaFont);
                updateLabel(optionADisplayArea, mcq[1], defaultOptionsDisplayAreaFont);
                updateLabel(optionBDisplayArea, mcq[2], defaultOptionsDisplayAreaFont);
                updateLabel(optionCDisplayArea, mcq[3], defaultOptionsDisplayAreaFont);
                updateLabel(optionDDisplayArea, mcq[4], defaultOptionsDisplayAreaFont);
                updateLabel(optionEDisplayArea, mcq[5], defaultOptionsDisplayAreaFont);
            }
        }
    }
    @FXML
    private void handleBackwardButtonAction(ActionEvent event) {
        questionBankArrayPointer--;
        if(questionBankArray != null){
            if(questionBankArrayPointer > -1){
                String[] mcq = (String[])questionBankArray.toArray()[questionBankArrayPointer];
                
                updateLabel(questionDisplayArea, mcq[0], defaultQuestionDisplayAreaFont);
                updateLabel(optionADisplayArea, mcq[1], defaultOptionsDisplayAreaFont);
                updateLabel(optionBDisplayArea, mcq[2], defaultOptionsDisplayAreaFont);
                updateLabel(optionCDisplayArea, mcq[3], defaultOptionsDisplayAreaFont);
                updateLabel(optionDDisplayArea, mcq[4], defaultOptionsDisplayAreaFont);
                updateLabel(optionEDisplayArea, mcq[5], defaultOptionsDisplayAreaFont);
            }
        }
    }
    @FXML
    private void handleAnswerDisplayAreaOnMouseEntered(MouseEvent event) {
        System.out.println("mouse entered");
        if(questionBankArray != null){
            System.out.println("mouse entered");
            if(questionBankArrayPointer > -1){
                System.out.println("mouse entered");
                String[] mcq = questionBankArray.get(questionBankArrayPointer);
                System.out.println("mouse entered");
                answerDisplayArea.setText(mcq[6].toUpperCase());
                System.out.println("mouse entered");
             }
        }
    }
    @FXML
    private void handleAnswerDisplayAreaOnMouseExited(MouseEvent event) {
        System.out.println("mouse exited");
        if(questionBankArray != null){
            System.out.println("mouse exited");
            if(questionBankArrayPointer > -1){
                System.out.println("mouse exited");
                answerDisplayArea.setText("");
                System.out.println("mouse exited");
             }
        }
    }
    @FXML
    private void handleAutoButtonOnAction(ActionEvent event) {
        if(autoButton.getText().equalsIgnoreCase("auto")){
            autoButton.setText("Manual");
            backwardButton.setDisable(true);
            forwardButton.setDisable(true);
            excelQuestionBankButton.setDisable(true);
            Platform.runLater(new AutoModeManager(this));
        }else{
            autoButton.setText("Auto");
            backwardButton.setDisable(false);
            forwardButton.setDisable(false);
            excelQuestionBankButton.setDisable(false);
        }
        
    }
    public void updateLabel(Label label, String s, Font font){
        double adequateFontSize = getAdequateRenderFontSize(label,s,font);
        System.err.println("Proposed Fontsize = "+font.getSize()+" Adequate Fontsize = "+adequateFontSize);
        label.setFont(Font.font(label.getFont().getFamily(),adequateFontSize));
        label.setText(s.trim());
        Tooltip toolTip = new Tooltip(s.trim());
        toolTip.setFont(Font.font(label.getFont().getFamily(),pofN(2,stage.getHeight())));
        label.setTooltip(new Tooltip(s.trim()));
    }
    private double getAdequateRenderFontSize(Label label, String s, Font font){
        boolean questionDisplayAreaFoundFlag = false;
        double fontSize = font.getSize();
        double limit = pofN(3.3,stage.getHeight());
        try{
            limit = Double.parseDouble(fontSizeRegulator.getText());
        }catch(NumberFormatException e){
            System.err.println("Getting numeric value from fontSizeRegulator TextField failed "+e.toString());
        }
        Text text = new Text(s);
        text.setFont(font);
        
        double proposedArea=text.getBoundsInLocal().getWidth()*label.getBoundsInLocal().getHeight();
        if(label.lookup("#questionDisplayArea") != null){
            String id = ((Node)label.lookup("#questionDisplayArea")).getId();
            if (id.equalsIgnoreCase("questionDisplayArea")){
                System.err.println("Node with fx:id questionDisplayArea found");
                proposedArea=text.getBoundsInLocal().getWidth()*text.getBoundsInLocal().getHeight();
                questionDisplayAreaFoundFlag = true;
            }
        }
        
        double providedArea=label.getBoundsInLocal().getWidth()*label.getBoundsInLocal().getHeight();
        System.err.println("proposedArea: " +proposedArea+" providedArea:"+providedArea);
       
        System.err.println("looking for adequate font size for : " +s);
        while(Math.round(((proposedArea/providedArea)*100))>=limit){
            System.err.print("..."+fontSize);
            fontSize = fontSize - 1;
            text = new Text(s.trim());
            text.setFont(Font.font(label.getFont().getFamily(),fontSize));
            if(questionDisplayAreaFoundFlag){
                proposedArea=text.getBoundsInLocal().getWidth()*text.getBoundsInLocal().getHeight();
            }else{
                proposedArea=text.getBoundsInLocal().getWidth()*label.getBoundsInLocal().getHeight();
            }
            System.err.print(" proposedArea:" +proposedArea+" providedArea:"+providedArea);
        }
        System.err.print("...ok: ");
        System.err.println();
        return fontSize;
    }
    @FXML
    private synchronized void handleAnswerTimerDisplayAreaOnMouseEntered(MouseEvent event) {
        //I don't understand this code, I took it somewhere. 
        //I need to understand the concepts of Timelines, KeyFrames, 
        //KeyValues, binding values values of java FX components to classes, the bean component stuff...
        System.out.println("Timer dislay area mouse entered");
        final Second s = new Second(answerTimeDuration.getText());
        answerTimer.textProperty().bind(s.getProperty());
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0),new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent actionEvent){
                s.setStrP(Integer.toString(Integer.parseInt(s.getStrP()) - 1));//I think you should have used an Integer here.
            }}),new KeyFrame(Duration.seconds(1))//Do something every second. In this case we are going to increment setStrP.
        );
        timeline.setCycleCount(Integer.parseInt(answerTimeDuration.getText()));//Repeat this 10 times
        timeline.play();
        System.out.println("Timer dislay handler exits");
    }
    @FXML
    private void handleAnswerTimerDisplayAreaOnMouseExited(MouseEvent event) {
        System.out.println("mouse exited");
    }
    
    //percentage of number
    private double pofN(double percentage, double number){
        return (percentage/100)*number;
    }
    public void updateGUI(double width, double height){
        
            //factor in that the default window is 800px (w)/ 600px (h) i.e. ration 4/3 and adjust the width
            width = (4*height)/3;
            
            stage.setWidth(pofN(100,width));
            stage.setHeight(pofN(100,height));
            stage.setX(pofN(0,width));
            stage.setY(pofN(0,height));
            
            backwardButton.setPrefWidth(pofN(6,width));
            backwardButton.setPrefHeight(pofN(7.3,height));
            backwardButton.setLayoutX(pofN(3,width));
            backwardButton.setLayoutY(pofN(2.6,height));
            updateFont(backwardButton,pofN(2,height));
            
            excelQuestionBankButton.setPrefWidth(pofN(20.5,width));
            excelQuestionBankButton.setPrefHeight(pofN(7.3,height));
            excelQuestionBankButton.setLayoutX(pofN(52.5,width));
            excelQuestionBankButton.setLayoutY(pofN(2.6,height));
            
            forwardButton.setPrefWidth(pofN(6,width));
            forwardButton.setPrefHeight(pofN(7.3,height));
            forwardButton.setLayoutX(pofN(9,width));
            forwardButton.setLayoutY(pofN(2.6,height));
            updateFont(forwardButton,pofN(2,height));
            
            answerDisplayArea.setPrefWidth(pofN(9.5,width));
            answerDisplayArea.setPrefHeight(pofN(7.3,height));
            answerDisplayArea.setLayoutX(pofN(42,width));
            answerDisplayArea.setLayoutY(pofN(2.6,height));
            updateFont(answerDisplayArea,pofN(4,height));
            
            answerTimer.setPrefWidth(pofN(9.5,width));
            answerTimer.setPrefHeight(pofN(7.3,height));
            answerTimer.setLayoutX(pofN(86.5,width));
            answerTimer.setLayoutY(pofN(2.6,height));
            updateFont(answerTimer,pofN(5.3,height));
            
            optionA.setPrefWidth(pofN(3.5,width));
            optionA.setPrefHeight(pofN(8,height));
            optionA.setLayoutX(pofN(3,width));
            optionA.setLayoutY(pofN(37.3,height));
            updateFont(optionA,pofN(5.3,height));
            
            optionADisplayArea.setPrefWidth(pofN(87.5,width));
            optionADisplayArea.setPrefHeight(pofN(8,height));
            optionADisplayArea.setLayoutX(pofN(8,width));
            optionADisplayArea.setLayoutY(pofN(37.3,height));
            updateFont(optionADisplayArea,pofN(5.3,height));
            
            optionB.setPrefWidth(pofN(3.5,width));
            optionB.setPrefHeight(pofN(8,height));
            optionB.setLayoutX(pofN(3,width));
            optionB.setLayoutY(pofN(47.3,height));
            updateFont(optionB,pofN(5.3,height));
            
            optionBDisplayArea.setPrefWidth(pofN(87.5,width));
            optionBDisplayArea.setPrefHeight(pofN(8,height));
            optionBDisplayArea.setLayoutX(pofN(8,width));
            optionBDisplayArea.setLayoutY(pofN(47.3,height));
            updateFont(optionBDisplayArea,pofN(5.3,height));
            
            optionC.setPrefWidth(pofN(3.5,width));
            optionC.setPrefHeight(pofN(8,height));
            optionC.setLayoutX(pofN(3,width));
            optionC.setLayoutY(pofN(57.3,height));
            updateFont(optionC,pofN(5.3,height));
            
            optionCDisplayArea.setPrefWidth(pofN(87.5,width));
            optionCDisplayArea.setPrefHeight(pofN(8,height));
            optionCDisplayArea.setLayoutX(pofN(8,width));
            optionCDisplayArea.setLayoutY(pofN(57.3,height));
            updateFont(optionCDisplayArea,pofN(5.3,height));
            
            optionD.setPrefWidth(pofN(3.5,width));
            optionD.setPrefHeight(pofN(8,height));
            optionD.setLayoutX(pofN(3,width));
            optionD.setLayoutY(pofN(67.3,height));
            updateFont(optionD,pofN(5.3,height));
            
            optionDDisplayArea.setPrefWidth(pofN(87.5,width));
            optionDDisplayArea.setPrefHeight(pofN(8,height));
            optionDDisplayArea.setLayoutX(pofN(8,width));
            optionDDisplayArea.setLayoutY(pofN(67.3,height));
            updateFont(optionDDisplayArea,pofN(5.3,height));
            
            optionE.setPrefWidth(pofN(3.5,width));
            optionE.setPrefHeight(pofN(8,height));
            optionE.setLayoutX(pofN(3,width));
            optionE.setLayoutY(pofN(77.3,height));
            updateFont(optionE,pofN(5.3,height));
            
            optionEDisplayArea.setPrefWidth(pofN(87.5,width));
            optionEDisplayArea.setPrefHeight(pofN(8,height));
            optionEDisplayArea.setLayoutX(pofN(8,width));
            optionEDisplayArea.setLayoutY(pofN(77.3,height));
            updateFont(optionEDisplayArea,pofN(5.3,height));
            
            questionDisplayArea.setPrefWidth(pofN(94,width));
            questionDisplayArea.setPrefHeight(pofN(24.6,height));
            questionDisplayArea.setLayoutX(pofN(3,width));
            questionDisplayArea.setLayoutY(pofN(11.3,height));
            updateFont(questionDisplayArea,pofN(6.6,height));

            answerTimeDuration.setPrefWidth(pofN(9.5,width));
            answerTimeDuration.setPrefHeight(pofN(7.3,height));
            answerTimeDuration.setLayoutX(pofN(76,width));
            answerTimeDuration.setLayoutY(pofN(2.6,height));
            updateFont(answerTimeDuration,pofN(3.3,height));
            
            fontSizeRegulator.setPrefWidth(pofN(9.5,width));
            fontSizeRegulator.setPrefHeight(pofN(7.3,height));
            fontSizeRegulator.setLayoutX(pofN(24,width));
            fontSizeRegulator.setLayoutY(pofN(2.6,height));
            updateFont(fontSizeRegulator,pofN(3.3,height));
            
            autoButton.setPrefWidth(pofN(8,width));
            autoButton.setPrefHeight(pofN(4,height));
            autoButton.setLayoutX(pofN(86.5,width));
            autoButton.setLayoutY(pofN(86.6,height));
            updateFont(autoButton,pofN(2,height));
            
            bottomBar.setPrefWidth(pofN(95,width));
            bottomBar.setPrefHeight(pofN(1.3,height));
            bottomBar.setLayoutX(pofN(3,width));
            bottomBar.setLayoutY(pofN(90.6,height));
            //updateFont(autoButton,pofN(2,height));
            
    }
    public void setStage(Stage stage){
        this.stage = stage;
        
        //add size change listeners and handlers to the stage
        stage.heightProperty().addListener(new ChangeListener<Number>() {
             @Override
             public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                 double width = stage.getWidth();
                 double height = stage.getHeight();
                 updateGUI(width,height);
             }
         });
        stage.widthProperty().addListener(new ChangeListener<Number>() {
             @Override
             public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                 double width = stage.getWidth();
                 double height = stage.getHeight();
                 updateGUI(width,height);
             }
         });
    }
    public Stage getStage(){
        return stage;
    }
    private void updateFont(Labeled labeled,double fontsize){
        double currentFontSize = labeled.getFont().getSize();
        System.out.println("updateFont:currentFontSize of "+labeled.getId()+" = "+currentFontSize);
        labeled.setFont(Font.font(labeled.getFont().getFamily(),fontsize));
        System.out.println("updateFont:newFontSize of "+labeled.getId()+" = "+fontsize);
    }
    private void updateFont(TextField textField,double fontsize){
        double currentFontSize = textField.getFont().getSize();
        System.out.println("updateFont:currentFontSize of "+textField.getId()+" = "+currentFontSize);
        textField.setFont(Font.font(textField.getFont().getFamily(),fontsize));
        System.out.println("updateFont:newFontSize of "+textField.getId()+" = "+fontsize);        
    }
    private void updateFont(Button button,double fontsize){
        double currentFontSize = button.getFont().getSize();
        System.out.println("updateFont:currentFontSize of "+button.getId()+" = "+currentFontSize);
        button.setFont(Font.font(button.getFont().getFamily(),fontsize));
        System.out.println("updateFont:newFontSize of "+button.getId()+" = "+fontsize);        
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
}
