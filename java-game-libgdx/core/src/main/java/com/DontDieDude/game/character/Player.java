package com.DontDieDude.game.character;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

public class Player extends Entity {

	private String[]  arrayDirection = {"up", "down", "left", "right"};
    private String playerDirection = arrayDirection[1];
    private int speed = 1;
    
    /* texture et animation du player */
    private Animation<Sprite> walkAnimationDown;
    private Animation<Sprite> walkAnimationUp;
    private Animation<Sprite> walkAnimationLeft;
    private Animation<Sprite> walkAnimationRight;
    
    
    private Animation<Sprite> clotheAnimationDown;
    private Animation<Sprite> clotheAnimationUp;
    private Animation<Sprite> clotheAnimationLeft;
    private Animation<Sprite> clotheAnimationRight;
    
    
    
    private List<Sprite> playerStand = new ArrayList<>();
    float stateTime = 0f;
    
    //rectangle de detection d'objet/personnage//
    private Map<String, Rectangle> rectDetection = new HashMap<>();
    
    private boolean movable = true;
    
    private boolean naked = true;
    
    private float oldLocationX;
    private float oldLocationY;

    public Player(String name, float x, float y) {
        super(name, x, y, "armor", null, null);
        loadTexture();
    	playerImg = walkAnimationDown.getKeyFrame(stateTime, true);
    	clothes = clotheAnimationDown.getKeyFrame(stateTime, true);
        this.hitBox = new Rectangle(x, y, this.playerImg.getWidth() - 32, this.playerImg.getHeight() -48);
        this.playerImg.setPosition(this.hitBox.getX() - 32, this.hitBox.getY() - 32);
        
        updateRectDetection();
    }

    public void update() {
    		oldLocationX = this.hitBox.getX();
    		oldLocationY = this.hitBox.getY();
    		stateTime += Gdx.graphics.getDeltaTime();
            move();
            this.playerImg.setPosition(this.hitBox.getX() - 32, this.hitBox.getY() - 32);
            this.clothes.setPosition(this.hitBox.getX() - 32, this.hitBox.getY() - 32);
            
            updateRectDetection();
    }
    
    public void render(SpriteBatch batch) {
    	batch.draw(this.getPlayerImg(), this.getPlayerImg().getX(), this.getPlayerImg().getY(), 96.0f,96.0f);
    	if (!this.naked) {
    		batch.draw(this.getClothes(), this.getPlayerImg().getX(), this.getPlayerImg().getY(), 96.0f,96.0f);
    	}
    }


    private void loadTexture() {
    	playerSprite = new Texture("characters/player.png"); 
		textureClothes = new Texture("characters/armor.png");	


    	
    	Sprite[] walkframes = new Sprite[6];
    	int index = 0;
    	for (int i = 0; i < 6; i++) {
    		walkframes[index++] = new Sprite(new TextureRegion(playerSprite, i *64 ,4 * 64, 64, 64));
    	}
    	
    	walkAnimationDown = new Animation<Sprite>(0.1f, walkframes);
    	
    	walkframes = new Sprite[6];
    	index = 0;
    	for (int i = 0; i < 6; i++) {
    		walkframes[index++] = new Sprite(new TextureRegion(playerSprite, i *64 ,5 * 64, 64, 64));
    	}
    	
    	walkAnimationUp = new Animation<Sprite>(0.1f, walkframes);
    	
    	walkframes = new Sprite[6];
    	index = 0;
    	for (int i = 0; i < 6; i++) {
    		walkframes[index++] = new Sprite(new TextureRegion(playerSprite, i *64 ,6 * 64, 64, 64));
    	}
    	
    	walkAnimationRight = new Animation<Sprite>(0.1f, walkframes);
    	
    	walkframes = new Sprite[6];
    	index = 0;
    	for (int i = 0; i < 6; i++) {
    		walkframes[index++] = new Sprite(new TextureRegion(playerSprite, i *64 ,7 * 64, 64, 64));
    	}
    	
    	walkAnimationLeft = new Animation<Sprite>(0.1f, walkframes);
    	
    	walkframes = new Sprite[6];
    	index = 0;
    	for (int i = 0; i < 6; i++) {
    		walkframes[index++] = new Sprite(new TextureRegion(textureClothes, i *64 ,4 * 64, 64, 64));
    	}
    	
    	clotheAnimationDown = new Animation<Sprite>(0.1f, walkframes);
    	
    	walkframes = new Sprite[6];
    	index = 0;
    	for (int i = 0; i < 6; i++) {
    		walkframes[index++] = new Sprite(new TextureRegion(textureClothes, i *64 ,5 * 64, 64, 64));
    	}
    	
    	clotheAnimationUp = new Animation<Sprite>(0.1f, walkframes);
    	
    	walkframes = new Sprite[6];
    	index = 0;
    	for (int i = 0; i < 6; i++) {
    		walkframes[index++] = new Sprite(new TextureRegion(textureClothes, i *64 ,6 * 64, 64, 64));
    	}
    	
    	clotheAnimationRight = new Animation<Sprite>(0.1f, walkframes);
    	
    	walkframes = new Sprite[6];
    	index = 0;
    	for (int i = 0; i < 6; i++) {
    		walkframes[index++] = new Sprite(new TextureRegion(textureClothes, i *64 ,7 * 64, 64, 64));
    	}
    	
    	clotheAnimationLeft = new Animation<Sprite>(0.1f, walkframes);
    	
    	
    	for(int i = 0; i < 4; i++) {
    		playerStand.add(new Sprite(new TextureRegion(playerSprite, 0, i * 64, 64, 64)));
    	}
    	
    }
    
    private void updateRectDetection() {
    	rectDetection.put("up", new Rectangle(this.hitBox.getX() + 7, this.hitBox.getY() + 8, this.hitBox.getWidth() - 16, this.hitBox.getHeight() +24));
    	rectDetection.put("down", new Rectangle(this.hitBox.getX() + 7, this.hitBox.getY() -20, this.hitBox.getWidth() - 16, this.hitBox.getHeight() + 20));
    	rectDetection.put("right", new Rectangle(this.hitBox.getX() + 16, this.hitBox.getY() + 8, this.hitBox.getWidth(), this.hitBox.getHeight()));
    	rectDetection.put("left", new Rectangle(this.hitBox.getX() -20, this.hitBox.getY() + 8, this.hitBox.getWidth(), this.hitBox.getHeight()));
    }
    
    public float getOldLocationX() {
    	return this.oldLocationX;
    }
    
    public float getOldLocationY() {
    	return this.oldLocationY;
    }
    
    public Map<String, Rectangle> getRectDetection() {
    	return this.rectDetection;
    }
    
    public String getDirection() {
    	return this.playerDirection;
    }

    public void setDirection(String direction) {
        this.playerDirection = direction;
    }
    
    public boolean isMovable() {
    	return this.movable;
    }
    
    public void setMovable(boolean bool) {
    	this.movable = bool;
    }
    
    public boolean isNaked() {
    	return this.naked;
    }
    
    public void setNaked(boolean bool) {
    	this.naked = bool;
    }


    public void move() {
    	 	
    	if(movable) {
    		if(Gdx.input.isKeyPressed(Input.Keys.A) || Gdx.input.isKeyPressed(Input.Keys.LEFT)){
    			this.hitBox.setX(this.hitBox.getX() - speed);
    			this.playerImg = walkAnimationLeft.getKeyFrame(stateTime, true);
    			this.clothes = clotheAnimationLeft.getKeyFrame(stateTime, true);
    			playerDirection = arrayDirection[2];
    		}
    		if(Gdx.input.isKeyPressed(Input.Keys.D) || Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
    			this.hitBox.setX(this.hitBox.getX() + speed);
    			this.playerImg = walkAnimationRight.getKeyFrame(stateTime, true);
    			this.clothes = clotheAnimationRight.getKeyFrame(stateTime, true);
    			playerDirection = arrayDirection[3];
    		}
    		if(Gdx.input.isKeyPressed(Input.Keys.S) || Gdx.input.isKeyPressed(Input.Keys.DOWN)){
    			this.hitBox.setY(this.hitBox.getY() - speed);
    			this.playerImg = walkAnimationDown.getKeyFrame(stateTime, true);
    			this.clothes = clotheAnimationDown.getKeyFrame(stateTime, true);
    			playerDirection = arrayDirection[1];
    		}
    		if(Gdx.input.isKeyPressed(Input.Keys.W) || Gdx.input.isKeyPressed(Input.Keys.UP)){
    			this.hitBox.setY(this.hitBox.getY() + speed);
    			this.playerImg = walkAnimationUp.getKeyFrame(stateTime, true);
    			this.clothes = clotheAnimationUp.getKeyFrame(stateTime, true);
    			playerDirection = arrayDirection[0];
    		}		
    	}
      }

   
    

}