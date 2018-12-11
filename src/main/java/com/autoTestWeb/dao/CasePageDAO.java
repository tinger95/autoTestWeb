package com.autoTestWeb.dao;

import com.autoTestWeb.model.CasePage;

import java.util.List;

public interface CasePageDAO {
    List<CasePage> findCasePageListByBaseCaseId(int baseCaseId);
    List<CasePage> findParentCasePageListByBaseCaseId(int baseCaseId);
    List<CasePage> findChildCasePageListByParentId(int parentId);
    int insertCasePage(CasePage caseScript);
    List<CasePage> findCasePageList(CasePage casePage);
    int deleteCasePageByBaseCaseId(int baseCaseId);
    int findMaxSort(CasePage casePage);
    CasePage findCasePageById(int id);
    int updateCasePageSort(CasePage casePage);
    int deleteCasePage(int id);
    int updateCasePageSortMinus(CasePage casePage);
}
