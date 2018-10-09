package com.paascloud.provider.model.dto.gaode;

import com.passcloud.common.base.dto.GaodeBaseDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * The class Gaode location.
 *
 * @author liyuzhang
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class GaodeLocation extends GaodeBaseDto {
	private String province;
	private String city;
	private String adcode;
	private String rectangle;
}
