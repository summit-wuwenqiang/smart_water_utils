package com.summit.constant.common.constant;

/**
 *枚举变量
 */
public enum ConstantDict {
		UNKONW_ERROR("-1", "未知错误"), 
		SUCCESS("0", "调用成功"), 
		ERROR("1", "失败"),
		FAIL_QUERY("2", "未知异常"),
		FAIL_ADD("3", "添加失败"),
		SYSTEM_ERROR("4", "查询信息"),
		FAIL_UPDATE("5", "更新失败"),
		FAIL_DELETE("6", "删除失败");
		
		private String code;
		private String msg;

		ConstantDict(String code, String msg) {
			this.code = code;
			this.msg = msg;
		}

		public String getCode() {
			return code;
		}

		public String getMsg() {
			return msg;
		}
	
}
