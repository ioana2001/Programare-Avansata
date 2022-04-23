Compulsory (1p)

- [x] Create a relational database using any RDBMS (Oracle, Postgres, MySql, Java DB, etc.).
- [x] Write an SQL script that will create the following tables:
* countries: id, name, code, continent
* continents: id, name

```postgres-psql
CREATE TABLE countries(
	id SERIAL PRIMARY KEY , 
	name VARCHAR(20), 
	code VARCHAR(10), 
	continent INTEGER, 
	CONSTRAINT fk_countries
		FOREIGN KEY(continent)
			REFERENCES continents(id)
);

CREATE TABLE continents(
	id SERIAL PRIMARY KEY, 
	name VARCHAR(20)
);

```

- [x] Update pom.xml, in order to add the database driver to the project libraries.
- [x] Create a singleton class in order to manage a connection to the database.
- [x] Create DAO classes that offer methods for creating countries and continents, and finding them by their ids and names;
- [x] Implement a simple test using your classes.

Output (the names of the countries from Europe): 
```
Romania
Ukraine
```