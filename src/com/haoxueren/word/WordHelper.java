package com.haoxueren.word;

import java.io.File;
import java.util.Map;
import java.util.TreeMap;

import org.junit.Test;

import com.haoxueren.config.Values;
import com.haoxueren.config.ConfigHelper;
import com.haoxueren.config.Keys;
import com.haoxueren.helper.FileHelper;
import com.haoxueren.helper.RandomHelper;

public class WordHelper
{
	private static String wrodsPath;
	/** 已抽取单词的个数； */
	private static int index, loop = 29;

	static
	{
		wrodsPath = ConfigHelper.getConfig(Keys.WORDS_PATH, Values.WORDS_PATH);
	}

	/** 初始化存储文件信息的集合； */
	private static Map<Long, File> initFileMap()
	{
		Map<Long, File> fileInfoMap = new TreeMap<>();
		File directory = new File(wrodsPath);
		if (!directory.exists())
		{
			directory.mkdirs();
		}
		File[] files = directory.listFiles();
		for (File file : files)
		{
			// 获取文件的最后修改时间；
			long lastModified = file.lastModified();
			// 将文件信息保存在fileInfoMap中；
			fileInfoMap.put(lastModified, file);
		}
		return fileInfoMap;
	}

	/**
	 * 算法：为知笔记2016.02.25； 要求：index<=loop<=sum；<br>
	 * 按就近原则(lastModifyTime)随机获取单词；<br>
	 */
	public static File getRandomWordFile()
	{
		Map<Long, File> map = initFileMap();
		Object[] objects = map.keySet().toArray();
		int sum = map.size();
		if (sum == 0)
		{
			return null;
		}
		int start = sum - (sum * (++index) / loop);
		int random = RandomHelper.getRandomInt(start, sum);
		Object time = objects[random];
		File file = map.get(time);
		System.out.println("[" + start + "~" + sum + "]→" + index + "、" + getWordName(file));
		return file;
	}

	/** 根据文件获取单词名； */
	public static String getWordName(File word)
	{
		String name = word.getName();
		return name.substring(0, name.length() - 4);
	}

}
