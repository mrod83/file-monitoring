package com.hcix.fileprocessing.businesslogic;

import java.io.File;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.hcix.fileprocessing.repository.FileSystemRepository;


@Service
public class FileProcessingServiceImpl implements FileProcessingService {

	private final FileSystemRepository repository;
	
	private final Set<String> validFileTypes = new HashSet<>(Arrays.asList("txt"));
	
	public FileProcessingServiceImpl(FileSystemRepository repository) {
		this.repository = repository;
	}

	@Override
	public void save(InputStream inputStream, String extension, String target) {
		Objects.requireNonNull(inputStream);
		Objects.requireNonNull(extension);
		Objects.requireNonNull(target);
		
		if (!this.isValidExtension(extension)) {
			throw new IllegalStateException(extension + " is not a valid extension");
		}
		
		this.repository.save(inputStream, target);
	}

	private boolean isValidExtension(String extension) {
		return this.validFileTypes.contains(extension);
	}

	@Override
	public void analytics(File file, Analytic analyticType) {
		Objects.requireNonNull(file);
		Objects.requireNonNull(analyticType);
		
		analyticType.show(file);
	}

}
