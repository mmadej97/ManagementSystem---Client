package views;

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
import views.employee.EmployeeMainScene;
import views.manager.ManagerMainScene;
import views.manager.TableScene;

import java.io.*;


public class LoggingScene extends Application {

//    public static BufferedReader reader;
//    public static PrintWriter writer;
    public static ObjectOutputStream writer;
    public static ObjectInputStream reader;
    public static EmployeeEntity employee = new EmployeeEntity();





    public static ObjectOutputStream getWriter() {
        return writer;
    }

    public static void setWriter(ObjectOutputStream writer) {
        LoggingScene.writer = writer;
    }

    public static ObjectInputStream getReader() {
        return reader;
    }

    public static void setReader(ObjectInputStream reader) {
        LoggingScene.reader = reader;
    }

    public static EmployeeEntity getEmployee() {
        return employee;
    }

    public static void setEmployee(EmployeeEntity employee) {
        LoggingScene.employee = employee;
    }

    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage window) throws Exception{


        setUserAgentStylesheet(STYLESHEET_MODENA);

        Label headerLabel   = new Label("Logging");
        Label usernameLabel = new Label("Username");
        Label passwordLabel = new Label("Password");
        Label errorLogging = new Label("Wrong username or password");
        errorLogging.setVisible(false);



        TextField username  = new TextField();

        PasswordField password = new PasswordField();
        password.setPromptText("Your password");

        Button button = new Button("Login");
        button.setOnAction((n) -> {


                employee.setUSERNAME(username.getText());
                employee.setPASSWORD(password.getText());

                try {
                    writer.writeObject(employee);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                try {
                    int resultCode = reader.read();
                    if(resultCode == 200) {

                        employee = (EmployeeEntity) reader.readObject();
                        EmployeeMainScene.getScene(employee, reader, writer, window);

                    }else if(resultCode == 201){


                        employee = (EmployeeEntity) reader.readObject();
                        ManagerMainScene.getScene(employee, reader, writer, window);


                    }else if (resultCode == -1){

                        username.clear();
                        password.clear();
                        errorLogging.setVisible(true);
                    }

                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }


//                writer.println("username:"+username.getText());
//                writer.println("password:"+password.getText());
//                    try {
//                        employee =  reader.readLine();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                  writer.println(new data.entities.EmployeeEntity(null,null, null, username.getText(), password.getText(),null));

            }

        );

        button.setAlignment(Pos.BOTTOM_CENTER);

        VBox layoutCenter = new VBox(10, usernameLabel, username, passwordLabel, password, button, errorLogging);
        layoutCenter.setAlignment(Pos.CENTER);


        BorderPane layout = new BorderPane();
        layout.setPadding(new Insets(20,20,20,20));
        layout.setTop(headerLabel);
        layout.setCenter(layoutCenter);



        Scene scene = new Scene(layout,500,500);

        window.setScene(scene);
        window.show();

    }





}
