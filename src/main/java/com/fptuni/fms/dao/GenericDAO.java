package com.fptuni.fms.dao;

import com.fptuni.fms.mapper.RowMapper;
import java.util.List;

/**
 *
 *
 * @param <T>
 */
public interface GenericDAO<T> {

    <T> List<T> query(String sql, RowMapper<T> rowMapper, Object... params);

    void update(String sql, Object... params);

    Integer insert(String sql, Object... params);

    int count(String sql, Object... params);
}
