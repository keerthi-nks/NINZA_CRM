package com.ninza.crm.campaignstest;

import org.testng.Assert;
import org.testng.annotations.Test;

public class Demo_assertions {
  @Test
  public void test() {
	  System.out.println("hi");
	  String s="test";
	  Assert.assertNotNull(s);
  }
}
