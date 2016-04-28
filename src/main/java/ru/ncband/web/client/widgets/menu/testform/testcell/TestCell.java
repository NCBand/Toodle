package ru.ncband.web.client.widgets.menu.testform.testcell;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.*;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;
import ru.ncband.web.client.services.TaskService;
import ru.ncband.web.client.widgets.menu.testform.testcell.answercell.AnswerCell;
import ru.ncband.web.shared.classes.Answers;
import ru.ncband.web.shared.classes.Ids;
import ru.ncband.web.shared.classes.Task;
import ru.ncband.web.shared.properties.LessonProperty;

public class TestCell extends Composite {
    interface TestCellUiBinder extends UiBinder<HTMLPanel, TestCell> {
    }

    private static TestCellUiBinder ourUiBinder = GWT.create(TestCellUiBinder.class);

    @UiField
    Label question;

    @UiField
    VerticalPanel answers;
    private String id;
    private String type;

    @UiField
    Image image;

    public TestCell() {
        initWidget(ourUiBinder.createAndBindUi(this));
    }

    public void setTask(String _id){
        this.id = _id;
        TaskService taskService1 = GWT.create(TaskService.class);
        taskService1.getTask(_id, new MethodCallback<Task>() {
            @Override
            public void onFailure(Method method, Throwable throwable) {}

            @Override
            public void onSuccess(Method method, Task task) {
                question.setText(task.getQuestion());
                image.setUrl(task.getImage());

                if(task.getImage().contains("null")){
                    image.setVisible(false);
                }

                if(task.getQuestion()== null){
                    question.setVisible(false);
                }

                type = task.getType();

                if(!task.getType().equals(LessonProperty.typeTest())){
                    TaskService taskService2 = GWT.create(TaskService.class);
                    taskService2.getIds(task.getId(), LessonProperty.answer(), new MethodCallback<Ids>() {
                        @Override
                        public void onFailure(Method method, Throwable throwable) {}

                        @Override
                        public void onSuccess(Method method, Ids ids) {
                            for (String answer_id:
                                 ids.getIds()) {
                                AnswerCell answercell = new AnswerCell();
                                answercell.setAnswer(id, answer_id, type);
                                answers.add(answercell);
                            }
                        }
                    });
                }
            }
        });
    }

    public void check(Answers answers){
        for (Widget widget:
             this.answers) {
            ((AnswerCell)widget).check(answers);
        }
    }

    public void clear(){
        question.setText("");
        id = null;
        type = null;
        image.setUrl("");

        for (Widget widget:
             answers) {
            ((AnswerCell)widget).clear();
        }
        answers.clear();
    }
}