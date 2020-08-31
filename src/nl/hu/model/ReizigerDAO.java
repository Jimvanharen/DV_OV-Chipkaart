package nl.hu.model;

public interface ReizigerDAO {

    public abstract boolean save(Reiziger reiziger);
    public abstract boolean update(Reiziger reiziger);
    public abstract boolean delete(Reiziger reiziger);
    public abstract Reiziger findById();
}
