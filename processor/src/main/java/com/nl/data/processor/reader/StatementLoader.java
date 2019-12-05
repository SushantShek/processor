package com.nl.data.processor.reader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class StatementLoader {
	Logger log = LoggerFactory.getLogger(StatementLoader.class);

	@Autowired
	StatementReader readFile;

	public StatementLoader() {/*default Constructor*/}

	public String loadFile(final String filename) {
		String content = "";
		try {
			content = readFile.load(filename);
		} catch (IOException e) {
			log.error("Error While loadFile() " + e);
		}
		return content;
	}

}
