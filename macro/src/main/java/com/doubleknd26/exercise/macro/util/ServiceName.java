package com.doubleknd26.exercise.macro.util;

public enum ServiceName {
	COUPANG("COUPANG");
	
	private String name;

	ServiceName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public static ServiceName fromString(String value) {
		for (ServiceName serviceName : ServiceName.values()) {
			if (serviceName.name.equalsIgnoreCase(value)) {
				return serviceName;
			}
		}
		return null;
	}
}
