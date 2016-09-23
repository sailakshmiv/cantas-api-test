package com.bluemeric.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.TestNG;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

import com.bluemeric.common.TestApp;
import com.bluemeric.common.Util;

public class RunTest  implements ITestListener {
	static String projectHome = System.getProperty("PROJECT_HOME") + "/";
	static String endpoint = System.getProperty("APP_ENDPOINT"); 

	List<XmlSuite> xmlSuites;
	
	RunTest() {
		xmlSuites = new ArrayList<XmlSuite>();
	}

	private void runTests(TestNG tng) throws JAXBException, IOException {

		String[] directories = Util.readDirectories(projectHome + "/config");
		for ( int dir = 0; dir < directories.length; dir++){
			XmlSuite suite = new XmlSuite();
			ArrayList<XmlTest> tests = new ArrayList<XmlTest>();	

			TestApp tstApp = (TestApp) com.bluemeric.common.GenericClass.unmarshallClass(projectHome + "/config/" + directories[dir] + "/" + directories[dir] + ".xml", TestApp.class);
			System.out.println(tstApp.getName());
			suite.setName(tstApp.getName());

			tests.addAll(createTest(tstApp, suite));
			suite.setTests(tests);
			xmlSuites.add(suite);
		}	

		// Add Tests into a suite
		tng.setXmlSuites(xmlSuites);
		tng.run();
	}

	//	Create individual Tests
	private List<XmlTest> createTest(TestApp tstApp, XmlSuite suite){
		List<XmlTest> xmlTest = new ArrayList<XmlTest>();

		for (int i = 0; i < tstApp.getQueryParam().length; i++) {
			Map<String, String> parameters  = new HashMap<String, String>();
			XmlTest test = new XmlTest(suite);
			test.setName(tstApp.getQueryParam()[i].getName());

			parameters.put("endpoint", endpoint); 
			parameters.put("url", tstApp.getQueryParam()[i].getUrl());
			parameters.put("outputFile", tstApp.getQueryParam()[i].getOutputFile());
			parameters.put("suiteName", suite.getName().toLowerCase());
			test.setParameters(parameters);
			List<XmlClass> classes = new ArrayList<XmlClass>();

			List<String> g = new ArrayList<String>();
			g.add("get");
			test.setIncludedGroups(g);

			classes.add(new XmlClass("com.bluemeric.test.GenerateTest"));

			test.setXmlClasses(classes) ;
			xmlTest.add(test);
		}
		return xmlTest;
	}

	public static void main(String[] args) throws JAXBException, IOException {
		// TODO Auto-generated method stub
		RunTest rtest = new RunTest();
		TestNG tng = new TestNG();
		tng.addListener(rtest);
		rtest.runTests(tng);

	}

	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
	}
}
