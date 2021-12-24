package com.tondeuse.demo.serviceImpl;

import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import com.tondeuse.demo.entites.Coordonnes;
import com.tondeuse.demo.exception.PatternException;
import com.tondeuse.demo.utils.Utils;

@Component
@Configurable
@ComponentScan({ "com.tondeuse.demo.entites" })
public class TondeuseService {

	public static String shemaPattern = "[DGA]+";
	public static String directionPattern = "[NSWE]{1}";

	static Logger logger = LoggerFactory.getLogger(TondeuseService.class);

	public static void lectureFichier(Scanner scanner) throws Exception {
		int xMax = scanner.nextInt();
		int yMax = scanner.nextInt();

		int i = 1;
		while (scanner.hasNext()) {

			System.out.println("Début du traitement de la tendouse n°" + i);

			int xTondouse = scanner.nextInt();
			int yTondeuse = scanner.nextInt();

			if (scanner.hasNext(directionPattern)) {
				String dTondeuse = scanner.next();
				scanner.nextLine();
				if (scanner.hasNext(shemaPattern)) {
					String shemaTondeuse = scanner.next(shemaPattern);
					Coordonnes postionIntial = Coordonnes.builder().x(xTondouse).y(yTondeuse).d(dTondeuse.charAt(0))
							.build();
					calculCoordonnFinal(xMax, yMax, postionIntial, shemaTondeuse);

				} else {

					throw new PatternException(
							"le traitement a été interrompu !!, la série d'instruction doit respecter le pattern [DGA]+");
				}
			} else {

				throw new PatternException(
						"le traitement a été interrompu !! ,La position de tondeuse est non reconnu par le systéme");
			}

			i++;
		}
	}

	public static Coordonnes calculCoordonnFinal(int xMax, int yMax, Coordonnes coordonnesIntial,
			String chaineDuExecution) {

		for (int i = 0; i < chaineDuExecution.length(); i++) {
			Character car = chaineDuExecution.charAt(i);
			Character nextDirection = null;
			switch (car) {

			case 'G':

				nextDirection = Utils.myMap.get(coordonnesIntial.getD()).getDirectionGauche();
				coordonnesIntial.setD(nextDirection);
				break;
			case 'D':
				nextDirection = Utils.myMap.get(coordonnesIntial.getD()).getDirectionDroite();
				coordonnesIntial.setD(nextDirection);

			case 'A':
				if (coordonnesIntial.getD() == 'N' && coordonnesIntial.getY() < yMax)
					coordonnesIntial.setY(coordonnesIntial.getY() + 1);
				if (coordonnesIntial.getD() == 'S' && coordonnesIntial.getY() > 0)
					coordonnesIntial.setY(coordonnesIntial.getY() - 1);
				if (coordonnesIntial.getD() == 'W' && coordonnesIntial.getX() > 0)
					coordonnesIntial.setX(coordonnesIntial.getX() - 1);
				if (coordonnesIntial.getD() == 'E' && coordonnesIntial.getX() < xMax)
					coordonnesIntial.setX(coordonnesIntial.getX() + 1);
				break;
			default:

			}

		}
		System.out.println(coordonnesIntial.getX() + "," + coordonnesIntial.getY() + "," + coordonnesIntial.getD());
		return coordonnesIntial;
	}
}
