package com.kpi.bank.system.services.dao.entity;

import com.kpi.bank.system.model.Card;

import java.sql.SQLException;
import java.util.List;

public interface DAO<T> {

    // create
    void insert(T row) throws SQLException;

    //read
    List<T> loadAll() throws SQLException;
    T loadById(Integer id) throws SQLException;

    // update
    void update(T row) throws SQLException;

    // delete
    void remove(T row) throws SQLException;
}
