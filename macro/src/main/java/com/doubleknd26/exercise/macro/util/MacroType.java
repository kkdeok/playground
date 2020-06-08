package com.doubleknd26.exercise.macro.util;

public enum MacroType {
	MASK("MASK");

	private String name;

	MacroType(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public static MacroType fromString(String value) {
		for (MacroType macroType : MacroType.values()) {
			if (macroType.name.equalsIgnoreCase(value)) {
				return macroType;
			}
		}
		return null;
	}
}
