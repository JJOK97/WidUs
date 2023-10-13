package com.naver.myhome.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.naver.myhome.domain.Issue;
import com.naver.myhome.mybatis.mapper.IssueMapper;

@Service
public class IssueServiceImpl implements IssueService{
	
	private IssueMapper mapper;
	
	@Autowired
	public IssueServiceImpl(IssueMapper mapper) {
		this.mapper = mapper;
	}
	
	@Override
	public int getIssueId() {
		return mapper.getIssueId();
	}
	
	@Override
	public int getListCount() {
		return mapper.getListCount();
	}
	
	@Override
	public List<Issue> getStatusCount() {
		return mapper.getStatusCount();
	}
	
	@Override
	public List<Issue> getMyWork(String status) {
		return mapper.getMyWork(status);
	}


	@Override
	public List<Issue> getIssueList() {
		return mapper.getIssueList();
	}
	
	@Override
	public List<Issue> getFilteredIssueList(String issueType, String issuePriority) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("issueType", issueType);
		map.put("issuePriority", issuePriority);
		
		return mapper.getFilteredIssueList(map);
	}

//	@Override
//	public List<Project> getMyProjectList(String userId) {
//		return mapper.getMyProjectList(userId);
//	}
	
	@Override
	public List<Issue> searchIssues(String searchText) {
		return mapper.searchIssues(searchText);
	}

	@Override
	public void issueAdd(Issue issue) {
		mapper.issueAdd(issue);
	}

	@Override
	public Issue getIssueDetail(int num) {
		return mapper.getIssueDetail(num);
	}
	
	@Override
	public int updateStatus(int issueId, String status, String selectedUserName) {
		return mapper.updateStatus(issueId, status, selectedUserName);
		
	}
	
	@Override
	public int issueUpdate(Issue issue) {
		return mapper.issueUpdate(issue);
	}

	@Override
	public int issueDelete(int issueId) {
		return mapper.issueDelete(issueId);
	}



}