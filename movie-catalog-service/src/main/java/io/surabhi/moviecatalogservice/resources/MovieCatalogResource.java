package io.surabhi.moviecatalogservice.resources;

import io.surabhi.moviecatalogservice.models.CatalogItem;
import io.surabhi.moviecatalogservice.models.Movie;
import io.surabhi.moviecatalogservice.models.UserRating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private WebClient.Builder webClientBuilder;

    @RequestMapping("/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable("userId") String userId){


        UserRating ratings= restTemplate.getForObject("http://ratings-data-service/ratingsdata/users/"+userId, UserRating.class);
        return ratings.getUserRating().stream().map(rating-> {
                    Movie movie = restTemplate.getForObject("http://movie-info-service/movies/" + rating.getMovieId(), Movie.class);

                    return new CatalogItem(movie.getName(), "Desc", rating.getRating());
                } )
                .collect(Collectors.toList());

    }
}
