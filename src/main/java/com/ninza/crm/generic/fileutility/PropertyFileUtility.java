package com.ninza.crm.generic.fileutility;


import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyFileUtility {
	public String toGetDataFromPropertiesFile(String key) throws IOException
    {
		FileInputStream fs=new FileInputStream("./src/test/resources/commondata.properties");
    
	Properties pf=new Properties();
	pf.load(fs);
	String value = pf.getProperty(key);	
	return value;
    }

}
