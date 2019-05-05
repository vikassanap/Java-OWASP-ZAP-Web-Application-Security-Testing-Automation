package com.zap.fileoperations;

import java.io.File;

import org.apache.commons.io.FileUtils;

import java.io.IOException;

import org.apache.commons.io.FileDeleteStrategy;

import com.zap.input.ReadConfig;
import com.zap.output.TestOutput;

public class FileOperations {

	String pathToScan;
	String target_file;
	String FileName;
	File[] listOfFiles1;

	public FileOperations() {

	}

	public String getFileName(String fileName) throws IOException {
		File folderToScan = new File(pathToScan);
		listOfFiles1 = folderToScan.listFiles();
		for (int i = 0; i < listOfFiles1.length; i++) {
			if (listOfFiles1[i].isFile()) {
				target_file = listOfFiles1[i].getName();
				if (target_file.startsWith(fileName.substring(0, 11))
						&& target_file.endsWith(".har")) {
					FileName = pathToScan + target_file;

					return FileName;
				}
			}
		}

		return FileName;
	}

	public void deleteAllFiles(String pathToScan) throws IOException {
		File folderToScan = new File(pathToScan);
		listOfFiles1 = folderToScan.listFiles();
		System.out.println("Deleting the files");
		for (int i = 0; i < listOfFiles1.length; i++) {

			FileDeleteStrategy.FORCE.delete(listOfFiles1[i]);

		}

	}

	public void copyFileToDir(ReadConfig readconfig, TestOutput testOutput,
			String fileName) throws IOException {
		System.out.println("inside copyFileToDir function");
		File srcFile = new File(testOutput.fileName);
		File destDir = new File(readconfig.tomcatDir);
		FileUtils.copyFileToDirectory(srcFile, destDir);

		destDir = new File(readconfig.historyDir);
		FileUtils.copyFileToDirectory(srcFile, destDir);

	}

}
