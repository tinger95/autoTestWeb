package com.autoTestWeb.service;

import com.autoTestWeb.dao.BaseCaseDAO;
import com.autoTestWeb.dao.CaseDAO;
import com.autoTestWeb.dao.CaseDataDAO;
import com.autoTestWeb.dao.CasePageDAO;
import com.autoTestWeb.model.BaseCase;
import com.autoTestWeb.model.Case;
import com.autoTestWeb.model.CaseData;
import com.autoTestWeb.model.CasePage;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CaseService{
	private static final Logger LOGGER = Logger.getLogger(CaseService.class);

	@Resource
	private CaseDAO caseDao;
	@Resource
	private BaseCaseDAO baseCaseDao;
	@Resource
	private CasePageDAO casePageDao;
	@Resource
	private CaseDataDAO caseDataDao;

	public List<Case> findCaseList(Case c) {
		return caseDao.findCaseList(c);
	}

	public Case findCaseById(int id) {
		return caseDao.findCaseById(id);
	}

	public int insertCase(Case executeCase, int formerCaseId) {
		try {
			int i = caseDao.insertCase(executeCase);
			if (formerCaseId > 0) {
				List<BaseCase> baseCaseList = baseCaseDao.findBaseCaseListByCaseId(formerCaseId);

				for (BaseCase bc : baseCaseList) {
					int baseCaseId = bc.getId();
					bc.setCaseId(executeCase.getId());
					bc.setUserId(executeCase.getUserId());
					int k = baseCaseDao.insertBaseCase(bc);
					if (k > 0) {
						List<CasePage> casePageList = casePageDao.findParentCasePageListByBaseCaseId(baseCaseId);
						for (CasePage cp : casePageList) {
							int casePageId = cp.getId();
							insertCasePage(cp,executeCase.getUserId(),bc.getId(),0,casePageId);
							
						}
					}
				}

			}
			return i;
		} catch (Exception e) {
			LOGGER.info(e.toString());
			throw new RuntimeException(e.toString());
		}
	}
	public void insertCasePage(CasePage cp,int userId,int baseCaseId,int parentId,int casePageId){
		cp.setBaseCaseId(baseCaseId);
		cp.setUserId(userId);
		cp.setParentId(parentId);
		int j = casePageDao.insertCasePage(cp);
		if (j > 0) {
			List<CaseData> caseDataList = caseDataDao.findCaseDataListByCasePageId(casePageId);
			for (CaseData cd : caseDataList) {
				cd.setCasePageId(cp.getId());
				caseDataDao.insertCaseData(cd);
			}
			List<CasePage> casePageList=casePageDao.findChildCasePageListByParentId(casePageId);
			for(CasePage casePage:casePageList){
				insertCasePage(casePage,userId,baseCaseId,cp.getId(),casePage.getId());
			}
		}
	}
	public int deleteCase(int id) {
		try {
			List<BaseCase> baseCaseList = baseCaseDao.findBaseCaseListByCaseId(id);
			for (BaseCase bc : baseCaseList) {
				List<CasePage> casePageList = casePageDao.findCasePageListByBaseCaseId(bc.getId());
				for (CasePage cp : casePageList) {
					caseDataDao.deleteCaseDataByCasePageId(cp.getId());
				}
				casePageDao.deleteCasePageByBaseCaseId(bc.getId());
			}
			baseCaseDao.deleteBaseCaseByCaseId(id);
			int i = caseDao.deleteCase(id);
			return i;
		} catch (Exception e) {
			LOGGER.info(e.toString());
			throw new RuntimeException(e.toString());
		}
	}

	public int updateCase(Case executeCase) {
		try {
			return caseDao.updateCase(executeCase);
		} catch (Exception e) {
			LOGGER.info(e.toString());
			throw new RuntimeException(e.toString());
		}
	}

	public List<Case> findCaseListByUserId(int userId) {
		return caseDao.findCaseListByUserId(userId);
	}
	public int findCaseCount(Case executeCase){
		return caseDao.findCaseCount(executeCase);
	}
}
