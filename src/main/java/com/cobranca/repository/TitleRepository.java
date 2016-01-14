package com.cobranca.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cobranca.model.Title;

/**
 * @author marcos olavo silva buganeme - marcos.after@gmail.com
 *
 */

public interface TitleRepository extends JpaRepository<Title, Long> {

	List<Title> findByDescriptionContaining(final String description);
}
