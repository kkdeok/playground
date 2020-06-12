package com.doubleknd26.exercise.macro;

import com.beust.jcommander.IStringConverter;
import com.beust.jcommander.ParameterException;
import com.doubleknd26.exercise.macro.util.MacroType;

import java.util.Arrays;
import java.util.stream.Collectors;

public class MacroTypeConverter implements IStringConverter<MacroType> {
	@Override
	public MacroType convert(String value) {
		MacroType macroType = MacroType.fromString(value);
		if(macroType == null) {
			throw new ParameterException(
					String.format("Value %s cannot be converted to MacroType. Available values are: %s.", 
							value, 
							Arrays.stream(MacroType.values())
									.map(MacroType::getName)
									.collect(Collectors.joining(","))));
		}
		return macroType;
	}
}
