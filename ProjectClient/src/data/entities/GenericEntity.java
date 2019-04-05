package data.entities;

import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class GenericEntity <T  extends Entity> {


    private T entity;

    public GenericEntity(T entityRow) {
        this.entity = entityRow;
    }

    public T getEntity() {
        return entity;
    }

    public void setEntity(T entity) {
        this.entity = entity;
    }


    public T initializeEntity(ResultSet queryResult){


        try {

            List<Method> listOfMethoddName = Arrays.asList(getEntity().getClass().getDeclaredMethods());

            for (int i = 1; i <= queryResult.getMetaData().getColumnCount(); i++) {

                String value = queryResult.getMetaData().getColumnName(i);
                if (listOfMethoddName.stream()
                        .anyMatch((n) -> n.getName().contains("set" + value))) {


                    List<Method> finalList = listOfMethoddName.stream()
                            .filter(n -> n.getName().contains("set" + value))
                            .collect(Collectors.toList());

                    if(finalList.get(0) == null)
                        throw new Exception("Error during searching for initializing method");


                    Method method = finalList.get(0);

                    Class<?> parameterTypes[] = method.getParameterTypes();
                    if (parameterTypes[0] == String.class)
                        method.invoke(this.getEntity(), queryResult.getString(i));
                    else if (parameterTypes[0] == Double.class)
                        method.invoke(this.getEntity(), queryResult.getDouble(i));
                    else if (parameterTypes[0] == Integer.class)
                        method.invoke(this.getEntity(), queryResult.getInt(i));


                }else
                    throw new Exception("Error during searching for initializing method");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return getEntity();

    }






}
