package bg.uni.fmi.theatre;

import bg.uni.fmi.theatre.domain.Show;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.List;

@SpringBootApplication
public class TheatreApplication implements CommandLineRunner {

	@Autowired
	private ShowController showController;

	@Autowired
	private ShowService showService;

	@Autowired
	private ApplicationContext context;

	public static void main(String[] args) {
		SpringApplication.run(TheatreApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		System.out.println("🚀 Application started successfully!");

		// Add Shows
		showService.addShow("Hamlet", "Drama", 180);
		showService.addShow("The Nutcracker", "Ballet", 120);

		try {
			showService.addShow("", "Comedy", 90);
		} catch (IllegalArgumentException ex) {
			System.out.println(ex);
		}

		System.out.println("---------------------------------------");
		System.out.println("✅ Shows added successfully!");
		System.out.println("---------------------------------------");

		// Display All Shows
		System.out.println("📌 Displaying all shows:");
		showController.displayAllShows();
		System.out.println("---------------------------------------");

		System.out.println("🔄 Updating 'Hamlet' duration to 200 minutes...");
		showController.updateShow(1, "Hamlet", "Drama", 200);

		System.out.println("---------------------------------------");
		System.out.println("📌 Displaying updated shows:");
		showController.displayAllShows();

		System.out.println("---------------------------------------");
		System.out.println("📌 Displaying all shows by genre 'Drama':");
		List<Show> dramaShows = showController.getShowsByGenre("Drama");
		dramaShows.stream().forEach(System.out::println);
		System.out.println("---------------------------------------");
	}
}
