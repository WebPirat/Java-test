package yolo;
import java.util.*;

public class Main {
    private static final Map<String, QuestionAnswer> questionAnswerMap = new HashMap<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Choose an option(or exit with 3):\n1. Ask a question\n2. Add a question");
            String option = scanner.nextLine().trim();


        if ("3".equals(option)) {
            break;
        }

        switch (option) {
                case "1":
                    System.out.println("Enter your question:");
                    String question = scanner.nextLine().trim();
                    printAnswer(question);
                    break;
                case "2":
                    System.out.println("Enter your question and answers:");
                    String input = scanner.nextLine().trim();
                    addQuestion(input);
                    break;
                default:
                    System.out.println("Invalid option. Please choose 1,2 or 3.");
                    break;
            }
        }
    }

private static void addQuestion(String input) {
    String[] parts = input.split("\\?", 2);
    String question = parts[0].trim();
    String[] answers = parts[1].split("\"\\s*\"");

    List<String> answerList = new ArrayList<>();
    for (String answer : answers) {
        answerList.add(answer.trim().replaceAll("\"", ""));
    }

    questionAnswerMap.put(question, new QuestionAnswer(question, answerList));

    // Print the question and answers
    System.out.println("Added question: " + question);
    System.out.println("With answers: ");
    for (String answer : answerList) {
        System.out.println(answer);
    }
}

private static void printAnswer(String question) {
    //check if ? is present in the question
    if (question.contains("?")) {
        //find the ? and delete it
        question = question.substring(0, question.indexOf("?"));
    }
    QuestionAnswer qa = questionAnswerMap.get(question);

    if (qa == null) {
        System.out.println("The answer to life, universe and everything is 42");
    } else {
        for (String answer : qa.getAnswers()) {
            System.out.println(answer);
        }
    }
}
}