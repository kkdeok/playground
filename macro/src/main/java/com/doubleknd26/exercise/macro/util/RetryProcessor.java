package com.doubleknd26.exercise.macro.util;

import java.util.concurrent.*;

public class RetryProcessor {
	private static final int RETRY = 5;

	// https://stackoverflow.com/questions/12967541/how-to-avoid-staleelementreferenceexception-in-selenium
	public static <V> V process(ExecutorService executor, Callable<V> task) {
		return process(executor, task, 1, TimeUnit.SECONDS);
	}

	public static <V> V process(ExecutorService executor, Callable<V> task, int timeout, TimeUnit timeUnit) {
		V response = null;
		for (int i=0 ; i<RETRY ; i++) {
			try {
				Future<V> future = executor.submit(task);
				response = future.get(timeout, timeUnit);
				break;
			}  catch (InterruptedException | ExecutionException | TimeoutException e) {
				// do nothing and retry
			}
		}
		return response;
	}
}
