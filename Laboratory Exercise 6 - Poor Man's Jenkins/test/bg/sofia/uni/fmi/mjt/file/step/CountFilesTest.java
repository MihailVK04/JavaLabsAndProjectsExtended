package bg.sofia.uni.fmi.mjt.file.step;

import bg.sofia.uni.fmi.mjt.file.File;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

class CountFilesTest {

    private CountFiles countFiles = new CountFiles();
    private Collection<File> practiceCollection;

    @BeforeEach
    void setUp() {
        practiceCollection = new HashSet<>();
    }

    @AfterEach
    void tearDown() {
        practiceCollection.clear();
    }

    @Test
    void testProcessCollectionIsNull() {
        assertThrows(IllegalArgumentException.class, () -> countFiles.process(null),
            "Process should throw IllegalArgumentException when input collection is null");
    }

    @Test
    void testProcessCollectionIsEmpty() {
        assertEquals(0, countFiles.process(practiceCollection),
            "Process should return 0 for empty collection");
    }

    @Test
    void testProcessCollectionHasFiles() {
        practiceCollection.add(new File("Content1"));
        practiceCollection.add(new File("Content2"));
        practiceCollection.add(new File("Content3"));
        assertEquals(practiceCollection.size(), countFiles.process(practiceCollection),
            "Process should return size of unempty collection");
    }
}