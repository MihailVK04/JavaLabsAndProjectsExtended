package bg.uni.fmi.theatre.ai;

import bg.uni.fmi.theatre.dto.SearchFiltersResponse;
import bg.uni.fmi.theatre.dto.ShowComparisonResponse;
import bg.uni.fmi.theatre.dto.ShowRecommendationResponse;
import bg.uni.fmi.theatre.dto.ShowResponse;
import bg.uni.fmi.theatre.dto.ShowSummaryResponse;
import bg.uni.fmi.theatre.service.ShowService;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.ChatOptions;
import org.springframework.stereotype.Service;

@Service
public class AiShowService {

    private final ChatClient chatClient;
    private final ShowService showService;

    public AiShowService(ChatClient.Builder builder, ShowService showService) {
        this.chatClient = builder
            .defaultSystem("You are a helpful theatre assistant. "
                + "You recommend shows based on user preferences. "
                + "Keep answers concise - 2-3 sentences max.")
            .build();
        this.showService = showService;
    }

    public String recommendShow(String userPreferences) {
        return chatClient.prompt()
            .user("I'm looking for: " + userPreferences + ". Recommend a theatre show and explain why.")
            .call()
            .content();
    }

    public ShowRecommendationResponse recommendShowStructured(String userPreferences) {
        return chatClient.prompt()
            .user("I'm looking for: " + userPreferences + ". Recommend a theatre show.")
            .call()
            .entity(ShowRecommendationResponse.class);
    }

    public ShowRecommendationResponse recommendWithTemperature(String userPreferences, double temperature) {
        return chatClient.prompt()
            .user("I'm looking for: " + userPreferences + ". Recommend a theatre show.")
            .options(ChatOptions.builder().temperature(temperature).build())
            .call()
            .entity(ShowRecommendationResponse.class);
    }

    public ShowSummaryResponse generateSummary(Long showId) {
        ShowResponse show = showService.getShowById(showId);

        return chatClient.prompt()
            .user("Generate a summary for this theatre event:\n"
                + "Title: " + show.title() + "\n"
                + "Genre: " + show.genre() + "\n"
                + "Duration: " + show.durationMinutes() + " minutes\n"
                + "Age rating: " + show.ageRating() + "\n\n"
                + "Write a 2-3 sentence summary, identify the target audience, "
                + "and list 2-4 highlights.")
            .call()
            .entity(ShowSummaryResponse.class);
    }

    public SearchFiltersResponse parseSearchQuery(String naturalLanguageQuery) {
        return chatClient.prompt()
            .user("Extract search filters from this query: \"" + naturalLanguageQuery + "\"\n\n"
                + "Available genres: DRAMA, COMEDY, MUSICAL, OPERA, BALLET\n"
                + "Rules:\n"
                + "- titleKeyword: a keyword to match show titles, or null\n"
                + "- genre: one of the available genres, or null if not mentioned\n"
                + "- maxDurationMinutes: max duration if mentioned (e.g. 'short' = 120), or null\n")
            .call()
            .entity(SearchFiltersResponse.class);
    }

    public ShowComparisonResponse compareShows(Long showId1, Long showId2, String occasion) {
        ShowResponse show1 = showService.getShowById(showId1);
        ShowResponse show2 = showService.getShowById(showId2);

        return chatClient.prompt()
            .user("Compare these two theatre shows for a " + occasion + ":\n\n"
                + "Show 1: " + show1.title() + " (" + show1.genre()
                + ", " + show1.durationMinutes() + " min)\n"
                + "Show 2: " + show2.title() + " (" + show2.genre()
                + ", " + show2.durationMinutes() + " min)\n\n"
                + "Which show is better for this occasion and why?")
            .call()
            .entity(ShowComparisonResponse.class);
    }
}
