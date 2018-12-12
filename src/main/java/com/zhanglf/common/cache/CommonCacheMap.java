package com.zhanglf.common.cache;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zlf.bo.StaffBo;
import com.zlf.service.IStaffService;

@Service("CommonCacheMap")
public class CommonCacheMap {
	
	@Resource
	private IStaffService staffService;
	
	@PostConstruct
	public void getOneStaff(){
		StaffBo staffBo = staffService.selectByPrimaryKey("s01");
		System.out.println(staffBo.getName());
	}
}
