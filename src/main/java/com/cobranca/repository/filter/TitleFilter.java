package com.cobranca.repository.filter;

import java.io.Serializable;

/**
 * @author marcos olavo silva buganeme - marcos.after@gmail.com
 *
 */

public class TitleFilter implements Serializable {

	private static final long serialVersionUID = 1L;

	private String description;

	public TitleFilter() {
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
