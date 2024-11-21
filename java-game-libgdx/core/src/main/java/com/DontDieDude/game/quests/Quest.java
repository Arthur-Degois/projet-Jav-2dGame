package com.DontDieDude.game.quests;

public abstract class Quest {
	
	protected String name;
	
	protected boolean finish = false;
	
	protected String avertissement = "Ce n'est pas une bonne idée de sortir nu, tu devrais t'habiller.";
	
	public String getName() {
		return this.name;
	}
	
	public boolean isFinish() {
		return this.finish;
	}
	
	
	public void setFinish(boolean bool) {
		this.finish = bool;
	}
	
	public String getAvertissement() {
		return this.avertissement;
	}
}
