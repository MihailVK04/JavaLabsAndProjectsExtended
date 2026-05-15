package bg.uni.fmi.theatre.config;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
@Profile("prod")
public class FileAppLogger implements AppLogger {
    private static final DateTimeFormatter FMT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private final LogLevel configuredLevel;
    private final String logFilePath;

    public FileAppLogger(TheatreProperties properties) {
        this.configuredLevel = properties.getLogLevel();
        this.logFilePath = properties.getLogFile();
        new File(logFilePath).getParentFile().mkdirs();
    }

    @Override
    public void trace(String message) {
        if (LogLevel.TRACE.isEnabled(configuredLevel)) {
            print("TRACE", message);
        }
    }

    @Override
    public void debug(String message) {
        if (LogLevel.DEBUG.isEnabled(configuredLevel)) {
            print("DEBUG", message);
        }
    }

    @Override
    public void info(String message) {
        if (LogLevel.INFO.isEnabled(configuredLevel)) {
            print("INFO", message);
        }
    }

    @Override
    public void error(String message) {
        print("ERROR", message);
    }

    @Override
    public void error(String message, Throwable throwable) {
        print("ERROR", message + " - " + throwable.getMessage());
    }

    private void print(String level, String msg) {
        String line = String.format("[%s] [%s] %s%n", LocalDateTime.now().format(FMT), level, msg);
        try (PrintWriter pw = new PrintWriter(new FileWriter(logFilePath, true))) {
            pw.print(line);
        } catch (IOException e) {
            System.err.println("Log write failed: " + e.getMessage());
        }
    }
}
