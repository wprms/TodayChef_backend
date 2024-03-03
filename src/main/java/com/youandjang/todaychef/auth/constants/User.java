package com.youandjang.todaychef.auth.constants;

	public enum User {
		SQL_TABLE_PARAM("tableName"),
		SQL_COLUMN_PARAM("columnName"),
		MEMBER_TABLE_NAME("MEMBER_MGT"),
		MEMBER_COLUMN_NAME("sys_id"),
		MEMBER__PASSWORD("password");
		private String code;

		User(String code) {
			this.code = code;
		}

		public String getCode() {
			return code;
		}
	}
	