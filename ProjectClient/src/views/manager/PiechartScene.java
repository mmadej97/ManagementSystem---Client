package views.manager;
import data.entities.EmployeeEntity;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import data.entities.TaskEntity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PiechartScene {


    public static Scene getScene(EmployeeEntity employeeEntity, ObjectInputStream reader, ObjectOutputStream writer, Stage window, String surveyName) {




        List<String> questionList = new ArrayList<>();

        try {
            writer.writeObject(new String("take:Questions:" + surveyName));
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


        List<String> questionOptionList = new ArrayList<>();

        try {
            writer.writeObject(new String("take:Options:" + surveyName));
            String options = (String) reader.readObject();
            while(!options.equals("end")){

                questionOptionList.add(options);
                options = (String) reader.readObject();
            }
        } catch (IOException | ClassNotFoundException e ) {
            e.printStackTrace();
        }


        List<Integer> questionOptionCountList = new ArrayList<>();
        int iterator = 1;
        for(String option : questionOptionList) {
            try {

                String[] split = option.split(":");
                for(String temp : split) {
                    writer.writeObject(new String("take:OptionsCount:" + temp + ":" + surveyName + ":" + iterator));
                    questionOptionCountList.add((Integer) reader.readObject());
                }
            }catch (IOException | ClassNotFoundException exc){
                exc.printStackTrace();
            }

            iterator ++;
        }


        questionOptionCountList.forEach(System.out::println);

        List<PieChart.Data> listOfData = new ArrayList<>();
        List<PieChart> pieChartList = new ArrayList<>();


        int index = 0;
        for(int i = 0; i < questionList.size() ; i++){

            PieChart piechart = new PieChart();
            piechart.setTitle(questionList.get(i));

            String[] stringArr = questionOptionList.get(i).split(":");
            for(String  temp : stringArr) {
                PieChart.Data data = new PieChart.Data(temp, questionOptionCountList.get(index));
                listOfData.add(data);
                index++;
            }

            piechart.getData().addAll(listOfData);
            listOfData  = new ArrayList<>();
            pieChartList.add(piechart);
        }


        VBox layout = new VBox(10, pieChartList.stream().toArray(PieChart[] :: new));

        BorderPane borderPane = new BorderPane(layout);
        Label labelHeading = new Label(surveyName + " Survey");
        labelHeading.setAlignment(Pos.TOP_CENTER);
        borderPane.setTop(labelHeading);
        Scene scene = new Scene(borderPane, 500,500);
        window.setScene(scene);
        window.setFullScreen(true);
        window.show();

        return null;

    }

}
