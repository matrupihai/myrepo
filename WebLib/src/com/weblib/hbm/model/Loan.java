package com.weblib.hbm.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table (name = "DBA.\"106_LOANS\"")
public class Loan {
	@Id
	@Column (name = "loan_id")
	@GeneratedValue (strategy = GenerationType.AUTO)
	private int loanId;
	
	@Temporal (TemporalType.TIMESTAMP)
	private Date dueDate;
	
	public Loan() {
		
	}

	public int getLoanId() {
		return loanId;
	}

	public void setLoanId(int loanId) {
		this.loanId = loanId;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}
	
}
