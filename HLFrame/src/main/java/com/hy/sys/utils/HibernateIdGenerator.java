package com.hy.sys.utils;

import java.io.Serializable;
import java.util.UUID;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.id.IdentifierGenerator;

public class HibernateIdGenerator implements IdentifierGenerator{
	public Serializable generate(SessionImplementor session, Object object) throws HibernateException {
		String uuid=UUID.randomUUID().toString().replace("-", "");
		return uuid;
	}
}
