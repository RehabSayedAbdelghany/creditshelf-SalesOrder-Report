package com.creditshelf.controller;


import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.creditshelf.service.upload.UploadService;




@RestController
@RequestMapping("/upload")
@Validated
public class UploadController {
	@Autowired
	private UploadService uploadService;
	
	@PostMapping(value="/product", produces = "application/json")
    public ResponseEntity<Map<String, String>> uploadProducts(@RequestParam("file") MultipartFile file) throws Exception {
	
		uploadService.uploadProducts(file);
		HashMap<String,String> map =  new HashMap<String, String>();
		map.put("response", "File Uploaded Successfully");
        return new ResponseEntity<Map<String, String>> (map,HttpStatus.OK);
    }
	
	
	@PostMapping(value="/sales", produces = "application/json")
    public ResponseEntity<Map<String, String>> uploadSales(@RequestParam("file") MultipartFile file) throws Exception {

		uploadService.uploadSales(file);
		HashMap<String,String> map =  new HashMap<String, String>();
		map.put("response", "File Uploaded Successfully");
        return new ResponseEntity<Map<String, String>> (map,HttpStatus.OK);
    }
}
