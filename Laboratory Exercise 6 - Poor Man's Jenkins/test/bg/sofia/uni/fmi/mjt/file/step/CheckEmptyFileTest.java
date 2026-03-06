package bg.sofia.uni.fmi.mjt.file.step;

import bg.sofia.uni.fmi.mjt.file.File;
import bg.sofia.uni.fmi.mjt.file.exception.EmptyFileException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CheckEmptyFileTest {

    private CheckEmptyFile emptyFile = new CheckEmptyFile();
    private File practiceFile;

    @BeforeEach
    void setUp() {
        practiceFile = new File("");
    }

    @AfterEach
    void tearDown() {
        practiceFile = null;
    }

    @Test
     void testProcessFileIsEmpty() {
        EmptyFileException efx = assertThrows(EmptyFileException.class, () -> emptyFile.process(practiceFile));
        assertEquals("Input file or its content is empty or null", efx.getMessage(),
            "Process should throw EmptyFileException when file is empty or null with message " +
                "\"Input file or its content is empty or null\" ");
    }

    @Test
    void testProcessFileIsNull() {
        EmptyFileException efx = assertThrows(EmptyFileException.class, () -> emptyFile.process(null));
        assertEquals("Input file or its content is empty or null", efx.getMessage(),
            "Process should throw EmptyFileException when file is empty or null with message " +
                "\"Input file or its content is empty or null\" ");
    }

    @Test
    void testProcessNormalFile() {
        practiceFile.setContent("Some message");
        assertSame(practiceFile, emptyFile.process(practiceFile),
            "Process should return the same file as the given");
    }
}