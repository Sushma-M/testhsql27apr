/*Copyright (c) 2016-2017 wavemaker.com All Rights Reserved.
 This software is the confidential and proprietary information of wavemaker.com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with wavemaker.com*/

package com.testhsql.sales.service;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/




import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.wavemaker.runtime.data.expression.QueryFilter;
import com.wavemaker.runtime.data.exception.EntityNotFoundException;

import com.testhsql.sales.*;

/**
 * Service object for domain model class Client.
 * @see com.testhsql.sales.Client
 */

public interface ClientService {
   /**
	 * Creates a new client.
	 * 
	 * @param created
	 *            The information of the created client.
	 * @return The created client.
	 */
	public Client create(Client created);

	/**
	 * Deletes a client.
	 * 
	 * @param clientId
	 *            The id of the deleted client.
	 * @return The deleted client.
	 * @throws EntityNotFoundException
	 *             if no client is found with the given id.
	 */
	public Client delete(Integer clientId) throws EntityNotFoundException;

	/**
	 * Finds all clients.
	 * 
	 * @return A list of clients.
	 */
	public Page<Client> findAll(QueryFilter[] queryFilters, Pageable pageable);
	
	public Page<Client> findAll(Pageable pageable);
	
	/**
	 * Finds client by id.
	 * 
	 * @param id
	 *            The id of the wanted client.
	 * @return The found client. If no client is found, this method returns
	 *         null.
	 */
	public Client findById(Integer id) throws
	 EntityNotFoundException;
	/**
	 * Updates the information of a client.
	 * 
	 * @param updated
	 *            The information of the updated client.
	 * @return The updated client.
	 * @throws EntityNotFoundException
	 *             if no client is found with given id.
	 */
	public Client update(Client updated) throws EntityNotFoundException;

	/**
	 * Retrieve the total count of the clients in the repository.
	 * 
	 * @param None
	 *            .
	 * @return The count of the client.
	 */

	public long countAll();


    public Page<Client> findAssociatedValues(Object value, String entityName, String key,  Pageable pageable);


}

