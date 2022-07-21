package com.sda.location;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class LocationDBRepository implements LocationRepository {
    private final SessionFactory sessionFactory;

    public Location save(Location location) {
        Session session = sessionFactory.openSession();
        try {
            Transaction transaction = session.beginTransaction();
            session.persist(location);
            transaction.commit();
            session.close();
            return location;
        } catch (Exception e) {
            Transaction transaction = session.getTransaction();
            transaction.rollback();
            session.close();
            throw new RuntimeException("Operacja zapisu w bazie danych nie powiodła się!");
        }
    }

    @Override
    public List<Location> findAll() {
        Session session = sessionFactory.openSession();
        try {
            Transaction transaction = session.beginTransaction();
            Query<Location> resultQuery = session.createQuery("SELECT l FROM Location l", Location.class);
            List<Location> resultList = resultQuery.getResultList();
            transaction.commit();
            session.close();
            return resultList;
        } catch (Exception e) {
            Transaction transaction = session.getTransaction();
            transaction.rollback();
            session.close();
            throw new RuntimeException("Operacja wyszukiwania w bazie danych nie powiodła się!");
        }
    }

    @Override
    public Optional<Location> findById(Long id) {
        Session session = sessionFactory.openSession();
        try {
            Transaction transaction = session.beginTransaction();
            Query<Location> select = session.createQuery("SELECT l FROM Location l WHERE id = :id", Location.class);
            select.setParameter("id", id);
            Optional<Location> locationOptional = Optional.ofNullable(select.getSingleResult());
            transaction.commit();
            session.close();
            return locationOptional;
        } catch (Exception e) {
            Transaction transaction = session.getTransaction();
            transaction.rollback();
            session.close();
            e.printStackTrace();
            throw new RuntimeException("Operacja wyszukiwania po id w bazie danych nie powiodła się!");
        }
    }
}
