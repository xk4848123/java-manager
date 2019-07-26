package com.nidecai.managerndc.commoncontroller;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.nidecai.managerndc.ExceptionHandle.BusinessException;
import com.nidecai.managerndc.common.codeutil.CommonMessageEnum;
import com.nidecai.managerndc.common.codeutil.FileUtil;
import com.nidecai.managerndc.common.codeutil.LoggingUtil;
import com.nidecai.managerndc.common.codeutil.ResultUtil;
import com.nidecai.managerndc.common.entitycommon.ResultDTO;



/**
 * 文件上传的Controller
 * 
 * @author chen_di
 * @create 2017年12月12日
 */
@RestController
@RequestMapping(value = "/common")
public class FileUploadController {

	/**
	 * 文件上传具体实现方法（单文件上传）
	 *
	 * @param file
	 * @return
	 * 
	 * @author chen_di
	 * @throws BusinessException
	 * @create 2017年12月12日
	 */

	@Value("${upload.catalogue}")
	private String catalogue;

	@Value("${upload.httpCatalogue}")
	private String httpCatalogue;

//	@Autowired
//	private AsyncTasks tasks;

	@RequestMapping(value = "/v1/upload", method = RequestMethod.POST)
	public ResultDTO<Object> upload(@RequestParam("file") MultipartFile file)
			throws BusinessException {
		BufferedOutputStream out = null;
		try {
			if (!file.isEmpty()) {
				// 这里只是简单例子，文件直接输出到项目路径下。
				// 实际项目中，文件需要输出到指定位置，需要在增加代码处理。
				// 还有关于文件格式限制、文件大小限制，详见：中配置。
				String newFileName = FileUtil.changeFileNameToRandom(file.getOriginalFilename());
				out = new BufferedOutputStream(new FileOutputStream(catalogue + newFileName));
				out.write(file.getBytes());
				out.flush();
				if (file.getSize() > 1024 * 500) {
					//压缩处理
                    //tasks.compressTask(catalogue + newFileName, 60d);
				}
				return ResultUtil.getSuccess(httpCatalogue + newFileName);
			} else {
				return ResultUtil.getFail(null);
			}
		} catch (Exception e) {
			throw new BusinessException(CommonMessageEnum.FAIL.getCode(), CommonMessageEnum.FAIL.getMsg());
		} finally {
			try {
				if (out != null) {
					out.close();
				}
			} catch (IOException e) {
				LoggingUtil.e("BufferedOutputStream close fail , " + e.getMessage());
			}
		}
	}

	/**
	 * 多文件上传 主要是使用了MultipartHttpServletRequest和MultipartFile
	 *
	 * @param request
	 * @return
	 * 
	 * @author chen_di
	 * @throws BusinessException
	 * @create 2017年12月12日
	 */
	@RequestMapping(value = "/v1/upload/batch", method = RequestMethod.POST)
	public ResultDTO<Object> batchUpload(HttpServletRequest request) throws BusinessException {
		BufferedOutputStream stream = null;
		List<BufferedOutputStream> listStream = null;
		try {
			List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("file");
			List<String> newfileNameList = new ArrayList<String>();
			MultipartFile file = null;
			int filesLength = files.size();
			for (int i = 0; i < filesLength; ++i) {
				file = files.get(i);
				if (!file.isEmpty()) {
					byte[] bytes = file.getBytes();
					String newFileName = FileUtil.changeFileNameToRandom(file.getOriginalFilename());
					if (listStream == null) {
						listStream = new ArrayList<BufferedOutputStream>();
					}
					stream = new BufferedOutputStream(new FileOutputStream(catalogue + newFileName));
					listStream.add(stream);
					stream.write(bytes);
					newfileNameList.add(httpCatalogue + newFileName);
					stream.flush();
					if (file.getSize() > 1024 * 500) {
						//压缩处理
//						tasks.compressTask(catalogue + newFileName, 60d);
					}
				} else {
					return ResultUtil.getFail(null);
				}
			}
			return ResultUtil.getSuccess(newfileNameList);
		} catch (Exception e) {
			throw new BusinessException(CommonMessageEnum.FAIL.getCode(), "批量上传失败 , " + e.getMessage());
		} finally {
			try {
				int listStreamLenth = listStream.size();
				for (int i = 0; i < listStreamLenth; i++) {
					if (listStream.get(i) != null) {
						listStream.get(i).close();
					}
				}
			} catch (IOException e) {
				LoggingUtil.e("BufferedOutputStream close fail , " + e.getMessage());
			}
		}
	}

	@RequestMapping(value = "/v1/upload/realease", method = RequestMethod.POST)
	public ResultDTO<Object> upload(@RequestParam("filepath") String[] filePaths, @RequestParam("token") String token)
			throws BusinessException {
		try {
			FileUtil.deletePics(filePaths);
			return ResultUtil.getSuccess(null);
		} catch (Exception e) {
			return ResultUtil.getFail(null);
		}

	}

}