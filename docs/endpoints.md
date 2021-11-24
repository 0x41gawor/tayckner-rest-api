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

### Activity

#### `api/activities/` - GET

| HTTP Method |      Value       | Consumes | Produces | Path Variables | Request Body |             Happy path              |
| :---------: | :--------------: | :------: | :------: | :------------: | :----------: | :---------------------------------: |
|     GET     | `api/activities` |    -     |   JSON   |       -        |      -       | Application returns list of models. |

##### Response protocol

| code |     message     |      content       |
| :--: | :-------------: | :----------------: |
| XxX0 |       OK        | `<List of Models>` |
| XxL1 | "List is empty" |        null        |

##### Sample request

```http
GET /api/activities/ HTTP/1.1
Host: 127.0.0.1:8080
Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE2Mzc3MTQzODAsImV4cCI6MTYzNzcyMzkwMiwidXNlcklkIjoxLCJ1c2VybmFtZSI6Ik1hcm9Lb2xhbm8xMjMifQ.Awh7pJ1vlCc_08j5HPGXIGQ6rDBfCO-hez627GXFZeI
```

##### Sample response

```json
{
    "code": "XxX0",
    "message": "OK",
    "content": [
        {
            "id": 3,
            "name": "Push day",
            "category": {
                "id": 1,
                "name": "Workout",
                "description": "...",
                "color": "#ffffff",
                "user": {
                    "id": 1,
                    "username": "VitaminBoy",
                    "password": "",
                    "firstName": "Casimir",
                    "lastName": "Funk",
                    "email": "k.funk@biopoczta.pl"
                }
            },
            "startTime": "2021-11-22T19:37:02",
            "endTime": "2021-11-22T19:37:02",
            "duration": 10,
            "breaks": 0
        },
        {
            "id": 4,
            "name": "Pull day",
            "category": {
                "id": 1,
                "name": "Workout",
                "description": "...",
                "color": "#ffffff",
                "user": {
                    "id": 1,
                    "username": "VitaminBoy",
                    "password": "",
                    "firstName": "Casimir",
                    "lastName": "Funk",
                    "email": "k.funk@biopoczta.pl"
                }
            },
            "startTime": "2021-11-22T19:37:02",
            "endTime": "2021-11-22T19:37:02",
            "duration": 10,
            "breaks": 0
        }
    ]
}
```

#### `api/activities/` - POST

| HTTP Method |      Value       | Consumes | Produces | Path Variables | Request Body  |                          Happy path                          |
| :---------: | :--------------: | :------: | :------: | :------------: | :-----------: | :----------------------------------------------------------: |
|    POST     | `api/activities` |   JSON   |   JSON   |       -        | ActivityModel | Application saves new model to database and returns it in response. |

##### Response protocol

| code |               message                |      content      |
| :--: | :----------------------------------: | :---------------: |
| XxX0 |                  OK                  | `<Created Model>` |
| AcX1 |   Category does not belong to user   |       null        |
| XxX4 |    Start time is before end time     |       null        |
| XxX5 | Duration has to be greater than zero |       null        |

##### Sample request

```http
POST /api/activities/ HTTP/1.1
Host: 127.0.0.1:8080
Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE2Mzc3MTQzODAsImV4cCI6MTYzNzcyMzkwMiwidXNlcklkIjoxLCJ1c2VybmFtZSI6Ik1hcm9Lb2xhbm8xMjMifQ.Awh7pJ1vlCc_08j5HPGXIGQ6rDBfCO-hez627GXFZeI
Content-Type: application/json
Content-Length: 281
{
            "id": 5,
            "name": "Leg day",
            "category": {
                "id": 1
            },
            "startTime": "2021-11-22T19:37:02",
            "endTime": "2021-11-22T19:37:02",
            "duration": 10,
            "breaks": 0
}
```

##### Sample response

```json
{
    "code": "XxX0",
    "message": "OK",
    "content": {
        "id": 5,
        "name": "Leg day",
        "category": {
            "id": 1,
            "name": "Workout",
            "description": "...",
            "color": "#ffffff",
            "user": {
                    "id": 1,
                    "username": "VitaminBoy",
                    "password": "",
                    "firstName": "Casimir",
                    "lastName": "Funk",
                    "email": "k.funk@biopoczta.pl"
                }
        },
        "startTime": "2021-11-22T19:37:02",
        "endTime": "2021-11-22T19:37:02",
        "duration": 10,
        "breaks": 0
    }
}
```

#### `api/activities/{id}` - GET

| HTTP Method |         Value         | Consumes | Produces | Path Variables | Request Body |                      Happy path                      |
| :---------: | :-------------------: | :------: | :------: | :------------: | :----------: | :--------------------------------------------------: |
|     GET     | `api/activities/{id}` |    -     |   JSON   |      `id`      |      -       | Application returns model with given id in response. |

##### Response protocol

| code |                 message                 |    content     |
| :--: | :-------------------------------------: | :------------: |
| XxX0 |                   OK                    | `<Read Model>` |
| XxX2 | Object with given id not found for user |      null      |

##### Sample request

```http
GET /api/activities/3 HTTP/1.1
Host: 127.0.0.1:8080
Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE2Mzc3MTQzODAsImV4cCI6MTYzNzcyMzkwMiwidXNlcklkIjoxLCJ1c2VybmFtZSI6Ik1hcm9Lb2xhbm8xMjMifQ.Awh7pJ1vlCc_08j5HPGXIGQ6rDBfCO-hez627GXFZeI
```

##### Sample response

```json
{
    "code": "XxX0",
    "message": "OK",
    "content":  {
            "id": 3,
            "name": "Push day",
            "category": {
                "id": 1,
                "name": "Workout",
                "description": "...",
                "color": "#ffffff",
                "user": {
                    "id": 1,
                    "username": "VitaminBoy",
                    "password": "",
                    "firstName": "Casimir",
                    "lastName": "Funk",
                    "email": "k.funk@biopoczta.pl"
                }
            },
            "startTime": "2021-11-22T19:37:02",
            "endTime": "2021-11-22T19:37:02",
            "duration": 10,
            "breaks": 0
        }
}
```

#### `api/activities/{id}` - PUT

| HTTP Method |         Value         | Consumes | Produces | Path Variables | Request Body  |                          Happy path                          |
| :---------: | :-------------------: | :------: | :------: | :------------: | :-----------: | :----------------------------------------------------------: |
|     PUT     | `api/activities/{id}` |   JSON   |   JSON   |      `id`      | ActivityModel | Application updates object with given id with new model. Then returns updated model. |

##### Response protocol

| code |                 message                 |      content      |
| :--: | :-------------------------------------: | :---------------: |
| XxX0 |                   OK                    | `<Updated model>` |
| AcX1 |    Category does not belong to user     |       null        |
| XxX4 |      Start time is before end time      |       null        |
| XxX5 |  Duration has to be greater than zero   |       null        |
| XxX2 | Object with given id not found for user |       null        |

##### Sample request

```http
PUT /api/activities/3 HTTP/1.1
Host: 127.0.0.1:8080
Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE2Mzc3MTQzODAsImV4cCI6MTYzNzcyMzkwMiwidXNlcklkIjoxLCJ1c2VybmFtZSI6Ik1hcm9Lb2xhbm8xMjMifQ.Awh7pJ1vlCc_08j5HPGXIGQ6rDBfCO-hez627GXFZeI
Content-Type: application/json
Content-Length: 285

{
            "id": 3,
            "name": "Push day but updated",
            "category": {
                "id": 1
            },
            "startTime": "2021-11-22T19:37:02",
            "endTime": "2021-11-22T19:37:02",
            "duration": 10,
            "breaks": 0
}
```

##### Sample response

```json
{
    "code": "XxX0",
    "message": "OK",
    "content": {
        "id": 3,
       "name": "Push day but updated",
        "category": {
            "id": 1,
                "name": "Workout",
                "description": "...",
                "color": "#ffffff",
            "user": {
                    "id": 1,
                    "username": "VitaminBoy",
                    "password": "",
                    "firstName": "Casimir",
                    "lastName": "Funk",
                    "email": "k.funk@biopoczta.pl"
                }
        },
        "startTime": "2021-11-22T19:37:02",
        "endTime": "2021-11-22T19:37:02",
        "duration": 10,
        "breaks": 0
    }
}
```

#### `api/activities/{id}` - DELETE

| HTTP Method |         Value         | Consumes | Produces | Path Variables | Request Body |                Happy path                 |
| :---------: | :-------------------: | :------: | :------: | :------------: | :----------: | :---------------------------------------: |
|   DELETE    | `api/activities/{id}` |    -     |    -     |      `id`      |      -       | Application deletes object with given id. |

##### Response protocol

| code |                 message                 | content |
| :--: | :-------------------------------------: | :-----: |
| XxX0 |                   OK                    |  null   |
| XxX2 | Object with given id not found for user |  null   |

##### Sample request

```http
DELETE /api/activities/3 HTTP/1.1
Host: 127.0.0.1:8080
Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE2Mzc3MTQzODAsImV4cCI6MTYzNzcyMzkwMiwidXNlcklkIjoxLCJ1c2VybmFtZSI6Ik1hcm9Lb2xhbm8xMjMifQ.Awh7pJ1vlCc_08j5HPGXIGQ6rDBfCO-hez627GXFZeI
```

##### Sample response

```json
{
    "code": "XxX0",
    "message": "OK",
    "content": null
}
```
### Category

#### `api/categories/` - GET

| HTTP Method |      Value       | Consumes | Produces | Path Variables | Request Body |             Happy path              |
| :---------: | :--------------: | :------: | :------: | :------------: | :----------: | :---------------------------------: |
|     GET     | `api/categories` |    -     |   JSON   |       -        |      -       | Application returns list of models. |

##### Response protocol

| code |     message     |      content       |
| :--: | :-------------: | :----------------: |
| XxX0 |       OK        | `<List of Models>` |
| XxL1 | "List is empty" |        null        |

##### Sample request

```http

```

##### Sample response

```json

```

#### `api/categories/` - POST

| HTTP Method |      Value       | Consumes | Produces | Path Variables | Request Body |                          Happy path                          |
| :---------: | :--------------: | :------: | :------: | :------------: | :----------: | :----------------------------------------------------------: |
|    POST     | `api/categories` |   JSON   |   JSON   |       -        |    Model     | Application saves new model to database and returns it in response. |

##### Response protocol

| code |               message                |      content      |
| :--: | :----------------------------------: | :---------------: |
| XxX0 |                  OK                  | `<Created Model>` |
| AcX1 |   Category does not belong to user   |       null        |
| XxX4 |    Start time is before end time     |       null        |
| XxX5 | Duration has to be greater than zero |       null        |

##### Sample request

```http

```

##### Sample response

```json

```

#### `api/categories/{id}` - GET

| HTTP Method |         Value         | Consumes | Produces | Path Variables | Request Body |                      Happy path                      |
| :---------: | :-------------------: | :------: | :------: | :------------: | :----------: | :--------------------------------------------------: |
|     GET     | `api/categories/{id}` |    -     |   JSON   |      `id`      |      -       | Application returns model with given id in response. |

##### Response protocol

| code |                 message                 |    content     |
| :--: | :-------------------------------------: | :------------: |
| XxX0 |                   OK                    | `<Read Model>` |
| XxX2 | Object with given id not found for user |      null      |

##### Sample request

```http

```

##### Sample response

```json

```

#### `api/categories/{id}` - PUT

| HTTP Method |         Value         | Consumes | Produces | Path Variables | Request Body |                          Happy path                          |
| :---------: | :-------------------: | :------: | :------: | :------------: | :----------: | :----------------------------------------------------------: |
|     PUT     | `api/categories/{id}` |   JSON   |   JSON   |      `id`      |    Model     | Application updates object with given id with new model. Then returns updated model. |

##### Response protocol

| code |                 message                 |      content      |
| :--: | :-------------------------------------: | :---------------: |
| XxX0 |                   OK                    | `<Updated model>` |
| AcX1 |    Category does not belong to user     |       null        |
| XxX4 |      Start time is before end time      |       null        |
| XxX5 |  Duration has to be greater than zero   |       null        |
| XxX2 | Object with given id not found for user |       null        |

##### Sample request

```http

```

##### Sample response

```json

```

#### `api/categories/{id}` - DELETE

| HTTP Method |         Value         | Consumes | Produces | Path Variables | Request Body |                Happy path                 |
| :---------: | :-------------------: | :------: | :------: | :------------: | :----------: | :---------------------------------------: |
|   DELETE    | `api/categories/{id}` |    -     |    -     |      `id`      |      -       | Application deletes object with given id. |

##### Response protocol

| code |                 message                 | content |
| :--: | :-------------------------------------: | :-----: |
| XxX0 |                   OK                    |  null   |
| XxX2 | Object with given id not found for user |  null   |

##### Sample request

```http

```

##### Sample response

```json

```
### Model

#### `api/models/` - GET

| HTTP Method |    Value     | Consumes | Produces | Path Variables | Request Body |             Happy path              |
| :---------: | :----------: | :------: | :------: | :------------: | :----------: | :---------------------------------: |
|     GET     | `api/models` |    -     |   JSON   |       -        |      -       | Application returns list of models. |

##### Response protocol

| code |     message     |      content       |
| :--: | :-------------: | :----------------: |
| XxX0 |       OK        | `<List of Models>` |
| XxL1 | "List is empty" |        null        |

##### Sample request

```http

```

##### Sample response

```json

```

#### `api/models/` - POST

| HTTP Method |    Value     | Consumes | Produces | Path Variables | Request Body |                          Happy path                          |
| :---------: | :----------: | :------: | :------: | :------------: | :----------: | :----------------------------------------------------------: |
|    POST     | `api/models` |   JSON   |   JSON   |       -        |    Model     | Application saves new model to database and returns it in response. |

##### Response protocol

| code |               message                |      content      |
| :--: | :----------------------------------: | :---------------: |
| XxX0 |                  OK                  | `<Created Model>` |
| AcX1 |   Category does not belong to user   |       null        |
| XxX4 |    Start time is before end time     |       null        |
| XxX5 | Duration has to be greater than zero |       null        |

##### Sample request

```http

```

##### Sample response

```json

```

#### `api/models/{id}` - GET

| HTTP Method |       Value       | Consumes | Produces | Path Variables | Request Body |                      Happy path                      |
| :---------: | :---------------: | :------: | :------: | :------------: | :----------: | :--------------------------------------------------: |
|     GET     | `api/models/{id}` |    -     |   JSON   |      `id`      |      -       | Application returns model with given id in response. |

##### Response protocol

| code |                 message                 |    content     |
| :--: | :-------------------------------------: | :------------: |
| XxX0 |                   OK                    | `<Read Model>` |
| XxX2 | Object with given id not found for user |      null      |

##### Sample request

```http

```

##### Sample response

```json

```

#### `api/models/{id}` - PUT

| HTTP Method |       Value       | Consumes | Produces | Path Variables | Request Body |                          Happy path                          |
| :---------: | :---------------: | :------: | :------: | :------------: | :----------: | :----------------------------------------------------------: |
|     PUT     | `api/models/{id}` |   JSON   |   JSON   |      `id`      |    Model     | Application updates object with given id with new model. Then returns updated model. |

##### Response protocol

| code |                 message                 |      content      |
| :--: | :-------------------------------------: | :---------------: |
| XxX0 |                   OK                    | `<Updated model>` |
| AcX1 |    Category does not belong to user     |       null        |
| XxX4 |      Start time is before end time      |       null        |
| XxX5 |  Duration has to be greater than zero   |       null        |
| XxX2 | Object with given id not found for user |       null        |

##### Sample request

```http

```

##### Sample response

```json

```

#### `api/models/{id}` - DELETE

| HTTP Method |       Value       | Consumes | Produces | Path Variables | Request Body |                Happy path                 |
| :---------: | :---------------: | :------: | :------: | :------------: | :----------: | :---------------------------------------: |
|   DELETE    | `api/models/{id}` |    -     |    -     |      `id`      |      -       | Application deletes object with given id. |

##### Response protocol

| code |                 message                 | content |
| :--: | :-------------------------------------: | :-----: |
| XxX0 |                   OK                    |  null   |
| XxX2 | Object with given id not found for user |  null   |

##### Sample request

```http

```

##### Sample response

```json

```

### Model

#### `api/models/` - GET

| HTTP Method |    Value     | Consumes | Produces | Path Variables | Request Body |             Happy path              |
| :---------: | :----------: | :------: | :------: | :------------: | :----------: | :---------------------------------: |
|     GET     | `api/models` |    -     |   JSON   |       -        |      -       | Application returns list of models. |

##### Response protocol

| code |     message     |      content       |
| :--: | :-------------: | :----------------: |
| XxX0 |       OK        | `<List of Models>` |
| XxL1 | "List is empty" |        null        |

##### Sample request

```http

```

##### Sample response

```json

```

#### `api/models/` - POST

| HTTP Method |    Value     | Consumes | Produces | Path Variables | Request Body |                          Happy path                          |
| :---------: | :----------: | :------: | :------: | :------------: | :----------: | :----------------------------------------------------------: |
|    POST     | `api/models` |   JSON   |   JSON   |       -        |    Model     | Application saves new model to database and returns it in response. |

##### Response protocol

| code |               message                |      content      |
| :--: | :----------------------------------: | :---------------: |
| XxX0 |                  OK                  | `<Created Model>` |
| AcX1 |   Category does not belong to user   |       null        |
| XxX4 |    Start time is before end time     |       null        |
| XxX5 | Duration has to be greater than zero |       null        |

##### Sample request

```http

```

##### Sample response

```json

```

#### `api/models/{id}` - GET

| HTTP Method |       Value       | Consumes | Produces | Path Variables | Request Body |                      Happy path                      |
| :---------: | :---------------: | :------: | :------: | :------------: | :----------: | :--------------------------------------------------: |
|     GET     | `api/models/{id}` |    -     |   JSON   |      `id`      |      -       | Application returns model with given id in response. |

##### Response protocol

| code |                 message                 |    content     |
| :--: | :-------------------------------------: | :------------: |
| XxX0 |                   OK                    | `<Read Model>` |
| XxX2 | Object with given id not found for user |      null      |

##### Sample request

```http

```

##### Sample response

```json

```

#### `api/models/{id}` - PUT

| HTTP Method |       Value       | Consumes | Produces | Path Variables | Request Body |                          Happy path                          |
| :---------: | :---------------: | :------: | :------: | :------------: | :----------: | :----------------------------------------------------------: |
|     PUT     | `api/models/{id}` |   JSON   |   JSON   |      `id`      |    Model     | Application updates object with given id with new model. Then returns updated model. |

##### Response protocol

| code |                 message                 |      content      |
| :--: | :-------------------------------------: | :---------------: |
| XxX0 |                   OK                    | `<Updated model>` |
| AcX1 |    Category does not belong to user     |       null        |
| XxX4 |      Start time is before end time      |       null        |
| XxX5 |  Duration has to be greater than zero   |       null        |
| XxX2 | Object with given id not found for user |       null        |

##### Sample request

```http

```

##### Sample response

```json

```

#### `api/models/{id}` - DELETE

| HTTP Method |       Value       | Consumes | Produces | Path Variables | Request Body |                Happy path                 |
| :---------: | :---------------: | :------: | :------: | :------------: | :----------: | :---------------------------------------: |
|   DELETE    | `api/models/{id}` |    -     |    -     |      `id`      |      -       | Application deletes object with given id. |

##### Response protocol

| code |                 message                 | content |
| :--: | :-------------------------------------: | :-----: |
| XxX0 |                   OK                    |  null   |
| XxX2 | Object with given id not found for user |  null   |

##### Sample request

```http

```

##### Sample response

```json

```

### Model

#### `api/models/` - GET

| HTTP Method |    Value     | Consumes | Produces | Path Variables | Request Body |             Happy path              |
| :---------: | :----------: | :------: | :------: | :------------: | :----------: | :---------------------------------: |
|     GET     | `api/models` |    -     |   JSON   |       -        |      -       | Application returns list of models. |

##### Response protocol

| code |     message     |      content       |
| :--: | :-------------: | :----------------: |
| XxX0 |       OK        | `<List of Models>` |
| XxL1 | "List is empty" |        null        |

##### Sample request

```http

```

##### Sample response

```json

```

#### `api/models/` - POST

| HTTP Method |    Value     | Consumes | Produces | Path Variables | Request Body |                          Happy path                          |
| :---------: | :----------: | :------: | :------: | :------------: | :----------: | :----------------------------------------------------------: |
|    POST     | `api/models` |   JSON   |   JSON   |       -        |    Model     | Application saves new model to database and returns it in response. |

##### Response protocol

| code |               message                |      content      |
| :--: | :----------------------------------: | :---------------: |
| XxX0 |                  OK                  | `<Created Model>` |
| AcX1 |   Category does not belong to user   |       null        |
| XxX4 |    Start time is before end time     |       null        |
| XxX5 | Duration has to be greater than zero |       null        |

##### Sample request

```http

```

##### Sample response

```json

```

#### `api/models/{id}` - GET

| HTTP Method |       Value       | Consumes | Produces | Path Variables | Request Body |                      Happy path                      |
| :---------: | :---------------: | :------: | :------: | :------------: | :----------: | :--------------------------------------------------: |
|     GET     | `api/models/{id}` |    -     |   JSON   |      `id`      |      -       | Application returns model with given id in response. |

##### Response protocol

| code |                 message                 |    content     |
| :--: | :-------------------------------------: | :------------: |
| XxX0 |                   OK                    | `<Read Model>` |
| XxX2 | Object with given id not found for user |      null      |

##### Sample request

```http

```

##### Sample response

```json

```

#### `api/models/{id}` - PUT

| HTTP Method |       Value       | Consumes | Produces | Path Variables | Request Body |                          Happy path                          |
| :---------: | :---------------: | :------: | :------: | :------------: | :----------: | :----------------------------------------------------------: |
|     PUT     | `api/models/{id}` |   JSON   |   JSON   |      `id`      |    Model     | Application updates object with given id with new model. Then returns updated model. |

##### Response protocol

| code |                 message                 |      content      |
| :--: | :-------------------------------------: | :---------------: |
| XxX0 |                   OK                    | `<Updated model>` |
| AcX1 |    Category does not belong to user     |       null        |
| XxX4 |      Start time is before end time      |       null        |
| XxX5 |  Duration has to be greater than zero   |       null        |
| XxX2 | Object with given id not found for user |       null        |

##### Sample request

```http

```

##### Sample response

```json

```

#### `api/models/{id}` - DELETE

| HTTP Method |       Value       | Consumes | Produces | Path Variables | Request Body |                Happy path                 |
| :---------: | :---------------: | :------: | :------: | :------------: | :----------: | :---------------------------------------: |
|   DELETE    | `api/models/{id}` |    -     |    -     |      `id`      |      -       | Application deletes object with given id. |

##### Response protocol

| code |                 message                 | content |
| :--: | :-------------------------------------: | :-----: |
| XxX0 |                   OK                    |  null   |
| XxX2 | Object with given id not found for user |  null   |

##### Sample request

```http

```

##### Sample response

```json

```



