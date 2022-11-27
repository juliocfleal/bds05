package com.devsuperior.movieflix.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devsuperior.movieflix.dto.ReviewDTO;
import com.devsuperior.movieflix.entities.Movie;
import com.devsuperior.movieflix.entities.Review;
import com.devsuperior.movieflix.entities.User;
import com.devsuperior.movieflix.repositories.MovieRepository;
import com.devsuperior.movieflix.repositories.ReviewRepository;
import com.devsuperior.movieflix.services.exceptions.ResourceNotFoundException;

@Service
public class ReviewService {

	
	@Autowired
	private ReviewRepository repository;
	
	@Autowired
	private MovieRepository movieRepository;
	
	@Autowired
	private AuthService authService;

	public ReviewDTO insert(ReviewDTO dto) {
		User user = authService.athenticated();
		Review obj = new Review();
		Movie movie = movieRepository.findById(dto.getMovieId()).orElseThrow(()-> new ResourceNotFoundException("Movie invalid!"));
		obj.setMovie(movie);
		obj.setText(dto.getText());
		obj.setUser(user);
		obj = repository.save(obj);
		ReviewDTO response = new ReviewDTO(obj);
		response.setUser(user);
		return response;
		
	}
	
}
