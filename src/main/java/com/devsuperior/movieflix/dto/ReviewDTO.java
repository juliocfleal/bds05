package com.devsuperior.movieflix.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import com.devsuperior.movieflix.entities.Review;
import com.devsuperior.movieflix.entities.User;

public class ReviewDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	@NotBlank(message = "Campo Obrigatório")
	private String text;
	
	private Long movieId;
	
	private UserDTO user = new UserDTO();
	
	public ReviewDTO() {
	}

	
	public ReviewDTO(Long id, String text, Long movieId) {
		super();
		this.id = id;
		
		this.text = text;
		
		this.movieId = movieId;
	}
	
	public ReviewDTO(Review rev) {
		this.id = rev.getId();
		this.text = rev.getText();
		this.movieId = rev.getMovie().getId();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Long getMovieId() {
		return movieId;
	}

	public void setMovieId(Long movieId) {
		this.movieId = movieId;
	}


	public UserDTO getUser() {
		return user;
	}


	public void setUser(User obj) {
		this.user.setId(obj.getId());
		this.user.setEmail(obj.getEmail());
		this.user.setName(obj.getName());
	}

	
}
