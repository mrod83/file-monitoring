package com.hcix.fileprocessing.businesslogic;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public final class PlainTextAnalytic implements Analytic {

	@Override
	public void show(File file) {
		// todo: coupled to System.out
		
		System.out.println("File: " + file.getName());
		System.out.println("Word count: " + this.wordCount(file));
		System.out.println("Dots count: " + this.dotDots(file));
		
	}
	
	private long wordCount(File file) {
        try {
			Stream<String> lines = Files.lines(file.toPath());
			
			return lines.flatMap(line -> Arrays.stream(line.split(" "))).count();
		} catch (IOException e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
		return 0L;
	}
	
	private long dotDots(File file) {
		Pattern pattern = Pattern.compile("[\\.]+");
		
		try {
			//todo: read the content of file only once.
			Matcher countDotsMatcher = pattern.matcher(Files.readString(file.toPath()));
			
			return countDotsMatcher.results().count();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		return 0;
	}
	
	

}
