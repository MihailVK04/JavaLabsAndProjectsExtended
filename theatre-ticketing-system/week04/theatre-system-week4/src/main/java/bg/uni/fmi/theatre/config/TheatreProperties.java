package bg.uni.fmi.theatre.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "theatre")
public class TheatreProperties {

    private int reservationHoldMinutes = 15;
    private int defaultPageSize = 10;
    private LogLevel logLevel = LogLevel.INFO;
    private String logFile = "logs/theatre.log";

    public int getReservationHoldMinutes() {
        return reservationHoldMinutes;
    }

    public void setReservationHoldMinutes(int reservationHoldMinutes) {
        this.reservationHoldMinutes = reservationHoldMinutes;
    }

    public int getDefaultPageSize() {
        return defaultPageSize;
    }

    public void setDefaultPageSize(int defaultPageSize) {
        this.defaultPageSize = defaultPageSize;
    }

    public LogLevel getLogLevel() {
        return logLevel;
    }

    public void setLogLevel(LogLevel logLevel) {
        this.logLevel = logLevel;
    }

    public String getLogFile() {
        return logFile;
    }

    public void setLogFile(String logFile) {
        this.logFile = logFile;
    }
}
