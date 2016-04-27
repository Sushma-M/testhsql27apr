/*Copyright (c) 2016-2017 wavemaker.com All Rights Reserved.
 This software is the confidential and proprietary information of wavemaker.com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with wavemaker.com*/

package com.testhsql.sales.service;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/





import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.wavemaker.runtime.data.dao.*;
import com.wavemaker.runtime.data.expression.QueryFilter;
import com.wavemaker.runtime.data.exception.EntityNotFoundException;

import com.testhsql.sales.*;


/**
 * ServiceImpl object for domain model class Client.
 * @see com.testhsql.sales.Client
 */
@Service("sales.ClientService")
public class ClientServiceImpl implements ClientService {


    private static final Logger LOGGER = LoggerFactory.getLogger(ClientServiceImpl.class);

    @Autowired
    @Qualifier("sales.ClientDao")
    private WMGenericDao<Client, Integer> wmGenericDao;

    public void setWMGenericDao(WMGenericDao<Client, Integer> wmGenericDao){
        this.wmGenericDao = wmGenericDao;
    }
     @Transactional(readOnly = true, value = "salesTransactionManager")
     public Page<Client> findAssociatedValues(Object value, String entityName, String key,  Pageable pageable){
          LOGGER.debug("Fetching all associated");
          return this.wmGenericDao.getAssociatedObjects(value, entityName, key, pageable);
     }

    @Transactional(value = "salesTransactionManager")
    @Override
    public Client create(Client client) {
        LOGGER.debug("Creating a new client with information: {}" , client);
        return this.wmGenericDao.create(client);
    }

    @Transactional(rollbackFor = EntityNotFoundException.class, value = "salesTransactionManager")
    @Override
    public Client delete(Integer clientId) throws EntityNotFoundException {
        LOGGER.debug("Deleting client with id: {}" , clientId);
        Client deleted = this.wmGenericDao.findById(clientId);
        if (deleted == null) {
            LOGGER.debug("No client found with id: {}" , clientId);
            throw new EntityNotFoundException(String.valueOf(clientId));
        }
        this.wmGenericDao.delete(deleted);
        return deleted;
    }

    @Transactional(readOnly = true, value = "salesTransactionManager")
    @Override
    public Page<Client> findAll(QueryFilter[] queryFilters, Pageable pageable) {
        LOGGER.debug("Finding all clients");
        return this.wmGenericDao.search(queryFilters, pageable);
    }
    
    @Transactional(readOnly = true, value = "salesTransactionManager")
    @Override
    public Page<Client> findAll(Pageable pageable) {
        LOGGER.debug("Finding all clients");
        return this.wmGenericDao.search(null, pageable);
    }

    @Transactional(readOnly = true, value = "salesTransactionManager")
    @Override
    public Client findById(Integer id) throws EntityNotFoundException {
        LOGGER.debug("Finding client by id: {}" , id);
        Client client=this.wmGenericDao.findById(id);
        if(client==null){
            LOGGER.debug("No client found with id: {}" , id);
            throw new EntityNotFoundException(String.valueOf(id));
        }
        return client;
    }
    @Transactional(rollbackFor = EntityNotFoundException.class, value = "salesTransactionManager")
    @Override
    public Client update(Client updated) throws EntityNotFoundException {
        LOGGER.debug("Updating client with information: {}" , updated);
        this.wmGenericDao.update(updated);

        Integer id = (Integer)updated.getId();

        return this.wmGenericDao.findById(id);
    }

    @Transactional(readOnly = true, value = "salesTransactionManager")
    @Override
    public long countAll() {
        return this.wmGenericDao.count();
    }
}

