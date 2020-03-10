package com.doubleknd26.exercise.macro.util;

import com.sun.deploy.net.HttpRequest;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class NotificationManager {
	private static final String SLACK_WEBHOOK_URL =
			"https://hooks.slack.com/services/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";

	public static void noti(String message) {
		try {
			URL url = new URL(SLACK_WEBHOOK_URL);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoInput(true);
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/json");
			conn.setRequestProperty("Accept-Charset", "UTF-8");
			conn.setConnectTimeout(10000);

			OutputStream os = conn.getOutputStream();

			String json = "{\"text\": \"<!channel> " + message +"\"}";
			os.write(json.getBytes("UTF-8"));
			os.flush();

			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
			String line;
			while ((line = br.readLine()) != null) {
				System.out.println(line);
			}
			conn.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
