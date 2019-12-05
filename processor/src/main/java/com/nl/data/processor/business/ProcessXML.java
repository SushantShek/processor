package com.nl.data.processor.business;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nl.data.processor.bean.Records;
import org.apache.commons.io.IOUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStreamReader;

/**
 * This is XML processor
 * takes Xml content as String
 * parse and map it to
 *
 */
@Service
public class ProcessXML {

	Logger log = LoggerFactory.getLogger(ProcessXML.class);

	public static int PRETTY_PRINT_INDENT_FACTOR = 4;

	@Autowired
	private Validator validator;

	public boolean createXMLToJSON(String xmlFilePath) throws IOException {
		log.info("Inside createXMLToJSON");
		final Resource resource = new ClassPathResource(xmlFilePath);
		String xmlString = IOUtils.toString(new InputStreamReader(resource.getInputStream()));
		return parseToJSON(xmlString);
	}

	private boolean parseToJSON(String xmlString) {
		log.info("Calling parseToJSON");
		boolean returnFlag=false;
		try {
			JSONObject xmlJSONObj = XML.toJSONObject(xmlString);
			String jsonPrettyPrintString = xmlJSONObj.toString(PRETTY_PRINT_INDENT_FACTOR);
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
			Records record = objectMapper.readValue(jsonPrettyPrintString, Records.class);
			returnFlag = validator.validate(record.getRecords().getRecord());
		} catch (JSONException | IOException je) {
			log.error("parseToJSON :" + je.toString());
		}
		return returnFlag;
	}
}
