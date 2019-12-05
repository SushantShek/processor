package com.nl.data.processor.controller;

import com.nl.data.processor.reader.StatementLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.nl.data.processor.utility.Constant.*;

/**
 * Controller class to read data from file and display response.
 */
@RestController
@RequestMapping(BASE_PATH)
public class StatementController {
	@Autowired
	StatementLoader loader;

	/**
	 * Get Method (default)
	 * @param fileName name of file to load
	 * @return Response text as String
	 */
	@RequestMapping(value = STATEMENT)
	public String getProcessStatement(@RequestParam(value = FILE_NAME, defaultValue = DEFAULT_FILE) final String fileName) {

		return loader.loadFile(fileName);
	}

}
