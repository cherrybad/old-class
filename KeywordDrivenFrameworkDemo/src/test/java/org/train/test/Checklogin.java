package org.train.test;

import org.testng.annotations.Test;
import org.train.keywordengine.Keywordengine;


public class Checklogin {
 public Keywordengine keywordact;
	@Test
	public void verifyLogin() throws Exception {
		keywordact= new Keywordengine();
		keywordact.executetest("Login");
		
	}
}
