package com.summit.constant.common.graphmodel;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@Data
@ApiModel(value = "折线图数据model", description = "折线图数据")
public class GraphDataPolylineBase{

    @ApiModelProperty(name = "名称")
    private String name;
    @ApiModelProperty(name = "数据集")
    private List<Double> data;
    
}