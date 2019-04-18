package me.muphy.template;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 2019/4/18
 * 莫非
 */
public abstract class JdbcTemplate {

    private DataSource dataSource;

    public JdbcTemplate(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<?> executeQuery(String sql, RowMapper<?> rowMapper, Object[] values) throws Exception {
        //1.获取连接
        Connection conn = this.getConnection();
        //2.创建语句集
        PreparedStatement ps = this.createPreparedStatement(conn, sql);
        //3.执行语句集
        ResultSet rs = this.executeQuery(ps, values);
        //4.处理结果
        List<?> result = this.parseResultSet(rs, rowMapper);
        //5.关闭结果集
        this.closeResultSet(rs);
        //6.关闭语句集
        this.closeStatement(ps);
        //7.关闭连接
        this.closeConnection(conn);

        return result;
    }

    protected void closeConnection(Connection conn) throws SQLException {
        conn.close();
    }

    protected void closeStatement(PreparedStatement ps) throws SQLException {
        ps.close();
    }

    protected void closeResultSet(ResultSet rs) throws SQLException {
        rs.close();
    }

    protected List<?> parseResultSet(ResultSet rs, RowMapper<?> rowMapper) throws Exception {
        List<Object> result = new ArrayList<>();
        int rowNumber = 1;
        while (rs.next()) {
            result.add(rowMapper.mapRow(rs, rowNumber++));
        }
        return result;
    }

    protected ResultSet executeQuery(PreparedStatement ps, Object[] values) throws SQLException {
        for (int i = 0; i < values.length; i++) {
            ps.setObject(i, values[i]);
        }
        return ps.executeQuery();
    }

    protected PreparedStatement createPreparedStatement(Connection conn, String sql) throws SQLException {
        return conn.prepareStatement(sql);
    }

    protected Connection getConnection() throws SQLException {
        return this.dataSource.getConnection();
    }

}
