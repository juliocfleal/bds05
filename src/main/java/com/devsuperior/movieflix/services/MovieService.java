package com.devsuperior.movieflix.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.movieflix.dto.MovieDTO;
import com.devsuperior.movieflix.entities.Movie;
import com.devsuperior.movieflix.repositories.MovieRepository;
import com.devsuperior.movieflix.services.exceptions.ResourceNotFoundException;

@Service
public class MovieService {

	@Autowired
	private MovieRepository repository;

	public MovieDTO findById(Long id) {
		Movie obj = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("This movie does not exist!"));
		MovieDTO dto = new MovieDTO(obj);
		return dto;
	}

	@Transactional(readOnly = true)
	public Page<MovieDTO> findAllPage(Pageable pageable, Long genreId) {
		Page<Movie> list;
		if (genreId != 0) {
			list = repository.findPaged(pageable, genreId);
		} else {
			list = repository.findAll(pageable);
		}

		Page<MovieDTO> listDto = list.map(x -> new MovieDTO(x));
		return listDto;
	}
}
