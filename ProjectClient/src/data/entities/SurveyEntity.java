package data.entities;

import java.io.Serializable;

public class SurveyEntity extends Entity implements Serializable {

    private Integer SURVEY_ID;
    private String SURVEY_NAME;


    public SurveyEntity() {
    }

    public SurveyEntity(Integer SURVEY_ID, String SURVEY_NAME) {
        this.SURVEY_ID = SURVEY_ID;
        this.SURVEY_NAME = SURVEY_NAME;
    }


    public Integer getSURVEY_ID() {
        return SURVEY_ID;
    }

    public void setSURVEY_ID(Integer SURVEY_ID) {
        this.SURVEY_ID = SURVEY_ID;
    }

    public String getSURVEY_NAME() {
        return SURVEY_NAME;
    }

    public void setSURVEY_NAME(String SURVEY_NAME) {
        this.SURVEY_NAME = SURVEY_NAME;
    }
}
