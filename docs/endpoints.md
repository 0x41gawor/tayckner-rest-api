# Endpoints.md

Detailed documentation of endpoints.

This documentation will be constantly developed during project growth.

## Intro

As for now api enables to:

- register new user,
- login user, 
- preform CRUD operation on 5 defined models

This way we have 27 endpoints. 2 for login and registration, and 5x5 for each model, for each CRUD operation.

### List of endpoints


| Path                    | HTTP method |         Action          |
| ----------------------- | :---------: | :---------------------: |
| `api/register`          |    POST     |    register new user    |
| `api/login`             |    POST     | login user (return JWT) |
| `api/categories`        |     GET     |     list categories     |
| `api/categories`        |    POST     |     create category     |
| `api/categories/{id}`   |     GET     |      read category      |
| `api/categories/{id}`   |     PUT     |     update category     |
| `api/categories/{id}`   |   DELETE    |     delete category     |
| `api/activities`        |     GET     |     list activities     |
| `api/activities`        |    POST     |     create activity     |
| `api/activities/{id}`   |     GET     |      read activity      |
| `api/activities/{id}`   |     PUT     |     update activity     |
| `api/activities/{id}`   |   DELETE    |     delete activity     |
| `api/schedules`         |     GET     |     list schedules      |
| `api/schedules`         |    POST     |     create schedule     |
| `api/schedules/{id}`    |     GET     |      read schedule      |
| `api/schedules/{id}`    |     PUT     |     update schedule     |
| `api/schedules/{id}`    |   DELETE    |     delete schedule     |
| `api/habits`            |     GET     |       list habits       |
| `api/habits`            |    POST     |      create habit       |
| `api/habits/{id}`       |     GET     |       read habit        |
| `api/habits/{id}`       |     PUT     |      update habit       |
| `api/habits/{id}`       |   DELETE    |      delete habit       |
| `api/habit-events`      |     GET     |    list habit-events    |
| `api/habit-events`      |    POST     |   create habit-event    |
| `api/habit-events/{id}` |     GET     |    read habit-event     |
| `api/habit-events/{id}` |     PUT     |   update habit-event    |
| `api/habit-events/{id}` |   DELETE    |   delete habit-event    |

## Detailed Documentation

Format of each endpoint documentation:

- HTTP Method - HTTP Method
- Value - String value of endpoint
- Consumes - MediaType of Request Body
- Produces - MediaType of returned data
- Path Variables - Variables in value
- Request Body - Application object that will be mapped from JSON body
- Happy path - What will happen in happy path scenario

This documentation only describes responses where HTTP response status code is `200 OK`.

### Users

Two endpoints related with users.

### `/api/register`

| HTTP Method |     Value      | Consumes | Produces | Path Variables | Request Body |             Happy path              |
| :---------: | :------------: | :------: | :------: | :------------: | :----------: | :---------------------------------: |
|    POST     | `api/register` |   JSON   |   JSON   |       -        | `UserModel`  | New user will be added to database. |

##### Response protocol

Response is a JSON with the following fields:

- **code** 
- **message**

It can be pair of one of these:

| code |                      message                       |
| :--: | :------------------------------------------------: |
|  0   |           "OK, registered successfully!"           |
|  R1  |             "Username already exists"              |
|  R2  |                "Email already used"                |
|  R3  |          "Invalid character in username"           |
|  R4  |                "Password too short"                |
|  R5  |      "Password does not contain any numbers"       |
|  R6  | "Password does not contain any uppercase letters"  |
|  R7  | "Password does not contain any lowercase letters"  |
|  R8  | "Password does not contain any special characters" |
|  R9  |          "Invalid character in password"           |
| R10  |              Incorrect email address"              |

##### Sample request

```http
POST /api/register HTTP/1.1
Host: localhost:8080
Content-Type: application/json
Content-Length: 67

{
    "username": "SuperUser123",
    "password": "secRET123"
    "firstName": "Matt"
    "lastname": "Mattowsky"
    "email" : "matt@example.com"
}
```

##### Sample response

```json
{
    "code": "R8",
    "message": "Password does not contain any special characters"
}
```

### `api/login`

| HTTP Method |    Value    | Consumes | Produces | Path Variables |   Request Body    |       Happy path       |
| :---------: | :---------: | :------: | :------: | :------------: | :---------------: | :--------------------: |
|    POST     | `api/login` |   JSON   |   JSON   |       -        | `UserCredentials` | Application sends JWT. |

##### Response protocol

Response is a JSON with the following fields:

- **code** 
- **message**

It can be pair of one of these:

| code |      message       |
| :--: | :----------------: |
|  0   |      `<JWT>`       |
|  L1  | "No such username" |
|  L2  |  "Wrong password"  |

##### Sample request

```http
POST /api/login HTTP/1.1
Host: localhost:8080
Content-Type: application/json
Content-Length: 67

{
    "username": "SuperUser123",
    "password": "hashedPasword543fsfgj3449gfnrewSFS4832nfgF4gn9f244343&&Fg8"
}
```

##### Sample response

```json
{
    "code": "0",
    "message": "eyJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE2MzU3Mjc1NzQsImV4cMTYzNTczNzA5NiwidXNlcklkIjo1LCJ1c2VybmFtZSI6Iko"
}
```

### Models

Generic documentation for 5 models. Instead of each "model" occurrence you can put one of {"activity", "category", "schedule", "habit", "habit-event"} and instead of each "models" - {"activities", "categories", "schedules", "habits", "habit-events"}.

### `api/models/` - GET

| HTTP Method |    Value     | Consumes | Produces | Path Variables | Request Body |             Happy path              |
| :---------: | :----------: | :------: | :------: | :------------: | :----------: | :---------------------------------: |
|     GET     | `api/models` |    -     |   JSON   |       -        |      -       | Application returns list of models. |

##### Response protocol

Response is a JSON with the following fields:

- **code** 
- **content**

It can be pair of one of these:

| code |      content       |
| :--: | :----------------: |
|  0   | `<List of Models>` |
| ML1  |  "List is empty"   |

##### Sample request

```http
GET /api/habits/ HTTP/1.1
Host: localhost:8080
Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE2MzU3Mjc1NzQsImV4cCI6MTYzNTczNzA5NiwidXNlcklkIjo1LCJ1c2VybmFtZSI6IkouZ3JvbmR6aXUifQ.21NHGIMM9_Tv6gp-YdyLKlMwFlgzCrM3o-R8ATV9hCs
```

##### Sample response

```json
{
    "code": "0",
    "content": [
        {
        "id": 4,
        "name": "Workout",
        "color": "#11ff11",
        "user": {
            "id": 5,
            "username": "J.grondziu",
            "password": null
        }
    },
    {
        "id": 6,
        "name": "Fast-Food",
        "color": "#ff0000",
        "user": {
            "id": 5,
            "username": "J.grondziu",
            "password": null
        }
    }
    ]
}
```

### `api/models/` - POST

| HTTP Method |    Value     | Consumes | Produces | Path Variables | Request Body |                          Happy path                          |
| :---------: | :----------: | :------: | :------: | :------------: | :----------: | :----------------------------------------------------------: |
|    POST     | `api/models` |   JSON   |   JSON   |       -        |    Model     | Application saves new model to database and returns it in response. |

##### Response protocol

Response is a JSON with the following fields:

- **code** 
- **content**

It can be pair of one of these:

| code |                   content                   |
| :--: | :-----------------------------------------: |
|  0   |              `<Created Model>`              |
| MCH1 | "User already has habit with the same name" |
| MCH2 |               "Invalid color"               |
| MCA1 |               "Invalid date"                |

##### Sample request

```http
POST /api/habits/ HTTP/1.1
Host: localhost:8080
Content-Type: application/json
Content-Length: 67
Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE2MzU3Mjc1NzQsImV4cCI6MTYzNTczNzA5NiwidXNlcklkIjo1LCJ1c2VybmFtZSI6IkouZ3JvbmR6aXUifQ.21NHGIMM9_Tv6gp-YdyLKlMwFlgzCrM3o-R8ATV9hCs

{
 "name": "Reading",
 "color": "#13ffff"
}
```

##### Sample response

```json
{
    "code": "0",
    "content": [
        {
        "id": 12,
        "name": "Reading",
        "color": "#13ffff",
        "user": {
            "id": 5,
            "username": "J.grondziu",
            "password": null
        }
    ]
}
```

### `api/models/{id}` - GET

| HTTP Method |       Value       | Consumes | Produces | Path Variables | Request Body |                      Happy path                      |
| :---------: | :---------------: | :------: | :------: | :------------: | :----------: | :--------------------------------------------------: |
|     GET     | `api/models/{id}` |    -     |   JSON   |      `id`      |      -       | Application returns model with given id in response. |

##### Response protocol

Response is a JSON with the following fields:

- **code** 
- **content**

It can be pair of one of these:

| code |             content              |
| :--: | :------------------------------: |
|  0   |          `<Read Model>`          |
| MR1  | "Object with given id not found" |

##### Sample request

```http
GET /api/habits/12 HTTP/1.1
Host: localhost:8080
Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE2MzU3Mjc1NzQsImV4cCI6MTYzNTczNzA5NiwidXNlcklkIjo1LCJ1c2VybmFtZSI6IkouZ3JvbmR6aXUifQ.21NHGIMM9_Tv6gp-YdyLKlMwFlgzCrM3o-R8ATV9hCs
```

##### Sample response

```json
{
    "code": "0",
    "content": [
        {
        "id": 12,
        "name": "Reading",
        "color": "#13ffff",
        "user": {
            "id": 5,
            "username": "J.grondziu",
            "password": null
        }
    ]
}
```

### `api/models/{id}` - PUT

| HTTP Method |       Value       | Consumes | Produces | Path Variables | Request Body |                          Happy path                          |
| :---------: | :---------------: | :------: | :------: | :------------: | :----------: | :----------------------------------------------------------: |
|     PUT     | `api/models/{id}` |   JSON   |   JSON   |      `id`      |    Model     | Application updates object with given id with new model. Then returns updated model. |

##### Response protocol

Response is a JSON with the following fields:

- **code** 
- **content**

It can be pair of one of these:

| code |                   content                   |
| :--: | :-----------------------------------------: |
|  0   |              `<Updated Model>`              |
| MR1  |      "Object with given id not found"       |
| MC1  | "User already has model with the same name" |
| MC2  |               "Invalid color"               |

##### Sample request

```http
GET /api/habits/12 HTTP/1.1
Host: localhost:8080
Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE2MzU3Mjc1NzQsImV4cCI6MTYzNTczNzA5NiwidXNlcklkIjo1LCJ1c2VybmFtZSI6IkouZ3JvbmR6aXUifQ.21NHGIMM9_Tv6gp-YdyLKlMwFlgzCrM3o-R8ATV9hCs

{
 "name": "Reading but faster",
 "color": "#13ffff"
}
```

##### Sample response

```json
{
    "code": "0",
    "content": [
        {
        "id": 12,
        "name": "Reading but faster",
        "color": "#13ffff",
        "user": {
            "id": 5,
            "username": "J.grondziu",
            "password": null
        }
    ]
}
```

### `api/models/{id}` - DELETE

| HTTP Method |       Value       | Consumes | Produces | Path Variables | Request Body |                Happy path                 |
| :---------: | :---------------: | :------: | :------: | :------------: | :----------: | :---------------------------------------: |
|   DELETE    | `api/models/{id}` |    -     |    -     |      `id`      |      -       | Application deletes object with given id. |

##### Response protocol

Response is a JSON with the following fields:

- **code** 
- **content**

It can be pair of one of these:

| code |             content              |
| :--: | :------------------------------: |
|  0   |  "Object with id {id} deleted"   |
| MD1  | "Object with given id not found" |

##### Sample request

```http
DELETE /api/habits/12 HTTP/1.1
Host: localhost:8080
Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE2MzU3Mjc1NzQsImV4cCI6MTYzNTczNzA5NiwidXNlcklkIjo1LCJ1c2VybmFtZSI6IkouZ3JvbmR6aXUifQ.21NHGIMM9_Tv6gp-YdyLKlMwFlgzCrM3o-R8ATV9hCs
```

##### Sample response

```json
{
    "code": "0",
    "content": "Object with id 12 deleted"
}
```
