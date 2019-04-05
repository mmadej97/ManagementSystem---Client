package views.manager;

import data.entities.EmployeeEntity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class ChoosingEmployeeScene {

    public static Scene getScene(EmployeeEntity employeeEntity, ObjectInputStream reader, ObjectOutputStream writer, Stage window ){


        List<String> employeeList = null;
        try {
            writer.writeObject(new String("take:employeeList"));
            employeeList = new ArrayList<>();
            String employee = (String) reader.readObject();
            while (!employee.equals("end")){

                employeeList.add(employee);
                employee = (String) reader.readObject();

            }
        } catch (IOException | ClassNotFoundException  e) {
            e.printStackTrace();
        }




        ObservableList<String> listOfTasks = FXCollections.observableArrayList(employeeList);
        ListView<String> listView = new ListView<>(listOfTasks);
        Stage stage = new Stage();

        Button buttonChoose = new Button("Choose employee");
        buttonChoose.setOnAction(n -> {
            String employeeName = listView.getSelectionModel().getSelectedItem();
            ChoosingSurveyScene.getScene(employeeEntity, reader, writer, stage, employeeName, false);
        });


        BorderPane layout = new BorderPane(listView);
        layout.setBottom(buttonChoose);



        Scene scene = new Scene(layout,600,200);
        stage.setScene(scene);
        stage.show();
        return null;

    }

}
