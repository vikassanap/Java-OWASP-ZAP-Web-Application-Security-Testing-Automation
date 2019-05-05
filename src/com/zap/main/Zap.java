package com.zap.main;

import java.io.IOException;
import java.util.List;

import org.zaproxy.clientapi.core.Alert;
import org.zaproxy.clientapi.core.ClientApi;
import org.zaproxy.clientapi.core.ClientApiException;

import com.zap.fileoperations.FileOperations;
import com.zap.input.ReadConfig;
import com.zap.mail.SendMail;
import com.zap.output.TestOutput;
import com.zap.testcases.Test;
import com.zap.zapsessionmgmt.ZapSessionManager;

public class Zap extends ClientApi {

	public static Process process;
	public static ZapSessionManager zapSessionManager;
	public static Zap zap;
	public static List<String> urls;
	public static List<Alert> alerts;
	public static Test test = new Test();
	public static TestOutput testOutput = new TestOutput();
	public static SendMail sendMail = new SendMail();
	public static ReadConfig readConfig = new ReadConfig();
	public static FileOperations fileOper = new FileOperations();

	public Zap(String arg0, int arg1) {
		super(arg0, arg1);

	}

	public static void end() throws ClientApiException {
		System.out.println("inside end function");
		zapSessionManager.stopZapSession(zap);
		zapSessionManager.stopZapProcess(process);
	}

	public static Zap init() throws IOException, InterruptedException,
			ClientApiException {
		System.out.println("inside init function");
		urls=readConfig.scanUrlList;
		zapSessionManager = new ZapSessionManager();
		process = zapSessionManager.startZapProcess();
		zap = zapSessionManager.startZapSession("localhost", 8080);
		return zap;
	}

	public static void getAlerts() throws ClientApiException, InterruptedException {
		System.out.println("inside getAlerts function");

		zapSessionManager.getBasicAlerts(zap);
		zapSessionManager.getActiveScanAlerts(zap, urls);
		zapSessionManager.getActiveScanUrlAlerts(zap, urls);
		alerts = zapSessionManager.getSpiderScanAlerts(zap, urls);
	}

	public static void executeTest() {
		System.out.println("inside executeTest function");
		test.TestCase();
	}

	public static void sendmail() throws IOException {
		System.out.println("inside sendMail function");
		for (Integer i = 0; i < readConfig.emailTo.size(); i++) {
			sendMail.sendMail(testOutput, readConfig.emailTo.get(i),readConfig);
		}
	}

	public static void processAlerts() {
		System.out.println("inside processAlerts function");
		testOutput = testOutput.saveData(alerts);
		testOutput.printResult();
		testOutput.filterAlerts(readConfig);
		testOutput.printFilteredResult();
		testOutput.generateHtmlReport(readConfig);

	}

	public static void performDirOpertions() throws IOException{
		fileOper.copyFileToDir(readConfig,testOutput,testOutput.fileTitle);
		fileOper.deleteAllFiles(readConfig.htmlReportDir);
		
	}
	public static void main(String args[]) throws ClientApiException,
			InterruptedException, IOException {
		System.out.println("inside main function");
		
		init();
		executeTest();
		getAlerts();
		processAlerts();
		performDirOpertions();
		end();
		sendmail();
		
	}

}
