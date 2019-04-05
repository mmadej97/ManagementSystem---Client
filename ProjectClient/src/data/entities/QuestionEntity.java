package data.entities;

import java.io.Serializable;

public class QuestionEntity extends Entity implements Serializable {

    private Integer QUESTION_ID;
    private String QUESTION_TEXT;
    private String QUESTION_OPTIONS;
    private Integer SURVEY_FK;


    public QuestionEntity() {
    }

    public QuestionEntity(Integer QUESTION_ID, String QUESTION_TEXT, String QUESTION_OPTIONS, Integer SURVEY_FK) {
        this.QUESTION_ID = QUESTION_ID;
        this.QUESTION_TEXT = QUESTION_TEXT;
        this.QUESTION_OPTIONS = QUESTION_OPTIONS;
        this.SURVEY_FK = SURVEY_FK;
    }


    public Integer getQUESTION_ID() {
        return QUESTION_ID;
    }

    public void setQUESTION_ID(Integer QUESTION_ID) {
        this.QUESTION_ID = QUESTION_ID;
    }

    public String getQUESTION_TEXT() {
        return QUESTION_TEXT;
    }

    public void setQUESTION_TEXT(String QUESTION_TEXT) {
        this.QUESTION_TEXT = QUESTION_TEXT;
    }

    public String getQUESTION_OPTIONS() {
        return QUESTION_OPTIONS;
    }

    public void setQUESTION_OPTIONS(String QUESTION_OPTIONS) {
        this.QUESTION_OPTIONS = QUESTION_OPTIONS;
    }

    public Integer getSURVEY_FK() {
        return SURVEY_FK;
    }

    public void setSURVEY_FK(Integer SURVEY_FK) {
        this.SURVEY_FK = SURVEY_FK;
    }
}
