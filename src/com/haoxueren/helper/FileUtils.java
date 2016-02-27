package com.haoxueren.helper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import com.haoxueren.helper.FileFinder.FileFinderListener;

/** ���ҳ�ָ��Ŀ¼�������ļ��� */
public class FileUtils implements FileFinderListener
{
	/** �������������ļ����ϣ� */
	private ArrayList<File> list;
	/** ���ط����������ļ��еļ��ϣ� */
	private FileHelperListener listener;

	public FileUtils(FileHelperListener listener)
	{
		this.listener = listener;
	}

	/** �����ļ��������ļ��� */
	public void getFiles(File directory)
	{
		list = new ArrayList<File>();
		FileFinder finder = new FileFinder(this);
		finder.findFile(directory);
		listener.onFileFindOver(list);
	}

	@Override
	public void onFileFindOut(File directory, File file)
	{
		list.add(file);
	}

	public interface FileHelperListener
	{
		void onFileFindOver(ArrayList<File> list);
	}

	/** �ж��ļ�����ָ���ĺ�׺�� */
	public boolean endWith(String filename, String... suffixs)
	{
		for (String suffix : suffixs)
		{
			if (filename.endsWith(suffix))
			{
				return true;
			}
		}
		return false;
	}

}