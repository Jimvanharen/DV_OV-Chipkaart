package nl.hu.model;

import java.sql.SQLException;
import java.util.List;

public interface ReizigerDAO {

    public abstract boolean save(Reiziger reiziger) throws SQLException;
    public abstract boolean update(Reiziger reiziger) throws SQLException;
    public abstract boolean delete(Reiziger reiziger) throws SQLException;
    public abstract Reiziger findById(int id) throws SQLException;
    public abstract List<Reiziger> findByGbDatum(String datum) throws SQLException;
    public abstract List<Reiziger> findAll() throws SQLException;
}
