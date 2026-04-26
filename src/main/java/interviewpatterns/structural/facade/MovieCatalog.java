package interviewpatterns.structural.facade;

public class MovieCatalog {
    public boolean hasMovie(String movieId) {
        return movieId != null && !movieId.isEmpty();
    }
}
