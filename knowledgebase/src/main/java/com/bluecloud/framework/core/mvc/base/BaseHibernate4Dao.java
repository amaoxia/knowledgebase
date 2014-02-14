package com.bluecloud.framework.core.mvc.base;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;

import javax.persistence.Id;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.util.Assert;

public class BaseHibernate4Dao<M extends java.io.Serializable, PK extends java.io.Serializable> {
	/*protected static final Logger LOGGER = LoggerFactory.getLogger(BaseHibernate4Dao.class);   

	private final Class<M> entityClass;
	private final String HQL_LIST_ALL;
	private final String HQL_COUNT_ALL;
	private final String HQL_OPTIMIZE_PRE_LIST_ALL;
	private final String HQL_OPTIMIZE_NEXT_LIST_ALL;
	private String pkName = null;

	@SuppressWarnings("unchecked")
	public BaseHibernate4Dao() {
		this.entityClass = (Class<M>) ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];
		Field[] fields = this.entityClass.getDeclaredFields();
		for (Field f : fields) {
			if (f.isAnnotationPresent(Id.class)) {
				this.pkName = f.getName();
			}
		}

		Assert.notNull(pkName);
		HQL_LIST_ALL = "from " + this.entityClass.getSimpleName()
				+ " order by " + pkName + " desc";
		HQL_OPTIMIZE_PRE_LIST_ALL = "from " + this.entityClass.getSimpleName()
				+ " where " + pkName + " > ? order by " + pkName + " asc";
		HQL_OPTIMIZE_NEXT_LIST_ALL = "from " + this.entityClass.getSimpleName()
				+ " where " + pkName + " < ? order by " + pkName + " desc";
		HQL_COUNT_ALL = " select count(*) from "
				+ this.entityClass.getSimpleName();
	}
	
	
	
	
	

	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;

	public Session getSession() {
		//事务必须是开启的，否则获取不到   
		return sessionFactory.getCurrentSession();
	}*/
}
