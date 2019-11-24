package com.cko.sampleSpringProject.dao;

import com.cko.sampleSpringProject.model.Films;
import org.springframework.data.repository.CrudRepository;

public interface FilmDAO extends CrudRepository<Films, Long> {
}
