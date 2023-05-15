package com.kh.app.util.file;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.Part;

public class FileUploader {
	
	/**
	 * 파일들을 서버에 저장 하고,
	 * 저장된 파일명들이 담긴 리스트를 반환한다.
	 * 
	 * @param 파일 저장할 경로
	 * @param 파일객체배열
	 * @return List<AttachmentVo>
	 * @throws Exception
	 */
	public static List<DramaFileVo> saveFile(String path, List<Part> fList) throws Exception{
		
		List<DramaFileVo> list = new ArrayList<>();
		
//		for(Part f : fList) {
//			DramaFileVo attVo = saveFile(path, f);
//			list.add(attVo);
//		}
//		
		return list;
	}
	
}//class















