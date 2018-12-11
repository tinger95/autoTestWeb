package com.autoTestWeb.dao;

import com.autoTestWeb.model.Case;

import java.util.List;

public interface CaseDAO {
	List<Case> findCaseList(Case c);
	Case findCaseById(int id);
	int insertCase(Case executeCase);
	int deleteCase(int id);
	int updateCase(Case executeCase);
	List<Case> findCaseListByUserId(int userId);
	int findCaseCount(Case executeCase);
}
