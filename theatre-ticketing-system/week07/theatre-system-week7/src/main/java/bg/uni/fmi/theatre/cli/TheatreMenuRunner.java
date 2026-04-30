package bg.uni.fmi.theatre.cli;

import bg.uni.fmi.theatre.config.AppLogger;
import bg.uni.fmi.theatre.config.TheatreProperties;
import bg.uni.fmi.theatre.exception.NotFoundException;
import bg.uni.fmi.theatre.exception.ValidationException;
import bg.uni.fmi.theatre.service.PerformanceService;
import bg.uni.fmi.theatre.service.ShowService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class TheatreMenuRunner implements CommandLineRunner {

    private static final int ZERO = 0;
    private final ShowService showService;
    private final PerformanceService performanceService;
    private final AppLogger logger;
    private final TheatreProperties properties;

    private void optionListShow() {
        var shows = showService.getAllShows();
        if (shows.isEmpty()) {
            System.out.println("No shows found.");
        } else {
            shows.forEach(s -> System.out.printf("  [%d] %s (%s)%n", s.id(), s.title(),
                s.genre()));
        }
    }

    private void optionSearch(Scanner scanner) {
        System.out.print("Title search: ");
        String q = scanner.nextLine().trim();
        try {
            showService.searchShows(q, null, ZERO, properties.getDefaultPageSize())
                .content().forEach(s -> System.out.printf("  [%d] %s%n", s.id(), s.title()));
        } catch (ValidationException e) {
            System.out.println("Validation: " + e.getMessage());
        }
    }

    private void optionPerformance(Scanner scanner) {
        System.out.print("Show ID: ");
        try {
            Long id = Long.parseLong(scanner.nextLine().trim());
            showService.getShowById(id);
            performanceService.findPerformancesByShow(id)
                .forEach(p -> System.out.printf("  [%d] %s%n", p.id(), p.startTime()));
        } catch (NumberFormatException e) {
            System.out.println("Invalid ID.");
        } catch (NotFoundException e) {
            System.out.println("Not found: " + e.getMessage());
        }
    }

    private boolean optionExit() {
        logger.info("CLI exiting.");
        System.out.println("Goodbye!");
        return false;
    }

    public TheatreMenuRunner(ShowService showService, PerformanceService performanceService, AppLogger logger,
                             TheatreProperties properties) {
        this.showService = showService;
        this.performanceService = performanceService;
        this.logger = logger;
        this.properties = properties;
    }

    @Override
    public void run(String... args) throws Exception {
        logger.info("Theatre CLI started - page size: " + properties.getDefaultPageSize() + ", hold: " +
            properties.getReservationHoldMinutes() + "min");

        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        System.out.println("\n=== Theatre Ticketing System (Week 06 - REST API) ===");

        while (running) {
            System.out.println("\n1. List shows | 2. Search | 3. Performance | 0. Exit");
            System.out.print("Choice: ");
            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1" -> optionListShow();
                case "2" -> optionSearch(scanner);
                case "3" -> optionPerformance(scanner);
                case "0" -> running = optionExit();
                default -> System.out.println("Unknown option.");
            }
        }
    }
}
