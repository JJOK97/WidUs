package com.naver.myhome.service;

import java.util.List;

import com.naver.myhome.domain.Issue;

public interface IssueService {
	
	public int getIssueId();
	
	public int getListCount();
	
	public List<Issue> getStatusCount();
	
	public List<Issue> getMyWork(String status);

	public List<Issue> getIssueList();
	
	public List<Issue> getFilteredIssueList(String issueType, String issuePriority);

//	public List<Project> getMyProjectList(String userId);
	
	public List<Issue> searchIssues(String searchText);
	
	public void issueAdd(Issue issue);

	public Issue getIssueDetail(int num);
	
	public int updateStatus(int issueId, String status, String selectedUserName);
	
	public int issueUpdate(Issue issue);

	public int issueDelete(int issueId);


}