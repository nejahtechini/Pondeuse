package com.tondeuse.demo;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.NoSuchElementException;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.tondeuse.demo.serviceImpl.TondeuseService;

@SpringBootApplication
@ComponentScan({ "com.tondeuse.demo.serviceImpl", "com.tondeuse.demo.entites" })
public class DemoApplication {

	public static Logger logger = LoggerFactory.getLogger(DemoApplication.class);

	public static TondeuseService tService;

	
	@Autowired
	public DemoApplication(TondeuseService tService) {
		this.tService = tService;
	}

	public static void main(String[] args) throws Exception {

		SpringApplication.run(DemoApplication.class, args);
		try {
			InputStream file = new FileInputStream("C:\\test\\test.txt");
			Scanner scanner = new Scanner(file);
			TondeuseService.lectureFichier(scanner);
			scanner.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		catch (NoSuchElementException ex) {
			logger.error("Donnés du fichier incompatible avec le type attendu par le programme ");
			ex.printStackTrace();
		}
	}

}
