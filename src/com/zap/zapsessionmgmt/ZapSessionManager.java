package com.zap.zapsessionmgmt;

import java.io.IOException;
import java.util.List;

import org.zaproxy.clientapi.core.Alert;
import org.zaproxy.clientapi.core.ClientApiException;

import com.zap.input.ReadConfig;
import com.zap.main.Zap;

public class ZapSessionManager {

	public Process startZapProcess() throws IOException, InterruptedException {
		System.out.println("inside startZapProcess function");

		ReadConfig readConfig = new ReadConfig();
		String[] cmd1 = { "/bin/sh", "-c",
				"cd " + readConfig.zapDir + ";java -jar zap.jar -daemon" };
		Runtime run = Runtime.getRuntime();
		Process process = run.exec(cmd1);
		Thread.sleep(40000);
		System.out.println("Zap Started");
		return process;

	}

	public void stopZapProcess(Process process) {
		System.out.println("inside stopZapProcess function");
		process.destroy();
	}

	@SuppressWarnings("deprecation")
	public Zap startZapSession(String host, Integer port)
			throws ClientApiException {
		System.out.println("inside startZapSession function");
		Zap zap = new Zap(host, port);
		zap.newSession();
		zap.core.generateRootCA();
		zap.autoupdate.optionInstallAddonUpdates();
		return zap;
	}

	@SuppressWarnings("deprecation")
	public void stopZapSession(Zap zap) throws ClientApiException {
		System.out.println("inside stopZapSession function");
		zap.stopZap();
	}

	public List<Alert> getBasicAlerts(Zap zap) throws ClientApiException {
		System.out.println("inside getBasicAlerts function");

		List<Alert> alerts = zap.getAlerts(null, 0, 0);
		// printAlerts(alerts);
		return alerts;
	}

	@SuppressWarnings("deprecation")
	public List<Alert> getActiveScanAlerts(Zap zap, List<String> urls)
			throws ClientApiException, InterruptedException

	{
		System.out.println("inside getActiveScanAlerts function");
		for (Integer count = 0; count < urls.size(); count++) {
			System.out.println(urls.get(count));
			zap.activeScanSite(urls.get(count));

		}
		List<Alert> alerts = zap.getAlerts(null, 0, 0);
		Thread.sleep(10000);
		// printAlerts(alerts);
		return alerts;
	}

	@SuppressWarnings("deprecation")
	public List<Alert> getSpiderScanAlerts(Zap zap, List<String> urls)
			throws ClientApiException, InterruptedException

	{
		System.out.println("inside getSpiderScanAlerts function");
		for (Integer count = 0; count < urls.size(); count++) {
			System.out.println(urls.get(count));
			zap.spiderUrl(urls.get(count));
		}
		List<Alert> alerts = zap.getAlerts(null, 0, 0);
		printAlerts(alerts);
		Thread.sleep(10000);
		return alerts;
	}

	@SuppressWarnings("deprecation")
	public List<Alert> getActiveScanUrlAlerts(Zap zap, List<String> urls)
			throws ClientApiException, InterruptedException

	{	System.out.println("inside getActiveScanUrlAlerts function");
		System.out.println(urls.size());
		for (Integer count = 0; count < urls.size(); count++) {
			System.out.println(urls.get(count));
			zap.activeScanUrl(urls.get(count));
		}
		List<Alert> alerts = zap.getAlerts(null, 0, 0);
		printAlerts(alerts);
		Thread.sleep(10000);
		return alerts;
	}

	public void printAlerts(List<Alert> alerts) {
		System.out.println("inside printAlerts function");
		for (Integer i = 0; i < alerts.size(); i++) {

		 System.out.println("Alert: " + alerts.get(i).getAlert());
		 System.out.println("Relaibility: " + alerts.get(i).getReliability());
			System.out.println("Risk: " + alerts.get(i).getRisk());
			System.out.println("URl: " + alerts.get(i).getUrl());
			System.out.println("GetEvi: " + alerts.get(i).getEvidence());
			System.out.println("Attack: " + alerts.get(i).getAttack());
			System.out.println("GetDesc: " + alerts.get(i).getDescription());
			System.out.println("GetRef: " + alerts.get(i).getReference());
			System.out.println("GetParam: " + alerts.get(i).getParam());
			System.out.println("GetSolution: " + alerts.get(i).getSolution());
			System.out.println("--------------------------------------------------------------");

		}
		System.out.println("Total alerts:"+alerts.size());
	}
}
