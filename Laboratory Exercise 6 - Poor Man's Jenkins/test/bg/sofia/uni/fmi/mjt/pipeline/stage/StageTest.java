package bg.sofia.uni.fmi.mjt.pipeline.stage;

import bg.sofia.uni.fmi.mjt.file.File;
import bg.sofia.uni.fmi.mjt.file.step.CheckEmptyFile;
import bg.sofia.uni.fmi.mjt.file.step.UpperCaseFile;
import bg.sofia.uni.fmi.mjt.pipeline.step.Step;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class StageTest {

    private Step<File,File> stepMock;
    private Stage<File,File> stage;

    @BeforeEach
    void setUp() {
        stage = null;
        stepMock = mock(Step.class);
    }

    @AfterEach
    void tearDown() {
        stage = null;
        stepMock = null;
    }

    @Test
    void testStartInitialStepIsNull() {
        assertThrows(IllegalArgumentException.class, () -> stage = Stage.start(null),
            "Start should throw IllegalArgumentException when initial step is null");
    }

    @Test
    void testStartNormalOutcome() {
        stage = Stage.start(stepMock);
        assertNotNull(stage,"Stage should be initialized");
    }

    @Test
    void testAddStepNewStepIsNull() {
        stage = Stage.start(stepMock);
        assertThrows(IllegalArgumentException.class, () -> stage = stage.addStep(null),
            "Add step should throw IllegalArgumentException when new step is null");
    }

    @Test
    void testAddStepNormalOutcome() {
        stage = Stage.start(new CheckEmptyFile());
        Stage<File,File> newStage = stage.addStep(new UpperCaseFile());
        assertSame(stage, newStage, "Add step should return the same instance casted type");
    }

    @Test
    void testExecuteNormalStep() {
        File practice = new File("Content");
        when(stepMock.process(practice)).thenReturn(practice);
        stage = Stage.start(stepMock);
        assertEquals(practice.getContent(), stage.execute(practice).getContent(),
            "Execute should return the result of processed Steps");

    }

    @Test
    void testExecuteThrowOfException() {
        File practice = new File("Content");
        when(stepMock.process(practice)).thenThrow(new IllegalArgumentException("Something bad"));
        stage = Stage.start(stepMock);
        assertThrows(IllegalArgumentException.class, () -> stage.execute(practice),
            "Execute should throw the exception in the process");

    }
}