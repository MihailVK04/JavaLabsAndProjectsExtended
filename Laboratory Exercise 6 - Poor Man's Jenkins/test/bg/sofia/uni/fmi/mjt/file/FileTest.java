package bg.sofia.uni.fmi.mjt.file;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FileTest {

    private File file;
    private String content;


    @BeforeEach
    void setUp() {
        content = "Information";
    }

    @AfterEach
    void tearDown() {
        content = null;
        file = null;
    }

    @Test
    void testFileNullContent() {
        assertThrows(IllegalArgumentException.class, () -> file = new File(null),
            "Constructor should throw IllegalArgumentException when content is null");
    }

    @Test
    void testFileNormalContent() {
        file = new File(content);
        assertEquals(file.getContent(), content, "Content should be initialized in the constructor");
    }
}
