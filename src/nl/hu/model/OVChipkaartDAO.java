package nl.hu.model;

import java.sql.SQLException;
import java.util.List;

public interface OVChipkaartDAO {
    public abstract List<OVChipkaart> findByReiziger(Reiziger reiziger) throws SQLException;
    public abstract boolean save(OVChipkaart ovChipkaart) throws SQLException;
    public abstract boolean update(OVChipkaart ovChipkaart) throws SQLException;
    public abstract boolean delete(OVChipkaart ovChipkaart) throws SQLException;
}
