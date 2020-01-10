package com.sdk4.javaexample.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 一个简单数据库操作类，使用 Spring JdbcTemplate 实现
 */
public class JdbcTool {

    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    {
        // 修改为自己的数据库连接
        String url = "jdbc:mysql://127.0.0.1:3306/mybatis-demo?useUnicode=true";
        String username = "root";
        String password = "123456";

        dataSource = new DriverManagerDataSource(url, username, password);
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public boolean save(Object entity) {
        JSONObject jsonObject = (JSONObject) JSON.toJSON(entity);
        Integer id = jsonObject.getInteger("id");
        List<String> fieldNames = new ArrayList<>(jsonObject.size());
        List<Object> fieldValues = new ArrayList<>(jsonObject.size());
        boolean insert = false;
        if (id == null || id < 1) {
            insert = true;
        }
        for (Map.Entry<String, Object> entry : jsonObject.entrySet()) {
            if ("id".equalsIgnoreCase(entry.getKey())) {
                continue;
            }
            if (insert) {
                fieldNames.add(entry.getKey());
            } else {
                fieldNames.add(String.format("%s=?", entry.getKey()));
            }
            fieldValues.add(entry.getValue());
        }

        String sql;
        if (insert) {
            List<String> values = new ArrayList<>(fieldValues.size());
            fieldValues.forEach(row -> values.add("?"));
            sql = String.format("insert into %s (%s) values (%s)",
                    entity.getClass().getSimpleName().toLowerCase(),
                    String.join(",", fieldNames),
                    String.join(",", values));
        } else {
            sql = String.format("update %s set %s where id=?",
                    entity.getClass().getSimpleName().toLowerCase(),
                    String.join(",", fieldNames));
            fieldValues.add(id);
        }
        jdbcTemplate.update(sql, fieldValues.toArray());
        return true;
    }

    public <T> List<T> listAll(Class<T> entityClass) {
        String sql = String.format("select * from %s", entityClass.getSimpleName().toLowerCase());
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
        List<T> data = new ArrayList<>(list.size());
        for (Map<String, Object> map : list) {
            JSONObject jsonObject = new JSONObject(map);
            data.add(jsonObject.toJavaObject(entityClass));
        }
        return data;
    }

}
