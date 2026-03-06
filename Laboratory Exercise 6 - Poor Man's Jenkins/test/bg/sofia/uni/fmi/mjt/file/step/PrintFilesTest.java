package bg.sofia.uni.fmi.mjt.file.step;

import bg.sofia.uni.fmi.mjt.file.File;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

public class PrintFilesTest {

    private PrintFiles printFiles = new PrintFiles();
    private Collection<File> practiceCollection;

//    @Disabled
    @BeforeEach
    void setUpEmpty() {
        practiceCollection = new HashSet<>();
    }

    @Disabled
    @BeforeEach
    void setUpWithElements() {
        practiceCollection = new HashSet<>();
        practiceCollection.add(new File("Content1"));
        practiceCollection.add(new File("Content2"));
        practiceCollection.add(new File("Content3"));
    }

    @AfterEach
    void tearDown() {
        practiceCollection.clear();
    }


    @Test
    void testProcessCollectionIsNull() {
        assertThrows(IllegalArgumentException.class, () -> printFiles.process(null),
            "Process should throw IllegalArgumentException when input is null");
    }

    @Test
    void testProcessCollectionHasFiles() {
        assertEquals(practiceCollection, printFiles.process(practiceCollection),
            "Process should return size of unempty collection");
    }
}