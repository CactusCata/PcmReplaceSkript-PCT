package com.gmail.cactus.cata.enums;

public enum PrefixMessage {

	PREFIX("§1[§bMondePCT§1]§e "),

	PREFIX_SENDER_BE_PLAYER(PrefixMessage.PREFIX + "La commande ne peut etre execute que par un joueur !");

	private final String text;

	private PrefixMessage(final String text) {
		this.text = text;
	}

	@Override
	public String toString() {
		return text;
	}

}