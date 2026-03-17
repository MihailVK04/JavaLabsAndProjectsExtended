package bg.sofia.uni.fmi.mjt.imagekit.algorithm.grayscale;


import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.awt.image.BufferedImage;

import static org.junit.jupiter.api.Assertions.*;

class LuminosityGrayscaleTest {

    private LuminosityGrayscale grayscale = new LuminosityGrayscale();
    private static BufferedImage imageActual;
    private static BufferedImage imageExpected;

    @BeforeAll
    static void setUpTestCase() {
        imageExpected = new BufferedImage(4, 4, BufferedImage.TYPE_INT_RGB);
        imageActual = new BufferedImage(4,4, BufferedImage.TYPE_INT_RGB);
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (i == 3 - j || i == 2 - j) {
                    imageActual.setRGB(j, i, 1167616);
                    imageExpected.setRGB(j, i,10132122);
                    continue;
                }
                imageActual.setRGB(j, i, 137207);
                imageExpected.setRGB(j, i,22369622);
            }
        }
    }

    @Test
    void testProcessBufferedImageIsNull() {
        assertThrows(IllegalArgumentException.class, () -> grayscale.process(null),
            "Process should throw IllegalArgumentException when image is null");
    }

    @Test
    void testProcessNormalImage() {
        assertEquals(imageExpected, grayscale.process(imageActual), "Images should be identical");
    }
}