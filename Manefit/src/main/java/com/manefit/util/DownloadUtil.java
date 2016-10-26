package com.manefit.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.FileCopyUtils;
import org.springframework.web.servlet.view.AbstractView;

public class DownloadUtil extends AbstractView {
	
	public DownloadUtil() {
		setContentType("application/download; charset=UTF-8");
	}

	@Override
	public void renderMergedOutputModel(Map model, HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		HashMap tempMap = (HashMap) model.get("downloadFile");
		File file = new File((String) tempMap.get("PATH"), (String) tempMap.get("SAVE"));
		String fileName = (String) tempMap.get("ORI");
		fileName = URLEncoder.encode(fileName, "UTF-8");

	
		resp.setContentType(getContentType());
		resp.setContentLength((int) file.length());
		resp.setHeader("Content-Disposition", "attachment;filename=\"" + fileName + "\";");
		resp.setHeader("Content-Transfer-Encoding", "binary");
		
		OutputStream os = resp.getOutputStream();
		
		FileInputStream fin = null;
		try {
			fin = new FileInputStream(file);
			FileCopyUtils.copy(fin, os);	
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				fin.close();
				os.flush();
				os.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
}
