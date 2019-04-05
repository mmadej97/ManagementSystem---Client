package data.entities;

import java.io.Serializable;

public class TaskEntity extends Entity implements Serializable {


    private Integer TASK_ID;
    private Integer EMPLOYEE_FK;
    private Integer SURVEY_FK;
    private Integer TASK_DONE;

    public TaskEntity() {
    }

    public TaskEntity(Integer TASK_ID, Integer EMPLOYEE_FK, Integer SURVEY_FK, Integer TASK_DONE) {
        this.TASK_ID = TASK_ID;
        this.EMPLOYEE_FK = EMPLOYEE_FK;
        this.SURVEY_FK = SURVEY_FK;
        this.TASK_DONE = TASK_DONE;
    }


    public Integer getTASK_DONE() {
        return TASK_DONE;
    }

    public void setTASK_DONE(Integer TASK_DONE) {
        this.TASK_DONE = TASK_DONE;
    }

    public Integer getTASK_ID() {
        return TASK_ID;
    }

    public void setTASK_ID(Integer TASK_ID) {
        this.TASK_ID = TASK_ID;
    }

    public Integer getEMPLOYEE_FK() {
        return EMPLOYEE_FK;
    }

    public void setEMPLOYEE_FK(Integer EMPLOYEE_FK) {
        this.EMPLOYEE_FK = EMPLOYEE_FK;
    }

    public Integer getSURVEY_FK() {
        return SURVEY_FK;
    }

    public void setSURVEY_FK(Integer SURVEY_FK) {
        this.SURVEY_FK = SURVEY_FK;
    }
}
