package com.summit.constant.common.graphmodel;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 * 
 * @author wu
 *
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel(value = "柱状图基础model", description = "柱状图基础model")
public class BarGraphModel {

    @ApiModelProperty(value = "x轴数据")
    private List<String> xAxisData;

    @ApiModelProperty(value = "y轴数据1")
    private List<Double> seriesData;

    @ApiModelProperty(value = "y轴数据2")
    private List<Double> seriesData2;
    
    @ApiModelProperty(value = "y轴数据3")
    private List<Double> seriesData3;
}