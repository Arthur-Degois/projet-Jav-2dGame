package com.DontDieDude.game;


import com.DontDieDude.game.character.NPC;
import com.DontDieDude.game.character.Player;
import com.DontDieDude.game.quests.Quests;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapLayers;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.ScreenUtils;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends Game {

	private OrthographicCamera camera;
	private OrthographicCamera HUDcamera;
	private SpriteBatch batch;
	private ShapeRenderer shape;
	private int squareSize = 40;

	private MapManager mapManager;
	private OrthogonalTiledMapRenderer rendererMap;
	
	
	private Player player;
	private DialogBox dialogBox;
	private Quests quests;
	
    @Override
    public void create() {
    	
    	float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();
  
    	batch = new SpriteBatch();
    	shape = new ShapeRenderer();
    	
    	
    	camera = new OrthographicCamera();
    	camera.setToOrtho(false, w, h);
    	camera.update();
    	
    	HUDcamera = new OrthographicCamera();
    	HUDcamera.setToOrtho(false, w, h);
    	HUDcamera.update();
    	
    	player = new Player("Arthur", 0, 0);
    	
    	dialogBox = new DialogBox();
    	
    	quests = new Quests();
    	
    	
    	mapManager = new MapManager(player, quests);
    	mapManager.spawnNpcs();
    	
    
    	rendererMap = new OrthogonalTiledMapRenderer(mapManager.getCurrentMap().getMap());
    	
    
    	
    	player.getRectangle().setPosition(mapManager.getCurrentMap().getSpawnPlayer().getRectangle().getX(), mapManager.getCurrentMap().getSpawnPlayer().getRectangle().getY());
    	player.getPlayerImg().setPosition(player.getRectangle().getX() -48, player.getRectangle().getY() -48);
    	camera.position.set(player.getPlayerImg().getX() +48, player.getPlayerImg().getY() +48 , 0);
    	camera.update();
    	
    	
    }

    @Override
    public void render() {
        ScreenUtils.clear(0.0f, 0.0f, 0.0f, 1f);
        
        //if (!mapManager.getTransitionFull()) {//
        	player.update(); 
       // }//
        
        mapManager.checkCollisions(dialogBox);
        mapManager.checkInteraction(dialogBox);
    
        if (mapManager.getTeleportation()) {
        	rendererMap = new OrthogonalTiledMapRenderer(mapManager.getCurrentMap().getMap());
        	mapManager.setTeleportation(false);
        	mapManager.setTransitionEmpty(true);
        }
        
        
        camera.position.set(player.getPlayerImg().getX() +32, player.getPlayerImg().getY() +32 , 0);
        camera.update();
        rendererMap.setView(camera);
        rendererMap.render(mapManager.getCurrentMap().getBackground());
        batch.begin();
        batch.setProjectionMatrix(camera.combined);
        for (NPC npc : mapManager.getCurrentMap().getNpcs()) {
        	npc.render(batch);
        }
        player.render(batch);
        batch.end();
        
        rendererMap.render(mapManager.getCurrentMap().getForeground());
        
        if (dialogBox.isTalking()) {
        	HUDcamera.update();
        	batch.begin();
        	batch.setProjectionMatrix(HUDcamera.combined);
        	dialogBox.render(batch, mapManager.getNpcNearby().getDialog()[mapManager.getNpcNearby().getIndex()]);
        	batch.end();  	
        }
        
        if (dialogBox.isInteract()) {
        	HUDcamera.update();
        	batch.begin();
        	batch.setProjectionMatrix(HUDcamera.combined);
        	dialogBox.render(batch, mapManager.getItemNearby().interaction(this.player));
        	batch.end();  	
        }
        
        if (dialogBox.isQuestBox()) {
        	System.out.println("ca march");
        	HUDcamera.update();
        	batch.begin();
        	batch.setProjectionMatrix(HUDcamera.combined);
        	dialogBox.render(batch, quests.getQuests().get(0).getAvertissement());
        	batch.end();  	
        }
        
        
       /* if(mapManager.getTransitionFull()) {
        	this.shape.begin(ShapeType.Filled);
        	this.shape.setColor(Color.BLACK);
        	this.shape.rect((Gdx.graphics.getWidth()/2 )- (Gdx.graphics.getWidth()/ squareSize) /2,(Gdx.graphics.getHeight()/ 2) - (Gdx.graphics.getWidth()/ squareSize) /2, Gdx.graphics.getWidth()/ squareSize ,Gdx.graphics.getWidth()/ squareSize);
        	this.shape.end();
        	
        	squareSize -= 1;	
        	if (squareSize == 0) {
        		squareSize = 1;
        		mapManager.setTeleportation(true);
        		mapManager.setTransitionFull(false);
        	}
        }
        
        if(mapManager.getTransitionEmpty()) {
        	this.shape.begin(ShapeType.Filled);
        	this.shape.setColor(Color.BLACK);
        	this.shape.rect((Gdx.graphics.getWidth()/2 )- (Gdx.graphics.getWidth()/ squareSize) /2,(Gdx.graphics.getHeight()/ 2) - (Gdx.graphics.getWidth()/ squareSize) /2, Gdx.graphics.getWidth()/ squareSize ,Gdx.graphics.getWidth()/ squareSize);
        	this.shape.end();
        	
        	squareSize += 1;
        	if (squareSize == 41) {
        		mapManager.setTransitionEmpty(false);
        		squareSize = 40;
        	}
        } */
    }

    
    
    @Override
    public void dispose() {

    }
    
    public void resize (int width, int height) {
    	
    }
    
    public void pause () {
    	
    }
    
    public void resume () {
    	
    }
}
