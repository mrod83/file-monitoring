package com.hcix.fileprocessing.api;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.ArrayList;
import java.util.List;

public final class DirectoryMonitoring {
	
	private final List<FileProcessingListener> listeners = new ArrayList<>();
	
	public void addFileProcessingListener(FileProcessingListener listener) {
		this.listeners.add(listener);
	}
	
	public void monitorsDirectory(Path path) {
		
		try {
			WatchService watchService = FileSystems.getDefault().newWatchService();
			
			// only new files are monitored
			path.register(watchService, StandardWatchEventKinds.ENTRY_CREATE);
			
			WatchKey key;
	        while ((key = watchService.take()) != null) {
	            for (WatchEvent<?> event : key.pollEvents()) {
	            	String filePath = String.format("%s/%s", path.toString(), event.context());
	            	File file = new File(filePath);
	            	
	            	this.listeners.forEach(l -> l.newFileProcessed(file));
	            	
	            	key.reset();	
	            }
	        }
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
