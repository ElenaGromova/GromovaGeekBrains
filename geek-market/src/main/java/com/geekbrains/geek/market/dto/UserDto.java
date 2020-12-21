package com.geekbrains.geek.market.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
public class UserDto {
	@Size(min = 4, message = "Username length must be at least 4 symbols")
    private String username;
    private String password;
	private String confirmPassword;
}
