package com.pe.timy.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.pe.timy.service.CloudinaryService;

@Service
public class CloudinaryServiceImpl implements CloudinaryService {

	private Cloudinary cloudinary;
	private Map<String, String> config = new HashMap<>();

	public CloudinaryServiceImpl() {
		config.put("cloud_name", "ds1ko0dcb");
        config.put("api_key", "782889841727727");
        config.put("api_secret", "_4Pq-Z1z3Xcgp1Zwv32K_hkn5uQ");
        cloudinary = new Cloudinary(config);
	}

	@Override
	public Map<?, ?> upload(MultipartFile multipartFile) throws IOException {
		File file = this.convert(multipartFile);
		Map<?, ?> result = cloudinary.uploader().upload(file, ObjectUtils.emptyMap());
		file.delete();
		return result;
	}

	@Override
	public Map<?, ?> delete(String id) throws IOException {
		Map<?, ?> result = cloudinary.uploader().destroy(id, ObjectUtils.emptyMap());
		return result;
	}

	private File convert(MultipartFile multipartFile) throws IOException {
		File file = new File(multipartFile.getOriginalFilename());
		FileOutputStream out = new FileOutputStream(file);
		out.write(multipartFile.getBytes());
		out.close();
		return file;
	}
}
