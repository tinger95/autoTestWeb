package com.autoTestWeb.service;

import com.autoTestWeb.dao.ClientDAO;
import com.autoTestWeb.model.Client;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ClientService {
	private static final Logger LOGGER = Logger.getLogger(ClientService.class);

	@Resource
	private ClientDAO clientDao;

	public List<Client> findClientList() {
		return clientDao.findClientList();
	}
	public int insertClient(Client client) {
		try {
			return clientDao.insertClient(client);
		} catch (Exception e) {
			LOGGER.info(e.toString());
			throw new RuntimeException(e.toString());
		}
	}

	public int updateClient(Client client) {
		try {
			return clientDao.updateClient(client);
		} catch (Exception e) {
			LOGGER.info(e.toString());
			throw new RuntimeException(e.toString());
		}
	}

	public Client findClientById(int id) {
		return clientDao.findClientById(id);
	}

	public int deleteClient(int id) {
		try {
			int i = clientDao.deleteClient(id);
			return i;
		} catch (Exception e) {
			LOGGER.info(e.toString());
			throw new RuntimeException(e.toString());
		}

	}
}
