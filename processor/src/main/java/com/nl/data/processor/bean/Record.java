package com.nl.data.processor.bean;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Record {

	List<Record> record;
	private String reference;
	private String accountNumber;
	private String description;
	private String startBalance;
	private String mutation;
	private String endBalance;

	public Record(String reference, String accountNumber, String description, String startBalance, String mutation,
			String endBalance) {
		this.reference = reference;
		this.accountNumber = accountNumber;
		this.startBalance = startBalance;
		this.mutation = mutation;
		this.description = description;
		this.endBalance = endBalance;
	}
}
