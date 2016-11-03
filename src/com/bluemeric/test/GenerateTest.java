package com.bluemeric.test;

import java.io.File;
import java.util.Scanner;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.TestNG;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.annotations.Listeners;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.bluemeric.connection.HttpConnection;

@Listeners(org.uncommons.reportng.HTMLReporter.class)
public class GenerateTest extends TestNG {

	/**
	 * @param args
	 */
	static String projectHome = System.getProperty("user.dir")+"/";
	//static String projectHome = System.getProperty("PROJECT_HOME") + "/";
	static Logger logger = Logger.getLogger(GenerateTest.class);
	HttpConnection con ;

	public GenerateTest() throws Exception {
		con = new HttpConnection();
		PropertyConfigurator.configure(projectHome + "/log4j.properties"); 

	}

	@Parameters({"endpoint", "url", "outputFile", "suiteName"})
	@Test(groups = { "get" })
	public void getTest(@Optional String endpoint, @Optional String uri, @Optional String outputFile, @Optional String suiteName) throws Exception {

		String url = "http://" + endpoint + "/" +  uri ;
		Reporter.log("\n\nGet path : " + url);
		
		int resCode = con.get(url);
		
		Reporter.log("\n Response code received : " + con.statusCode);
		Reporter.log("\nResponse String received : " + con.responseString);

		if (resCode != 200 )
			Assert.fail("Expected response code is 200. But got " + resCode + ". Hence failed the test.");

		outputFile = projectHome + "/output/" + suiteName + "/" + outputFile;
		String content = new Scanner(new File(outputFile)).useDelimiter("\\Z").next();
		System.out.println("Expected Reponse : " + content);
		String temp = con.responseString;
		if (!temp.contains(content))
			Assert.fail("Expected response is " + content + ". But received reponse = " + temp + ". Output mismatching. Hence failing the test.");

		Reporter.log("\nBoth response code and string are matching with expected output. Hence Passed the test.");
	}

}
