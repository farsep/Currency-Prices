package src.model;

import java.util.Map;

public record TransitionApi(String base_code, Map<String, Double> conversion_rates) {
    public TransitionApi {
        if (base_code == null || base_code.isBlank()) {
            throw new IllegalArgumentException("base_code cannot be null or empty");
        }
        if (conversion_rates == null) {
            throw new IllegalArgumentException("conversion_rates cannot be null");
        }
    }
}
