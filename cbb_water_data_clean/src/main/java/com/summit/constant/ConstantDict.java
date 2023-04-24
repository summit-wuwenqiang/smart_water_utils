package com.summit.constant;

import com.summit.api.ApiDict;

/**
 * Description: 字典类型 可调用公共远程访问接口
 *
 * @author cy
 * @date 2021年03月04日 9:20
 * Version 1.0
 */
public enum ConstantDict implements ApiDict {

    /**
     * 规则说明
     * PCODE 表示字典的第一级code
     * CODE  表示字典的第二级code 需要指定父级code
     * 名称规则说明： 级别 + 业务表或公共 + 字典字段 + 子类code
     */
    PCODE_COMMON_ENABLED("isEnabled", "公共父类是否启动"),
    CODE_PROJECT_TIME_YEAR_YQ("project_time_year_yq", "project_time_year", "项目时间，远期"),
    PCODE_PROJECT_STATUS("project_status", "项目状态");


    ConstantDict(String code, String pcode, String msg) {
        this.code = code;
        this.pcode = pcode;
        this.msg = msg;
    }

    ConstantDict(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    /**
     * code
     */
    private String code;

    /**
     * 父级code
     */
    private String pcode;

    /**
     * 说明
     */
    private String msg;


    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getPcode() {
        return this.pcode;
    }


    public String getMsg() {
        return msg;
    }
}
