package com.hcix.fileprocessing.api;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.io.Files;
import com.hcix.fileprocessing.businesslogic.Analytic;
import com.hcix.fileprocessing.businesslogic.FileProcessingService;
import com.hcix.fileprocessing.businesslogic.PlainTextAnalytic;

@Service
public class FileProcessingImpl implements FileProcessingListener, FileProcessing {

	@Autowired
	FileProcessingService service;
	
	private String target = "";
	
	@Override
	public void monitor(Path directory, String target) {
		var monitoring = new DirectoryMonitoring();
		
		this.target = target;
		
		monitoring.addFileProcessingListener(this);
		monitoring.monitorsDirectory(directory);
	}
	
	@Override
	public void newFileProcessed(File file) {
		 try (InputStream inputStream = new FileInputStream(file)) {
			 
			 // persists
			 service.save(inputStream, Files.getFileExtension(file.getName()), target + file.getName());
			 
			 // do some analytics
			 service.analytics(file, new PlainTextAnalytic());
			 
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


}
