Homework (2p)

- [x] Create all entity classes and repositories. Implement properly the one-to-many relationships.

- [x] Create a generic AbstractRepository using generics in order to simplify the creation of the repository classes.
  You may take a look at the CrudRepository interface from Spring Framework.

- [x] Insert, using JPA, a large number of cities in the database and log the execution time of your JPQL queries.

- [x] (partial) (+1p) Assume each city has a new property, its population. Use a constraint solver, such as Choco solver,
  OptaPlanner or ORTools, in order to find a set of cities having names that start with the same letter, the total sum
  of their population is between two given bounds and they are from different countries.

Bonus (2p)

- [x] Implement properly the many-to-many sisterhood relationship.

- [x] (partial) Implement both the JDBC and JPA implementations and use an AbstractFactory in order to create the DAO objects (the repositories).

- [x] (partial) The application will use JDBC or JPA depending on a parameter given in an initialization file.

- [ ] You may also use an IoC container in order to inject the DAO implementations.

```postgresql
CREATE TABLE continents
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR(20)
);

CREATE TABLE countries
(
    id        SERIAL PRIMARY KEY,
    name      VARCHAR(40),
    code      VARCHAR(10),
    continent INTEGER,
    CONSTRAINT fk_countries
        FOREIGN KEY (continent)
            REFERENCES continents (id)
);

CREATE TABLE cities
(
    id         SERIAL PRIMARY KEY,
    name       VARCHAR(40),
    capital    boolean,
    latitude   NUMERIC(18, 15),
    longitude  NUMERIC(18, 15),
    population INTEGER,
    id_country INTEGER,
    CONSTRAINT fk_cities
        FOREIGN KEY (id_country)
            REFERENCES countries (id)
);
```

* Instead of doing a set of cities we did the solver between any 2 cities that meet the requirements

output example (for solver):
```
Solution 1: id1 = 7, id2 = 6
Solution 2: id1 = 8, id2 = 6
```

<hr>

Output after inserting 5000 queries:

```
Execution time of queries: 3.0
```
