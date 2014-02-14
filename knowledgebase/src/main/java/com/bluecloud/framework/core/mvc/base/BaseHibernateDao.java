package com.bluecloud.framework.core.mvc.base;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.apache.commons.beanutils.PropertyUtils;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.type.LongType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate4.SessionFactoryUtils;
import org.springframework.stereotype.Repository;

import com.bluecloud.framework.AppConstant;
import com.bluecloud.framework.core.mvc.pager.PaginationSupport;
import com.bluecloud.framework.core.mvc.sqlmanager.SQLManager;

/**
 * 
 * <p>
 * 通过dao类
 * </p>
 * 
 * @author dafei
 * 
 * @param <T>
 * @param <PK>
 */
@Repository("baseDao")
public class BaseHibernateDao<T, PK extends java.io.Serializable> {
	protected static final Logger logger = LoggerFactory
			.getLogger(BaseHibernate4Dao.class);

	 // 实体类类型(由构造方法自动赋值)
    private Class<T> entityClass;

    @SuppressWarnings("unchecked")
	public BaseHibernateDao() {
		 this.entityClass = null;
		 Class c = getClass();
	        Type t = c.getGenericSuperclass();
	        if (t instanceof ParameterizedType)
	        {
	            Type[] p = ((ParameterizedType) t).getActualTypeArguments();
	            this.entityClass = (Class<T>) p[0];
	        }
	}

	// -------------------- 基本检索、增加、修改、删除操作 --------------------

	// 根据主键获取实体。如果没有相应的实体，返回 null。
	public T get(PK id) {
		return (T) getSession().get(entityClass, id);
	}

	// 根据主键获取实体并加锁。如果没有相应的实体，返回 null。
	public T getWithLock(PK id, LockMode lock) {
		T t = (T) getSession().get(entityClass, id, lock);
		if (t != null) {
			this.flush(); // 立即刷新，否则锁不会生效。
		}
		return t;
	}

	// 获取全部实体。
	public List<T> loadAll() {
		return (List<T>) getSession().createCriteria(entityClass).list();
	}

	// 存储实体到数据库
	public void save(T entity) {
		getSession().save(entity);
	}

	// 增加或更新实体
	public void saveOrUpdate(T entity) {
		getSession().saveOrUpdate(entity);
	}

	// 更新实体
	public void update(T entity) {
		getSession().update(entity);
	}

	/**
	 * 根据主键及要修改的实体属性来更新数据库中记录
	 * 
	 * @param pk
	 * @param cols
	 * @param entity
	 * @throws Exception
	 */
	public void update(PK id, T sourceObj, String[] cols) throws Exception {
		T entity = this.get(id);
		if (entity == null)
			return;
		BeanUtils.copyProperties(sourceObj, entity, cols);
		this.update(entity);
	}

	// 更新实体并加锁
	public void updateWithLock(T entity, LockMode lock) {
		getSession().update(entity);
		getSession().lock(entity, lock);
		this.flush(); // 立即刷新，否则锁不会生效。
	}

	// 删除指定的实体
	public void delete(T entity) {
		getSession().delete(entity);
	}

	// 根据主键删除指定实体
	public void deleteByKey(PK id) {
		this.delete(this.get(id));
	}

	// 加锁并删除指定的实体
	public void deleteWithLock(T entity, LockMode lock) {
		getSession().delete(entity);
		getSession().lock(entity, lock);
		this.flush(); // 立即刷新，否则锁不会生效。
	}

	// 根据主键加锁并删除指定的实体
	public void deleteByKeyWithLock(PK id, LockMode lock) {
		this.deleteWithLock(this.get(id), lock);
	}

	// -------------------- HSQL ----------------------------------------------

	// 使用HSQL语句检索数据
	public List find(String queryString) {
		return getSession().createQuery(queryString).list();
	}

	// 使用带命名参数的命名HSQL语句检索数据
	public List findByParam(String queryName, String[] paramNames,
			Object[] values) {
		Query queryObject = getSession().getNamedQuery(queryName);
		if (values != null) {
			for (int i = 0; i < values.length; i++) {
				queryObject.setParameter(i, values[i]);
			}
		}
		return queryObject.list();
	}

	public Object getEntityByPropname(String propname, String propvalue,
			String entityname) {
		String hql = " from " + entityname + " t where " + propname + "='"
				+ propvalue + "' ";
		List list = getSession().createQuery(hql).list();
		if (list == null || list.size() < 1)
			return null;
		return list.get(0);
	}

	public int getCountByQuery(final String hql, Object... values) {
		String countQueryString = " select count(*) "
				+ removeSelect(removeOrders(hql));
		Query queryObject = getSession().createQuery(countQueryString);
		if (values != null) {
			for (int i = 0; i < values.length; i++) {
				queryObject.setParameter(i, values[i]);
			}
		}
		Object count = queryObject.uniqueResult();
		return count == null ? 0
				: ((Long) count).intValue();
	}

	// hql分页数据
	public PaginationSupport loadPageResultByHQuery(final String hql,
			final int pageSize, final int startIndex, Object... values) {
		int totalCount = getCountByQuery(hql, values);
		if (totalCount < 1)
			return new PaginationSupport(new ArrayList(0), 0);
		Query query = createQuery(hql, values);
		List items = query.setFirstResult(startIndex).setMaxResults(pageSize)
				.list();
		PaginationSupport ps = new PaginationSupport(items, totalCount,
				pageSize, startIndex);
		return ps;
	}

	// 使用HSQL语句直接增加、更新、删除实体
	public int bulkUpdate(String hql) {
		Query queryObject = getSession().createQuery(hql);
		return queryObject.executeUpdate();
	}

	public int bulkUpdate(String hql, Object... values) {
		Query queryObject = getSession().createQuery(hql);
		if (values != null) {
			for (int i = 0; i < values.length; i++) {
				queryObject.setParameter(i, values[i]);
			}
		}
		return queryObject.executeUpdate();
	}

	// -------------------------------- sqlquery
	// --------------------------------

	public List getResultList(String sql, Class entityClass, Object... values) throws Exception {
		List items = null;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = SessionFactoryUtils.getDataSource(sessionFactory)
					.getConnection();
			ps = conn.prepareStatement(sql);
			if (values != null) {
				for (int i = 1; i <= values.length; i++) {
					ps.setObject(i, values[i-1]);
				}
			}
			rs = ps.executeQuery();

			items = SQLManager.mappingList(rs, entityClass);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (ps != null) {
				ps.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
		return items;
	}

	public List getResultList(String sql, Class entityClass, String[] props)
			throws Exception {
		List items = null;
		try {
			SQLQuery sqlQuery = getSession().createSQLQuery(sql);
			items = SQLManager.mappingList(sqlQuery.list(), entityClass, props);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return items;
	}

	/**
	 * 分页方法
	 * 
	 * @param sql
	 * @param values
	 * @param entityClass
	 * @param props
	 * @param pageSize
	 * @param startIndex
	 * @return
	 * @throws Exception
	 */
	public PaginationSupport loadPageResultBySQLQuery(String sql,
			Object[] values, Class entityClass, final int pageSize,
			final int startIndex) throws Exception {
		PaginationSupport pager = null;
		int totalCount = getCountBySqlQuery(sql, values);
		if (totalCount < 1)
			return new PaginationSupport(new ArrayList(0), 0);
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			sql = getPagerSql(sql, pageSize, startIndex);
			conn = SessionFactoryUtils.getDataSource(sessionFactory)
					.getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			List items = SQLManager.mappingList(rs, entityClass);
			pager = new PaginationSupport(items, totalCount, pageSize,
					startIndex);
		} catch (Exception e) {
			throw e;
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (ps != null) {
				ps.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
		return pager;
	}

	private int getCountBySqlQuery(final String sql, Object... values) {
		String countQueryString = " select count(*) as totalrow from ("
				+ removeOrders(sql) + ") t";
		SQLQuery sqlQuery = getSession().createSQLQuery(countQueryString);
		sqlQuery.addScalar("totalrow");
		Object totalrow = sqlQuery.list().get(0);
		if (totalrow instanceof BigDecimal) {
			return ((BigDecimal) totalrow).intValue();
		} else if (totalrow instanceof Long) {
			return ((Long) totalrow).intValue();
		}
		return ((Integer) totalrow).intValue();
	}

	/**
	 * 分页sql
	 * 
	 * @param sql
	 * @param pageSize
	 * @param startIndex
	 * @return
	 * @throws Exception
	 */
	private String getPagerSql(String sql, int pageSize, int startIndex)
			throws Exception {
		String newSQL = "";
		String dbtype = AppConstant.getConfig("sys.database.type");
		if (dbtype == null || "".equals(dbtype)) {
			throw new Exception("The Database's type is not config.");
		}
		if (dbtype.toUpperCase().equals("MYSQL")) {
			sql = "select t.* from(" + sql + ") t limit " + startIndex + ","
					+ pageSize;
			logger.info(sql);
		} else if (dbtype.toUpperCase().equals("ORACLE")) {
			int num = startIndex > 1 ? startIndex - 1 : 0;
			int maxNum = pageSize * (startIndex == 0 ? 1 : startIndex);
			int minNum = pageSize * num;
			newSQL = " select t.* from (";
			newSQL += "select t.*,rownum rn from(" + sql
					+ ") t where rownum <  " + maxNum;
			newSQL += " ) t where rn > " + minNum;
			logger.info(newSQL);
		}
		return newSQL;
	}

	// -------------------------------- Criteria ------------------------------

	// 创建与会话无关的检索标准
	public DetachedCriteria createDetachedCriteria() {
		return DetachedCriteria.forClass(this.entityClass);
	}

	// 创建与会话绑定的检索标准
	public Criteria createCriteria() {
		return this.createDetachedCriteria().getExecutableCriteria(
				this.getSession());
	}

	// 检索满足标准的数据
	public List findByCriteria(DetachedCriteria criteria) {
		Criteria executableCriteria = criteria.getExecutableCriteria(getSession());
		return executableCriteria.list();
	}

	// 检索满足标准的数据，返回指定范围的记录
	public List findByCriteria(DetachedCriteria criteria, int firstResult,
			int maxResults) {
		Criteria executableCriteria = criteria.getExecutableCriteria(getSession());
		if (firstResult >= 0) {
			executableCriteria.setFirstResult(firstResult);
		}
		if (maxResults > 0) {
			executableCriteria.setMaxResults(maxResults);
		}
		return executableCriteria.list();
	}

	// 使用指定的实体及属性检索（满足除主键外属性＝实体值）数据
	public List<T> findEqualByEntity(T entity, String[] propertyNames) {
		Criteria criteria = this.createCriteria();
		Example exam = Example.create(entity);
		exam.excludeZeroes();
		String[] defPropertys = sessionFactory.getClassMetadata(
				entityClass).getPropertyNames();
		for (String defProperty : defPropertys) {
			int ii = 0;
			for (ii = 0; ii < propertyNames.length; ++ii) {
				if (defProperty.equals(propertyNames[ii])) {
					criteria.addOrder(Order.asc(defProperty));
					break;
				}
			}
			if (ii == propertyNames.length) {
				exam.excludeProperty(defProperty);
			}
		}
		criteria.add(exam);
		return (List<T>) criteria.list();
	}

	// 使用指定的实体及属性检索（满足属性 like 串实体值）数据
	public List<T> findLikeByEntity(T entity, String[] propertyNames) {
		Criteria criteria = this.createCriteria();
		for (String property : propertyNames) {
			try {
				Object value = PropertyUtils.getProperty(entity, property);
				if (value instanceof String) {
					criteria.add(Restrictions.like(property, (String) value,
							MatchMode.ANYWHERE));
					criteria.addOrder(Order.asc(property));
				} else {
					criteria.add(Restrictions.eq(property, value));
					criteria.addOrder(Order.asc(property));
				}
			} catch (Exception ex) {
				// 忽略无效的检索参考数据。
			}
		}
		return (List<T>) criteria.list();
	}

	// 使用指定的检索标准获取满足标准的记录数
	public Integer getRowCount(DetachedCriteria criteria) {
		criteria.setProjection(Projections.rowCount());
		List list = this.findByCriteria(criteria, 0, 1);
		return (Integer) list.get(0);
	}

	// 使用指定的检索标准检索数据，返回指定统计值(max,min,avg,sum)
	public Object getStatValue(DetachedCriteria criteria, String propertyName,
			String StatName) {
		if (StatName.toLowerCase().equals("max"))
			criteria.setProjection(Projections.max(propertyName));
		else if (StatName.toLowerCase().equals("min"))
			criteria.setProjection(Projections.min(propertyName));
		else if (StatName.toLowerCase().equals("avg"))
			criteria.setProjection(Projections.avg(propertyName));
		else if (StatName.toLowerCase().equals("sum"))
			criteria.setProjection(Projections.sum(propertyName));
		else
			return null;
		List list = this.findByCriteria(criteria, 0, 1);
		return list.get(0);
	}

	// -------------------------------- Others --------------------------------

	public Query createQuery(String hql, Object... values) {
		Query query = getSession().createQuery(hql);
		if (values != null) {
			for (int i = 0; i < values.length; i++) {
				query.setParameter(i, values[i]);
			}
		}
		return query;
	}

	// 加锁指定的实体
	public void lock(T entity, LockMode lock) {
		getSession().lock(entity, lock);
	}

	// 强制初始化指定的实体
	public void initialize(Object proxy) {
		Hibernate.initialize(proxy);
	}

	// 强制立即更新缓冲数据到数据库（否则仅在事务提交时才更新）
	public void flush() {
		getSession().flush();
	}

	private static String removeSelect(String hql) {
		int beginPos = hql.toLowerCase().indexOf("from");
		return hql.substring(beginPos);
	}

	private static String removeOrders(String hql) {
		Pattern p = Pattern.compile("order\\s*by[\\w|\\W|\\s|\\S]*",
				Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(hql);
		StringBuffer sb = new StringBuffer();
		while (m.find()) {
			m.appendReplacement(sb, "");
		}
		m.appendTail(sb);
		return sb.toString();
	}

	public Long querySeqByName(String SeqName) {
		Long seqValue = (Long) getSession().createSQLQuery(
				"select " + SeqName + ".nextval as id from dual").addScalar(
				"id", LongType.INSTANCE).uniqueResult();
		return seqValue;
	}
	
	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;

	public Session getSession() {
		//事务必须是开启的，否则获取不到   
		return sessionFactory.getCurrentSession();
	}
}
