package at.czlabinger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class QuestionHandler {

    private List<String> questions = new ArrayList<>();

    private List<String> answers = new ArrayList<>();

    private final int[] selected;
    int correct = 0;

    public QuestionHandler() {

        this.readQA();

        this.selected = new int[]{new Random().nextInt(10), new Random().nextInt(10), new Random().nextInt(10)};
    }

    public String getQuestion(int i) {
        int a = new Random().nextInt(10);
        int b = new Random().nextInt(10);
        if(this.questions.get(this.selected[i]).equals("Calculate")) {
            char op;
            switch (new Random().nextInt(4)){
                case 1:
                    op = '-';
                    this.answers.set(this.selected[i], String.valueOf(a - b));
                    break;
                case 2:
                    op = '*';
                    this.answers.set(this.selected[i], String.valueOf(a - b));
                    break;
                case 3:
                    op = '/';
                    this.answers.set(this.selected[i], String.valueOf(a - b));
                    break;
                default:
                    op = '+';
                    this.answers.set(this.selected[i], String.valueOf(a - b));
                    break;
            }

            this.questions.set(this.selected[i], a + " " + op + " " + b);
        }

        return this.questions.get(this.selected[i]);
    }

    public boolean check(String answer, int i) {
        if (this.answers.get(this.selected[i]).equals(answer)) this.correct++;
        return this.answers.get(this.selected[i]).equals(answer);
    }

    public int getCorrect() {
        return this.correct;
    }

    public String getAnswer(int i) {
        return this.answers.get(this.selected[i]);
    }

    private void readQA() {
        try {
            this.questions = Files.readAllLines(Paths.get("/home/stoffi05/Documents/School/4xHIT/SEW/exercise-server/server/src/main/resources/questions.txt"));
            this.answers = Files.readAllLines(Paths.get("/home/stoffi05/Documents/School/4xHIT/SEW/exercise-server/server/src/main/resources/answers.txt"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
