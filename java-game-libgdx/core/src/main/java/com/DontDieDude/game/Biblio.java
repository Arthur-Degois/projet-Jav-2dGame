package com.DontDieDude.game;

import com.badlogic.gdx.maps.objects.RectangleMapObject;

public class Biblio extends Item{
	
	
	public Biblio(String name, RectangleMapObject rectangle) {
		super(name, rectangle);
		this.sentence = "Une bibliothèque avec plein de livres. Rien d'intéressant.";
	}
}
