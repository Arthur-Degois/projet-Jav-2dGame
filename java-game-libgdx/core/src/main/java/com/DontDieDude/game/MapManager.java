package com.DontDieDude.game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.DontDieDude.game.character.NPC;
import com.DontDieDude.game.character.Player;
import com.DontDieDude.game.quests.Quests;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;

public class MapManager {
	
	private List<Map> maps = new ArrayList<>();
	
	private Map currentMap;

	private String nameCurrentMap = "home";
	
	private Quests quests;
	
	private Player player;
	
	private NPC npcNearby;
	private Item itemNearby;
	
	private float timeSeconds = 0f;
	private float period = 0.2f;
	
	private boolean teleportation = false;
	
	private boolean transitionFull = false;
	
	private boolean transitionEmpty = false;
	
	public MapManager(Player player, Quests quest) {
		
		this.player = player;
		
		this.quests = quest;
		
		
		maps.add(registerMap("world", Arrays.asList(new Portal("world", "enter_house_player", "home", "spawn_house"),
				new Portal("world", "enter_other_house_1", "otherhouseone", "spawn_player"),
				new Portal("world", "enter_other_house_2", "otherhousetwo", "spawn_player"),
				new Portal("world", "enter_other_house_3", "otherhousethree", "spawn_player"),
				new Portal("world", "enter_mairie", "mairiefirstfloor", "spawn_player")),
				Arrays.asList(new NPC("Jack", 0, 0, "armor", "cheveux", null, "player", Dialog.jack),
						new NPC("Claude", 0, 0, "armor", "cheveux2", null, "player", Dialog.claude),
						new NPC("Paul", 0, 0, "armor2", "cheveux3", null, "player", Dialog.paul),
						new NPC("Tristan", 0, 0, "armor2", "cheveux", null, "player", Dialog.tristan),
						new NPC("marchand", 0, 0, "armor2", null, "chapeau", "player", Dialog.marchand),
						new NPC("Andrew", 0, 0, "armor", "cheveux", null, "player", Dialog.andrew))));
		
		
		maps.add(registerMap("home", Arrays.asList( new Portal("home", "exit_house_player", "world", "spawn_player")), null));
		
		
		maps.add(registerMap("otherhouseone", Arrays.asList( new Portal("otherhouseone", "exit_other_house_1", "world", "spawn_exit_other_house_1")),
				Arrays.asList( new NPC("villageois", 0, 0, "armor", "cheveux", null, "player", Dialog.villageois))));
		
		
		maps.add(registerMap("otherhousetwo", Arrays.asList( new Portal("otherhousetwo", "exit_other_house_2", "world", "spawn_exit_other_house_2")), 
				Arrays.asList( new NPC("villageois2", 0, 0, "armor", "cheveux", null, "player", Dialog.villageois2))));
		
		
		maps.add(registerMap("otherhousethree", Arrays.asList( new Portal("otherhousethree", "exit_other_house_3", "world", "spawn_exit_other_house_3")), null));
		
		
		maps.add(registerMap("mairiefirstfloor", Arrays.asList( new Portal("mairiefirstfloor", "exit_mairie", "world", "spawn_exit_mairie"),
				new Portal("mairiefirstfloor", "enter_second_floor_1", "mairiesecondfloor", "spawn_second_floor_1"),
				new Portal("mairiefirstfloor", "enter_second_floor_2", "mairiesecondfloor", "spawn_second_floor_2")), 
				Arrays.asList( new NPC("secretaire", 0, 0, "armor2", "cheveux", null, "player", Dialog.secretaire))));
		
		
		maps.add(registerMap("mairiesecondfloor", Arrays.asList( new Portal("mairiesecondfloor", "exit_second_floor_1", "mairiefirstfloor", "spawn_first_floor_1"),
				new Portal("mairiesecondfloor", "exit_second_floor_2", "mairiefirstfloor", "spawn_first_floor_2")), 
				Arrays.asList( new NPC("maire", 0, 0, "armor2", null, "chapeau", "player", Dialog.maire))));
		
		
		
		for(Map map : maps) {
			if(map.getName().equalsIgnoreCase(nameCurrentMap)) {
				this.currentMap = map;	
			}
		}
	}
	
	public Map registerMap(String name, List<Portal> args, List<NPC> args2) {
		Map newMap = new Map(name, args, args2);
		return newMap;
	}
	
	public void spawnNpcs() {
		for (Map map : maps) {
			for (NPC npc : map.getNpcs()) {
				npc.getRectangle().setPosition(map.getRectPos().get(map.getPosObjects().getIndex(npc.getName())).getRectangle().getX() , map.getRectPos().get(map.getPosObjects().getIndex(npc.getName())).getRectangle().getY());
				npc.getPlayerImg().setPosition(npc.getRectangle().getX() - 40, npc.getRectangle().getY() - 32);
			}
		}
	}
	
	public List<Map> getMaps() {
		return this.maps;
	}
	
	public Map getCurrentMap() {
		return this.currentMap;
	}
	
	public String getNameCurrentMap() {
		return this.nameCurrentMap;
	}
	
	public Player getPlayer() {
		return this.player;
	}  
	
	public NPC getNpcNearby() {
		return this.npcNearby;
	}
	
	public Item getItemNearby() {
		return this.itemNearby;
	}
	
	public boolean getTransitionFull() {
		return this.transitionFull;
	}
	
	public void setTransitionFull(boolean bool) {
		this.transitionFull = bool;
	}
	
	public boolean getTransitionEmpty() {
		return this.transitionEmpty;
	}
	
	public void setTransitionEmpty(boolean bool) {
		this.transitionEmpty = bool;
	}
	
	public boolean getTeleportation() {
		return this.teleportation;
	}
	
	public void setTeleportation(boolean bool) {
		this.teleportation = bool;
	}
	
	public void setNameCurrentMap(String name) {
		this.nameCurrentMap = name;
	}
	
	public boolean checkCollisions(DialogBox dialog) {
		for(MapObject wall : this.currentMap.getWalls()) {
    		RectangleMapObject newWall = (RectangleMapObject)wall;
    		if (player.getRectangle().overlaps(newWall.getRectangle())) {
    			player.getRectangle().setX(player.getOldLocationX());
    			player.getRectangle().setY(player.getOldLocationY());
    			return true;
    		}
    	}
		
		for(NPC npc : this.currentMap.getNpcs()) {
			if (player.getRectangle().overlaps(npc.getRectangle())) {
				player.getRectangle().setX(player.getOldLocationX());
    			player.getRectangle().setY(player.getOldLocationY());
    			return true;
			}
		}
		
		for(Item item : this.currentMap.getListItem()) {
			if (player.getRectangle().overlaps(item.getRectangle().getRectangle())) {
				player.getRectangle().setX(player.getOldLocationX());
    			player.getRectangle().setY(player.getOldLocationY());
    			return true;
			}
		}
		
		for(Portal portal : this.currentMap.getPortals()) {
			RectangleMapObject newPortal = (RectangleMapObject)this.currentMap.getPosObjects().get(portal.getOriginPoint());
			if (player.getRectangle().overlaps(newPortal.getRectangle())) {
				if (!quests.getQuests().get(0).isFinish() && this.currentMap.getName().contains("home")) {
					player.getRectangle().setX(player.getOldLocationX());
	    			player.getRectangle().setY(player.getOldLocationY());
					player.setMovable(false);
					dialog.setQuestBox(true);
	    			return true;
				} else {
					this.nameCurrentMap = portal.getTargetWorld();
					for(Map map : maps) {
						if (map.getName().equalsIgnoreCase(this.nameCurrentMap)) {
							this.currentMap = map;	
							for (RectangleMapObject spawn : this.currentMap.getRectPos()) {
								if (spawn.getName().equalsIgnoreCase(portal.getTeleportPoint())) {
									player.getRectangle().setX(spawn.getRectangle().getX());
									player.getRectangle().setY(spawn.getRectangle().getY());
									this.transitionFull = true;
									this.setTeleportation(true);
									return true;	
								}
							}
						}
					}		
				}
			}
		}
		return false;
	}
	
	public void checkInteraction(DialogBox dialog) {
		for(NPC npc : this.currentMap.getNpcs()) {
			if (player.getRectDetection().get(player.getDirection()).overlaps(npc.getRectangle())) {
				timeSeconds += Gdx.graphics.getDeltaTime();
				if (Gdx.input.isKeyPressed(Input.Keys.E) && !dialog.isTalking() && timeSeconds >= period) {
					npcNearby = npc;
					dialog.setTalking(true);
					player.setMovable(false);
					timeSeconds = 0;
				}
				if (Gdx.input.isKeyPressed(Input.Keys.E) && dialog.isTalking() && timeSeconds >= period) {
					if(npc.getDialog().length - 1 <= npc.getIndex()) {
						dialog.setTalking(false);
						player.setMovable(true);
						npc.setIndex(0);
						timeSeconds = 0;				
					} else {
						npc.setIndex(npc.getIndex() + 1);
						timeSeconds = 0;
					}
				}		
			}
		}
		for(Item item : this.currentMap.getListItem()) {
			if (player.getRectDetection().get(player.getDirection()).overlaps(item.getRectangle().getRectangle())) {
				timeSeconds += Gdx.graphics.getDeltaTime();
				if (Gdx.input.isKeyPressed(Input.Keys.E) && !dialog.isInteract() && timeSeconds >= period) {
					itemNearby = item;
					dialog.setInteract(true);
					player.setMovable(false);
					timeSeconds = 0;
				}
				if (Gdx.input.isKeyPressed(Input.Keys.E) && dialog.isInteract() && timeSeconds >= period) {
					dialog.setInteract(false);
					player.setMovable(true);
					timeSeconds = 0;
					if (item.getName().equalsIgnoreCase("armoire") && player.isNaked()) {
						player.setNaked(false);
						this.quests.getQuests().get(0).setFinish(true);
					}
				}		
			}
		}
		if(this.currentMap.getName().contains("home")) {
			if (player.getRectDetection().get(player.getDirection()).overlaps(this.currentMap.getRectPos().get(this.currentMap.getPosObjects().getIndex("exit_house_player")).getRectangle())) {
				timeSeconds += Gdx.graphics.getDeltaTime();
				if (Gdx.input.isKeyPressed(Input.Keys.E) && timeSeconds >= period) {
					dialog.setQuestBox(false);
					player.setMovable(true);
					timeSeconds = 0;
				}
			}	
		}
	}
}
