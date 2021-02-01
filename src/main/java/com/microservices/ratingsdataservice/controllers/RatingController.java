package com.microservices.ratingsdataservice.controllers;

import com.microservices.ratingsdataservice.models.Rating;
import com.microservices.ratingsdataservice.models.UserRatings;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/ratings")
public class RatingController {

    private static final Map<String, List<Rating>> ratingsFromDB = new HashMap<>();

    static {
        List<Rating> ratings_1 = Arrays.asList(Rating.builder().movieId("movie_id_1").rating(4.5).build(),
                Rating.builder().movieId("movie_id_2").rating(4.6).build(),
                Rating.builder().movieId("movie_id_3").rating(3.8).build());

        List<Rating> ratings_2 = Arrays.asList(Rating.builder().movieId("movie_id_4").rating(4.0).build(),
                Rating.builder().movieId("movie_id_5").rating(4.1).build(),
                Rating.builder().movieId("movie_id_6").rating(4.2).build());

        List<Rating> ratings_3 = Arrays.asList(Rating.builder().movieId("movie_id_1").rating(3.6).build(),
                Rating.builder().movieId("movie_id_2").rating(4.6).build(),
                Rating.builder().movieId("movie_id_4").rating(4.1).build());

        List<Rating> ratings_4 = Arrays.asList(Rating.builder().movieId("movie_id_1").rating(3.6).build(),
                Rating.builder().movieId("movie_id_4").rating(4.4).build(),
                Rating.builder().movieId("movie_id_6").rating(4.7).build());


        ratingsFromDB.put("user_id_1", ratings_1);
        ratingsFromDB.put("user_id_2", ratings_2);
        ratingsFromDB.put("user_id_3", ratings_3);
        ratingsFromDB.put("user_id_4", ratings_4);
    }

    @GetMapping(value = "/user/{userId}")
    public UserRatings getRating(@PathVariable(value = "userId") String id) {
        List<Rating> ratings =  ratingsFromDB.get(id);

        return UserRatings.builder()
                .ratings(ratings)
                .build();
    }
}
