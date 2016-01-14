package com.cobranca.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

/**
 * @author marcos olavo silva buganeme - marcos.after@gmail.com
 *
 */

@Entity
public class Title extends AbstractEntity<Long> {

	private static final long serialVersionUID = 1L;

	@NotEmpty(message = "Informe a descrição para o título")
	@Size(max = 90, message = "A descrição não pode conter mais que 90 caracteres")
	private String description;

	@Temporal(TemporalType.DATE)
	@Column(name = "data_vencimento")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@NotNull(message = "Informe a data de vencimento do título")
	@Future(message = "A data de vencimento do título deve ser maior que a data atual")
	private Date dateExpiration;

	@NotNull(message = "Preencha um valor para o título")
	@NumberFormat(style = Style.CURRENCY, pattern = "#,##0.00")
	@DecimalMin(value = "0.01", message = "O valor do título não pode ser menor que 0,01")
	@DecimalMax(value = "999999.99", message = "O valor não pode ser maior que 999.999,99")
	private BigDecimal price;

	@Column(name = "status")
	@Enumerated(EnumType.STRING)
	private EnumStatusTitle status;

	public Title() {
	}

	public boolean isPending() {

		return EnumStatusTitle.PENDING.equals(this.status);
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDateExpiration() {
		return dateExpiration;
	}

	public void setDateExpiration(Date dateExpiration) {
		this.dateExpiration = dateExpiration;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public EnumStatusTitle getStatus() {
		return status;
	}

	public void setStatus(EnumStatusTitle status) {
		this.status = status;
	}

}
