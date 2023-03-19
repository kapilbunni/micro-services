package com.microservices.user.service.services.impl;

import com.microservices.user.service.entities.Hotel;
import com.microservices.user.service.entities.Rating;
import com.microservices.user.service.entities.User;
import com.microservices.user.service.exceptions.ResourceNotFoundException;
import com.microservices.user.service.repositories.UserRepository;
import com.microservices.user.service.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.UUID;
import java.util.Arrays;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

//    RestTemplate bean will not be present with spring and throws the error message
//    "No bean of RestTemplate type found"
    @Autowired
    private RestTemplate restTemplate;
    @Override
    public User saveUser(User user) {
        String randomId = UUID.randomUUID().toString();
        user.setUserId(randomId);
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUser(String id) {
//use lambda function to create and pass object as param
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User with given id is not found on server !! "+id));

//fetching ratings of the above user from rating service
//here we are using rest template to call the api

    // When we try to get any element from the list it is giving the error : " class java.util.LinkedHashMap cannot be cast to class com.microservices.user.service.entities.Rating (java.util.LinkedHashMap is in module java.base of loader 'bootstrap'
    // So using a different approach even though this is way more feasible to implement

    // Reason for the above error is we have given List.class in restTemplate.getForEntity but it is not possible to specify what this list should contain.
    // So, our resttemplate results formed as list, but it is list of some object(linked hash map) but not Rating.
//        List<Rating> ratings = restTemplate.getForEntity("http://localhost:8083/ratings/user/"+id, List.class).getBody();

    // another approach
        Rating [] ratings = restTemplate.getForEntity("http://localhost:8083/ratings/user/"+id, Rating [].class).getBody();
        List<Rating> userRatings = Arrays.asList(ratings);

//in the ratings we have hotel object, we can fetch the hotel object by passing the hotelId which we can get from ratings
//fetching hotel based on hotelId

        List<Rating> userRatingsNew = userRatings.stream().map(rating -> {
            Hotel hotel = restTemplate.getForObject("http://localhost:8082/hotels/"+rating.getHotelId(),Hotel.class);
            System.out.println(hotel.toString(hotel));
            rating.setHotel(hotel);
            return rating;
        }).collect(Collectors.toList());


    // Another way using foreach loop
//        for(Rating rating : userRatings) {
//            System.out.println(rating.getClass().getName());
//            Hotel hotel = restTemplate.getForObject("http://localhost:8082/hotels/"+rating.getHotelId(),Hotel.class);
//            rating.setHotel(hotel);
//        }

// There is one error I'm not getting the hotelId in hotel object

//        {
//            "userId": "c700adc4-c854-4a2b-8491-ca53e3599f9d",
//                "name": "kapil bunni",
//                "email": "kapil.bunni2zessta.com",
//                "about": "I love dogs",
//                "ratings": [
//            {
//                "ratingId": "e97c4362-c94a-492e-919e-a38e22b3e73d",
//                    "userId": "c700adc4-c854-4a2b-8491-ca53e3599f9d",
//                    "hotelId": "a61342fc-c900-40ff-bf66-bdcda929289a",
//                    "rating": 5,
//                    "feedback": "Hotel is pet friendly",
//                    "hotel": {
//                "hotelId": null,
//                        "name": "comfy homes",
//                        "location": "Hyderabad",
//                        "about": "lake view is out USP"
//            }
//            }
//    ]
//        }

        user.setRatings(userRatingsNew);
        return user;
    }
}
