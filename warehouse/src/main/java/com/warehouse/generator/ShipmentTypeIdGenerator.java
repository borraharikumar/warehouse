package com.warehouse.generator;

import java.util.Random;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

public class ShipmentTypeIdGenerator implements IdentifierGenerator {

	private static final long serialVersionUID = -5800423486935979997L;

	@Override
	public Object generate(SharedSessionContractImplementor session, Object object) {
		String code = "ST";
		Integer id = new Random().nextInt(10000, 99999);
		return code + id;
	}

}
