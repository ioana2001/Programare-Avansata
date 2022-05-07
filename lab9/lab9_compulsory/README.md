Compulsory (1p)

- [x] Create a persistence unit (use EclipseLink or Hibernate or other JPA implementation). Verify the presence of the persistence.xml file in your project. Make sure that the driver for EclipseLink or Hibernate was added to your project classpath (or add it yourself).

- [x] Define the entity classes for your model (at least one) and put them in a dedicated package. You may use the IDE support in order to generate entity classes from database tables.

- [x] Create a singleton responsible with the management of an EntityManagerFactory object.

- [x] Define repository classes for your entities (at least one). They must contain the following methods:
- create - receives an entity and saves it into the database;
- findById - returns an entity based on its primary key;
- findByName - returns a list of entities that match a given name pattern. Use a named query in order to implement this method.

- [x] Test your application.

<hr>

We defined the classes CitiesEntity, ContinentsEntity and CountriesEntity (corresponding with the database tables) in the package entity.

The class Manager is responsible for managing the EntityManagerFactory object.

We tested in our program the findByName and create methods for the repositories.

Program output:

```
[ContinentsEntity{id=1, name='Europe'}]
[CountriesEntity{id=1, name='Romania', code='RO', continent=1}]
[City{id=1, name='Bucharest', capital=true, latitude=0.0, longitude=0.0, id_country=1}]
```

