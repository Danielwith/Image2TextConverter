package com.example.demo.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.component.TesseractOCR;

import net.sourceforge.tess4j.TesseractException;

@Controller
@RequestMapping("/")
public class OCRController {

  private final TesseractOCR tesseractOCR;

  public OCRController(TesseractOCR tesseractOCR) {
    this.tesseractOCR = tesseractOCR;
  }
  
  @RequestMapping("/")
  public String indice() {
	  return "index";
  }

  @PostMapping("/recognize")
  public String recognizeText(@RequestParam("image") MultipartFile image, Model model) throws TesseractException, IllegalStateException, IOException {
	  File imageFile = null;
	  try {
		  imageFile = convertToFile(image);
		  model.addAttribute("response", tesseractOCR.recognizeText(imageFile));
		  model.addAttribute("ICON", "success");
		  model.addAttribute("MENSAJE", "Successful conversion!");

	  }
	  catch(TesseractException e) {
		  model.addAttribute("ICON", "error");
		  model.addAttribute("MENSAJE", "Such embedded format is not supported, please try to convert it to a supported one.");
	  }
	  finally {
		  if (imageFile != null && imageFile.exists()) {
	            imageFile.delete();
	        }
	  }

    return "index";
  }

  private File convertToFile(MultipartFile multipartFile) throws IllegalStateException, IOException {
    File convFile = new File(multipartFile.getOriginalFilename());
    convFile.createNewFile();
    FileOutputStream fos = new FileOutputStream(convFile);
    fos.write(multipartFile.getBytes());
    fos.close();
    return convFile;
  }

}
