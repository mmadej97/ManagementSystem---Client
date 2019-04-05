package views.manager;

import data.entities.EmployeeEntity;
import data.entities.TaskEntity;
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

public class ChoosingSurveyScene {


    public static Scene getScene(EmployeeEntity employeeEntity, ObjectInputStream reader, ObjectOutputStream writer, Stage window, String employeeName, boolean forChart ){


        List<String> surveyList = null;
        try {
            writer.writeObject(new String("take:surveyList"));
            surveyList = new ArrayList<>();
            String survey = (String) reader.readObject();
            while (!survey.equals("end")){

                surveyList.add(survey);
                survey = (String) reader.readObject();

            }
        } catch (IOException | ClassNotFoundException  e) {
            e.printStackTrace();
        }




        ObservableList<String> listOfTasks = FXCollections.observableArrayList(surveyList);
        ListView<String> listView = new ListView<>(listOfTasks);

        Stage stage = new Stage();
        Button buttonChoose = new Button("Choose survey");

        if(forChart == false) {
            buttonChoose.setOnAction(n -> {

                try {
                    writer.writeObject(new String("save:Task"));
                    String temp = employeeName.replace("ID: ", "");
                    String[] split = temp.split(" Surname: ");

                    Integer employeeFK = Integer.valueOf(split[0]);

                    String temp1 = listView.getSelectionModel().getSelectedItem().replace("ID: ", "");
                    String[] split1 = temp1.split(" Name: ");

                    Integer surveyFK = Integer.valueOf(split1[0]);
                    writer.writeObject(new TaskEntity(null, employeeFK, surveyFK, 0));
                    stage.close();
                    window.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            });
        }else{

            buttonChoose.setOnAction(n ->{


                    String temp = listView.getSelectionModel().getSelectedItem().replace("ID: ", "");
                    String[] split1 = temp.split(" Name: ");

                    PiechartScene.getScene(employeeEntity, reader, writer, stage, split1[1]);

        });

        }



        BorderPane layout = new BorderPane(listView);
        layout.setBottom(buttonChoose);



        Scene scene = new Scene(layout,600,200);
        stage.setScene(scene);
        stage.show();
        return null;
    }
}
