package bg.sofia.uni.fmi.mjt.file.step;

import bg.sofia.uni.fmi.mjt.file.File;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class SplitFileTest {

    private final String whitespace_regex = "\\s+";
    private SplitFile splitFile = new SplitFile();
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
        assertThrows(IllegalArgumentException.class, () -> splitFile.process(null),
            "Process should throw IllegalArgumentException when input is null");
    }

    @Test
    void testProcessFileContentIsNull() {
        assertThrows(IllegalArgumentException.class, () -> splitFile.process(practiceFile),
            "Process should throw IllegalArgumentException when input content is null");
    }

    @Test
    void testProcessCorrectFileSplit() {
        practiceFile.setContent("There is content and there is a message");
        Set<File> actualResult = splitFile.process(practiceFile);
        String[] words = practiceFile.getContent().split(whitespace_regex);
        boolean allSplit = false;
        for (File f: actualResult) {
            boolean foundWord = false;

            for (String word: words) {

                if (word.equals(f.getContent())) {
                    foundWord = true;
                    break;
                }
            }

            allSplit = foundWord;
            if (!foundWord){
                break;
            }
        }
        assertTrue(allSplit, "File should be split in files with words");
    }
}
