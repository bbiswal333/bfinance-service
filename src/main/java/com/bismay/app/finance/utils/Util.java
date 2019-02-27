package com.bismay.app.finance.utils;

public class Util {
	
	public Long generateLong(){
		long leftLimit = 1L;
	    long rightLimit = 1000L;
	    long generatedLong = leftLimit + (long) (Math.random() * (rightLimit - leftLimit));
	    return generatedLong;
	}

}
