package views.employee;

import data.entities.EmployeeEntity;
import data.entities.TaskEntity;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import data.entities.EmployeeEntity;
import data.entities.GenericEntity;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import views.manager.ManagerMainScene;
import views.manager.TableScene;
import java.io.*;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SurveyScene {





    public static Scene getScene(EmployeeEntity employeeEntity, ObjectInputStream reader, ObjectOutputStream writer, Stage window, String surveyName, Integer taskID) {

        List<String> questionList = new ArrayList<>();

        try {
            writer.writeObject(new String("get:Questions:" + surveyName));
            String question = (String) reader.readObject();
            while(!question.equals("end")){

                questionList.add(question);
                question = (String) reader.readObject();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        List<Label> labelList = new ArrayList<>();
        for(String str : questionList){
            labelList.add(new Label(str));
        }

        List<String> questionOptionList = new ArrayList<>();

        try {
            writer.writeObject(new String("get:Options:" + surveyName));
            String options = (String) reader.readObject();
            while(!options.equals("end")){

                questionOptionList.add(options);
                options = (String) reader.readObject();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }




        List<ChoiceBox<String>> choiceBoxList = new ArrayList<>();
        questionOptionList.stream()
                          .map(n -> choiceBoxList.add(new ChoiceBox<String>(FXCollections.observableArrayList(n.split(":")))))
                          .collect(Collectors.toList());

        List<Node> nodeList = new ArrayList<>();

        for(int i = 0; i < labelList.size(); i++){
            nodeList.add(labelList.get(i));
            nodeList.add(choiceBoxList.get(i));
        }


        Button buttonSubmit = new Button("Submit button");
        buttonSubmit.setOnAction(n -> {

            try {
                writer.writeObject("save:Answer:" + surveyName);
                for(int i = 0 ; i < choiceBoxList.size() ; i++){
                    writer.writeObject((i + 1) +":" + choiceBoxList.get(i).getSelectionModel().getSelectedItem());
                }
                writer.writeObject(new String("end"));

                writer.writeObject("update:task:" + taskID);

                Label labelSubmit = new Label("Answers were send to database");
                StackPane layout1 = new StackPane(labelSubmit);
                Scene scene1 = new Scene(layout1,300,200);
                window.setScene(scene1);

            } catch (IOException e) {
                e.printStackTrace();
            }

        });
        VBox layout = new VBox(10, nodeList.stream().toArray(Node[]::new));
        layout.getChildren().add(buttonSubmit);

        Scene scene = new Scene(layout,500,500);
        window.setScene(scene);
        window.show();
        return null;
    }
}
