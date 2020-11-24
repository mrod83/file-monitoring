package com.hcix.fileprocessing.repository;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

import org.springframework.stereotype.Service;

@Service
public class FileSystemRepositoryImpl implements FileSystemRepository {

	@Override
	public void save(InputStream inputStream, String target) {
		try {
			Files.copy(inputStream, Path.of(target));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
