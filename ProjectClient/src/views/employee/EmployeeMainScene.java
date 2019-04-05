package views.employee;

import data.entities.EmployeeEntity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import data.entities.EmployeeEntity;
import data.entities.GenericEntity;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import views.manager.ManagerMainScene;
import views.manager.TableScene;

import java.io.*;


import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class EmployeeMainScene {


    public static Scene getScene(EmployeeEntity employeeEntity, ObjectInputStream reader, ObjectOutputStream writer, Stage window) {


    Button showSurveys = new Button("Show your tasks");
    showSurveys.setOnAction(n -> {
        TaskViewScene.getScene(employeeEntity, reader, writer, window);
    });



    Label employeeInfoLabel = new Label("Name:   " + employeeEntity.getFIRST_NAME() + "\nSurname:   " + employeeEntity.getLAST_NAME() +
                                         "\nManager ID: " + employeeEntity.getMANAGER_ID());

    employeeInfoLabel.setAlignment(Pos.TOP_LEFT);
    BorderPane layout = new BorderPane(showSurveys);
    layout.setTop(employeeInfoLabel);

    Scene scene = new Scene(layout,600,200);
    window.setScene(scene);
    window.show();
    window.setOnCloseRequest(event -> {
        try {
                writer.writeObject(new String("end:program"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    return null;
    }
}








