package com.cobranca.model;

/**
 * @author marcos olavo silva buganeme - marcos.after@gmail.com
 *
 */

public enum EnumStatusTitle {

	RECEIVED(1, "Recebido"),

	PENDING(2, "Pendente");

	private int key;

	private String value;

	private EnumStatusTitle(final int key, final String value) {

		this.key = key;

		this.value = value;
	}

	public int getKey() {

		return this.key;
	}

	public String getValue() {

		return this.value;
	}

}
