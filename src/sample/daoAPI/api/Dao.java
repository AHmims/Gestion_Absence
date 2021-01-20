package sample.daoAPI.api;

import sample.domainClasses.Administrateur;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;

public interface Dao<T> {
    Administrateur get(String id);

    Optional<T> get(Calendar id);

    List<T> getAll();

    void save(T t);

    void update(T t);

    void delete(T t);
}
