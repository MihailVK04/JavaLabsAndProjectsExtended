package bg.sofia.uni.fmi.mjt.file.step;

import bg.sofia.uni.fmi.mjt.file.File;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UpperCaseFileTest {

    private UpperCaseFile upperCaseFile = new UpperCaseFile();
    private File practiceFile;

    @BeforeEach
    void setUp() {
        practiceFile = new File("");
        practiceFile.setContent(null);
    }

    @AfterEach
    void tearDown() {
        practiceFile = null;
    }

    @Test
    void testProcessFileIsNull() {
        assertThrows(IllegalArgumentException.class, () -> upperCaseFile.process(null),
            "Process should throw IllegalArgumentException when input is null");
    }

    @Test
    void testProcessFileContentIsNull() {
        assertThrows(IllegalArgumentException.class, () -> upperCaseFile.process(practiceFile),
            "Process should throw IllegalArgumentException when input content is null");
    }

    @Test
    void testProcessFileCorrectWordsTransformation() {
        practiceFile.setContent("I am not shouting, you are.");
        File result = upperCaseFile.process(practiceFile);
        String expectedStringResult = practiceFile.getContent().toUpperCase();
        assertEquals(expectedStringResult, result.getContent(),
            "Words in the output file should be in uppercase");
    }
}
