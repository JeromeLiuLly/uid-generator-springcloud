package com.candao.dms.jpa.assigned;

import java.io.Serializable;
import java.util.Properties;

import org.hibernate.HibernateException;
import org.hibernate.MappingException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.id.AbstractUUIDGenerator;
import org.hibernate.id.Configurable;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.type.Type;

import com.candao.dms.uid.service.UIDGeneratorUtil;

/**
 * 分布式ID 自动化注入
 * 
 * @author jeromeLiu
 *
 */
public class AssignedSequenceGenerator extends AbstractUUIDGenerator implements Configurable {
	
	/**
	 * 业务前缀
	 */
	private String serverPrefix;
	
	
	@Override
	public Serializable generate(SessionImplementor session, Object obj) throws HibernateException {
		return UIDGeneratorUtil.getUidWithCache(serverPrefix);
	}

	@Override
	public void configure(Type type, Properties params, ServiceRegistry d) throws MappingException {
		this.serverPrefix = params.getProperty("serverPrefix");
	}

}