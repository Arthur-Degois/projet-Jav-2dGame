package com.DontDieDude.game.quests;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.maps.objects.RectangleMapObject;

public class Quests {
	
	private List<Quest> quests = new ArrayList<>();
	
	public Quests() {
		quests.add(new QuestOne());
	}
	
	public List<Quest> getQuests() {
		return this.quests;
	}
	
}
