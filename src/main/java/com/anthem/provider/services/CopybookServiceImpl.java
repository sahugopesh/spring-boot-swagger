package com.anthem.provider.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.anthem.provider.domain.CopybookResponse;

@Service
public class CopybookServiceImpl implements CopybookService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    @Override
    public CopybookResponse getCopybook() {
    	
        logger.debug("get Copy book");
        //TO DO write logic to get data from IMS and prepare CopybookResponse
        return new CopybookResponse();

    }

}
