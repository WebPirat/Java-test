package yolo;
import java.util.ArrayList;
import java.util.List;

public class QuestionAnswer {
    private String question;
    private List<String> answers;

    public QuestionAnswer(String question, List<String> answers) {
        this.question = question;
        this.answers = new ArrayList<>(answers);
    }

    public String getQuestion() {
        return question;
    }

    public List<String> getAnswers() {
        return new ArrayList<>(answers);
    }
}