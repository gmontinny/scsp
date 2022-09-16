package br.gov.mt.controladoria.scsp.resource.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserDto {
	private String user;
	private String pwd;
	private String token;

}
