package data.entities;

import java.io.Serializable;

public class EmployeeEntity extends Entity implements Serializable {


    private Integer EMPLOYEE_ID;
    private String FIRST_NAME;
    private String LAST_NAME;
    private String USERNAME;
    private String PASSWORD;
    private Integer MANAGER_ID;
    private Double SALARY;


    public EmployeeEntity() {
    }

    public EmployeeEntity(Integer EMPLOYEE_ID, String FIRST_NAME, String LAST_NAME, String USERNAME, String PASSWORD, Integer MANAGER_ID, Double SALARY) {
        this.EMPLOYEE_ID = EMPLOYEE_ID;
        this.FIRST_NAME = FIRST_NAME;
        this.LAST_NAME = LAST_NAME;
        this.USERNAME = USERNAME;
        this.PASSWORD = PASSWORD;
        this.MANAGER_ID = MANAGER_ID;
        this.SALARY = SALARY;
    }


    public Integer getEMPLOYEE_ID() {
        return EMPLOYEE_ID;
    }

    public void setEMPLOYEE_ID(Integer EMPLOYEE_ID) {
        this.EMPLOYEE_ID = EMPLOYEE_ID;
    }

    public String getFIRST_NAME() {
        return FIRST_NAME;
    }

    public void setFIRST_NAME(String FIRST_NAME) {
        this.FIRST_NAME = FIRST_NAME;
    }

    public String getLAST_NAME() {
        return LAST_NAME;
    }

    public void setLAST_NAME(String LAST_NAME) {
        this.LAST_NAME = LAST_NAME;
    }

    public String getUSERNAME() {
        return USERNAME;
    }

    public void setUSERNAME(String USERNAME) {
        this.USERNAME = USERNAME;
    }

    public String getPASSWORD() {
        return PASSWORD;
    }

    public void setPASSWORD(String PASSWORD) {
        this.PASSWORD = PASSWORD;
    }

    public Integer getMANAGER_ID() {
        return MANAGER_ID;
    }

    public void setMANAGER_ID(Integer MANAGER_ID) {
        this.MANAGER_ID = MANAGER_ID;
    }

    public Double getSALARY() {
        return SALARY;
    }

    public void setSALARY(Double SALARY) {
        this.SALARY = SALARY;
    }


    @Override
    public String toString() {
        return "EmployeeEntity{" +
                "EMPLOYEE_ID=" + EMPLOYEE_ID +
                ", FIRST_NAME='" + FIRST_NAME + '\'' +
                ", LAST_NAME='" + LAST_NAME + '\'' +
                ", USERNAME='" + USERNAME + '\'' +
                ", PASSWORD='" + PASSWORD + '\'' +
                ", MANAGER_ID=" + MANAGER_ID +
                ", SALARY=" + SALARY +
                '}';
    }
}
