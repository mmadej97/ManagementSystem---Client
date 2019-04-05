package views.manager;

import data.entities.*;
import data.entities.GenericEntity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javax.xml.soap.Text;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.lang.reflect.Field;

public class TableScene {

    public static Entities entityType ;


        public static Entity[] getEntities(ObjectInputStream reader, ObjectOutputStream writer, GenericEntity<? extends Entity> genericEntity) {



        GenericEntity[] genericEntities = new GenericEntity[256];
        try {

            int index = 1;
            writer.writeObject(new String("get:" + genericEntity.getEntity().getClass().getName().replace("class ", "").replace("data.entities.", "").replace("Entity","")));
            GenericEntity<? extends Entity> entity = new GenericEntity<> ((Entity) reader.readObject());
            genericEntities[0] = entity;
            while (entity.getEntity() != null) {

                entity = new GenericEntity((Entity)reader.readObject());
                genericEntities[index] = entity ;
                index += 1;
            }

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }


            Entity[] entityArr = new Entity[500];

            int index = 0;
            for(GenericEntity ent : genericEntities){

                if(ent != null) {
                    entityArr[index] = ent.getEntity();
                    index += 1;
                }
            }


        return entityArr;
    }


    public static Scene getScene(ObjectInputStream reader, ObjectOutputStream writer, GenericEntity<? extends Entity> genericEntity, boolean editable){



        if(genericEntity.getEntity().getClass().getName().contains("Employee"))
            entityType = Entities.EMPLOYEE;
        if(genericEntity.getEntity().getClass().getName().contains("Survey"))
            entityType = Entities.SURVEY;
        if(genericEntity.getEntity().getClass().getName().contains("Answer"))
            entityType = Entities.ASNWER;
        if(genericEntity.getEntity().getClass().getName().contains("Question"))
            entityType = Entities.QUESTION;
        if(genericEntity.getEntity().getClass().getName().contains("Task"))
            entityType = Entities.TASK;


        Entity[] entityArr = getEntities(reader, writer, genericEntity);
        ObservableList<Entity> observableListEntity = FXCollections.observableArrayList(entityArr);
        Field[] entityFields =  entityArr[0].getClass().getDeclaredFields();
        List<TableColumn<Entity, String>> tableColumnList = new ArrayList<>();

        for (Field field : entityFields) {


            String fieldName = field.getName();
            TableColumn<Entity,String> tableColumn= new TableColumn<Entity, String>(fieldName);
            tableColumn.setCellValueFactory(new PropertyValueFactory(fieldName));
            tableColumnList.add(tableColumn);
        }


        TableView<Entity> entityTable = new TableView<>(observableListEntity);
        entityTable.getColumns().addAll(tableColumnList.stream()
                                                       .toArray(TableColumn[]::new));
        entityTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        entityTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);


        Button buttonAdd = new Button("Add");
        buttonAdd.setOnAction(n -> {

            Stage popUp = new Stage();
            List<Label> labelList = new ArrayList<>();
            List<TextField> textFieldList = new ArrayList<>();


            for (Field field : entityFields) {
                String fieldName = field.getName();
                if(!fieldName.equals(entityType + "_ID")){
                    labelList.add(new Label("Insert " + fieldName));
                    textFieldList.add(new TextField());
                }
            }





            Button buttonSubmit = new Button("Submit");
            buttonSubmit.setOnAction(a -> {

                try {
                    writer.writeObject(new String("save:" + entityArr[0].getClass().getName().replace("data.entities.","").replace("Entity","")));
                    Entity entity = null;
                    try {
                        entity = (Entity) Class.forName(entityArr[0].getClass().getName()).newInstance();
                        Field[] fieldArr = entityArr[0].getClass().getDeclaredFields();
                        int value = 0;
                        for(Field field : fieldArr){

                            field.setAccessible(true);

                            if(field.getName().equals(entity.getClass().getName().replace("data.entities.","").replace("Entity","").concat("_ID").toUpperCase()))
                                continue;

                            for(Label label : labelList) {

                                if (label.getText().replace("Insert ", "").equals(field.getName())) {
                                    List<Method> methodList = Arrays.asList(entityArr[0].getClass().getDeclaredMethods()).stream()
                                            .filter(c -> c.getName().equals("set" + field.getName()))
                                            .collect(Collectors.toList());
                                    try {

                                        Class<?> parameterTypes[] = methodList.get(0).getParameterTypes();
                                        if (parameterTypes[0] == String.class)
                                            methodList.get(0).invoke(entity, textFieldList.get(value).getText());
                                        else if (parameterTypes[0] == Double.class)
                                            methodList.get(0).invoke(entity, Double.valueOf(textFieldList.get(value).getText()));
                                        else if (parameterTypes[0] == Integer.class)
                                            methodList.get(0).invoke(entity,Integer.valueOf(textFieldList.get(value).getText()));

                                    } catch (InvocationTargetException e) {
                                        e.printStackTrace();
                                    }
                                }
                                value += 1;

                            }
                            value = 0;
                        }


                    } catch (InstantiationException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                    writer.writeObject(entity);
                    popUp.close();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

            VBox layout = new VBox(10);
            for (int i = 0; i < labelList.size(); i++) {
                layout.getChildren().add(labelList.get(i));
                layout.getChildren().add(textFieldList.get(i));
            }

            layout.getChildren().add(buttonSubmit);

            Scene scene = new Scene(layout, 600, 500);
            popUp.setScene(scene);
            popUp.show();
        });


        Button buttonRemove = new Button("Remove");
        buttonRemove.setOnAction(n ->
        {

            ObservableList<TablePosition> listTablePositon = entityTable.getSelectionModel().getSelectedCells();
            String idGetMethodName = "get" + entityType + "_ID";
            List<Integer> list = (List<Integer>) listTablePositon.stream()
                    .map(TablePosition::getRow)
                    .map(a -> {
                        try {
                            return (Integer) entityArr[a].getClass().getDeclaredMethod(idGetMethodName).invoke(entityArr[a]);
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        } catch (InvocationTargetException e) {
                            e.printStackTrace();
                        } catch (NoSuchMethodException e) {
                            e.printStackTrace();
                        }


                        return null;
                    })
                    .collect(Collectors.toList());



            try {
                writer.writeObject(new String("delete:" + entityArr[0].getClass().getName().replace("data.entities.","").replace("Entity","")));
                list.forEach((a) -> {
                    try {
                        writer.writeObject(a);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });

                writer.writeObject(0);
            } catch (IOException e) {
                e.printStackTrace();
            }

        });

        Button buttonEdit = new Button("Edit");

        Button buttorRefresh = new Button("Refresh Table");
        buttorRefresh.setOnAction(n -> {
            entityTable.getItems().setAll(getEntities(reader, writer, genericEntity));


        });







        HBox layout1 = new HBox();
        if(editable)
             layout1 = new HBox(30, buttonAdd, buttonRemove, buttorRefresh);
        else
             layout1 = new HBox(30, buttorRefresh);

        VBox layout2 = new VBox(10, entityTable);
        BorderPane layout = new BorderPane();

        layout.setPadding(new Insets(20, 20, 20, 20));
        layout.setCenter(layout2);
        layout.setBottom(layout1);



        Scene scene = new Scene(layout, 800, 800);

        return scene;


    }
}
