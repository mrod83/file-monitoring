package com.hcix.fileprocessing;

import java.nio.file.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import com.hcix.fileprocessing.api.FileProcessing;

@SpringBootApplication
@Component 
public class FileprocessingApplication {
	
	@Autowired
    FileProcessing fileProcessing;
	
	public static void main(String[] args) {
		System.out.println("  First argument is the Directory to monitor");
		System.out.println("  Second argument is the target Directory");
		
		Path pathToMonitor = Path.of(args[0]);
		String target = args[1];
		
		SpringApplication.run(FileprocessingApplication.class, args);
		
		ApplicationContext ctx = new AnnotationConfigApplicationContext("com.hcix.fileprocessing");
		FileprocessingApplication app = ctx.getBean(FileprocessingApplication.class);
		
		app.fileProcessing.monitor(pathToMonitor, target);
	}
		
}
