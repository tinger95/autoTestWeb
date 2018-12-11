package com.autoTestWeb.dao;

import com.autoTestWeb.model.CaseData;

import java.util.List;

public interface CaseDataDAO {
    List<CaseData> findCaseDataListByCasePageId(int caseScriptId);
    CaseData findCaseData(CaseData caseData);
    int insertCaseData(CaseData caseData);
    int findMaxSort(int casePageId);
    int deleteCaseDataByCasePageId(int casePageId);
    int updateCaseData(CaseData caseData);
    int deleteCaseDataByDataMapId(int dataMapId);
    int updateCaseDataSortMinus(int dataMapId);
    int updateCaseDataSortAdd(int dataMapId);
}
