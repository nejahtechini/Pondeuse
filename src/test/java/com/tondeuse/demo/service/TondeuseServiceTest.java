package com.tondeuse.demo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;

import com.tondeuse.demo.entites.Coordonnes;
import com.tondeuse.demo.exception.PatternException;
import com.tondeuse.demo.serviceImpl.TondeuseService;

@ComponentScan({ "com.tondeuse.demo.entites" })
@SpringBootTest
public class TondeuseServiceTest {

	@Test
	void test_calculCoordonnFinal() {
		Coordonnes postionIntial = Coordonnes.builder().x(1).y(2).d('N').build();
		String instructionChain = "GAGAGAGAA";

		Coordonnes postionFinal = TondeuseService.calculCoordonnFinal(5, 5, postionIntial, instructionChain);
		assertEquals(postionFinal.getX(), 1);
		assertEquals(postionFinal.getY(), 3);
		assertEquals(postionFinal.getD(), 'N');

	}

	@Test
	public void lectureFichier_throwPatternException_PostionNonReconnu() throws FileNotFoundException {
		File catalogFile = new File("src/test/resources/testPositionIntial.txt");
		Scanner scanner = new Scanner(catalogFile);

		Exception exception = assertThrows(PatternException.class, () -> {
			TondeuseService.lectureFichier(scanner);
		});

		String expectedMessage = "le traitement a été interrompu !! ,La position de tondeuse est non reconnu par le systéme";
		String actualMessage = exception.getMessage();

		assertTrue(actualMessage.contains(expectedMessage));
	}

	@Test
	public void lectureFichier_throwPatternException_SérieInstructionNonReconnu() throws FileNotFoundException {
		File catalogFile = new File("src/test/resources/testChaineInstruction.txt");
		Scanner scanner = new Scanner(catalogFile);

		Exception exception = assertThrows(PatternException.class, () -> {
			TondeuseService.lectureFichier(scanner);
		});

		String expectedMessage = "le traitement a été interrompu !!, la série d'instruction doit respecter le pattern [DGA]+";
		String actualMessage = exception.getMessage();

		assertTrue(actualMessage.contains(expectedMessage));
	}
}
