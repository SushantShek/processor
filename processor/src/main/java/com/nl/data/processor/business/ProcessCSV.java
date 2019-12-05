package com.nl.data.processor.business;

import com.nl.data.processor.bean.Record;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * ProcessCSV takes in String CSV from the file and change it to corresponding JSON and create List of records
 */
@Service
public class ProcessCSV {
	Logger log = LoggerFactory.getLogger(ProcessCSV.class);

	@Autowired
	private Validator validator;

	public void createJSON(String csvAsString) throws IOException {
		log.info("Inside createJSON");
		final Resource resource = new ClassPathResource(csvAsString);
		Pattern pattern = Pattern.compile(",");

		try (BufferedReader in = new BufferedReader(new InputStreamReader(resource.getInputStream()))) {
			List<Record> csvRecords = in
					.lines()
					.skip(1)
					.map(line -> {
						String[] x = pattern.split(line);
						return new Record(x[0],
								x[1], x[2], x[3], (x[4]), x[5]);
					})
					.collect(Collectors.toList());
			validator.validate(csvRecords);
		}
	}
}
