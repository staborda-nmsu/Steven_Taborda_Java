
/* Categories */
SELECT DISTINCT category FROM products;

/* Made by Dell */
SELECT p.product_name
FROM products p
JOIN employees e ON e.id = p.id
WHERE e.company = 'Dell';

/* Orders shipped to Pennsylvania */
SELECT o.id, o.order_date, o.shipped_date, o.ship_name, c.address1, c.address2, c.city, c.state, c.postal_code, c.country
FROM orders o
JOIN customers c ON c.id = o.customer_id
WHERE o.ship_state = 'Pennsylvania';

/* List the first name and last name of all employees with last names that start with the letter W. */
SELECT first_name, last_name
FROM employees
WHERE last_name LIKE 'W%';

/* List all customers from zip codes that start with 55. */
SELECT *
FROM customers
WHERE postal_code LIKE '55%';

/* List all customers from zip codes that end with 0. */
SELECT *
FROM customers
WHERE postal_code LIKE '%0';

/* List the first name, last name, and email for all customers with a ".org" email address. */
SELECT first_name, last_name, email
FROM customers
WHERE email LIKE '%.org';

/* List the first name, last name, and phone number for all customers from the 202 area code. */
SELECT first_name, last_name, phone
FROM customers
WHERE phone LIKE '(202)%' OR phone LIKE '+1 (202)%';

/* List the first name, last name, and phone number for all customers from the 202 area code, ordered by last name, first name. */
SELECT first_name, last_name, phone
FROM customers
WHERE phone LIKE '(202)%' OR phone LIKE '+1 (202)%'
ORDER BY last_name, first_name;


