package com.tarasenko.deliveryapp.common;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public interface Config
{

  ObjectMapper OBJECT_MAPPER = new ObjectMapper();
  Logger LOGGER = LoggerFactory.getLogger(Config.class);

  default String toJsonString()
  {
    try
    {
      return OBJECT_MAPPER.writeValueAsString(this);
    }
    catch (JsonProcessingException e)
    {
      LOGGER.error("Exception occurred during transforming config into json " + this.toJsonString(), e);
    }

    return StringUtils.EMPTY;
  }

}
