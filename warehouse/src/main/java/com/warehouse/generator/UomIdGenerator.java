package com.warehouse.generator;

import java.util.Random;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

public class UomIdGenerator implements IdentifierGenerator {

	private static final long serialVersionUID = -6069693660929455348L;

	@Override
	public Object generate(SharedSessionContractImplementor session, Object object) {
		String code = "UOM";
		Integer id = new Random().nextInt(1000, 9999);
		return code + id;
	}

}
