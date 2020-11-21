/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Voter;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author hayde
 */
public class DetailedModelViewController {
    
    
    @FXML 
    private ResourceBundle resources;

    @FXML 
    private URL location;

    @FXML
    private Label idLabel;

    @FXML
    private Label fNameLabel;

    @FXML
    private Label lNameLabel;

    @FXML
    private Label partyLabel;

    @FXML
    private ImageView imageView;
    
     @FXML
    private Button backButton;
     
     @FXML
    private Button showDetailsInPlace;


    @FXML
    void goBack(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        
        if (previousScene != null) {
            stage.setScene(previousScene);
        }

    }

    Voter selectedModel;
    Scene previousScene;

    public void setPreviousScene(Scene scene) {
        previousScene = scene;
        backButton.setDisable(false);

    }

    public void initData(Voter model) {
        selectedModel = model;
        idLabel.setText(model.getId().toString());
        fNameLabel.setText(model.getFirstname());
        lNameLabel.setText(model.getLastname());
        partyLabel.setText(model.getPoliticalparty());
        
        try {
            String imagename = "/Resources/Images/" + model.getPoliticalparty() + ".png";
            Image profile = new Image(getClass().getResourceAsStream(imagename));
            imageView.setImage(profile);

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML 
    void initialize() {
        assert backButton != null : "fx:id=\"backButton\" was not injected: check your FXML file 'DetailModelView.fxml'.";
        assert idLabel != null : "fx:id=\"idLabel\" was not injected: check your FXML file 'DetailModelView.fxml'.";
        assert fNameLabel != null : "fx:id=\"fNameLabel\" was not injected: check your FXML file 'DetailModelView.fxml'.";
        assert lNameLabel != null : "fx:id=\"lNameLabel\" was not injected: check your FXML file 'DetailModelView.fxml'.";
        assert partyLabel != null : "fx:id=\"partyLabel\" was not injected: check your FXML file 'DetailModelView.fxml'.";
        assert imageView != null : "fx:id=\"imageView\" was not injected: check your FXML file 'DetailModelView.fxml'.";

        backButton.setDisable(true);

    }
}
