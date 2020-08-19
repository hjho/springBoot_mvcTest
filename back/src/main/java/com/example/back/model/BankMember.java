package com.example.back.model;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("Bank")
public class BankMember {
	private long   accNum;
	private String memId;
	private String memName;
	private String memPassword;
	private int    balance;
}
