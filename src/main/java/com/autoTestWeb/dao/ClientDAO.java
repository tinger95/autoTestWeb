package com.autoTestWeb.dao;

import com.autoTestWeb.model.Client;

import java.util.List;

public interface ClientDAO{
	List<Client> findClientList();
	int insertClient(Client client);
	int updateClient(Client client);
	Client findClientById(int id);
	int deleteClient(int id);
}
