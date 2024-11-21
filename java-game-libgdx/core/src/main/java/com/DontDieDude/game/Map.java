package com.DontDieDude.game;

import java.util.ArrayList;
import java.util.List;

import com.DontDieDude.game.character.NPC;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapLayers;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;

public class Map {

	private String name;
	private TiledMap map;
	private MapObjects walls;
	
	private MapLayers mapLayers;
	private List<Integer> backgrounds = new ArrayList<>();
	private List<Integer> foregrounds = new ArrayList<>();
	private int[] background;
	private int[] foreground;
	
	private MapObjects posObjects;
	private List<RectangleMapObject> rectPos = new ArrayList<>();
	private RectangleMapObject spawnPlayer;
	
	private List<Portal> portals = new ArrayList<>();
	
	private List<NPC> npcs = new ArrayList<>();
	
	private MapObjects items;
	
	private List<Item> listItem = new ArrayList<>();
	
	public Map(String name, List<Portal> args, List<NPC> args2) {
		
		this.name = name;
		this.map = new TmxMapLoader().load("data-map/" + name + ".tmx");
		
		mapLayers = map.getLayers();
		for( MapLayer layer : mapLayers) {
			if (layer.getName().contains("1")) {
				backgrounds.add(mapLayers.getIndex(layer.getName()));
			}
			if (layer.getName().contains("2")) {
				foregrounds.add(mapLayers.getIndex(layer.getName()));
			}
		}
		int len = backgrounds.size();
		background = new int[len];
		for(int i = 0; i < len; i++) {
			background[i] = backgrounds.get(i);
		}
		len = foregrounds.size();
		foreground = new int[len];
		for(int i = 0; i < len; i++) {
			foreground[i] = foregrounds.get(i);
		}
		
		 posObjects = map.getLayers().get("pos").getObjects();
		 for(MapObject  rect : posObjects) {
			 rectPos.add((RectangleMapObject)rect);
		 }
		 
		 
		 spawnPlayer = (RectangleMapObject)posObjects.get("spawn_player");
    	   	
    	this.walls = map.getLayers().get("wall").getObjects();
    	
    	this.items = map.getLayers().get("item").getObjects();
    	for( MapObject item : items) {
    		RectangleMapObject newItem = (RectangleMapObject)item;
    		if (item.getName().contains("biblio")) {
    			listItem.add(new Biblio("biblio", newItem));
    		}
    		if (item.getName().contains("armoire")) {
    			listItem.add(new ArmoirQuest("armoire", newItem));
    		}
    	}
    	
    	for (Portal portal : args) {
    		this.portals.add(portal);
    	}
    	
    	if(args2 != null) {
    		for (NPC npc : args2) {
    			this.npcs.add(npc);	
    	}
    	}
	}
	
	public String getName() {
		return this.name;
	}
	
	public TiledMap getMap() {
		return this.map;
	}
	
	public MapObjects getWalls() {
		return this.walls;
	}
	
	public MapLayers getMapLayers() {
		return this.mapLayers;
	}
	
	public int[] getBackground() {
		return this.background;
	}
	
	public int[] getForeground() {
		return this.foreground;
	}
	
	public MapObjects getPosObjects() {
		return this.posObjects;
	}
	
	public MapObjects getItems() {
		return this.items;
	}
	
	public List<Item> getListItem() {
		return this.listItem;
	}
	
	public List<RectangleMapObject> getRectPos() {
		return this.rectPos;
	}
	
	public RectangleMapObject getSpawnPlayer() {
		return this.spawnPlayer;
	}
	
	public List<Portal> getPortals() {
		return this.portals;
	}
	
	public List<NPC> getNpcs() {
		return this.npcs;
	}
}
