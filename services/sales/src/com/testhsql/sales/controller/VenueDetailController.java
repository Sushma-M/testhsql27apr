/*Copyright (c) 2016-2017 wavemaker.com All Rights Reserved.
 This software is the confidential and proprietary information of wavemaker.com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with wavemaker.com*/
package com.testhsql.sales.controller;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/
import com.testhsql.sales.service.VenueDetailService;
import java.io.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.hibernate.TypeMismatchException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import com.wavemaker.runtime.data.exception.EntityNotFoundException;
import com.wavemaker.runtime.data.expression.QueryFilter;
import com.wavemaker.runtime.util.WMMultipartUtils;
import com.wavemaker.runtime.util.WMRuntimeUtils;
import com.wavemaker.runtime.file.model.DownloadResponse;
import com.wordnik.swagger.annotations.*;
import com.testhsql.sales.*;
import com.testhsql.sales.service.*;
import com.wavemaker.tools.api.core.annotations.WMAccessVisibility;
import com.wavemaker.tools.api.core.models.AccessSpecifier;

/**
 * Controller object for domain model class VenueDetail.
 * @see com.testhsql.sales.VenueDetail
 */
@RestController(value = "Sales.VenueDetailController")
@RequestMapping("/sales/VenueDetail")
@Api(description = "Exposes APIs to work with VenueDetail resource.", value = "VenueDetailController")
public class VenueDetailController {

    private static final Logger LOGGER = LoggerFactory.getLogger(VenueDetailController.class);

    @Autowired
    @Qualifier("sales.VenueDetailService")
    private VenueDetailService venueDetailService;

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    @ApiOperation(value = "Returns the list of VenueDetail instances matching the search criteria.")
    public Page<VenueDetail> findVenueDetails(Pageable pageable, @RequestBody QueryFilter[] queryFilters) {
        LOGGER.debug("Rendering VenueDetails list");
        return venueDetailService.findAll(queryFilters, pageable);
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ApiOperation(value = "Returns the list of VenueDetail instances.")
    public Page<VenueDetail> getVenueDetails(Pageable pageable) {
        LOGGER.debug("Rendering VenueDetails list");
        return venueDetailService.findAll(pageable);
    }

    /**
	 * This setter method should only be used by unit tests
	 * 
	 * @param service
	 */
    protected void setVenueDetailService(VenueDetailService service) {
        this.venueDetailService = service;
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    @ApiOperation(value = "Creates a new VenueDetail instance.")
    public VenueDetail createVenueDetail(@RequestBody VenueDetail instance) {
        LOGGER.debug("Create VenueDetail with information: {}", instance);
        instance = venueDetailService.create(instance);
        LOGGER.debug("Created VenueDetail with information: {}", instance);
        return instance;
    }

    @RequestMapping(value = "/count", method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    @ApiOperation(value = "Returns the total count of VenueDetail instances.")
    public Long countAllVenueDetails() {
        LOGGER.debug("counting VenueDetails");
        Long count = venueDetailService.countAll();
        return count;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    @ApiOperation(value = "Returns the VenueDetail instance associated with the given id.")
    public VenueDetail getVenueDetail(@PathVariable(value = "id") Integer id) throws EntityNotFoundException {
        LOGGER.debug("Getting VenueDetail with id: {}", id);
        VenueDetail instance = venueDetailService.findById(id);
        LOGGER.debug("VenueDetail details with id: {}", instance);
        return instance;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    @ApiOperation(value = "Updates the VenueDetail instance associated with the given id.")
    public VenueDetail editVenueDetail(@PathVariable(value = "id") Integer id, @RequestBody VenueDetail instance) throws EntityNotFoundException {
        LOGGER.debug("Editing VenueDetail with id: {}", instance.getId());
        instance.setId(id);
        instance = venueDetailService.update(instance);
        LOGGER.debug("VenueDetail details with id: {}", instance);
        return instance;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    @ApiOperation(value = "Deletes the VenueDetail instance associated with the given id.")
    public boolean deleteVenueDetail(@PathVariable(value = "id") Integer id) throws EntityNotFoundException {
        LOGGER.debug("Deleting VenueDetail with id: {}", id);
        VenueDetail deleted = venueDetailService.delete(id);
        return deleted != null;
    }
}