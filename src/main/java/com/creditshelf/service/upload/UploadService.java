package com.creditshelf.service.upload;

import org.springframework.web.multipart.MultipartFile;

public interface UploadService {
	int uploadProducts(MultipartFile file) throws Exception;
	int uploadSales(MultipartFile file) throws Exception;
}
