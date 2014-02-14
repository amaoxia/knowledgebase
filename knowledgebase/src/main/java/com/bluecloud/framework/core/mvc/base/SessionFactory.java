package com.bluecloud.framework.core.mvc.base;

import java.io.IOException;   
import java.io.Reader;   
import java.util.List;
  
import org.apache.ibatis.io.Resources;   
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.mapping.ParameterMode;
import org.apache.ibatis.mapping.ResultMap;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.factory.DefaultObjectFactory;
import org.apache.ibatis.reflection.factory.ObjectFactory;
import org.apache.ibatis.reflection.property.PropertyTokenizer;
import org.apache.ibatis.reflection.wrapper.DefaultObjectWrapperFactory;
import org.apache.ibatis.reflection.wrapper.ObjectWrapperFactory;
import org.apache.ibatis.scripting.xmltags.ForEachSqlNode;
import org.apache.ibatis.session.SqlSessionFactory;   
import org.apache.ibatis.session.SqlSessionFactoryBuilder;     
public final class SessionFactory {   
    private String resource="config/mybatis/sqlconfig.xml";   
    private SqlSessionFactory sqlSessionFactory = null;   
    private static SessionFactory sessionFactory = new SessionFactory();
    private static final ObjectFactory DEFAULT_OBJECT_FACTORY = new DefaultObjectFactory();
    private static final ObjectWrapperFactory DEFAULT_OBJECT_WRAPPER_FACTORY = new DefaultObjectWrapperFactory();
       
    private SessionFactory() {   
        try {   
            Reader reader = Resources.getResourceAsReader(resource);   
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader); 
        } catch (IOException e) {   
            System.out.println("#IOException happened in initialising the SessionFactory:"+e.getMessage());   
            throw new ExceptionInInitializerError(e);   
        }   
    }   
    public static SessionFactory getInstance() {   
        return sessionFactory;   
    }   
    public SqlSessionFactory getSqlSessionFactory() {   
        return sqlSessionFactory;   
    }   
  
    public static IbatisSql getIbatisSql(String id, Object parameterObject) {    
    	IbatisSql ibatisSql = new IbatisSql();    

        MappedStatement ms = SessionFactory.getInstance().getSqlSessionFactory().getConfiguration().getMappedStatement(id);    
        BoundSql boundSql = ms.getBoundSql(parameterObject);    
        
        List<ResultMap> ResultMaps=ms.getResultMaps();    
        if(ResultMaps!=null&&ResultMaps.size()>0){    
            ResultMap ResultMap = ms.getResultMaps().get(0);    
            ibatisSql.setResultClass(ResultMap.getType());    
        }    
        ibatisSql.setSql(boundSql.getSql());
        
        List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();    
        if (parameterMappings != null) {    
            Object[] parameterArray = new Object[parameterMappings.size()];    
            MetaObject metaObject = parameterObject == null ? null : MetaObject.forObject(parameterObject, DEFAULT_OBJECT_FACTORY, DEFAULT_OBJECT_WRAPPER_FACTORY);    
            for (int i = 0; i < parameterMappings.size(); i++) {    
              ParameterMapping parameterMapping = parameterMappings.get(i);    
              if (parameterMapping.getMode() != ParameterMode.OUT) {    
                Object value;    
                String propertyName = parameterMapping.getProperty();    
                PropertyTokenizer prop = new PropertyTokenizer(propertyName);    
                if (parameterObject == null) {    
                  value = null;    
                } else if (ms.getConfiguration().getTypeHandlerRegistry().hasTypeHandler(parameterObject.getClass())) {    
                  value = parameterObject;    
                } else if (boundSql.hasAdditionalParameter(propertyName)) {    
                  value = boundSql.getAdditionalParameter(propertyName);    
                } else if (propertyName.startsWith(ForEachSqlNode.ITEM_PREFIX)    
                    && boundSql.hasAdditionalParameter(prop.getName())) {    
                  value = boundSql.getAdditionalParameter(prop.getName());    
                  if (value != null) {    
                    value = MetaObject.forObject(value, DEFAULT_OBJECT_FACTORY, DEFAULT_OBJECT_WRAPPER_FACTORY).getValue(propertyName.substring(prop.getName().length()));    
                  }    
                } else {    
                  value = metaObject == null ? null : metaObject.getValue(propertyName);    
                }    
                parameterArray[i] = value;    
              }    
            }    
            ibatisSql.setParameters(parameterArray);    
        }
         return ibatisSql;
    }   
}  
