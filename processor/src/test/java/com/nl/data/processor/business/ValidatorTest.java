package com.nl.data.processor.business;

import com.nl.data.processor.bean.Record;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ValidatorTest {

	List<Record> list = new ArrayList<>();

	@Before
	public void setUp() throws Exception {

		list.add(new Record("145477",  "ABCD0909887", "Testing", "20.22", "+00.00",
				"20.22"));
		list.add(new Record("145478",  "ABCD0909888", "Testing", "20.22", "+00.00",
				"20.22"));
		list.add(new Record("145477",  "ABCD0909887", "Testing", "20.22", "+00.00",
				"20.22"));
	}

	@Test
	public void testValidator_validate_assertTrue(){
		Validator validator = new Validator();
		assertTrue(validator.validate(list));
	}

	@Test
	public void testValidator_validate_assertFalse(){
		Validator validator = new Validator();
		list.remove(2);
		assertFalse(validator.validate(list));
	}

}