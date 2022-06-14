package ru.job4j.tdd;

import org.junit.Ignore;
import org.junit.Test;

import java.util.Calendar;
import java.util.List;

import static org.junit.Assert.*;

public class CinemaTest {
    @Ignore
    @Test
    public void whenBuy() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        date.set(2020, 10, 10, 23, 00);
        Ticket ticket = cinema.buy(account, 1, 1, date);
        assertEquals(ticket, new Ticket3D());
    }
    @Ignore
    @Test(expected = IllegalArgumentException.class)
    public void whenInvalidAPlaceThenException() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        date.set(2022, 5, 25, 18, 30);
        cinema.buy(account, 30, 40, date);
    }
    @Ignore
    @Test(expected = IllegalArgumentException.class)
    public void whenInvalidDateThenException() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        date.set(2023, 5, 25, 18, 30);
        cinema.buy(account, 1, 1, date);
    }
    @Ignore
    @Test(expected = IllegalArgumentException.class)
    public void whenPlaceIsSoldYetThenException() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        date.set(2022, 5, 25, 18, 30);
        cinema.buy(account, 1, 2, date);
    }
    @Ignore
    @Test
    public void whenListOfSessionsIsEmptyThenReturnsEmptyList() {
        Cinema cinema = new Cinema3D();
        List<Session> sessions = cinema.find(session -> true);
        assertTrue(sessions.isEmpty());
    }
    @Ignore
    @Test
    public void whenThereIsNoSuchSessionThenReturnEmptyList() {
        Cinema cinema = new Cinema3D();
        cinema.add(new Session3D());
        List<Session> sessions = cinema.find(session -> true);
        assertTrue(sessions.isEmpty());
    }
    @Ignore
    @Test
    public void whenAddTwoSessionThenListMustContainsTwoElements() {
        Cinema cinema = new Cinema3D();
        cinema.add(new Session3D());
        cinema.add(new Session3D());
        List<Session> sessions = cinema.find(session -> true);
        assertEquals(2, sessions.size());
    }
}