package com.doubleknd26.exercise.macro.mask;

import com.doubleknd26.exercise.macro.mask.search.CoupangSearcher;
import com.doubleknd26.exercise.macro.mask.search.Searcher;
import com.doubleknd26.exercise.macro.util.NotificationManager;

public class MaskMacro {

	public void start() throws Exception {
		Searcher searcher = new CoupangSearcher(TargetInfo.COUPANG, false);
		NotificationManager.noti("마스크 매크로를 시작합니다.");
		searcher.start();

		Runtime.getRuntime().addShutdownHook(new Thread(() -> {
			// Use stderr here since the logger may have been reset by its JVM shutdown hook.
			searcher.stop();
			System.err.println("MaskMacro shutdown");
		}));
	}

	public static void main(String[] args) throws Exception {
		MaskMacro maskMacro = new MaskMacro();
		maskMacro.start();
	}
}
