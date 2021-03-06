package ma.ac.ensias.model.service.factory;

import ma.ac.ensias.model.service.*;
import ma.ac.ensias.model.service.impl.*;

public class ServiceFactory {

    private static volatile ServiceFactory instance;

    private final UserService userService = new UserServiceImpl();

    private final MovieService movieService = new MovieServiceImpl();

    private final ReviewService reviewService = new ReviewServiceImpl();

    private final RatingService ratingService = new RatingServiceImpl();

    private final LikeService likeService = new LikeServiceImpl();

    private ServiceFactory() { }

    public static ServiceFactory getInstance() {
        if (instance == null) {
            synchronized (ServiceFactory.class) {
                if (instance == null) {
                    instance = new ServiceFactory();
                }
            }
        }
        return instance;
    }

    public UserService getUserService() {
        return userService;
    }

    public MovieService getMovieService() {
        return movieService;
    }

    public ReviewService getReviewService() {
        return reviewService;
    }

    public RatingService getRatingService(){
        return  ratingService;
    }

    public LikeService getLikeService() {
        return likeService;
    }
}
