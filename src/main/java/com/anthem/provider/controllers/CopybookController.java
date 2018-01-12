package com.anthem.provider.controllers;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.anthem.provider.domain.CopybookRequest;
import com.anthem.provider.domain.CopybookResponse;
import com.anthem.provider.services.CopybookService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/v1")
@Api(tags = { "Provider Copybook API" })
public class CopybookController {

	private static final Logger LOGGER = LogManager.getLogger(CopybookController.class);
	
	@Autowired
	private CopybookService copybookService;
	
	@ApiOperation(value = "get copybook data for requested parameters", notes = "get copybook data for requested parameters", response = CopybookResponse.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "successful operation", response = CopybookResponse.class),
			@ApiResponse(code = 404, message = "copybook data not found", response = CopybookResponse.class),
			@ApiResponse(code = 415, message = "copybook request data format issue", response = CopybookResponse.class),
			@ApiResponse(code = 500, message = "Internal server Error", response = CopybookResponse.class) })
	@RequestMapping(value = "/copybook", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CopybookResponse> getCopybook(
			@ApiParam(value = "Copybook request object", required = true) @RequestBody CopybookRequest copybookRequest)
			throws Exception

	{

		LOGGER.info(" Processing copybook request data ");
		CopybookResponse response = null;

		// TO DO
		response = copybookService.getCopybook();

		if (response == null) {

			LOGGER.debug(" Data not found for requested parameters");
			return new ResponseEntity<CopybookResponse>(response, HttpStatus.NOT_FOUND);
		}

		if (!"".equals(response.getWsgpErrorCode())) {

			return new ResponseEntity<CopybookResponse>(response, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<CopybookResponse>(response, HttpStatus.OK);
	}

}
