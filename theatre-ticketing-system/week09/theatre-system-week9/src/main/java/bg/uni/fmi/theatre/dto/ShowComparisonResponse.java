package bg.uni.fmi.theatre.dto;

import java.util.List;

public record ShowComparisonResponse(String show1Title, String show2Title, String recommendation, String reasoning,
                                     List<String> prosShow1, List<String> prosShow2) {
}
