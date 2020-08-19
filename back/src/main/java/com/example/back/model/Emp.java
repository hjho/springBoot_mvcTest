package com.example.back.model;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("Emp")
public class Emp {
	private String empNo;
	private String empName;
	private String empJob;
	private String empMgr;
	private String empSal;
	private String empComm;
	private String deptNo;
	private String empHireDate;
}
