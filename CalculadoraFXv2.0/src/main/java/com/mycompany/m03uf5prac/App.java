package com.mycompany.m03uf5prac;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import javafx.scene.image.Image;
import javafx.stage.StageStyle;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    /**
     * 
     * @param stage
     * @throws IOException 
     */
    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("CalculadoraResponsiveVista"), 650, 550);
        stage.setTitle("CalculadoraFX");
        stage.setScene(scene);
         // Setting the min height and width to limit resizing
        stage.setMinHeight(550);
        stage.setMinWidth(650);
        //Favicon
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/images/favicon-32x32.png")));
        stage.show();
    }

    /**
     * 
     * @param fxml
     * @throws IOException 
     */
    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    /**
     * 
     * @param fxml
     * @return
     * @throws IOException 
     */
    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }
    
    /**
     * 
     * @param args 
     */
    public static void main(String[] args) {
        launch();
    }

}
