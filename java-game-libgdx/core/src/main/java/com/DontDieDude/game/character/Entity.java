package com.DontDieDude.game.character;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;

public abstract class Entity {


    protected String name;
    
    // corps du personnage //
    protected Texture playerSprite;
    protected Sprite playerImg;
    
    //vetements des personnages//
    protected Texture textureClothes;
    protected Sprite clothes;
    
    //cheveux des personnages//
    protected Texture textureHair;
    protected Sprite hair;
    
    //chapeau des personnages//
    protected Texture textureHat;
    protected Sprite hat;
    
    protected Rectangle hitBox;
    
    
    
    protected float x;
    protected float y;

    public Entity(String name, float x, float y, String clothe, String hair, String hat) {
        this.name = name;
        this.x = x;
        this.y = y;
    }
    
    public String getName() {
    	return this.name;
    }
    
    public Sprite getPlayerImg() {
    	return this.playerImg;
    }
    
    public Sprite getClothes() {
    	return this.clothes;
    }
    
    public Sprite getHair() {
    	return this.hair;
    }
    
    public Sprite getHat() {
    	return this.hat;
    }
    
    public Rectangle getRectangle() {
    	return this.hitBox;
    }
    
    public void setRectangle(float x, float y, float w, float h) {
    	this.hitBox = new Rectangle(x, y, w, h);
    }
}