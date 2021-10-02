package com.kkd.study.algorithm.codingtest.baemin.unknown;

import java.util.*;

/**
 * Your computer's hard drive is almost full. In order to make some space, you need to compile
 * some file statistics.
 * You want to know how many bytes of memory each file type is consuming.
 * Each file has a name, and the part of the name after the last dot is called the file
 * extension, which identifies what type of file it is. We distinguish four broad types of file:
 * music (only extensions: mp3, aac, flac)
 * image (only extensions: jpg, bmp, gif)
 * movie (only extensions: mp4, avi, mkv)
 * other (all other extensions; for example: 7z, txt, zip)
 *
 * You receive string S, containing a list of all the files on your computer (each file appears
 * on a separate line).
 * Each line contains a file name and the file's size in bytes, separated by a space. For
 * example, string S could look like:
 * "my.song.mp3 11b
 * greatSong.flac 1000b
 * not3.txt 5b
 * video.mp4 200b
 * game.exe 100b
 * mov!e.mkv 10000b"
 *
 * There are two music files (my.song.mp3 and greatSong.flac, of size 11 and 1000 bytes
 * respectively). There are no images files. We have two movies files (video.mp4 and mov!e.mkv of
 * size 200 and 10000 bytes). There are two files of other types (not3.txt and game.exe of size 5
 * and 100 bytes). In total there are 1011 bytes of music, 0 bytes of images, 10200 bytes of
 * movies and 105 bytes of other files.
 * Write a function:
 * class Solution { public String solution(String S); }
 * that, given string S describing the files on disk, returns a string containing four rows,
 * describing music, images, movies and other file types respectively. Each row should consist of
 * a file type and the number of bytes consumed by files of that type on the disk (use format
 * "<<type>> <<size>>b", where <<type>> is the files' type and <<size>> is the total file size of
 * this group).
 * For instance, given string S as shown above, your function should return:
 * "music 1011b
 * images 0b
 * movies 10200b
 * other 105b"
 * as described above.
 * Assume that:
 * string S contains at most 500 lines; there are no empty lines; each line contains exactly one
 * space, separating the file name from its size;
 * each file described in S has a non-empty file name of at most 30 characters' length; the file
 * name includes a file extension as its final few characters, from the character after the last
 * dot to the end of the file name; the file extension is never empty and it consists only of
 * lower-case English letters (a−z) and digits (0−9); the file name can't consist of an extension
 * alone;
 * the file name consists only of lower-case English letters (a−z), upper-case English letters
 * (A−Z), digits (0−9) and special characters "^&'@{}[],$=!-#()%.+~_" (without quotes);
 * the size of each file described in S is a positive integer, less than or equal to 1,000,000
 * bytes.
 * In your solution, focus on correctness. The performance of your solution will not be the focus
 * of the assessment.
 */
public class _2 {
	public enum Extension {
		music(Arrays.asList("mp3", "aac", "flac")),
		image(Arrays.asList("jpg", "bmp", "gif")),
		movie(Arrays.asList("mp4", "avi", "mkv")),
		other(Collections.emptyList());

		private List<String> list;

		Extension(List<String> list) {
			this.list = list;
		}

		private static Extension fromString(String extensionStr) {
			for (Extension extension : Extension.values()) {
				if (extension.list.contains(extensionStr)) {
					return extension;
				}
			}
			return other;
		}
	}

	/**
	 * string S:
	 *   - contains at most 500 lines.
	 *   - there are no empty lines.
	 *   - each line contains exactly one space, separating the file name from its size.
	 * each file described in S:
	 *   - has a non-empty file name of at most 30 length.
	 *   - the file name includes a file extension as its final few characters, from the character after the last dot to the end of the file name.
	 *   - the file extension is never empty and it consists only of lower-case English letters (a−z) and digits (0−9)
	 *   - the file name can't consist of an extension alone.
	 *   - the file name consists only of lower-case English letters (a−z), upper-case English letters (A−Z), digits (0−9) and special characters "^&'@{}[],$=!-#()%.+~_" (without quotes);
	 *   - the size of each file described in S is a positive integer, less than or equal to 1,000,000 bytes.
	 */
	public String solution(String S) {
		Map<Extension, Integer> map = new HashMap<>();
		String[] filenames = S.split("\n");
		for (String filename: filenames) {
			String extensionStr = filename.substring(filename.lastIndexOf(".")+1, filename.lastIndexOf(" "));
			Extension extension = Extension.fromString(extensionStr);
			int size = Integer.parseInt(filename.substring(filename.lastIndexOf(" ") + 1, filename.length()-1));
			map.put(extension, map.getOrDefault(extension, 0) + size);
		}
		StringBuilder sb = new StringBuilder();
		for (Extension extension : Extension.values()) {
			int bytes = map.getOrDefault(extension, 0);
			sb.append(extension.name()).append(" ").append(bytes).append("b");
			if (!extension.equals(Extension.other)) {
				sb.append("\n");
			}
		}
		return sb.toString();
	}

	public static void main(String[] args) {
		_2 program = new _2();
		String res = program.solution(
			"my.song.mp3 11b\ngreatSong.flac 1000b\nnot3.txt 5b\nvideo.mp4 200b\ngame.exe 100b\nmov!e.mkv 10000b");

		System.out.println(res);
	}
}
