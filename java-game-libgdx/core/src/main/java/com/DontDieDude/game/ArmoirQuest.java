package com.DontDieDude.game;

import com.DontDieDude.game.character.Player;
import com.badlogic.gdx.maps.objects.RectangleMapObject;

public class ArmoirQuest extends Item{
	
	public ArmoirQuest(String name, RectangleMapObject rectangle) {
		super(name, rectangle);
		this.sentence = "Vous fouillez dans votre armoire... Oh un vêtement cela paraît judicieux de le mettre";
	}
	

	public String interaction(Player player) {
		if (player.isNaked()) {
			return this.sentence;
		} else {
			return "Il n'y a plus rien à prendre dans cette armoire";
		}
		
	}
}
