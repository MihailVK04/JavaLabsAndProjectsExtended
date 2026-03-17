package bg.sofia.uni.fmi.mjt.imagekit.algorithm.detection;

import bg.sofia.uni.fmi.mjt.imagekit.algorithm.grayscale.LuminosityGrayscale;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.awt.image.BufferedImage;

import static org.junit.jupiter.api.Assertions.*;

class SobelEdgeDetectionTest {

    private SobelEdgeDetection sobelDetection = new SobelEdgeDetection(new LuminosityGrayscale());
    private static int[][] horizontalKernel;
    private static int[][] verticalKernel;
    private static BufferedImage imageActual;
    private static BufferedImage imageExpected;

    @BeforeAll
    static void setUpTestCase() {
        horizontalKernel = new int[][] {{-1, 0, 1}, {-2, 0, 2}, {-1, 0, 1}};
        verticalKernel = new int[][] {{-1, -2, -1}, {0, 0, 0,}, {1, 2, 1}};
        imageExpected = new BufferedImage(4, 4, BufferedImage.TYPE_INT_RGB);
        imageActual = new BufferedImage(4,4, BufferedImage.TYPE_INT_RGB);
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (i == 3 - j || i == 2 - j) {
                    imageActual.setRGB(j, i, 1114112);
                    imageExpected.setRGB(j, i,16777215);
                    continue;
                }
                if( i == 3 && j == 3) {
                    imageActual.setRGB(j, i, 137207);
                    imageExpected.setRGB(j, i, 9474192);
                    continue;
                }
                imageActual.setRGB(j, i, 137207);
                imageExpected.setRGB(j, i,16777215);
            }
        }
    }

    @Test
    void testProcessBufferedImageIsNull() {
        assertThrows(IllegalArgumentException.class, () -> sobelDetection.process(null),
            "Process should throw IllegalArgumentException when image is null");
    }

    @Test
    void testSobelEdgeDetectionAlgorithmIsNull() {
        assertThrows(IllegalArgumentException.class, () -> new SobelEdgeDetection(null),
            "Constructor should throw IllegalArgumentException when algorithm is null");
    }

    @Test
    void testGetHorizontalKernel() {
        assertArrayEquals(horizontalKernel, sobelDetection.getHorizontalKernel(), "Kernels should be equal");
    }

    @Test
    void testGetVerticalKernel() {
        assertArrayEquals(verticalKernel, sobelDetection.getVerticalKernel(), "Kernels should be equal");
    }

    @Test
    void testProcessNormalImage() {
        assertEquals(imageExpected, sobelDetection.process(imageActual), "Images should be identical");
    }
}