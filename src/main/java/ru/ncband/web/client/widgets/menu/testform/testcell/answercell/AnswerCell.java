package ru.ncband.web.client.widgets.menu.testform.testcell.answercell;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.*;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;
import ru.ncband.web.client.services.TaskService;
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

    public void setAnswer(String task_id, String id, String type){
        this.type = type;
        this.id = id;

        radio.setName(task_id);
        if (type.equals(LessonProperty.typeTest())){
            check.setVisible(false);
            radio.setVisible(true);
        }else {
            check.setVisible(true);
            radio.setVisible(false);
        }

        TaskService taskService = GWT.create(TaskService.class);
        taskService.getAnswer(id, new MethodCallback<Answer>() {
            @Override
            public void onFailure(Method method, Throwable throwable) {}

            @Override
            public void onSuccess(Method method, Answer answer) {
                image.setUrl(answer.getImage());
                text.setText(answer.getAnswer());

                if(answer.getImage().contains("null")){
                    image.setVisible(false);
                }

                if(answer.getAnswer()== null){
                    text.setVisible(false);
                }
            }
        });

    }

    public void check(Answers answers){
        List<String> ids = answers.getIds();
        List<String> rights = answers.getRights();

        ids.add(id);
        if(type.equals(LessonProperty.typeTest())){
            rights.add((radio.isChecked())? LessonProperty.right(): LessonProperty.wrong());
        }else {
            rights.add((check.isChecked())? LessonProperty.right(): LessonProperty.wrong());
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