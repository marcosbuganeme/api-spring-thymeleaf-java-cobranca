package com.cobranca.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cobranca.model.EnumStatusTitle;
import com.cobranca.model.Title;
import com.cobranca.repository.TitleRepository;
import com.cobranca.repository.filter.TitleFilter;

/**
 * @author marcos olavo silva buganeme - marcos.after@gmail.com
 *
 */

@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class TitleService {

	private static Logger LOG = Logger.getLogger(TitleService.class);

	private static final String ERROR_DATE_FORMATER = "A data de vencimento do formulário de cadastro foi inserida fora do formato padrão de data";

	@Autowired
	private TitleRepository repository;

	@Transactional(readOnly = false)
	public void saveOrUpdate(final Title title) {

		try {

			this.repository.save(title);

		} catch (final DataIntegrityViolationException exception) {

			LOG.error(TitleService.ERROR_DATE_FORMATER, exception.getCause());

			throw new IllegalArgumentException("A data está fora do padrão dia/mês/ano");

		}
	}

	@Transactional(readOnly = false)
	public void delete(final Long id) {

		try {

			this.repository.delete(id);

		} catch (final EmptyResultDataAccessException exception) {

			throw new IllegalArgumentException("Não é possível remover um registro inexistente");
		}
	}

	@Transactional(readOnly = false)
	public String get(final Long id) {

		final Title title = this.repository.findOne(id);

		title.setStatus(EnumStatusTitle.RECEIVED);

		this.repository.save(title);

		return EnumStatusTitle.RECEIVED.getValue();
	}

	public List<Title> findByDescription(final TitleFilter filter) {

		final String description = filter.getDescription() == null ? "%" : filter.getDescription();

		return this.repository.findByDescriptionContaining(description);
	}
}
