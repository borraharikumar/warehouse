package com.warehouse.util;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public interface CollectionUtil {
	
	public static Map<String, String> converListToMap(List<Object[]> list) {
		return list.stream().collect(Collectors.toMap(ob->ob[0].toString(), ob->ob[1].toString()));
	}

}
