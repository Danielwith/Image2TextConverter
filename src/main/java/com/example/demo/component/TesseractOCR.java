package com.example.demo.component;

import java.io.File;

import org.springframework.stereotype.Component;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

// https://github.com/tesseract-ocr/tessdata

@Component
public class TesseractOCR {

  private final ITesseract tesseract;

  public TesseractOCR() {
    tesseract = new Tesseract();
    
    // Ubicación del archivo de idioma
    tesseract.setDatapath("C:\\Users\\The\\Desktop\\output");
    tesseract.setLanguage("spa");
  }

  public String recognizeText(File imageFile) throws TesseractException {
    return tesseract.doOCR(imageFile);
  }

}
