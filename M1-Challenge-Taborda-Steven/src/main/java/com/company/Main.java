package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    private static List<String[]> customerData = Arrays.asList(
            new String[] {"1","Wayne Enterprises","10000","12-01-2021"},
            new String[] {"2","Daily Planet","-7500","01-10-2022"},
            new String[] {"1","Wayne Enterprises","18000","12-22-2021"},
            new String[] {"3","Ace Chemical","-48000","01-10-2022"},
            new String[] {"3","Ace Chemical","-95000","12-15-2021"},
            new String[] {"1","Wayne Enterprises","175000","01-01-2022"},
            new String[] {"1","Wayne Enterprises","-35000","12-09-2021"},
            new String[] {"1","Wayne Enterprises","-64000","01-17-2022"},
            new String[] {"3","Ace Chemical","70000","12-29-2022"},
            new String[] {"2","Daily Planet","56000","12-13-2022"},
            new String[] {"2","Daily Planet","-33000","01-07-2022"},
            new String[] {"1","Wayne Enterprises","10000","12-01-2021"},
            new String[] {"2","Daily Planet","33000","01-17-2022"},
            new String[] {"3","Ace Chemical","140000","01-25-2022"},
            new String[] {"2","Daily Planet","5000","12-12-2022"},
            new String[] {"3","Ace Chemical","-82000","01-03-2022"},
            new String[] {"1","Wayne Enterprises","10000","12-01-2021"}
    );

    private static Customer createNewCustomer(String id, String name) {
        Customer newCustomer = new Customer();
        newCustomer.setId(Integer.parseInt(id));
        newCustomer.setName(name);
        return newCustomer;
    }

    private static AccountRecord createNewAccountRecord(String charge, String date) {
        AccountRecord newAccountRecord = new AccountRecord();
        newAccountRecord.setCharge(Integer.parseInt(charge));
        newAccountRecord.setChargeDate(date);
        return newAccountRecord;
    }

    // Update Customer charges information
    private static void addCharge(Customer account, AccountRecord record) {
        account.getCharges().add(record);
    }

    private static void printPositiveAccounts(List<Customer> customerData) {
        System.out.println("Positive Accounts: ");
        for (Customer customer : customerData) {
            if (customer.getBalance() >= 0) {
                System.out.println(customer.toString());
            }
        }
    }

    private static void printNegativeAccounts(List<Customer> customerData) {
        System.out.println("Negative Accounts: ");
        for (Customer customer : customerData) {
            if (customer.getBalance() < 0) {
                System.out.println(customer.toString());
            }
        }
    }


    public static void main(String[] args) {

        List<Customer> newCustomerData = new ArrayList<>();

        // Convert List<String[]> of customerData to List<Customer> of customerData
        for (String[] record : customerData) {
            String id = record[0];
            String name = record[1];
            String charge = record[2];
            String date = record[3];

            Customer currentCustomer = null;

            boolean customerExists = newCustomerData.stream().anyMatch(o -> name.equals(o.getName()));

            // Edit existing customer or create new customer then add to new data list
            if (customerExists) {
                for (Customer customer : newCustomerData) {
                    if (customer.getId() == Integer.parseInt(id)) {
                        currentCustomer = customer;
                        break;
                    }
                }
            } else {
                currentCustomer = createNewCustomer(id, name);
                newCustomerData.add(currentCustomer);
            }

            // Create new accountRecord and edit customer data
            AccountRecord newAccountRecord = createNewAccountRecord(charge, date);
            addCharge(currentCustomer, newAccountRecord);

        }

        printPositiveAccounts(newCustomerData);

        System.out.println();

        printNegativeAccounts(newCustomerData);
    }
}
