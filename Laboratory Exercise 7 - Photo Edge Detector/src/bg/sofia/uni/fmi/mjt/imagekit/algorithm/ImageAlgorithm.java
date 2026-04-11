package bg.sofia.uni.fmi.mjt.imagekit.algorithm;

import java.awt.image.BufferedImage;

/**
 * Represents an algorithm that processes images.
 */
public interface ImageAlgorithm {

    static final int MAX_RGB_VALUE = 255;
    static final int R = 0;
    static final int G = 1;
    static final int B = 2;
    static final int TWO = 2;
    static final int COLOURS = 3;
    static final int ONE_BYTE = 8;
    static final int TWO_BYTES = 16;
    static final int THREE_BYTES = 24;

    default int[] returnRGBValues(int pixel) {
        int[] rgb = new int[COLOURS];
        rgb[R] = (pixel % Math.powExact(TWO, THREE_BYTES)) >> TWO_BYTES;
        rgb[G] = (pixel % Math.powExact(TWO, TWO_BYTES)) >> ONE_BYTE;
        rgb[B] = (pixel % Math.powExact(TWO, ONE_BYTE));
        return rgb;
    }

    default int returnPixelByValues(int[] rgb) {
        return ((rgb[R] << TWO_BYTES) + (rgb[G] << ONE_BYTE) + rgb[B]);
    }

    /**
     * Applies the image processing algorithm to the given image.
     *
     * @param image the image to be processed
     * @return BufferedImage the processed image of type (TYPE_INT_RGB)
     * @throws IllegalArgumentException if the image is null
     */
    BufferedImage process(BufferedImage image);
}