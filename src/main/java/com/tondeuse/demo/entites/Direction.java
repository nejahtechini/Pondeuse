package com.tondeuse.demo.entites;

import lombok.Data;

@Data 
public class Direction {
	private char  directionDroite ;
	private char directionGauche ;
	
	public Direction(Character dD, Character dG) {
		this.directionDroite=dD;
		this.directionGauche=dG;
	}

	public char getDirectionDroite() {
		return directionDroite;
	}

	public void setDirectionDroite(char directionDroite) {
		this.directionDroite = directionDroite;
	}

	public char getDirectionGauche() {
		return directionGauche;
	}

	public void setDirectionGauche(char directionGauche) {
		this.directionGauche = directionGauche;
	}

	
}
