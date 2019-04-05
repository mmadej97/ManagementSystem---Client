package views.employee;
import data.entities.EmployeeEntity;
import data.entities.TaskEntity;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import java.util.List;

public class TaskViewScene {

    public static Scene getScene(EmployeeEntity employeeEntity, ObjectInputStream reader, ObjectOutputStream writer, Stage window) {


        List<String> taskList = null;
        try {
            writer.writeObject(new String("get:taskList"));
            taskList = new ArrayList<>();
            String task = (String) reader.readObject();
            while (!task.equals("end")){

                taskList.add(task);
                task = (String) reader.readObject();

            }
        } catch (IOException | ClassNotFoundException  e) {
            e.printStackTrace();
        }





        ObservableList<String> listOfTasks = FXCollections.observableArrayList(taskList);
        ListView<String> listView = new ListView<>(listOfTasks);

        Stage stage = new Stage();
        Button buttonChoose = new Button("Choose survey");
        buttonChoose.setOnAction(n -> {

            String task = listView.getSelectionModel().getSelectedItems().get(0);
            String[] taskArr = task.replace("ID:", "").split("  ");
            SurveyScene.getScene(employeeEntity, reader, writer, stage, taskArr[1], Integer.valueOf(taskArr[0]));



        });


        BorderPane layout = new BorderPane(listView);
        layout.setBottom(buttonChoose);

        if (taskList.size() !=  0){
        Scene scene = new Scene(layout,600,200);
        stage.setScene(scene);
        stage.show();
        }else {
            Label errorLabel = new Label("There are not tasks for you");
            StackPane layout2 = new StackPane(errorLabel);
            Scene scene2 = new Scene(layout2,600,200);
            stage.setScene(scene2);
            stage.show();
        }
        return null;
    }
}








