package com.nidecai.managerndc.common.codeutil;

import java.io.File;
import java.util.UUID;

public class FileUtil {
	public static String changeFileNameToRandom(String originalFilename) {
		return UUID.randomUUID().toString() + originalFilename.substring(originalFilename.lastIndexOf("."));
	};
	// 删除图片
	public static void deletePics(String... args) {
		for (int i = 0; i < args.length; i++) {
			File file = new File(args[i]);
			// 路径为文件且不为空则进行删除
			if (file.isFile() && file.exists()) {
				file.delete();
			}
		}
	}
}
