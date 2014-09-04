package com.yummynoodlebar.persistence.repository;

import com.yummynoodlebar.persistence.domain.MenuItem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface MenuItemRepository extends CrudRepository<MenuItem, String>, AnalyseIngredients {

    public List<MenuItem> findByIngredientsNameIn(String... name);
}