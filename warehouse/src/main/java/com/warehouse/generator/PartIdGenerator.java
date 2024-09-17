package com.warehouse.generator;

import java.util.Random;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

public class PartIdGenerator implements IdentifierGenerator {

	private static final long serialVersionUID = -3632376641298335851L;

	@Override
	public Object generate(SharedSessionContractImplementor session, Object object) {
		String code = "PART";
		Integer id = new Random().nextInt(10000, 99999);
		return code+id;
	}

}
