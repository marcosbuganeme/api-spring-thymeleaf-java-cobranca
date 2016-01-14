package com.cobranca.model;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * @author marcos olavo silva buganeme - marcos.after@gmail.com
 *
 */

@MappedSuperclass
@SuppressWarnings("rawtypes")
public abstract class AbstractEntity<PK extends Serializable> implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private PK id;

	public AbstractEntity() {
	}

	public PK getId() {

		return this.id;
	}

	public void setId(final PK id) {

		this.id = id;
	}

	public boolean isNew() {

		return null == this.getId();
	}

	@Override
	public int hashCode() {

		final int prime = 31;

		int result = 1;

		result = prime * result + ((id == null) ? 0 : id.hashCode());

		return result;
	}

	@Override
	public boolean equals(Object object) {

		if (this == object) {

			return true;
		}
		if (object == null) {

			return false;
		}
		if (!(object instanceof AbstractEntity)) {

			return false;
		}

		AbstractEntity entity = (AbstractEntity) object;

		if (id == null) {

			if (entity.id != null) {

				return false;
			}
		} else if (!id.equals(entity.id)) {

			return false;
		}

		return true;
	}

	@Override
	public String toString() {

		return "AbstractEntity [id=" + id + "]";
	}
}
