package bg.sofia.uni.fmi.mjt.imagekit.filesystem;

public enum Formats {
    JPEG(".jpeg"),
    PNG(".png"),
    BMP(".bmp");

    private final String extension;

    Formats(String extension) {
        this.extension = extension;
    }

    String getExtension() {
        return extension;
    }
}
