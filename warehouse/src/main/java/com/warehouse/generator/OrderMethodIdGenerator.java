package com.warehouse.generator;

import java.util.Random;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

public class OrderMethodIdGenerator implements IdentifierGenerator {

	private static final long serialVersionUID = 1356441845589780372L;

	@Override
	public Object generate(SharedSessionContractImplementor session, Object object) {
		String code = "OM";
		Integer id = new Random().nextInt(10000, 99999);
		return code + id;
	}

}
