package com.nl.data.processor.reader;

import com.nl.data.processor.business.ProcessCSV;
import com.nl.data.processor.business.ProcessXML;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStreamReader;

import static com.nl.data.processor.utility.Constant.RESOURCE_PATH;

/**
 * Statement Reader Class reads the data file from resources of the format provided and map it to java Class
 */
@Component
public class StatementReader {
	Logger log = LoggerFactory.getLogger(StatementReader.class);
	@Autowired
	private ProcessCSV processCsv;
	@Autowired
	private ProcessXML processXml;

	public StatementReader() {/*default Constructor*/}

	public String load(final String resourceName) throws IOException {
		log.info(" Reading file content");
		String path = RESOURCE_PATH + resourceName;
		final Resource resource = new ClassPathResource(path);

		InputStreamReader reader = new InputStreamReader(resource.getInputStream());
		if (FilenameUtils.getExtension(path).equalsIgnoreCase("xml")) {
			processXml.createXMLToJSON(path);
		} else {
			processCsv.createJSON(path);
		}
		return IOUtils.toString(reader);
	}

}
