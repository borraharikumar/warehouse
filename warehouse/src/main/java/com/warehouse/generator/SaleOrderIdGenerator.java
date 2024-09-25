package com.warehouse.generator;

import java.util.Random;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

public class SaleOrderIdGenerator implements IdentifierGenerator {

	private static final long serialVersionUID = 6006907030943751697L;

	@Override
	public Object generate(SharedSessionContractImplementor session, Object object) {
		return "SO"+ new Random().nextInt(10000, 99999);
	}

}
