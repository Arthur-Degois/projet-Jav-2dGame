package com.DontDieDude.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class DialogBox {
	
	private float x;
	private float y;
	private Texture dialogBox;
	
	private BitmapFont font;
	
	private boolean talking = false;
	
	private boolean interact = false;
	
	private boolean questBox = false;
	
	public DialogBox() {
		this.x = 50;
		this.y = 50;
		dialogBox = new Texture("dialog/dialog_box.png");
		
		font = new BitmapFont();
		font.getData().setScale(1.5f, 1.5f);
		font.setColor(Color.BLACK);
		
	}
	
	public void render(SpriteBatch batch,String str) {
			batch.draw(dialogBox, this.x, this.y, 1100, 150);		
			font.draw(batch, str, this.x +100, this.y + 120);		
	}
	
	public boolean isTalking() {
		return this.talking;
	}
	
	public void setTalking(boolean bool) {
		this.talking = bool;
	}
	
	public boolean isInteract() {
		return this.interact;
	}
	
	public void setInteract(boolean bool) {
		this.interact = bool;
	}
	
	public boolean isQuestBox() {
		return this.questBox;
	}
	
	public void setQuestBox(boolean bool) {
		this.questBox = bool;
	}
}
