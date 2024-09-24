package com.warehouse.generator;

import java.util.Random;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

public class GrnIdGenerator implements IdentifierGenerator {

	private static final long serialVersionUID = 8167270857068519568L;

	@Override
	public Object generate(SharedSessionContractImplementor session, Object object) {
		String code = "GRN";
		Integer id = new Random().nextInt(10000, 99999);
		return code + id;
	}

}
