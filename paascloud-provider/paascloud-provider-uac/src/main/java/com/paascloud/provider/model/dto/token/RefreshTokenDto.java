package com.paascloud.provider.model.dto.token;


import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

/**
 * The class Refresh token dto.
 *
 * @author liyuzhang
 */
@Data
public class RefreshTokenDto {
	@NotBlank
	private String refreshToken;
	@NotBlank
	private String accessToken;
}
