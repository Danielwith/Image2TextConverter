package com.example.demo.component;

import java.io.File;
import java.io.IOException;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.core.io.Resource;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

// https://github.com/tesseract-ocr/tessdata

@Component
public class TesseractOCR {

  private final ITesseract tesseract;

  public TesseractOCR() throws IOException {
    tesseract = new Tesseract();
    Resource resource = new ClassPathResource("static/resources");
    String path = resource.getFile().getAbsolutePath();
    
    // Ubicación del archivo de idioma
    tesseract.setDatapath(path);
    tesseract.setLanguage("spa");
  }

  public String recognizeText(File imageFile) throws TesseractException {
    return tesseract.doOCR(imageFile);
  }

}
