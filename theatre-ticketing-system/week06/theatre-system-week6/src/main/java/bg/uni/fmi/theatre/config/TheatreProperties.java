package bg.uni.fmi.theatre.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "theatre")
public class TheatreProperties {

    private static final int RESERVATION_HOLD_MINUTES_INIT = 15;
    private static final int DEFAULT_PAGE_SIZE_INIT = 10;
    private int reservationHoldMinutes = RESERVATION_HOLD_MINUTES_INIT;
    private int defaultPageSize = DEFAULT_PAGE_SIZE_INIT;
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
