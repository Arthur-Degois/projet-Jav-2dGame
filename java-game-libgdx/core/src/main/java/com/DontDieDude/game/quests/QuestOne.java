package com.DontDieDude.game.quests;

public class QuestOne extends Quest{

	protected String avertissement = "Ce n'est pas une bonne idée de sortir nu, tu devrais t'habiller.";
	
	
	public QuestOne() {
		this.name = "Habillage";
	}
	
	
	public String getAvertissement() {
		return this.avertissement;
	}
}
