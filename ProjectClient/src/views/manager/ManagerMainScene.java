package views.manager;

import data.entities.*;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ManagerMainScene {



    public static Scene getScene(EmployeeEntity employeeEntity, ObjectInputStream reader, ObjectOutputStream writer, Stage window ){





        Stage tableStage = new Stage();
        Button buttonTasks = new Button("Show tasks");
        buttonTasks.setOnAction(n -> {

            tableStage.setScene(TableScene.getScene(reader,writer,new GenericEntity<>(new TaskEntity()), false));
            tableStage.show();

        });
        Button buttonEmployees = new Button("Show employees");
        buttonEmployees.setOnAction(n -> {
            tableStage.setScene(TableScene.getScene(reader,writer,new GenericEntity<>(new EmployeeEntity()), true));
            tableStage.show();
        });
        Button buttonSurveys = new Button("Show surveys");
        buttonSurveys.setOnAction(n ->{
            tableStage.setScene(TableScene.getScene(reader,writer,new GenericEntity<>(new SurveyEntity()), true));
            tableStage.show();
                });

        Button buttonQuestions = new Button("Show questions");
        buttonQuestions.setOnAction(n -> {
            tableStage.setScene(TableScene.getScene(reader,writer,new GenericEntity<>(new QuestionEntity()), false));
            tableStage.show();
        });

        Button buttonAnswers = new Button("Show answers");
        buttonAnswers.setOnAction(n ->  {
            tableStage.setScene(TableScene.getScene(reader,writer,new GenericEntity<>(new AnswerEntity()), false));
            tableStage.show();
        });

        Label managerInfoLabel = new Label("Name:   " + employeeEntity.getFIRST_NAME() + "\nSurname:   " + employeeEntity.getLAST_NAME());

        managerInfoLabel.setAlignment(Pos.TOP_LEFT);
        HBox layout1 = new HBox(10,buttonEmployees, buttonTasks, buttonSurveys, buttonQuestions, buttonAnswers);
        layout1.setAlignment(Pos.CENTER);

        Button buttonAdd = new Button("Add task");
        buttonAdd.setOnAction(n -> {

                ChoosingEmployeeScene.getScene(employeeEntity,reader,writer, window);
        });

        Button buttonChart = new Button("Show PieChart");
        buttonChart.setOnAction(n -> {

            ChoosingSurveyScene.getScene(employeeEntity,reader,writer, window, employeeEntity.getFIRST_NAME(), true);
        });




        BorderPane layout2 = new BorderPane(layout1);
        HBox layout3 = new HBox(40,buttonAdd, buttonChart);
        layout2.setTop(managerInfoLabel);
        layout2.setBottom(layout3);



        Scene scene = new Scene(layout2, 800,200);
        window.setScene(scene);
        window.show();
        window.setOnCloseRequest(event -> {

            try {
                writer.writeObject(new String("end:program"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });


        return scene;
    }

}
