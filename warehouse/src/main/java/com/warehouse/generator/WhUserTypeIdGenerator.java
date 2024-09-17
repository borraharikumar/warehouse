package com.warehouse.generator;

import java.util.Random;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

public class WhUserTypeIdGenerator implements IdentifierGenerator {

	private static final long serialVersionUID = 618481882181916691L;

	@Override
	public Object generate(SharedSessionContractImplementor session, Object object) {
		String code = "WUT";
		Integer id = new Random().nextInt(1000, 9999);
		return code + id;
	}

}
