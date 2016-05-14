package ru.ncband.web.client.widgets.menu.testform.testcell;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.*;
import ru.ncband.web.client.widgets.menu.testform.testcell.answercell.AnswerCell;
import ru.ncband.web.shared.classes.Answer;
import ru.ncband.web.shared.classes.Answers;
import ru.ncband.web.shared.classes.Task;
import ru.ncband.web.shared.properties.LessonProperty;

import java.util.List;

public class TestCell extends Composite {
    interface TestCellUiBinder extends UiBinder<HTMLPanel, TestCell> {
    }

    private static TestCellUiBinder ourUiBinder = GWT.create(TestCellUiBinder.class);

    @UiField
    Label question;

    @UiField
    VerticalPanel answers;

    @UiField
    Image image;

    public TestCell() {
        initWidget(ourUiBinder.createAndBindUi(this));
    }

    public void setTask(Task task){
        question.setText(task.getQuestion());
        if(task.getImage() != null) {
            image.setUrl(task.getImage());
        }

        if(!task.getType().equals(LessonProperty.typeText())){
            List<Answer> answerList = task.getAnswers();

            for (Answer answer:
                 answerList) {
                AnswerCell answerCell = new AnswerCell();
                answerCell.setAnswer(answer, task.getType(), task.getId());
                answers.add(answerCell);
            }
        }
    }

    public void check(Answers answers){
        for (Widget widget:
             this.answers) {
            ((AnswerCell)widget).check(answers);
        }
    }

    public void clear(){
        question.setText("");
        image.setUrl("");

        for (Widget widget:
             answers) {
            ((AnswerCell)widget).clear();
        }
        answers.clear();
    }
}