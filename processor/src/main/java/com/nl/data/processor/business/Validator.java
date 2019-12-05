package com.nl.data.processor.business;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nl.data.processor.bean.Record;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.File;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class Validator {
	Logger log = LoggerFactory.getLogger(Validator.class);
	private List<Record> invalidRecords = new ArrayList<>();

	public boolean validate(List<Record> recordList) {

		log.info("Calling Validate");
		Set<String> allItems = new HashSet<>();
		List<Record> duplicateRecord =  recordList.stream()
				.filter(n -> !allItems.add(n.getReference())) // returns false if present Already.
				.collect(Collectors.toList());
		boolean flag = invalidRecords.addAll(duplicateRecord);
		findErroneousTransaction(recordList);
		createReport();
		return flag;
	}

	private void findErroneousTransaction(List<Record> faultyMap) {

		List<Record> faultyBalance  = faultyMap.stream()
				.filter(rec -> !checkBalanceAmount(rec))// returns false if balance mismatch.
				.collect(Collectors.toList());
		invalidRecords.addAll(faultyBalance);
		log.info("faultyBalance Count " + faultyBalance.size());
	}

	private boolean checkBalanceAmount(Record record) {

		BigDecimal startBalance = BigDecimal.valueOf(Double.parseDouble(record.getStartBalance()));
		BigDecimal mutation = BigDecimal.valueOf(Double.parseDouble(record.getMutation()));
		BigDecimal endBalance = BigDecimal.valueOf(Double.parseDouble(record.getEndBalance()));
		BigDecimal finalBal = startBalance.add(mutation);
		log.info("startBalance = " + startBalance + "finalBal ="+ finalBal);
		return finalBal.equals(endBalance);

	}

	private void createReport() {

		Map<String, Object> reportMap = new HashMap<>();
		ObjectMapper mapper = new ObjectMapper();
		invalidRecords.forEach(r -> reportMap.put(r.getAccountNumber(), r));
		/**
		 * Convert Map to JSON and write to a file
		 */
		try {
			mapper.writeValue(new File("report.json"), reportMap.toString());
		} catch (Exception e) {
			log.error("Error while creating report");
			e.printStackTrace();
		}finally {
			invalidRecords.clear();
		}
	}
}
