package com.example.aspectj;


public class Customer {

	private Long id;
	private String firstName;
	private String lastName;
	private String lastName2;
	private String lastName3;
	private String lastName4;
	private String lastName5;
	private String lastName6;
	private int n;
	private boolean usesTx;

	protected Customer() {}

	public Customer(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}

	@Override
	public String toString() {
		return String.format(
				"Customer[id=%d, firstName='%s', lastName='%s']",
				id, firstName, lastName);
	}

	public Long getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getN() {
		return n;
	}

	public void setN(int n) {
		this.n = n;
	}

	public boolean isUsesTx() {
		return usesTx;
	}

	public void setUsesTx(boolean usesTx) {
		this.usesTx = usesTx;
	}
}
