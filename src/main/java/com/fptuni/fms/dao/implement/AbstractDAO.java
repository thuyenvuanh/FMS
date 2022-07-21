/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fptuni.fms.dao.implement;

import com.fptuni.fms.dao.GenericDAO;
import com.fptuni.fms.mapper.RowMapper;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import static com.fptuni.fms.utils.DBUtils.getConnection;

/**
 * @param <T>
 * @author LucasBV
 */
public class AbstractDAO<T> implements GenericDAO<T> {

    private void setParameters(PreparedStatement ps, Object... params) {
        try {
            for (int i = 0; i < params.length; i++) {
                Object parameter = params[i];
                int index = i + 1;
                if (parameter instanceof Integer) {
                    ps.setInt(index, (Integer) parameter);
                } else if (parameter instanceof Double) {
                    ps.setDouble(index, (Double) parameter);
                } else if (parameter instanceof String) {
                    ps.setString(index, (String) parameter);
                } else if (parameter instanceof Timestamp) {
                    ps.setTimestamp(index, (Timestamp) parameter);
                } else if (parameter instanceof Boolean) {
                    ps.setBoolean(index, (Boolean) parameter);
                } else if (parameter instanceof BigDecimal) {
                    ps.setBigDecimal(index, (BigDecimal) parameter);
                } else if (parameter instanceof Date) {
                    ps.setDate(index, new java.sql.Date(((Date) parameter).getTime()));
                } else if (parameter instanceof Short) {
                    ps.setShort(index, (Short) parameter);
                } else if(parameter == null){
                    ps.setNull(index, Types.NULL);
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public <T> List<T> query(String sql, RowMapper<T> rowMapper, Object... params) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<T> results = new ArrayList<>();
        try {
            conn = getConnection();
            if (conn != null) {
                ps = conn.prepareStatement(sql);
                setParameters(ps, params);
                rs = ps.executeQuery();
                while (rs.next()) {
                    results.add(rowMapper.mapRow(rs));
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
                return null;
            }
        }
        return results;
    }

    @Override
    public boolean update(String sql, Object... params) {
        Connection conn = null;
        PreparedStatement ps = null;
        boolean result = false;
        try {
            conn = getConnection();
            if (conn != null) {
                conn.setAutoCommit(false);
                ps = conn.prepareStatement(sql);
                setParameters(ps, params);
                ps.executeUpdate();
                conn.commit();
                result = true;
            }
        } catch (Exception e) {
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException exc) {
                    System.out.println(e.getMessage());
                }
            }
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return result;
    }

    @Override
    public Integer insert(String sql, Object... params) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Integer id = null;
        try {
            conn = getConnection();
            if (conn != null) {
                conn.setAutoCommit(false);
                ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                setParameters(ps, params);
                ps.executeUpdate();
                //get auto generate key (IDENTITY)
                rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    try {
                        id = rs.getInt(1);
//                        id = 1;
                        conn.commit();
                    } catch (Exception exception) {
                        if (!rs.getString(1).isEmpty()) {
                            id = 1;
                            conn.commit();
                        } else {
                            System.out.println(exception.getMessage());
                        }
                    }
                }
            }
        } catch (Exception e) {
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return id;
    }

    @Override
    public int count(String sql, Object... params) {
        int count = 0;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            ps = conn.prepareStatement(sql);
            setParameters(ps, params);
            rs = ps.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return count;
    }

    @Override
    public BigDecimal sum(String sql, Object... params) {
        BigDecimal count = BigDecimal.valueOf(0);
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            ps = conn.prepareStatement(sql);
            setParameters(ps, params);
            rs = ps.executeQuery();
            if (rs.next()) {
                count = rs.getBigDecimal(1);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return count;
    }

}
