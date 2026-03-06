package bg.sofia.uni.fmi.mjt.pipeline;

import bg.sofia.uni.fmi.mjt.file.File;
import bg.sofia.uni.fmi.mjt.pipeline.stage.Stage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class PipelineTest {

    private Pipeline<File,File> pipeline;
    private Stage<File,File> stageMock;
    private Cache cache;

    @BeforeEach
    void setUp() {
        pipeline = null;
        stageMock = mock(Stage.class);
        cache = mock(Cache.class);
    }

    @AfterEach
    void tearDown() {
        pipeline = null;
        stageMock = null;
    }

    @Test
    void testStartInitialStepIsNull() {
        assertThrows(IllegalArgumentException.class, () -> pipeline = Pipeline.start(null),
            "Start should throw IllegalArgumentException when initial step is null");
    }

    @Test
    void testStartNormalOutcome() {
        pipeline = Pipeline.start(stageMock);
        assertNotNull(pipeline,"Pipeline should be initialized");
    }

    @Test
    void testAddStepNormalOutcome() {
        pipeline = Pipeline.start(stageMock);
        Pipeline<File,File> newPipeline = pipeline.addStage(stageMock);
        assertSame(pipeline, newPipeline, "Add step should return the same instance casted type");
    }

    @Test
    void testExecuteNormalStepWithCache() {
        File practice = new File("Content");
        when(cache.containsKey(practice)).thenReturn(true);
        when(cache.getCachedValue(practice)).thenReturn(practice);
        pipeline = new Pipeline<>(List.of(stageMock),cache);
        assertEquals(practice.getContent(), pipeline.execute(practice).getContent(),
            "Execute should return the result of processed Steps");

    }

    @Test
    void testExecuteNormalStepWithoutCache() {
        File practice = new File("Content");
        when(cache.containsKey(practice)).thenReturn(false);
        when(stageMock.execute(practice)).thenReturn(practice);
        pipeline = new Pipeline<>(List.of(stageMock),cache);
        assertEquals(practice.getContent(), pipeline.execute(practice).getContent(),
            "Execute should return the result of processed Steps");

    }

    @Test
    void testExecuteThrowOfException() {
        File practice = new File("Content");
        when(cache.containsKey(practice)).thenReturn(false);
        when(stageMock.execute(practice)).thenThrow(new IllegalArgumentException());
        pipeline = new Pipeline<>(List.of(stageMock),cache);
        assertThrows(IllegalArgumentException.class, () -> pipeline.execute(practice),
            "Execute should throw the exception in the process");

    }
}