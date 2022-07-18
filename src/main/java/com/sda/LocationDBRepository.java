package com.sda;

import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.engine.transaction.internal.TransactionImpl;

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
            throw new RuntimeException("Operacja zapisu w bazie danych nie powiodła się!");
        }
    }
}
