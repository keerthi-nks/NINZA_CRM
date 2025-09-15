package Practice_for_TestNG;

import org.testng.Assert;
import org.testng.annotations.Test;

public class Demo_IRetryAnalyser {
  @Test(retryAnalyzer = com.ninza.crm.listenerUtility.IRetryAnalyzerImplementation.class )
  public void add() {
	  System.out.println("hi");
	  Assert.assertEquals("hfdc","hdfc");
  }
}
