package com.warehouse.generator;

import java.util.Random;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

public class PurchaseOrderIdGenerator implements IdentifierGenerator {

	private static final long serialVersionUID = 8844549863384968479L;

	@Override
	public Object generate(SharedSessionContractImplementor session, Object object) {
		String code = "PO";
		Integer id = new Random().nextInt(100000, 99999);
		return code+id;
	}

}
