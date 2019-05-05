package com.zap.input;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ReadConfig {

	public Integer proxyPort, ignoreFlag;

	public String mailPort, mailHost, auth, emailFromPassword, emailFrom, host,
			defaultDriver, projectName, serverUrl, zapDir, htmlReportDir,tomcatDir, historyDir;
	public List<String> emailTo = new ArrayList<String>();
	public List<String> scanUrlList = new ArrayList<String>();
	public List<String> ignoreFilterReliability = new ArrayList<String>();
	public List<String> ignoreFilterRisk = new ArrayList<String>();
	public List<String> ignoreAlert = new ArrayList<String>();
	public List<String> ignoreReliability = new ArrayList<String>();
	public List<String> ignoreRisk = new ArrayList<String>();

	public ReadConfig() {
		System.out.println("inside readConfig function");

		try {

			File fXmlFile = new File(
					"/home/vikas/workspace/ZAP-6/input/config.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
			doc.getDocumentElement().normalize();

			Element driver = (Element) doc
					.getElementsByTagName("defaultDriver").item(0);
			defaultDriver = driver.getTextContent().toString();

			Node node = (Node) doc.getElementsByTagName("proxy").item(0);
			driver = (Element) node;
			host = driver.getElementsByTagName("host").item(0).getTextContent()
					.toString();
			proxyPort = Integer.parseInt(driver.getElementsByTagName("port")
					.item(0).getTextContent().toString());

			NodeList nList = doc.getElementsByTagName("users");

			for (int temp = 0; temp < nList.getLength(); temp++) {

				Node nNode = nList.item(temp);

				if (nNode.getNodeType() == Node.ELEMENT_NODE) {

					Element eElement = (Element) nNode;
					emailTo.add(eElement.getElementsByTagName("email").item(0)
							.getTextContent());
				}
			}

			NodeList emailfrom = doc.getElementsByTagName("user");

			Node emailFromNode = emailfrom.item(0);

			Element emailFromElement = (Element) emailFromNode;

			emailFrom = emailFromElement.getElementsByTagName("email").item(0)
					.getTextContent();
			emailFromPassword = emailFromElement
					.getElementsByTagName("password").item(0).getTextContent();

			System.out.println(emailFrom);
			System.out.println(emailFromPassword);

			NodeList smtpData = doc.getElementsByTagName("smtp");

			Node smtpNode = smtpData.item(0);
			Element smtpNodeElement = (Element) smtpNode;
			mailHost = smtpNodeElement.getElementsByTagName("host").item(0)
					.getTextContent();

			mailPort = smtpNodeElement.getElementsByTagName("port").item(0)
					.getTextContent();

			auth = smtpNodeElement.getElementsByTagName("auth").item(0)
					.getTextContent();
			Element projectNameElement = (Element) doc.getElementsByTagName(
					"projectName").item(0);
			projectName = projectNameElement.getTextContent().toString();

			Element serverUrlElement = (Element) doc.getElementsByTagName(
					"serverUrl").item(0);
			serverUrl = serverUrlElement.getTextContent().toString();

			Element zapDirElement = (Element) doc
					.getElementsByTagName("zapDir").item(0);
			zapDir = zapDirElement.getTextContent().toString();

			//******
			Element tomcatDirElement = (Element) doc
					.getElementsByTagName("tomcatDir").item(0);
			tomcatDir = tomcatDirElement.getTextContent().toString();
			
			Element historyDirElement = (Element) doc
					.getElementsByTagName("historyDir").item(0);
			historyDir = historyDirElement.getTextContent().toString();
			//******
			
			
			Element htmlReportDirElement = (Element) doc.getElementsByTagName(
					"reportDir").item(0);
			htmlReportDir = htmlReportDirElement.getTextContent().toString();

			NodeList urlList = doc.getElementsByTagName("urlScan");
			System.out.println("urls to scan-" + urlList.getLength());
			for (Integer i = 0; i < urlList.getLength(); i++) {

				Node urlListNode = urlList.item(i);

				if (urlListNode.getNodeType() == Node.ELEMENT_NODE) {

					Element eElement = (Element) urlListNode;
					scanUrlList.add(eElement.getTextContent());
				}

			}

			Element ignoreFlagElement = (Element) doc.getElementsByTagName(
					"ignoreFlag").item(0);
			ignoreFlag = Integer.parseInt(ignoreFlagElement
					.getAttribute("flag").toString());

			NodeList ignoreFilterList = (NodeList) doc
					.getElementsByTagName("ignoreFilter");
			System.out.println("No of filters-" + ignoreFilterList.getLength());
			for (Integer i = 0; i < ignoreFilterList.getLength(); i++) {

				Node ignoreFilterNode = ignoreFilterList.item(i);

				if (ignoreFilterNode.getNodeType() == Node.ELEMENT_NODE) {

					Element eElement = (Element) ignoreFilterNode;
					ignoreFilterReliability.add(eElement
							.getAttribute("reliability"));
					ignoreFilterRisk.add(eElement.getAttribute("risk"));
				}

			}

			NodeList ignoreList = doc
					.getElementsByTagName("ignoreAlert");
			System.out.println("No of ignore list-" + ignoreList.getLength());
			for (Integer i = 0; i < ignoreList.getLength(); i++) {

				Node ignoreAlertNode = ignoreList.item(i);

				if (ignoreAlertNode.getNodeType() == Node.ELEMENT_NODE) {

					Element eElement = (Element) ignoreAlertNode;
					ignoreAlert.add(eElement.getAttribute("alert"));
					ignoreReliability.add(eElement.getAttribute("relaibility"));
					ignoreRisk.add(eElement.getAttribute("risk"));
				}

			}

			System.out.println(ignoreFlag);
			System.out.println(projectName);
			System.out.println(serverUrl);
			System.out.println(proxyPort);
			System.out.println(host);
			System.out.println(defaultDriver);
			System.out.println(mailHost);
			System.out.println(mailPort);
			System.out.println(auth);
			System.out.println(zapDir);
			System.out.println(htmlReportDir);
			System.out.println(historyDir);
			System.out.println(tomcatDir);
			for (Integer temp = 0; temp < emailTo.size(); temp++) {
				System.out.println(emailTo.get(temp));
			}

			for (Integer i = 0; i < scanUrlList.size(); i++) {
				System.out.println("" + scanUrlList.get(i));
			}
			for (Integer i = 0; i < ignoreAlert.size(); i++) {
				System.out.println("" + ignoreAlert.get(i));
				System.out.println("" + ignoreReliability.get(i));
				System.out.println("" + ignoreRisk.get(i));
			}

			for (Integer i = 0; i < ignoreFilterReliability.size(); i++) {
				System.out.println("" + ignoreFilterReliability.get(i));
				System.out.println("" + ignoreFilterRisk.get(i));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}