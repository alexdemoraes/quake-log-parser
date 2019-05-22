# LLabs backend Chalenge

## Description

This is a challenge proposed by LLabs.
The objective is to parse a log file of Quake 3 Arena and exposes an API of game statistics.
An example file and a README.md of the requirements are available at **requirements** folder.


## Proposed Solution

The proposed approach uses Java 8, Maven and an embedded relational database (H2) to store the games data.
Instead of processing and grouping the data while reading the file, this solution allows the API to be expanded to display other kinds of game statistics.

## How does it work

When you run the application, the first file (in alphabetical order) ending with **.log** placed in the folder **requirements** will be loaded into the embeded H2 database.
The application will automatically create and populate the tables GAME and KILL.
After that a Embedded Tomcat Server starts to run and servers game statistics.
Everytime the application runs the tables are recreated and populated and all the previous data is lost.

---

## Build and run the application

You must have a JDK 8 (or newer) and Maven installed in your machine, besides having access to the Internet so that Maven can download all the dependencies to build and test the application.
You must also have Git installed.

### Clone the repository

From the command prompt, run:
```
[user@machine workspace]$  git clone https://github.com/alexdemoraes/quake-log-parser.git
```

### Build the application

After changing directory, run:
```
[user@machine quake-log-parser]$  mvn clean install
```
All the dependencies will be downloaded, all the code will be compiled, all the tests will be executed and a jar will be placed at **target** folder.


### Run the application

After changing directory, run:
```
[user@machine quake-log-parser]$  java -jar target/quake-log-parser-0.1.0-SNAPSHOT.jar
```
After about 15 seconds (depending on the power of your machine) the application will be up and responding HTTP requests.
You can query the game statistics using any of the [endpoints](#Endpoints list) below.


### Stopping the application

You can stop the application pressing Control-C.


## Testing and code Coverage

To test the application and see the code coverage you should run:

```
mvn clean test
mvn jacoco:report
```

You can see the results opening the file **target/site/jacoco/index.html** in a browser.

---

## Endpoints list:

- [**GET** /games/entities](#getEntities)
- [**GET** /games](#getStatistics)
- [**GET** /games/:id](#getGameStatistics)


### getEntities

Returns all game and kill entities:

**Endpoint:**

```
/games/entities
```

**Curl command**

```
curl -i --header "Content-Type: application/json" --request GET http://localhost:8080/games/entities
```

**Output:**

```
[{
	"id": 1,
	"start": "0:00",
	"finish": "20:37",
	"kills": []
}, {
	"id": 2,
	"start": "20:37",
	"kills": [{
		"id": 1,
		"player": "<world>",
		"killed": "Isgalamido",
		"mode": "MOD_TRIGGER_HURT"
	}, {
		"id": 2,
		"player": "<world>",
		"killed": "Isgalamido",
		"mode": "MOD_TRIGGER_HURT"
	}, {
		"id": 3,
		"player": "<world>",
		"killed": "Isgalamido",
		"mode": "MOD_TRIGGER_HURT"
	}, {
		"id": 4,
		"player": "Isgalamido",
		"killed": "Mocinha",
		"mode": "MOD_ROCKET_SPLASH"
	}, {
		"id": 5,
		"player": "Isgalamido",
		"killed": "Isgalamido",
		"mode": "MOD_ROCKET_SPLASH"
	}, {
		"id": 6,
		"player": "Isgalamido",
		"killed": "Isgalamido",
		"mode": "MOD_ROCKET_SPLASH"
	}, {
		"id": 7,
		"player": "<world>",
		"killed": "Isgalamido",
		"mode": "MOD_TRIGGER_HURT"
	}, {
		"id": 8,
		"player": "<world>",
		"killed": "Isgalamido",
		"mode": "MOD_TRIGGER_HURT"
	}, {
		"id": 9,
		"player": "<world>",
		"killed": "Isgalamido",
		"mode": "MOD_TRIGGER_HURT"
	}, {
		"id": 10,
		"player": "<world>",
		"killed": "Isgalamido",
		"mode": "MOD_FALLING"
	}, {
		"id": 11,
		"player": "<world>",
		"killed": "Isgalamido",
		"mode": "MOD_TRIGGER_HURT"
	}]
},...
]
```

---

### getStatistics

Returns all games statistics

**Endpoint:**

```
/games
```

**Curl command**

```
curl -i --header "Content-Type: application/json" --request GET http://localhost:8080/games
```

**Output:**

```
[{
	"id": 1,
	"start": "0:00",
	"finish": "20:37",
	"players": [],
	"kills": [],
	"total_kills": 0
}, {
	"id": 2,
	"start": "20:37",
	"players": ["Isgalamido"],
	"kills": [{
		"name": "Isgalamido",
		"count": 3
	}],
	"total_kills": 11
},...
]
```

---

### getGameStatistics

Returns a specific game statistic

**Endpoint:**

```
/games/:id
```

**Curl command**

```
curl -i --header "Content-Type: application/json" --request GET http://localhost:8080/games/20
```


**Output:**

```
{
	"id": 20,
	"start": "6:10",
	"finish": "6:34",
	"players": ["Dono da Bola", "Oootsimo"],
	"kills": [{
		"name": "Dono da Bola",
		"count": 2
	}, {
		"name": "Oootsimo",
		"count": 1
	}],
	"total_kills": 3
}
```


## To-do List

* Improve Application Logs
* Create Javadocs
* Increase Code Test Coverage
* Redesign FileProcessorImpl to load more than one file
* Redesign web application to allows file upload and smart processing of more than one file
* Retrieve database configuration properties from Environment variables instead of the properties file
* Implement pagination
* Implement Hystrix
* Use Docker

----

