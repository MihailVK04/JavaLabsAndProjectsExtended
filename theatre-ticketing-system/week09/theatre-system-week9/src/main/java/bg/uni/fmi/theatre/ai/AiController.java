package bg.uni.fmi.theatre.ai;

import bg.uni.fmi.theatre.dto.PageResponse;
import bg.uni.fmi.theatre.dto.SearchFiltersResponse;
import bg.uni.fmi.theatre.dto.ShowComparisonResponse;
import bg.uni.fmi.theatre.dto.ShowRecommendationResponse;
import bg.uni.fmi.theatre.dto.ShowResponse;
import bg.uni.fmi.theatre.dto.ShowSummaryResponse;
import bg.uni.fmi.theatre.service.ShowService;
import bg.uni.fmi.theatre.vo.Genre;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/ai")
public class AiController {

    private static final int DEFAULT_PAGE = 0;
    private static final int DEFAULT_PAGE_SIZE = 10;
    private final AiShowService aiShowService;
    private final ShowService showService;

    public AiController(AiShowService aiShowService, ShowService showService) {
        this.aiShowService = aiShowService;
        this.showService = showService;
    }

    @GetMapping("/recommend")
    public String recommend(@RequestParam String preferences) {
        return aiShowService.recommendShow(preferences);
    }

    @GetMapping("/recommend/structured")
    public ShowRecommendationResponse recommendStructured(@RequestParam String preferences) {
        return aiShowService.recommendShowStructured(preferences);
    }

    @GetMapping("/recommend/experiment")
    public ShowRecommendationResponse experiment(@RequestParam String preferences,
                                                 @RequestParam(defaultValue = "0.7") double temperature) {
        return aiShowService.recommendWithTemperature(preferences, temperature);
    }

    @GetMapping("/shows/{id}/summary")
    public ShowSummaryResponse showSummary(@PathVariable Long id) {
        return aiShowService.generateSummary(id);
    }

    @GetMapping("/search")
    public PageResponse<ShowResponse> naturalLanguageSearch(@RequestParam String query) {
        SearchFiltersResponse filtersResponse = aiShowService.parseSearchQuery(query);
        Genre genre = null;

        if (filtersResponse.genre() != null) {
            try {
                genre = Genre.valueOf(filtersResponse.genre());
            }  catch (IllegalArgumentException ignored) {

            }
        }
        return showService.searchShows(filtersResponse.titleKeyword(),  genre, DEFAULT_PAGE, DEFAULT_PAGE_SIZE);
    }

    @GetMapping("/compare")
    public ShowComparisonResponse compareShows(@RequestParam Long showId1, @RequestParam Long showId2,
                                               @RequestParam(defaultValue = "evening out") String occasion) {
        return aiShowService.compareShows(showId1, showId2, occasion);
    }
}
