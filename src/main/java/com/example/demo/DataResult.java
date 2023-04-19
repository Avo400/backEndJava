package com.example.demo;

public class DataResult {
	String dataString;
	int dataInt;

	public DataResult(String dataString, int dataInt) {
		super();
		this.dataString = dataString;
		this.dataInt = dataInt;
	}

	public String getDataString() {
		return dataString;
	}

	public void setDataString(String dataString) {
		this.dataString = dataString;
	}

	public int getDataInt() {
		return dataInt;
	}

	public void setDataInt(int dataInt) {
		this.dataInt = dataInt;
	}

	@Override
	public String toString() {
		return "DataResult [dataString=" + dataString + ", dataInt=" + dataInt + "]";
	}

}
