package com.bluecloud.framework.core.security.listerner;
import java.util.HashMap;
import java.util.Map;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.core.Authentication;

import com.bluecloud.framework.core.security.bean.UserDetailsEX;

public class LockUserListener implements ApplicationListener<ApplicationEvent> {
	
	public void onApplicationEvent(ApplicationEvent event) {
		//密码错误事件
        if (event instanceof AuthenticationFailureBadCredentialsEvent) {
            AuthenticationFailureBadCredentialsEvent authEvent = (AuthenticationFailureBadCredentialsEvent) event;
            Authentication authentication = (Authentication) authEvent.getSource();
            String username = (String) authentication.getPrincipal();
            addCount(username);
        }
        //密码成功事件
        if (event instanceof AuthenticationSuccessEvent) {
            AuthenticationSuccessEvent authEvent = (AuthenticationSuccessEvent) event;
            Authentication authentication = (Authentication) authEvent.getSource();
            UserDetailsEX user = (UserDetailsEX)authentication.getPrincipal();
            String username = user.getUsername();
            cleanCount(username);
        }
    }

	/**
	 * 增加密码输入错误的次数
	 * @param username
	 * @author ChenHua
	 * create on 2012-5-9  下午2:13:47
	 */
    protected void addCount(String username) {
        Map<String, Integer> lockUserMap = getLockUserMap();
        Integer count = lockUserMap.get(username);

        if (count == null) {
            lockUserMap.put(username, Integer.valueOf(1));
        } else {
            int resultCount = count.intValue() + 1;

            if (resultCount > 3) {
            	//修改数据库，锁定账户 //未完全，应该调用dao保存数据库的
                //User user = (User) myUserDetailsService.loadUserByUsername(username);
                //lock 操作
            } else {
                lockUserMap.put(username, Integer.valueOf(resultCount));
            }
        }
    }
    /**
     * 清除密码输入错误的次数，同时清楚账号锁定状态
     * @param username
     * @author ChenHua
     * create on 2012-5-9  下午2:14:00
     */
    protected void cleanCount(String username) {
        Map<String, Integer> lockUserMap = getLockUserMap();
        if (lockUserMap.containsKey(username)) {
            lockUserMap.remove(username);
        }
    }

    /**
     * 获取所有锁定的账号
     * @return
     * @author ChenHua
     * create on 2012-5-9  下午2:14:04
     */
    protected Map<String, Integer> getLockUserMap() {
		return new HashMap<String, Integer>();
    }
}

