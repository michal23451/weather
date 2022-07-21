package com.sda.forecast;

import com.sda.location.Location;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.time.Instant;
import java.util.Optional;
import java.util.Queue;

@RequiredArgsConstructor
public class ForecastDBRepository implements ForecastRepository {
    private final SessionFactory sessionFactory;

    public Forecast save(Forecast forecast) {
        Session session = sessionFactory.openSession();
        try {
            Transaction transaction = session.beginTransaction();
            session.persist(forecast);
            transaction.commit();
            session.close();
            return forecast;
        } catch (Exception e) {
            Transaction transaction = session.getTransaction();
            transaction.rollback();
            session.close();
            throw new RuntimeException("Operacja zapisu w bazie danych nie powiodła się!");
        }
    }

    // TODO: 21.07.2022, na pewno select do uzupełnienia
    public Optional<Forecast> getActiveForecast(Location location, Instant forecastDate, Instant createdDate) {
        Session session = sessionFactory.openSession();
        try {
            Transaction transaction = session.beginTransaction();
            Query<Forecast> select = session.createQuery("SELECT f FROM Forecast f WHERE f.location = :location AND", Forecast.class);
            transaction.commit();
            session.close();
            return null;
        } catch (Exception e) {
            Transaction transaction = session.getTransaction();
            transaction.rollback();
            session.close();
            e.printStackTrace();
            throw new RuntimeException("Operacja wyszukania prognozy w bazie nie powiodła się!");
        }
    }
}
