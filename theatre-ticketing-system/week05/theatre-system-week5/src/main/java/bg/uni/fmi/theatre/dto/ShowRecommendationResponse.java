package bg.uni.fmi.theatre.dto;

public record ShowRecommendationResponse(String title, String genre, String targetAudience, String reason,
    int estimatedDurationMinutes) {

}
