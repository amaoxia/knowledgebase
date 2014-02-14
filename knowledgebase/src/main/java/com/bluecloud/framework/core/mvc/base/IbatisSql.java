package com.bluecloud.framework.core.mvc.base;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class IbatisSql {
	private String sql;//运行期的sql，带?    
    private Object[] parameters;//;//运行期的参数，与?相匹配    
    private Class resultClass;//<select id="XXX" resultType="ZZZ">中的resultType    
   
    public Class getResultClass() {    
        return resultClass;    
    }    
   
    public void setResultClass(Class resultClass) {    
        this.resultClass = resultClass;    
    }    
   
    public void setSql(String sql) {    
        this.sql = sql;    
    }    
   
    public String getSql() {    
        return sql;    
    }    
   
    public void setParameters(Object[] parameters) {    
        this.parameters = parameters;    
    }    
   
    public Object[] getParameters() {    
        return parameters;    
    }    
    
    @Override  
    public String toString() {  
        if(parameters == null || sql == null){  
            return "";  
        }  
        List<Object> parametersArray = Arrays.asList(parameters);  
        List<Object> list = new ArrayList<Object>(parametersArray);  
        while(sql.indexOf("?") != -1 && list.size() > 0 && parameters.length > 0){  
            sql = sql.replaceFirst("\\?", list.get(0).toString());  
            list.remove(0);
        }  
        return sql.replaceAll("(\r?\n(\\s*\r?\n)+)", "\r\n");  
    }  
}
