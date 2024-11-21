package com.DontDieDude.game;

import com.DontDieDude.game.character.Player;
import com.badlogic.gdx.maps.objects.RectangleMapObject;

public abstract class Item {

	
	protected String name;
	protected RectangleMapObject rectangle;
	protected String[]  arrayDirection = {"up", "down", "left", "right"};
	
	protected String sentence;
	
	public Item(String name, RectangleMapObject rectangle) {
		this.name = name;
		this.rectangle = rectangle;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String interaction(Player player) {
		return this.sentence;
	}
	
	public RectangleMapObject getRectangle() {
		return this.rectangle;
	}
}
