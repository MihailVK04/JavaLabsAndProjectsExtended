package tasks.task26;

import java.util.*;

// --- ISP: small, focused interfaces ---
interface LogFormatter {
    String format(String level, String message);
}

interface LogDestination {
    void write(String formattedMessage);
}

// --- OCP: new formatters/destinations without modifying Logger ---
class SimpleFormatter implements LogFormatter {
    public String format(String level, String message) {
        return "[" + level + "] " + message;
    }
}

class ConsoleDestination implements LogDestination {
    public void write(String msg) { System.out.println(msg); }
}

class FileDestination implements LogDestination {
    private final String filename;
    FileDestination(String filename) { this.filename = filename; }
    public void write(String msg) {
        System.out.println("(→ " + filename + ") " + msg);
    }
}

// --- SRP: Logger only orchestrates; doesn't format or write ---
// --- DIP: depends on abstractions (interfaces), not concrete classes ---
class Logger {
    private final LogFormatter formatter;
    private final List<LogDestination> destinations;
    private final Set<String> enabledLevels;

    Logger(LogFormatter formatter, List<LogDestination> destinations, Set<String> levels) {
        this.formatter = formatter;
        this.destinations = destinations;
        this.enabledLevels = levels;
    }

    void log(String level, String message) {
        if (!enabledLevels.contains(level)) return;
        String formatted = formatter.format(level, message);
        for (LogDestination dest : destinations) dest.write(formatted);
    }

    void info(String msg)  { log("INFO", msg); }
    void warn(String msg)  { log("WARN", msg); }
    void error(String msg) { log("ERROR", msg); }
}

class Main {
    public static void main(String[] args) {
        Logger logger = new Logger(
            new SimpleFormatter(),
            List.of(new ConsoleDestination(), new FileDestination("app.log")),
            Set.of("INFO", "WARN", "ERROR")
        );

        logger.info("Application started");
        logger.warn("Memory usage high");
        logger.error("Connection lost");
    }
}
