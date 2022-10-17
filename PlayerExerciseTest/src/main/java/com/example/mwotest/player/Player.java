package com.example.mwotest.player;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Data
public class Player {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Size(min = 4, max = 26)
	private String name;

	@Size(min = 4, max = 24)
	private String surname;

	private String country;

	private Date birth;

	private Integer length;

	private Integer weight;

	private Long trainerId;

	public Player() {

	}
}
