package yolo;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class MainTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private ByteArrayInputStream inContent;

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setIn(System.in);
    }

@Test
public void testAddQuestion() {
    List<String> lines = Arrays.asList("2", "What is Peters favorite food? \"Pizza\" \"Spaghetti\" \"Ice cream\"", "1", "What is Peters favorite food?", "3");
    String inputString = String.join(System.lineSeparator(), lines);
    inContent = new ByteArrayInputStream(inputString.getBytes());
    System.setIn(inContent);

    Main.main(new String[]{});

    String[] outputLines = outContent.toString().split(System.lineSeparator());
    assertEquals(outputLines[0], "Choose an option:");
    assertEquals(outputLines[1], "1. Ask a question");
    assertEquals(outputLines[2], "2. Add a question");
    assertEquals(outputLines[3], "Enter your question and answers:");
    assertEquals(outputLines[4], "Added question: What is Peters favorite food");
    assertEquals(outputLines[5], "With answers: ");
    assertEquals(outputLines[6], "Pizza");
    assertEquals(outputLines[7], "Spaghetti");
    assertEquals(outputLines[8], "Ice cream");
    assertEquals(outputLines[9], "Choose an option:");
    assertEquals(outputLines[10], "1. Ask a question");
    assertEquals(outputLines[11], "2. Add a question");
    assertEquals(outputLines[12], "Enter your question:");
    assertEquals(outputLines[13], "Pizza");
    assertEquals(outputLines[14], "Spaghetti");
    assertEquals(outputLines[15], "Ice cream");
}

    @Test
    public void testAskQuestionNotInSystem() {
        List<String> lines = Arrays.asList("1", "When is Peters birthday?", "3");
        String inputString = String.join(System.lineSeparator(), lines);
        inContent = new ByteArrayInputStream(inputString.getBytes());
        System.setIn(inContent);

        Main.main(new String[]{});

        String[] outputLines = outContent.toString().split(System.lineSeparator());
        assertEquals("The answer to life, universe and everything is 42", outputLines[4]);
    }
}