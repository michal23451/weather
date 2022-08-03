package com.sda.forecast;

import com.sda.location.Location;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@RequiredArgsConstructor
public class ForecastRepositoryImpl implements ForecastRepository {
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

    public Optional<Forecast> getActiveForecast(Location location, LocalDate forecastDate, LocalDateTime expirationDate) {
        Session session = sessionFactory.openSession();
        try {
            Transaction transaction = session.beginTransaction();
            Optional<Forecast> forecast = session.createQuery("SELECT f FROM Forecast f WHERE f.location.id = :id AND f.forecastDate = :forecastDate AND f.createdDate >= :expirationDate", Forecast.class)
                    .setParameter("id", location.getId())
                    .setParameter("forecastDate", forecastDate)
                    .setParameter("expirationDate", expirationDate)
                    .getResultList()
                    .stream().findFirst();
            transaction.commit();
            session.close();
            return forecast;
        } catch (Exception e) {
            Transaction transaction = session.getTransaction();
            transaction.rollback();
            session.close();
            e.printStackTrace();
            throw new RuntimeException("Operacja wyszukania prognozy w bazie nie powiodła się!");
        }
    }
}
