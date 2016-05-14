package ru.ncband.web.client.widgets.menu.testform.testcell.answercell;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.*;
import ru.ncband.web.shared.classes.Answer;
import ru.ncband.web.shared.classes.Answers;
import ru.ncband.web.shared.properties.LessonProperty;

import java.util.List;

public class AnswerCell extends Composite {
    interface AnswerCellUiBinder extends UiBinder<HTMLPanel, AnswerCell> {
    }

    private static AnswerCellUiBinder ourUiBinder = GWT.create(AnswerCellUiBinder.class);

    @UiField
    RadioButton radio;
    @UiField
    CheckBox check;
    @UiField
    Label text;
    private String type;
    private String id;

    @UiField
    Image image;

    public AnswerCell() {
        initWidget(ourUiBinder.createAndBindUi(this));
    }

    public void setAnswer(Answer answer, String type, String task_id){
        this.type = type;
        id = answer.getId();
        if(answer.getImage() != null) {
            image.setUrl(answer.getImage());
        }

        radio.setName(task_id);
        if (type.equals(LessonProperty.typeTest())){
            check.setVisible(false);
            radio.setVisible(true);
            radio.setText(answer.getAnswer());
        }else {
            check.setVisible(true);
            radio.setVisible(false);
            check.setText(answer.getAnswer());
        }
    }

    public void check(Answers answers){
        List<String> ids = answers.getIds();
        List<String> rights = answers.getRights();

        ids.add(id);
        if(type.equals(LessonProperty.typeTest())){
            rights.add((radio.getValue())? LessonProperty.right(): LessonProperty.wrong());
        }else {
            rights.add((check.getValue())? LessonProperty.right(): LessonProperty.wrong());
        }
        answers.setIds(ids);
        answers.setRights(rights);
    }

    public void clear(){
        type = null;
        text.setText("");
        image.setUrl("");
    }
}