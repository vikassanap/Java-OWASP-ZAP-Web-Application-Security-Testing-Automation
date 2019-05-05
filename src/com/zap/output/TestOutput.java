package com.zap.output;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import org.zaproxy.clientapi.core.Alert;

import com.zap.input.ReadConfig;

public class TestOutput {

	public List<String> alert = new ArrayList<String>();
	public List<String> reliability = new ArrayList<String>();
	public List<String> risk = new ArrayList<String>();
	public List<String> url = new ArrayList<String>();
	public List<String> solution = new ArrayList<String>();
	public List<String> reference = new ArrayList<String>();
	public List<String> param = new ArrayList<String>();
	public List<String> evidence = new ArrayList<String>();
	public List<String> description = new ArrayList<String>();
	public List<String> attack = new ArrayList<String>();

	public List<String> alertFilter = new ArrayList<String>();
	public List<String> reliabilityFilter = new ArrayList<String>();
	public List<String> riskFilter = new ArrayList<String>();
	public List<String> urlFilter = new ArrayList<String>();
	public List<String> solutionFilter = new ArrayList<String>();
	public List<String> referenceFilter = new ArrayList<String>();
	public List<String> paramFilter = new ArrayList<String>();
	public List<String> evidenceFilter = new ArrayList<String>();
	public List<String> descriptionFilter = new ArrayList<String>();
	public List<String> attackFilter = new ArrayList<String>();
	public String htmlreportLink,fileTitle,fileName;

	public String timeStamp, trimTimeStamp;

	public TestOutput() {
		Date date = new Date();
		Timestamp timestamp = new Timestamp(date.getTime());
		Timestamp myTimeStamp = timestamp;
		timeStamp = myTimeStamp.toString().substring(0, 19);
		trimTimeStamp = timeStamp.replaceAll("\\s+", "_");
		System.out.println(timeStamp);
		System.out.println(trimTimeStamp);
	}

	public void printResult() {
		System.out.println("inside printResult function");
		for (Integer i = 0; i < alert.size(); i++) {

			System.out.println("Alert: " + alert.get(i));
			System.out.println("Relaibility: " + reliability.get(i));
			System.out.println("Risk: " + risk.get(i));
			System.out.println("URl: " + url.get(i));
			System.out.println("-----------------------------------");
		}

		System.out.println("Total alerts: " + alert.size());
	}

	public void generateHtmlReport(ReadConfig readConfig) {
		System.out.println("inside generateHtmlReport function");


		fileTitle=readConfig.projectName+trimTimeStamp+".html";
		fileName = readConfig.htmlReportDir+fileTitle;
		htmlreportLink = readConfig.serverUrl+fileTitle;

		String headerLine = "<html><head>"
				+ "<title></title>"
				+ "</head>"
				+ "<body>"
				+ "<h2><font color=\"6A94E2\">"+readConfig.projectName+": Security Testing Report on "+timeStamp+"</font></h2>"
				+ "<br>"
				+ "<div class=\"datagrid\""
				+ " style=\"font: normal 12px/150% Courier New, Courier, monospace; background: #fff; -webkit-border-radius: 4px; -moz-border-radius: 4px; border-radius: 4px;\">"
				+ "<table"
				+ " style=\"border-collapse: collapse; text-align: left; width: 100%;\">"
				+ "<thead  style=\"border: none;\">"
				+ "<tr>"
				+ "<th"
				+ " style=\"padding: 6px 20px; background: -webkit-gradient(linear, left top, left bottom, color-stop(0.05, #006699), color-stop(1, #00557F)); background: -moz-linear-gradient(center top, #006699 5%, #00557F 100%); filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#006699', endColorstr='#00557F'); background-color: #006699; color: #FFFFFF; font-size: 15px; font-weight: bold; border-left: 1px solid #0070A8;\">Sr. No</th>"
				+ "<th"
				+ " style=\"padding: 6px 20px; background: -webkit-gradient(linear, left top, left bottom, color-stop(0.05, #006699), color-stop(1, #00557F)); background: -moz-linear-gradient(center top, #006699 5%, #00557F 100%); filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#006699', endColorstr='#00557F'); background-color: #006699; color: #FFFFFF; font-size: 15px; font-weight: bold; border-left: 1px solid #0070A8;\">Alert</th>"
				+ "<th"
				+ " style=\"padding: 6px 20px; background: -webkit-gradient(linear, left top, left bottom, color-stop(0.05, #006699), color-stop(1, #00557F)); background: -moz-linear-gradient(center top, #006699 5%, #00557F 100%); filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#006699', endColorstr='#00557F'); background-color: #006699; color: #FFFFFF; font-size: 15px; font-weight: bold; border-left: 1px solid #0070A8;\">Relaibility</th>"
				+ "<th"
				+ " style=\"padding: 6px 20px; background: -webkit-gradient(linear, left top, left bottom, color-stop(0.05, #006699), color-stop(1, #00557F)); background: -moz-linear-gradient(center top, #006699 5%, #00557F 100%); filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#006699', endColorstr='#00557F'); background-color: #006699; color: #FFFFFF; font-size: 15px; font-weight: bold; border-left: 1px solid #0070A8;\">Risk</th>"
				+ "<th"
				+ " style=\"padding: 6px 20px; background: -webkit-gradient(linear, left top, left bottom, color-stop(0.05, #006699), color-stop(1, #00557F)); background: -moz-linear-gradient(center top, #006699 5%, #00557F 100%); filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#006699', endColorstr='#00557F'); background-color: #006699; color: #FFFFFF; font-size: 15px; font-weight: bold; border-left: 1px solid #0070A8;\">Url</th>"
				+ "<th"
				+ " style=\"padding: 6px 20px; background: -webkit-gradient(linear, left top, left bottom, color-stop(0.05, #006699), color-stop(1, #00557F)); background: -moz-linear-gradient(center top, #006699 5%, #00557F 100%); filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#006699', endColorstr='#00557F'); background-color: #006699; color: #FFFFFF; font-size: 15px; font-weight: bold; border-left: 1px solid #0070A8;\">Description</th>"
				+ "<th"
				+ " style=\"padding: 6px 20px; background: -webkit-gradient(linear, left top, left bottom, color-stop(0.05, #006699), color-stop(1, #00557F)); background: -moz-linear-gradient(center top, #006699 5%, #00557F 100%); filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#006699', endColorstr='#00557F'); background-color: #006699; color: #FFFFFF; font-size: 15px; font-weight: bold; border-left: 1px solid #0070A8;\">Evidence</th>"
				+ "<th"
				+ " style=\"padding: 6px 20px; background: -webkit-gradient(linear, left top, left bottom, color-stop(0.05, #006699), color-stop(1, #00557F)); background: -moz-linear-gradient(center top, #006699 5%, #00557F 100%); filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#006699', endColorstr='#00557F'); background-color: #006699; color: #FFFFFF; font-size: 15px; font-weight: bold; border-left: 1px solid #0070A8;\">Attack</th>"
				+ "<th"
				+ " style=\"padding: 6px 20px; background: -webkit-gradient(linear, left top, left bottom, color-stop(0.05, #006699), color-stop(1, #00557F)); background: -moz-linear-gradient(center top, #006699 5%, #00557F 100%); filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#006699', endColorstr='#00557F'); background-color: #006699; color: #FFFFFF; font-size: 15px; font-weight: bold; border-left: 1px solid #0070A8;\">Parameters</th>"
				+ "<th"
				+ " style=\"padding: 6px 20px; background: -webkit-gradient(linear, left top, left bottom, color-stop(0.05, #006699), color-stop(1, #00557F)); background: -moz-linear-gradient(center top, #006699 5%, #00557F 100%); filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#006699', endColorstr='#00557F'); background-color: #006699; color: #FFFFFF; font-size: 15px; font-weight: bold; border-left: 1px solid #0070A8;\">Reference</th>"
				+ "<th"
				+ " style=\"padding: 6px 20px; background: -webkit-gradient(linear, left top, left bottom, color-stop(0.05, #006699), color-stop(1, #00557F)); background: -moz-linear-gradient(center top, #006699 5%, #00557F 100%); filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#006699', endColorstr='#00557F'); background-color: #006699; color: #FFFFFF; font-size: 15px; font-weight: bold; border-left: 1px solid #0070A8;\">Solution</th>"
				+ "			</tr>" + "			</thead>" + "		<tbody>";

		String footerLine = " </tbody>" + "</table>" + "</div>" + "</body>"
				+ "</html>";
		Integer flag=0;
		String bodyLine="";
		String temp;
		for (Integer i = 0; i < alert.size(); i++) {
			if(flag==0){
			temp = "<tr>"
					+ "	<td"
					+ "	style=\"padding: 6px 20px; color: #00496B; border-left: 1px solid #E1EEF4; font-size: 12px; font-weight: normal;\">"
					+ (i + 1)
					+ "</td>"
					+ "	<td"
					+ "	style=\"padding: 6px 20px; color: #00496B; border-left: 1px solid #E1EEF4; font-size: 12px; font-weight: normal;\">"
					+ alert.get(i)
					+ "</td>"
					+ " <td"
					+ " style=\"padding: 6px 20px; color: #00496B; border-left: 1px solid #E1EEF4; font-size: 12px; font-weight: normal;\">"
					+ reliability.get(i)
					+ "</td>"
					+ " <td"
					+ " style=\"padding: 6px 20px; color: #00496B; border-left: 1px solid #E1EEF4; font-size: 12px; font-weight: normal;\">"
					+ risk.get(i)
					+ "</td>"
					+ "	<td"
					+ "	style=\"padding: 6px 20px; color: #00496B; border-left: 1px solid #E1EEF4; font-size: 12px; font-weight: normal;\">"
					+ url.get(i)
					+ "</td>"
					+ "	<td"
					+ "	style=\"padding: 6px 20px; color: #00496B; border-left: 1px solid #E1EEF4; font-size: 12px; font-weight: normal;\">"
					+ description.get(i)
					+ "</td>"
					+ " <td"
					+ " style=\"padding: 6px 20px; color: #00496B; border-left: 1px solid #E1EEF4; font-size: 12px; font-weight: normal;\">"
					+ evidence.get(i)
					+ "</td>"
					+ " <td"
					+ " style=\"padding: 6px 20px; color: #00496B; border-left: 1px solid #E1EEF4; font-size: 12px; font-weight: normal;\">"
					+ attack.get(i).toString().replaceAll("\\<|>","")
					+ "</td>"
					+ "	<td"
					+ "	style=\"padding: 6px 20px; color: #00496B; border-left: 1px solid #E1EEF4; font-size: 12px; font-weight: normal;\">"
					+ param.get(i)
					+ "</td>"
					+ "	<td"
					+ "	style=\"padding: 6px 20px; color: #00496B; border-left: 1px solid #E1EEF4; font-size: 12px; font-weight: normal;\">"
					+ reference.get(i)
					+ "</td>"
					+ "	<td"
					+ "	style=\"padding: 6px 20px; color: #00496B; border-left: 1px solid #E1EEF4; font-size: 12px; font-weight: normal;\">"
					+ solution.get(i)
					+ "</td>"
					+ " </tr>";
			flag=1;
			}
			else{
				temp=" <tr class=\"alt\">"
						+ "	<td"
						+ "	style=\"background: #E1EEF4;"
						+ " color: #00496B;padding: 6px 20px; color: #00496B; border-left: 1px solid #E1EEF4; font-size: 12px; font-weight: normal;\">"
						+ (i + 1)
						+ "</td>"
						+ "	<td"
						+ "	style=\"background: #E1EEF4;"
						+ " color: #00496B;padding: 6px 20px; color: #00496B; border-left: 1px solid #E1EEF4; font-size: 12px; font-weight: normal;\">"
						+ alert.get(i).toString().replaceAll("\\<|>","")
						+ "</td>"
						+ "	<td"
						+ "	style=\"background: #E1EEF4;"
						+ " color: #00496B;padding: 6px 20px; color: #00496B; border-left: 1px solid #E1EEF4; font-size: 12px; font-weight: normal;\">"
						+ reliability.get(i)
						+ "</td>"
						+ "	<td"
						+ "	style=\"background: #E1EEF4;"
						+ " color: #00496B;padding: 6px 20px; color: #00496B; border-left: 1px solid #E1EEF4; font-size: 12px; font-weight: normal;\">"
						+ risk.get(i)
						+ "</td>"
						+ "	<td"
						+ "	style=\"background: #E1EEF4;"
						+ " color: #00496B;padding: 6px 20px; color: #00496B; border-left: 1px solid #E1EEF4; font-size: 12px; font-weight: normal;\">"
						+ url.get(i)
						+ "</td>"
						+ "	<td"
						+ "	style=\"background: #E1EEF4;"
						+ " color: #00496B;padding: 6px 20px; color: #00496B; border-left: 1px solid #E1EEF4; font-size: 12px; font-weight: normal;\">"
						+ description.get(i)
						+ "</td>"
						+ "	<td"
						+ "	style=\"background: #E1EEF4;"
						+ " color: #00496B;padding: 6px 20px; color: #00496B; border-left: 1px solid #E1EEF4; font-size: 12px; font-weight: normal;\">"
						+ evidence.get(i)
						+ "</td>"
						+ "	<td"
						+ "	style=\"background: #E1EEF4;"
						+ " color: #00496B;padding: 6px 20px; color: #00496B; border-left: 1px solid #E1EEF4; font-size: 12px; font-weight: normal;\">"
						+ attack.get(i)
						+ "</td>"
						+ "	<td"
						+ "	style=\"background: #E1EEF4;"
						+ " color: #00496B;padding: 6px 20px; color: #00496B; border-left: 1px solid #E1EEF4; font-size: 12px; font-weight: normal;\">"
						+ param.get(i)
						+ "</td>"
						+ "	<td"
						+ "	style=\"background: #E1EEF4;"
						+ " color: #00496B;padding: 6px 20px; color: #00496B; border-left: 1px solid #E1EEF4; font-size: 12px; font-weight: normal;\">"
						+ reference.get(i)
						+ "</td>"
						+ "	<td"
						+ "	style=\"background: #E1EEF4;"
						+ " color: #00496B;padding: 6px 20px; color: #00496B; border-left: 1px solid #E1EEF4; font-size: 12px; font-weight: normal;\">"
						+ solution.get(i) + "</td>" + "	</tr>";
				flag=0;
			}
			bodyLine = bodyLine + temp;
		}

		try {
			// Assume default encoding.
			FileWriter fileWriter = new FileWriter(fileName);

			// Always wrap FileWriter in BufferedWriter.
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

			// Note that write() does not automatically
			// append a newline character.
			bufferedWriter.write(headerLine +bodyLine+ footerLine);

			// Always close files.
			bufferedWriter.close();
		} catch (IOException ex) {
			System.out.println("Error writing to file '" + fileName + "'");
			// Or we could just do this:
			// ex.printStackTrace();
		}

		// ----End of html report generation

	}

	public TestOutput saveData(List<Alert> alerts) {
		System.out.println("inside saveData function");
		TestOutput testOutput = new TestOutput();
		for (Integer i = 0; i < alerts.size(); i++) {

			testOutput.alert.add(alerts.get(i).getAlert());
			testOutput.reliability.add(alerts.get(i).getReliability()
					.toString());
			testOutput.risk.add(alerts.get(i).getRisk().toString());
			testOutput.url.add(alerts.get(i).getUrl());
			testOutput.evidence.add(alerts.get(i).getEvidence());
			testOutput.attack.add(alerts.get(i).getAttack());
			testOutput.description.add(alerts.get(i).getDescription());
			testOutput.reference.add(alerts.get(i).getReference());
			testOutput.param.add(alerts.get(i).getParam());
			testOutput.solution.add(alerts.get(i).getSolution());

		}

		return testOutput;

	}

	public void filterAlerts(ReadConfig readConfig) {
		System.out.println("inside filterResult function");

		System.out.println("Ignore Flag: " + readConfig.ignoreFlag);
		if (readConfig.ignoreFlag == 1) {
			System.out.println("inside filter 1 section");
			System.out.println(alert.size());
			for (Integer i = 0; i < alert.size(); i++) {
				Integer tempFlag = 0;

				for (Integer j = 0; j < readConfig.ignoreFilterReliability
						.size(); j++) {

					if (risk.get(i).equalsIgnoreCase(
							readConfig.ignoreFilterRisk.get(j))
							&& reliability.get(i).equalsIgnoreCase(
									readConfig.ignoreFilterReliability.get(j))) {
						System.out.println(risk.get(i) + "--"
								+ readConfig.ignoreFilterRisk.get(j) + "  "
								+ reliability.get(i) + "--"
								+ readConfig.ignoreFilterReliability.get(j));
						System.out.println("Filtered");
						tempFlag = 1;

					}
				}

				if (tempFlag == 1) {

				}

				else {
					System.out.println("Added to filtered list");
					alertFilter.add(alert.get(i));
					reliabilityFilter.add(reliability.get(i));
					riskFilter.add(risk.get(i));
					urlFilter.add(url.get(i));
					evidenceFilter.add(evidence.get(i));
					attackFilter.add(attack.get(i));
					descriptionFilter.add(description.get(i));
					referenceFilter.add(reference.get(i));
					paramFilter.add(param.get(i));
					solutionFilter.add(solution.get(i));
				}

			}

		}

		else if (readConfig.ignoreFlag == 2) {
			System.out.println("inside filter 2 section");
			for (Integer i = 0; i < alert.size(); i++) {
				System.out.println("loop 1");
				Integer tempFlag = 0;

				for (Integer j = 0; j < readConfig.ignoreReliability.size(); j++) {
					System.out.println("loop 2");
					if (alert.get(i).equalsIgnoreCase(
							readConfig.ignoreAlert.get(j))
							&& risk.get(i).equalsIgnoreCase(
									readConfig.ignoreRisk.get(j))
							&& reliability.get(i).equalsIgnoreCase(
									readConfig.ignoreReliability.get(j))) {
						System.out.println(risk.get(i) + "--"
								+ readConfig.ignoreRisk.get(j) + "  "
								+ reliability.get(i) + "--"
								+ readConfig.ignoreReliability.get(j));
						System.out.println("--Filtered");
						tempFlag = 1;

					}
				}

				if (tempFlag == 1) {

				} else {
					alertFilter.add(alert.get(i));
					reliabilityFilter.add(reliability.get(i));
					riskFilter.add(risk.get(i));
					urlFilter.add(url.get(i));
					evidenceFilter.add(evidence.get(i));
					attackFilter.add(attack.get(i));
					descriptionFilter.add(description.get(i));
					referenceFilter.add(reference.get(i));
					paramFilter.add(param.get(i));
					solutionFilter.add(solution.get(i));
				}

			}
		}

		else if (readConfig.ignoreFlag == 0) {
			System.out.println("inside filter 0 section");
			for (Integer i = 0; i < alert.size(); i++) {
				alertFilter.add(alert.get(i));
				reliabilityFilter.add(reliability.get(i));
				riskFilter.add(risk.get(i));
				urlFilter.add(url.get(i));
				evidenceFilter.add(evidence.get(i));
				attackFilter.add(attack.get(i));
				descriptionFilter.add(description.get(i));
				referenceFilter.add(reference.get(i));
				paramFilter.add(param.get(i));
				solutionFilter.add(solution.get(i));
			}
		}

	}

	public void printFilteredResult() {
		System.out.println("inside printFilteredResult function");

		for (Integer i = 0; i < alertFilter.size(); i++) {

			System.out.println("Alert: " + alertFilter.get(i));
			System.out.println("Relaibility: " + reliabilityFilter.get(i));
			System.out.println("Risk: " + riskFilter.get(i));
			System.out.println("URl: " + urlFilter.get(i));
			/*
			 * System.out.println("GetEvi: " + evidenceFilter.get(i));
			 * System.out.println("Attack: " + attackFilter.get(i));
			 * System.out.println("GetDesc: " + descriptionFilter.get(i));
			 * System.out.println("GetRef: " + referenceFilter.get(i));
			 * System.out.println("GetParam: " + paramFilter.get(i));
			 * System.out.println("GetSolution: " + solutionFilter.get(i));
			 */
			System.out.println("-------------------------------------");
		}

		System.out.println("Filtered alerts: " + alertFilter.size());

	}

}
