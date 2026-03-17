package bg.sofia.uni.fmi.mjt.imagekit.algorithm.grayscale;

import java.awt.image.BufferedImage;

public class LuminosityGrayscale implements GrayscaleAlgorithm {

    private static final double RED_COEFFICIENT = 0.21;
    private static final double GREEN_COEFFICIENT = 0.72;
    private static final double BLUE_COEFFICIENT = 0.07;

    private int grayscaleOfPixel(int r, int g, int b) {

        long gray = Math.round((RED_COEFFICIENT * r) + (GREEN_COEFFICIENT * g) + (BLUE_COEFFICIENT * b));
        return Math.min(MAX_RGB_VALUE, (int) gray);
    }

    private int changePixel(int pixel) {
        int[] rgb = returnRGBValues(pixel);
        int grayValue = grayscaleOfPixel(rgb[R], rgb[G], rgb[B]);

        rgb[R] = grayValue;
        rgb[G] = grayValue;
        rgb[B] = grayValue;

        return returnPixelByValues(rgb);
    }

    @Override
    public BufferedImage process(BufferedImage image) {

        if (image == null) {
            throw new IllegalArgumentException("Image is null");
        }

        for (int i = image.getMinY(); i < image.getHeight(); i++) {
            for (int j = image.getMinX(); j < image.getWidth(); j++) {

                int pixel = image.getRGB(j, i);
                pixel = changePixel(pixel);
                image.setRGB(j, i, pixel);
            }
        }

        return image;
    }
}
