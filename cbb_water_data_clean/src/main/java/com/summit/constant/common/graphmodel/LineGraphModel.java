package com.summit.constant.common.graphmodel;

import java.util.List;

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
@ApiModel(value = "折线图基础model", description = "折线图基础model")
public class LineGraphModel {

	@ApiModelProperty(name = "图例")
    private List<String> legend;
	
    @ApiModelProperty(name = "x轴")
    private List<String> xAxisData;
    
    @ApiModelProperty(name = "折线数据")
    private List<GraphDataPolylineBase> seriesData;
}