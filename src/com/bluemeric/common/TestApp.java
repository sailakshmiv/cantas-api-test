package com.bluemeric.common;

import java.util.Arrays;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import com.bluemeric.common.GenericClass;

@XmlRootElement(name="TestApp")
public class TestApp extends GenericClass {

	private String name;

	private QueryParam[] queryParam;

	@XmlAttribute
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public QueryParam[] getQueryParam() {
		return queryParam;
	}

	public void setQueryParam(QueryParam[] queryParam) {
		this.queryParam = queryParam;
	}

	@XmlRootElement 
	public static class QueryParam {

		private String url;
		private String name;
		private String outputFile;

		@XmlAttribute
		public String getUrl() {
			return url;
		}

		public void setUrl(String url) {
			this.url = url;
		}

		@XmlAttribute
		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		@XmlAttribute
		public String getOutputFile() {
			return outputFile;
		}

		public void setOutputFile(String outputFile) {
			this.outputFile = outputFile;
		}

		@Override
		public String toString() {
			return "QueryParam [url=" + url + ", name=" + name
					+ ", outputFile=" + outputFile + "]";
		}

	}

	@Override
	public String toString() {
		return "TestApp [name=" + name + ", queryParam="
				+ Arrays.toString(queryParam) + "]";
	}

}


