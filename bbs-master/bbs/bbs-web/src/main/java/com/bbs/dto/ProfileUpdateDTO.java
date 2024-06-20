package com.bbs.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class ProfileUpdateDTO {
	@NotEmpty
	private String name;

	@NotEmpty
	private String mail;
	@NotEmpty
	private String head;
}
