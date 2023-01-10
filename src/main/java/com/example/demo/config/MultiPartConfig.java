package com.example.demo.config;

import java.io.IOException;

import org.springframework.http.codec.multipart.Part;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/recognize")
@MultipartConfig(fileSizeThreshold = 5_242_880,  // 5MB
				 maxFileSize = 20_971_520L,       // 20MB
				 maxRequestSize = 41_943_040L)    // 40MB
public class MultiPartConfig extends HttpServlet {

  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

@Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    // Obtiene el archivo subido
    Part filePart = (Part) request.getPart("file");

  }

}