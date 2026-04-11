package bg.sofia.uni.fmi.mjt.imagekit.algorithm;

public interface ImageKernelAlgorithms {

    static final int KERNEL_SIDE = 3;

    default int[][] getHorizontalKernel() {
        return new int[KERNEL_SIDE][KERNEL_SIDE];
    }

    default int[][] getVerticalKernel() {
        return new int[KERNEL_SIDE][KERNEL_SIDE];
    }

    default int[][] getEmptyKernel() {
        return new int[KERNEL_SIDE][KERNEL_SIDE];
    }
}
