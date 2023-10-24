package com.naver.myhome.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.naver.myhome.domain.Employee;
import com.naver.myhome.domain.User;
import com.naver.myhome.mybatis.mapper.EmployeeMapper;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	private EmployeeMapper dao;
	
	@Autowired
	public EmployeeServiceImpl(EmployeeMapper dao) {
		this.dao = dao;
	}
	
	//주영START
	@Override
	public Employee getEmplyeeInfoById(User user) {
		return dao.getEmplyeeInfoById(user);
	}

	@Override
	public int insert(Employee employee) {
		return dao.insert(employee);
	}
	//주영END
	
}
