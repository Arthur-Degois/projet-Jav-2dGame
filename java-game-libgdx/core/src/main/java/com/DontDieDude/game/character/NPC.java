package com.DontDieDude.game.character;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class NPC extends Entity{
	
	private String[] dialog;
	private int index = 0;

	public NPC(String name, float x, float y, String clothe, String hair, String hat, String body, String[] dialog) {
		super(name, x, y , clothe, hair, hat);
		loadTexture(body, clothe, hair, hat);
		this.hitBox = new Rectangle(x, y, this.playerImg.getWidth() - 48, this.playerImg.getHeight() -16);
        this.playerImg.setPosition(this.hitBox.getX() - 40, this.hitBox.getY() - 32);
        this.clothes.setPosition(this.playerImg.getX(), this.playerImg.getY());
        
        this.dialog = dialog;
	}
	
	public String[] getDialog() {
		return this.dialog;
	}
	
	public int getIndex() {
		return this.index;
	}
	
	public void setIndex(int nbr) {
		this.index = nbr;
	}
	
	public void loadTexture(String body, String clothe, String hair, String hat) {
		
		if (body != null) {
			this.playerSprite = new Texture("characters/" + body + ".png");
			this.playerImg = new Sprite(playerSprite, 0, 0, 64, 64);
		}
		if (clothe != null) {
			this.textureClothes = new Texture("characters/" + clothe + ".png");	
			this.clothes = new Sprite(textureClothes, 0, 0, 64, 64);
		}
		if (hair != null) {
			this.textureHair = new Texture("characters/" + hair + ".png");	
			this.hair = new Sprite(textureHair, 0, 0, 64, 64);
		}
		if (hat != null) {
			this.textureHat = new Texture("characters/" + hat + ".png");	
			this.hat = new Sprite(textureHat, 0, 0, 64, 64);
		}
	}
	
	
	public void render(SpriteBatch batch) {
		batch.draw(this.getPlayerImg(), this.getPlayerImg().getX(), this.getPlayerImg().getY(), 96.0f,96.0f);
		if (this.clothes != null) {
			batch.draw(this.getClothes(), this.getPlayerImg().getX(), this.getPlayerImg().getY(), 96.0f,96.0f);	
		}
		if (this.hair != null) {
			batch.draw(this.getHair(), this.getPlayerImg().getX(), this.getPlayerImg().getY(), 96.0f,96.0f);	
		}
		if (this.hat != null) {
			batch.draw(this.getHat(), this.getPlayerImg().getX(), this.getPlayerImg().getY(), 96.0f,96.0f);
		}
	}
}
