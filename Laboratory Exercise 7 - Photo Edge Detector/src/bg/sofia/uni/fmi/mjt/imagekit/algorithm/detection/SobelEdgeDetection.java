package bg.sofia.uni.fmi.mjt.imagekit.algorithm.detection;

import bg.sofia.uni.fmi.mjt.imagekit.algorithm.ImageAlgorithm;
import bg.sofia.uni.fmi.mjt.imagekit.algorithm.ImageKernelAlgorithms;

import java.awt.image.BufferedImage;

public class SobelEdgeDetection implements EdgeDetectionAlgorithm, ImageKernelAlgorithms {

    private static final int NEGATIVE = -1;
    private static final int ZERO = 0;
    private static final int ONE = 1;
    private static final int TWO = 2;
    private final ImageAlgorithm algorithm;

    private long convolute(int[][] pixelKernel, int[][] changeKernel) {
        long result = ZERO;

        for (int i = ZERO; i < KERNEL_SIDE; i++) {
            for (int j = ZERO; j < KERNEL_SIDE; j++) {
                result += ((long) changeKernel[i][j] * pixelKernel[i][j]);
            }
        }

        return result;
    }

    private int changePixel(int xCoordinate, int yCoordinate, BufferedImage image) {
        int[][] pixelKernel = getEmptyKernel();

        for (int i = yCoordinate - ONE; i <= yCoordinate + ONE; i++) {
            for (int j = xCoordinate - ONE; j <= xCoordinate + ONE; j++) {
                if (!(i < ZERO || j < ZERO || i >= image.getHeight() || j >= image.getWidth())) {
                    int[] colours = returnRGBValues(image.getRGB(j, i));
                    pixelKernel[j - xCoordinate + ONE][i - yCoordinate + ONE] = colours[ZERO];
                }
            }
        }

        long gX = convolute(pixelKernel, getHorizontalKernel());
        long gY = convolute(pixelKernel, getVerticalKernel());
        double g = Math.sqrt((gX * gX) + (gY * gY));
        int colourValue = Math.min(MAX_RGB_VALUE, (int) Math.round(g));
        int[] rgb = new int[] {colourValue, colourValue, colourValue};
        return returnPixelByValues(rgb);
    }

    public SobelEdgeDetection(ImageAlgorithm grayscaleAlgorithm) {

        if (grayscaleAlgorithm == null) {
            throw new IllegalArgumentException("Grayscale algorithm is null");
        }

        algorithm = grayscaleAlgorithm;
    }

    @Override
    public int[][] getHorizontalKernel() {
        int[][] kernel = new int[KERNEL_SIDE][];
        kernel[ZERO] = new int[] {NEGATIVE * ONE, ZERO, ONE};
        kernel[ONE] = new int[] {NEGATIVE * TWO, ZERO, TWO};
        kernel[TWO] = new int[] {NEGATIVE * ONE, ZERO, ONE};
        return kernel;
    }

    @Override
    public int[][] getVerticalKernel() {
        int[][] kernel = new int[KERNEL_SIDE][];
        kernel[ZERO] = new int[] {NEGATIVE * ONE, NEGATIVE * TWO, NEGATIVE * ONE};
        kernel[ONE] = new int[] {ZERO, ZERO, ZERO};
        kernel[TWO] = new int[] {ONE, TWO, ONE};
        return kernel;
    }

    @Override
    public BufferedImage process(BufferedImage image) {

        if (image == null) {
            throw new IllegalArgumentException("Image is null");
        }

        algorithm.process(image);
        BufferedImage result = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);

        for (int i = image.getMinY(); i < image.getHeight(); i++) {
            for (int j = image.getMinX(); j < image.getWidth(); i++) {

                int pixel = changePixel(j, i, image);
                result.setRGB(j, i, pixel);
            }
        }

        return result;
    }
}
