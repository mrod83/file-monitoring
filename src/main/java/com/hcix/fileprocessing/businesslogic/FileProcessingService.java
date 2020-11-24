package com.hcix.fileprocessing.businesslogic;

import java.io.File;
import java.io.InputStream;

public interface FileProcessingService {
	
	public void save(InputStream inputStream, String extension, String target);
	
	public void analytics(File file, Analytic analyticType);

}
