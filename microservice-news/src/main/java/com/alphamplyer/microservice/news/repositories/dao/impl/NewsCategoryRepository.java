package com.alphamplyer.microservice.news.repositories.dao.impl;

import com.alphamplyer.microservice.news.models.NewsCategory;
import com.alphamplyer.microservice.news.repositories.dao.DAORepository;
import com.alphamplyer.microservice.news.repositories.dao.interf.INewsCategoryRepository;
import com.alphamplyer.microservice.news.repositories.rowMappers.NewsCategoryRowMapper;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public class NewsCategoryRepository extends DAORepository implements INewsCategoryRepository {

    @Override
    public NewsCategory getById(Integer id) {
        String sql = "SELECT * FROM news_categories WHERE id = :id";

        RowMapper<NewsCategory> rowMapper = new NewsCategoryRowMapper();

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);

        NewsCategory news;

        try {
            news = namedParameterJdbcTemplate.queryForObject(sql, params, rowMapper);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }

        return news;
    }

    @Override
    public NewsCategory getParent(Integer id) {
        String sql = "SELECT nca.* FROM news_categories nca, news_categories ncb " +
            "WHERE ncb.parent_id IS NOT NULL AND nca.id = ncb.parent_id AND ncb.parent_id = :id";

        RowMapper<NewsCategory> rowMapper = new NewsCategoryRowMapper();

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);

        NewsCategory news;

        try {
            news = namedParameterJdbcTemplate.queryForObject(sql, params, rowMapper);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }

        return news;
    }

    @Override
    public List<NewsCategory> getChild(Integer id) {
        String sql = "SELECT * FROM news_categories WHERE parent_id IS NOT NULL AND parent_id = :id";

        RowMapper<NewsCategory> rowMapper = new NewsCategoryRowMapper();

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);

        List<NewsCategory> news = namedParameterJdbcTemplate.query(sql, params, rowMapper);

        if (news.isEmpty())
            return null;

        return news;
    }

    @Override
    public NewsCategory save(NewsCategory category) {
        String sql = "INSERT INTO news_categories (parent_id, creator_id, name, description, created_at, updated_at) " +
            "VALUES (:parent_id, :creator_id, :name, :description, :created_at, :updated_at)";

        Timestamp now = new Timestamp(System.currentTimeMillis());

        MapSqlParameterSource params_news = new MapSqlParameterSource();
        params_news.addValue("parent_id", category.getParentId());
        params_news.addValue("creator_id", category.getCreatorId());
        params_news.addValue("name", category.getName());
        params_news.addValue("description", category.getDescription());
        params_news.addValue("created_at", now);
        params_news.addValue("updated_at", now);

        KeyHolder keyHolder = new GeneratedKeyHolder();

        namedParameterJdbcTemplate.update(sql, params_news, keyHolder);

        if (keyHolder.getKey() == null) {
            return null;
        }

        category.setId(keyHolder.getKey().intValue());
        category.setCreatedAt(now);
        category.setUpdatedAt(now);

        return category;
    }

    @Override
    public void update(Integer id, NewsCategory category) {
        String sql = "UPDATE news_categories " +
            "SET parent_id = :parent_id, name = :name, description = :description, updated_at = :updated_at " +
            "WHERE id = :id";

        Timestamp now = new Timestamp(System.currentTimeMillis());

        MapSqlParameterSource params_news = new MapSqlParameterSource();
        params_news.addValue("id", id);
        params_news.addValue("parent_id", category.getParentId());
        params_news.addValue("name", category.getName());
        params_news.addValue("description", category.getDescription());
        params_news.addValue("updated_at", now);

        namedParameterJdbcTemplate.update(sql, params_news);
    }

    @Override
    public void delete(Integer id) {
        String sql = "DELETE FROM news_categories WHERE id = :id";

        MapSqlParameterSource params_news = new MapSqlParameterSource();
        params_news.addValue("id", id);

        namedParameterJdbcTemplate.update(sql, params_news);
    }
}