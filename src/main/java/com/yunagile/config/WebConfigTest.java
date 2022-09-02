package com.yunagile.config;

import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.core.type.TypeReference;
import com.yunagile.common.util.JsonHelper;
import com.yunagile.system.ContextHolder;
import com.yunagile.system.ContextUtils;
import com.yunagile.system.actions.Context;
@Configuration 
public class WebConfigTest {
	@Bean 
	public ContextUtils getContextUtils() {
		final Context ctt = new Context();
		String t  = "{\"currentUserFullName\":\"/临沧财贸学校系统管理员/管理员\",\"currentPersonCode\":\"system\",\"currentDeptFullID\":\"/ORG01.org/1DB40834F00A4723901C671B2146B180.pos\",\"currentPositionID\":\"1DB40834F00A4723901C671B2146B180\",\"currentOrgFullName\":\"//系统管理员\",\"currentPersonFullID\":\"/ORG01.org/1DB40834F00A4723901C671B2146B180.pos/PSN01@1DB40834F00A4723901C671B2146B180.psm\",\"currentDeptFullCode\":\"/yunagile/manager\",\"currentPositionFullID\":\"/ORG01.org/1DB40834F00A4723901C671B2146B180.pos\",\"loginDate\":\"2020-01-10 19:24:37\",\"currentDeptName\":\"系统管理员\",\"currentOgnFullID\":\"/ORG01.org\",\"personCode\":\"system\",\"currentOgnName\":\"临沧财贸学校\",\"currentOgnFullName\":\"/临沧财贸学校\",\"currentPersonFullName\":\"/临沧财贸学校系统管理员/管理员\",\"currentDeptCode\":\"manager\",\"currentUserCode\":\"system\",\"currentPositionFullCode\":\"/yunagile/manager\",\"loginName\":\"system\",\"bsessionid\":\"99135E482F02C5424402AF0D0D7ED07F\",\"personID\":\"PSN01\",\"currentUserFullCode\":\"/yunagile/manager/system\",\"currentDeptFullName\":\"/临沧财贸学校/系统管理员\",\"currentOrgFullCode\":\"/yunagile/manager\",\"currentPositionName\":\"系统管理员\",\"currentOrgCode\":\"manager\",\"currentOgnID\":\"ORG01\",\"currentUserName\":\"管理员\",\"currentPersonName\":\"管理员\",\"currentOgnFullCode\":\"/yunagile\",\"currentOgnCode\":\"yunagile\",\"currentOrgID\":\"1DB40834F00A4723901C671B2146B180\",\"personName\":\"管理员\",\"currentDeptID\":\"1DB40834F00A4723901C671B2146B180\",\"currentPositionFullName\":\"/临沧财贸学校/系统管理员\",\"currentPersonID\":\"PSN01\",\"currentUserID\":\"PSN01@1DB40834F00A4723901C671B2146B180\",\"currentOrgFullID\":\"/ORG01.org/1DB40834F00A4723901C671B2146B180.pos\",\"currentPersonFullCode\":\"/yunagile/manager/system\",\"currentOrgName\":\"系统管理员\",\"currentUserFullID\":\"/ORG01.org/1DB40834F00A4723901C671B2146B180.pos/PSN01@1DB40834F00A4723901C671B2146B180.psm\",\"currentPositionCode\":\"manager\",\"username\":\"管理员\"}";
		Map<String,String> map = JsonHelper.fromJson(t, new TypeReference<Map<String,String>>() {});
		ctt.initLoginContext(map);
		ContextUtils a = new ContextUtils(new ContextHolder() {
			
			
			@Override
			public String getSessionId() { 
				return ctt.getSessionID();
			}
			
			@Override
			public Context getContext(String sessionId) { 
				return ctt;
			}
			
			@Override
			public Context getContext() { 
				return ctt;
			}
		});
		return a;
	}
}
