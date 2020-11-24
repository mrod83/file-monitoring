package com.hcix.fileprocessing.repository;

import java.io.InputStream;

public interface FileSystemRepository {

	public void save(InputStream inputStream, String target);
	
}
