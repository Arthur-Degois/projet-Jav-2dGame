package com.DontDieDude.game;

public class Portal {
	
	private String fromWorld;
	private String originPoint;
	private String targetWorld;
	private String teleportPoint;
	
	public Portal(String from, String origin, String target, String teleport) {
		
		this.fromWorld = from;
		this.originPoint = origin;
		this.targetWorld = target;
		this.teleportPoint = teleport;
	}
	
	public String getFromWorld() {
		return this.fromWorld;
	}
	
	public String getOriginPoint() {
		return this.originPoint;
	}
	
	public String getTargetWorld() {
		return this.targetWorld;
	}
	
	public String getTeleportPoint() {
		return this.teleportPoint;
	}
}
