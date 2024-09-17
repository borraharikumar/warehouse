package com.warehouse.generator;

import java.util.Random;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

public class DocumentIdGenerator implements IdentifierGenerator {

	private static final long serialVersionUID = -5631973262155005948L;

	@Override
	public Object generate(SharedSessionContractImplementor session, Object object) {
		String code = "DOC";
		Integer id = new Random().nextInt(100, 999);
		return code + id;
	}

}
