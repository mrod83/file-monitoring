package com.hcix.fileprocessing.api;

import java.io.File;
import java.nio.file.Path;

import com.hcix.fileprocessing.businesslogic.Analytic;

public interface FileProcessing {

	void monitor(Path directory, String target);
	
}