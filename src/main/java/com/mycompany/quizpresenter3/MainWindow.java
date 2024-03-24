package com.mycompany.quizpresenter3;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;

import javafx.application.Platform;
import javafx.scene.control.Button;



public class MainWindow extends Application {
    private double pofN(double percentage, double number) {
        return ((percentage / 100) * number);
    }
    @Override
    public void start(Stage stage) throws Exception {
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Scene.fxml"));        
        Parent root = loader.load();

        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/Styles.css");
        
        stage.setTitle("Quiz Presenter");
        stage.setScene(scene);
        stage.show();
        FXMLController instance = loader.getController();
        instance.setStage(stage);
        
        //Platform.runLater(new Runnable() {public void run() {updateGui();}});
        Platform.runLater(() -> {
            //update stage to screen size
            Rectangle2D screenBounds = Screen.getPrimary().getBounds();
            //questionDisplayArea.setText(screenBounds.getHeight()+" "+screenBounds.getWidth());
            double width = screenBounds.getWidth();
            double height = screenBounds.getHeight();
            
            width=pofN(80,width);
            height = pofN(80,height);
            instance.updateGUI(width, height);


        });
        
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
