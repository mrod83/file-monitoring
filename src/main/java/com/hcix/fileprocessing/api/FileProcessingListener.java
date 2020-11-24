package com.hcix.fileprocessing.api;

import java.io.File;

public interface FileProcessingListener {
	void newFileProcessed(File file);
}
