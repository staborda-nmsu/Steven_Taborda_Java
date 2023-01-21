package com.company;

import static org.junit.jupiter.api.Assertions.*;

class CustomerTest {

    @org.junit.jupiter.api.Test
    void getBalancePositive() {
        Customer customer = new Customer();
        AccountRecord newAccountRecord = new AccountRecord();
        newAccountRecord.setCharge(1);
        newAccountRecord.setChargeDate("NULL");
        customer.getCharges().add(newAccountRecord);
        assertEquals(customer.getBalance(), 1);
    }

    @org.junit.jupiter.api.Test
    void getBalanceNonNegative() {
        Customer customer = new Customer();
        AccountRecord newAccountRecord = new AccountRecord();
        newAccountRecord.setCharge(0);
        newAccountRecord.setChargeDate("NULL");
        customer.getCharges().add(newAccountRecord);
        assertEquals(customer.getBalance(), 0);
    }

    @org.junit.jupiter.api.Test
    void getBalanceNegative() {
        Customer customer = new Customer();
        AccountRecord newAccountRecord = new AccountRecord();
        newAccountRecord.setCharge(-1);
        newAccountRecord.setChargeDate("NULL");
        customer.getCharges().add(newAccountRecord);
        assertEquals(customer.getBalance(), -1);
    }

    @org.junit.jupiter.api.Test
    void testToString() {
        Customer customer = new Customer();
        AccountRecord newAccountRecord = new AccountRecord();

        customer.setId(1);
        customer.setName("Bob");

        newAccountRecord.setCharge(-1);
        newAccountRecord.setChargeDate("NULL");
        customer.getCharges().add(newAccountRecord);

        assertEquals(customer.toString(), "1 Bob -1");
    }
}