package io.surabhi.ratingsdataservice.resources;

import io.surabhi.ratingsdataservice.models.Rating;
import io.surabhi.ratingsdataservice.models.UserRating;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/ratingsdata")
public class RatingsResource {

    @RequestMapping("/{movieId}")
    public Rating getRating(@PathVariable("movieId") String movieId) {
        return new Rating(movieId, 4);}

        @RequestMapping("users/{userId}")
        public UserRating getUserRating(@PathVariable("userId") String userId) {
            List<Rating> ratings= Arrays.asList(
                    new Rating("1234",3),
                    new Rating("5678",4),
                    new Rating("2354",1),
                    new Rating("5878",2),
                    new Rating("5078",3)
            );
            UserRating userRating= new UserRating();
            userRating.setUserRating(ratings);
            return userRating;
    }
}