package com.summit.constant.common.graphmodel;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel(value = "饼图基础model", description = "饼图基础model")
public class PieGraphModel {

	@ApiModelProperty(name = "名称")
    private String name;
	
    @ApiModelProperty(name = "值")
    private  Double value;
}