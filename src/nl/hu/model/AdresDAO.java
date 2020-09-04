package nl.hu.model;

import java.sql.SQLException;
import java.util.List;

public interface AdresDAO {

    public abstract boolean save(Adres adres) throws SQLException;
    public abstract boolean update(Adres adres) throws SQLException;
    public abstract boolean delete(Adres adres) throws SQLException;
    public abstract Adres findByReiziger(Reiziger reiziger) throws SQLException;
    public abstract List<Adres> findAll() throws SQLException;
}
