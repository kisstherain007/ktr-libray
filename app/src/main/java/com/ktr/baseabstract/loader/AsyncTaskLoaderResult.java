package com.ktr.baseabstract.loader;

import android.os.Bundle;

/**
 * 
 * @author kisstherain
 *
 * @param <T>
 */
public class AsyncTaskLoaderResult<T> {

	public T data;
	
	public Bundle args;
	
	public Exception exception;
}
