package bg.sofia.uni.fmi.mjt.imagekit.filesystem;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryIteratorException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class LocalFileSystemImageManager implements FileSystemImageManager {

    private boolean isInSupportedFormats(String name, Formats[] values) {
        for (Formats f : values) {
            if (name.contains(f.getExtension())) {
                return true;
            }
        }
        return false;
    }

    private String getTheSupportedFormat(String name, Formats[] values) {
        for (Formats f : values) {
            if (name.contains(f.getExtension())) {
                return f.getExtension();
            }
        }
        throw new IllegalArgumentException("The file is not of the supported formats");
    }

    @Override
    public BufferedImage loadImage(File imageFile) throws IOException {

        if ( imageFile == null) {
            throw new IllegalArgumentException("Image file is null");
        } else if (!(imageFile.exists() || imageFile.isFile())) {
            throw new IOException("File does not exists or file is not a file");
        } else if (!isInSupportedFormats(imageFile.getName(), Formats.values())) {
            throw new IOException("File is not of the supported formats");
        }

        try (ImageInputStream imageStream = ImageIO.createImageInputStream(imageFile)) {
            return ImageIO.read(imageStream);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Image file is null", e);
        } catch (IOException e) {
            throw new IOException("Stream cannot be created", e);
        }
    }

    @Override
    public List<BufferedImage> loadImagesFromDirectory(File imagesDirectory) throws IOException {

        if (imagesDirectory == null) {
            throw new IllegalArgumentException("Images directory is null");
        } else if (!imagesDirectory.exists() && !imagesDirectory.isDirectory()) {
            throw new IOException("Directory does not exists or directory is not a directory");
        }

        Path dirpath = imagesDirectory.toPath();
        List<BufferedImage> imageList = new ArrayList<>();
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(dirpath)) {
            for (Path pathFile : stream) {
                File imageFile = pathFile.toFile();
                BufferedImage image = loadImage(imageFile);
                imageList.add(image);
            }
        } catch (IOException | DirectoryIteratorException e) {
            throw new IOException("Could not iterate throughout the directory: " + e.getMessage(), e);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Problem with file: " + e.getMessage(), e);
        }
        return imageList;
    }

    @Override
    public void saveImage(BufferedImage image, File imageFile) throws IOException {
        if (image == null) {
            throw new IllegalArgumentException("Image is null");
        } else if (imageFile == null) {
            throw new IllegalArgumentException("Image file is null");
        } else if (imageFile.exists() || !imageFile.getParentFile().exists()) {
            throw new IOException("File already exists or parent directory does not exists");
        } else if (!imageFile.getParentFile().isDirectory()) {
            throw new IOException("Parent file path expected to be a directory");
        }

        try {
            ImageIO.write(image, getTheSupportedFormat(imageFile.getName(), Formats.values()), imageFile);
        } catch (IOException e) {
            throw new IOException("Cannot create output stream", e);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("A parameter is null or there is a problem with the extension", e);
        }
    }
}
