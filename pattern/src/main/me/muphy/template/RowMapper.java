package me.muphy.template;

import java.sql.ResultSet;

/**
 * 2019/4/18
 * 莫非
 */
public interface RowMapper<T> {
    T mapRow(ResultSet rs, int rowNum) throws Exception;
}
