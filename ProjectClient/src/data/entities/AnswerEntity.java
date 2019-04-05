package data.entities;

import java.io.Serializable;

public class AnswerEntity extends Entity implements Serializable {

    private Integer ANSWER_ID;
    private String ANSWER_TEXT;
    private Integer TASK_FK;
    private Integer QUESTION_FK;


    public AnswerEntity() {
    }

    public AnswerEntity(String ANSWER_TEXT, Integer TASK_FK, Integer QUESTION_FK) {
        this.ANSWER_TEXT = ANSWER_TEXT;
        this.TASK_FK = TASK_FK;
        this.QUESTION_FK = QUESTION_FK;
    }

    public AnswerEntity(Integer ANSWER_ID, String ANSWER_TEXT, Integer TASK_FK, Integer QUESTION_FK) {
        this.ANSWER_ID = ANSWER_ID;
        this.ANSWER_TEXT = ANSWER_TEXT;
        this.TASK_FK = TASK_FK;
        this.QUESTION_FK = QUESTION_FK;
    }

    public Integer getANSWER_ID() {
        return ANSWER_ID;
    }

    public void setANSWER_ID(Integer ANSWER_ID) {
        this.ANSWER_ID = ANSWER_ID;
    }

    public String getANSWER_TEXT() {
        return ANSWER_TEXT;
    }

    public void setANSWER_TEXT(String ANSWER_TEXT) {
        this.ANSWER_TEXT = ANSWER_TEXT;
    }

    public Integer getTASK_FK() {
        return TASK_FK;
    }

    public void setTASK_FK(Integer TASK_FK) {
        this.TASK_FK = TASK_FK;
    }

    public Integer getQUESTION_FK() {
        return QUESTION_FK;
    }

    public void setQUESTION_FK(Integer QUESTION_FK) {
        this.QUESTION_FK = QUESTION_FK;
    }
}
