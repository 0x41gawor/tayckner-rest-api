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


| Path                    | HTTP method |         Action          | Docs                                                    |
| ----------------------- | :---------: | :---------------------: | ------------------------------------------------------- |
| `api/register`          |    POST     |    register new user    | [register](#apiregister)                               |
| `api/login`             |    POST     | login user (return JWT) | [login](#apilogin)                                     |
| `api/categories`        |     GET     |     list categories     | [categories - list](#apicategories---list)            |
| `api/categories`        |    POST     |     create category     | [categories - create](#apicategories---create)        |
| `api/categories/{id}`   |     GET     |      read category      | [categories - read](#apicategoriesid---read)        |
| `api/categories/{id}`   |     PUT     |     update category     | [categories - update](#apicategoriesid---update)    |
| `api/categories/{id}`   |   DELETE    |     delete category     | [categories - delete](#apicategoriesid---delete)   |
| `api/activities`        |     GET     |     list activities     | [activities- list](#apiactivities---list)             |
| `api/activities`        |    POST     |     create activity     | [activities- create](#apiactivities---create)         |
| `api/activities/{id}`   |     GET     |      read activity      | [activities- read](#apiactivitiesid---read)         |
| `api/activities/{id}`   |     PUT     |     update activity     | [activities- update](#apiactivitiesid---update)     |
| `api/activities/{id}`   |   DELETE    |     delete activity     | [activities- delete](#apiactivitiesid---delete)     |
| `api/schedules`         |     GET     |     list schedules      | [schedules- list](#apischedules---list)                |
| `api/schedules`         |    POST     |     create schedule     | [schedules- create](#apischedules---create)            |
| `api/schedules/{id}`    |     GET     |      read schedule      | [schedules- read](#apischedulesid---read)            |
| `api/schedules/{id}`    |     PUT     |     update schedule     | [schedules- update](#apischedulesid---update)       |
| `api/schedules/{id}`    |   DELETE    |     delete schedule     | [schedules- delete](#apischedulesid---delete)       |
| `api/habits`            |     GET     |       list habits       | [habits- list](#apihabits---list)                      |
| `api/habits`            |    POST     |      create habit       | [habits- create](#apihabits---create)                  |
| `api/habits/{id}`       |     GET     |       read habit        | [habits- read](#apihabitsid---read)                  |
| `api/habits/{id}`       |     PUT     |      update habit       | [habits- update](#apihabitsid---update)             |
| `api/habits/{id}`       |   DELETE    |      delete habit       | [habits- delete](#apihabitsid---delete)              |
| `api/habit-events`      |     GET     |    list habit-events    | [habit-events- list](#apihabit-events---list)          |
| `api/habit-events`      |    POST     |   create habit-event    | [habit-events- create](#apihabit-events---create)      |
| `api/habit-events/{id}` |     GET     |    read habit-event     | [habit-events- read](#apihabit-eventsid---read)     |
| `api/habit-events/{id}` |     PUT     |   update habit-event    | [habit-events- update](#apihabit-eventsid---update) |
| `api/habit-events/{id}` |   DELETE    |   delete habit-event    | [habit-events- delete](#apihabit-eventsid---delete) |

## Detailed Documentation

Format of each endpoint documentation:

- HTTP Method - HTTP Method
- Value - String value of endpoint
- Consumes - MediaType of Request Body
- Produces - MediaType of returned data
- Path Variables - Variables in value
- Request Body - Application object that will be mapped from JSON body
- Happy path - What will happen in happy path scenario

This documentation only describes responses where HTTP response status code is `200 OK`. And sample responses present the [happy paths](https://en.wikipedia.org/wiki/Happy_path#:~:text=In%20the%20context%20of%20software,no%20exceptional%20or%20error%20conditions.&text=Happy%20path%20test%20is%20a,and%20produces%20an%20expected%20output.)

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

#### `api/activities/` - LIST

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

#### `api/activities/` - CREATE

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

#### `api/activities/{id}` - READ

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

#### `api/activities/{id}` - UPDATE

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

#### `api/categories/` - LIST

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
GET /api/categories/ HTTP/1.1
Host: 127.0.0.1:8080
Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE2Mzc3NjE4MjAsImV4cCI6MTYzNzc3MTM0MiwidXNlcklkIjoyLCJ1c2VybmFtZSI6IlZpdGFtaW5Cb3kifQ.RPqZTl5HrE1mF4TcCtGs9AuaFjxhNLkQJuzNTvFW618
```

##### Sample response

```json
{
    "code": "XxX0",
    "message": "OK",
    "content": [
        {
            "id": 2,
            "name": "Work",
            "description": "Time spend on work, breaks counted in as work also",
            "color": "#ff0111",
            "user": {
                "id": 2,
                "username": "VitaminBoy",
                "password": "",
                "firstName": "Casimir",
                "lastName": "Funk",
                "email": "k.funk@biopoczta.pl"
            }
        }
    ]
}
```

#### `api/categories/` - CREATE

| HTTP Method |      Value       | Consumes | Produces | Path Variables | Request Body |                          Happy path                          |
| :---------: | :--------------: | :------: | :------: | :------------: | :----------: | :----------------------------------------------------------: |
|    POST     | `api/categories` |   JSON   |   JSON   |       -        |    Model     | Application saves new model to database and returns it in response. |

##### Response protocol

| code |                    message                     |      content      |
| :--: | :--------------------------------------------: | :---------------: |
| XxX0 |                       OK                       | `<Created Model>` |
| CaX1 | User already has a category with the same name |       null        |
| XxX3 |                 Invalid color                  |       null        |

##### Sample request

```http
POST /api/categories/ HTTP/1.1
Host: 127.0.0.1:8080
Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE2Mzc3NjE4MjAsImV4cCI6MTYzNzc3MTM0MiwidXNlcklkIjoyLCJ1c2VybmFtZSI6IlZpdGFtaW5Cb3kifQ.RPqZTl5HrE1mF4TcCtGs9AuaFjxhNLkQJuzNTvFW618
Content-Type: application/json
Content-Length: 220

{
    "id": 1,
    "name": "Work",
    "description": "Time spend on work, breaks counted in as work also",
    "color": "#ff0111",
    "user": null
}
```

##### Sample response

```json
{
    "code": "XxX0",
    "message": "OK",
    "content": {
        "id": 2,
        "name": "Work",
        "description": "Time spend on work, breaks counted in as work also",
        "color": "#ff0111",
        "user": {
            "id": 2,
            "username": "VitaminBoy",
            "password": "",
            "firstName": "Casimir",
            "lastName": "Funk",
            "email": "k.funk@biopoczta.pl"
        }
    }
}
```

#### `api/categories/{id}` - READ

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
GET /api/categories/2 HTTP/1.1
Host: 127.0.0.1:8080
Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE2Mzc3NjE4MjAsImV4cCI6MTYzNzc3MTM0MiwidXNlcklkIjoyLCJ1c2VybmFtZSI6IlZpdGFtaW5Cb3kifQ.RPqZTl5HrE1mF4TcCtGs9AuaFjxhNLkQJuzNTvFW618
```

##### Sample response

```json
{
    "code": "XxX0",
    "message": "OK",
    "content": {
        "id": 2,
        "name": "Work",
        "description": "Time spend on work, breaks counted in as work also",
        "color": "#ff0111",
        "user": {
            "id": 2,
            "username": "VitaminBoy",
            "password": "",
            "firstName": "Casimir",
            "lastName": "Funk",
            "email": "k.funk@biopoczta.pl"
        }
    }
}
```

#### `api/categories/{id}` - UPDATE

| HTTP Method |         Value         | Consumes | Produces | Path Variables | Request Body |                          Happy path                          |
| :---------: | :-------------------: | :------: | :------: | :------------: | :----------: | :----------------------------------------------------------: |
|     PUT     | `api/categories/{id}` |   JSON   |   JSON   |      `id`      |    Model     | Application updates object with given id with new model. Then returns updated model. |

##### Response protocol

| code |                    message                     |      content      |
| :--: | :--------------------------------------------: | :---------------: |
| XxX0 |                       OK                       | `<Updated model>` |
| CaX1 | User already has a category with the same name |       null        |
| XxX3 |                 Invalid color                  |       null        |
| XxX2 |    Object with given id not found for user     |       null        |

##### Sample request

```http
PUT /api/categories/2 HTTP/1.1
Host: 127.0.0.1:8080
Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE2Mzc3NjE4MjAsImV4cCI6MTYzNzc3MTM0MiwidXNlcklkIjoyLCJ1c2VybmFtZSI6IlZpdGFtaW5Cb3kifQ.RPqZTl5HrE1mF4TcCtGs9AuaFjxhNLkQJuzNTvFW618
Content-Type: application/json
Content-Length: 180

 {
        "id": 2,
        "name": "Working",
        "description": "Time spend on work. Breaks counted in as work also",
        "color": "#ffbbbb",
        "user": null
}
```

##### Sample response

```json
{
    "code": "XxX0",
    "message": "OK",
    "content": {
        "id": 2,
        "name": "Working",
        "description": "Time spend on work. Breaks counted in as work also",
        "color": "#ffbbbb",
        "user": {
            "id": 2,
            "username": "VitaminBoy",
            "password": "",
            "firstName": "Casimir",
            "lastName": "Funk",
            "email": "k.funk@biopoczta.pl"
        }
    }
}
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
DELETE /api/categories/2 HTTP/1.1
Host: 127.0.0.1:8080
Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE2Mzc3NjE4MjAsImV4cCI6MTYzNzc3MTM0MiwidXNlcklkIjoyLCJ1c2VybmFtZSI6IlZpdGFtaW5Cb3kifQ.RPqZTl5HrE1mF4TcCtGs9AuaFjxhNLkQJuzNTvFW618
```

##### Sample response

```json
{
    "code": "XxX0",
    "message": "OK",
    "content": null
}
```
### Habit

#### `api/habits/` - LIST

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
GET /api/habits/ HTTP/1.1
Host: 127.0.0.1:8080
Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE2Mzc4NzM4MjUsImV4cCI6MTYzNzg4MzM0NywidXNlcklkIjoyLCJ1c2VybmFtZSI6IlZpdGFtaW5Cb3kifQ.F2s8RdmAPCjKfolk3BMmEmAbDym2HpgS5aJI89Tvj3k
```

##### Sample response

```json
{
    "code": "XxX0",
    "message": "OK",
    "content": [
        {
            "id": 2,
            "name": "Fast food",
            "color": "#ffffff",
            "user": {
                "id": 2,
                "username": "VitaminBoy",
                "password": "",
                "firstName": "Casimir",
                "lastName": "Funk",
                "email": "k.funk@biopoczta.pl"
            }
        }
    ]
}
```

#### `api/habits/` - CREATE

| HTTP Method |    Value     | Consumes | Produces | Path Variables | Request Body |                          Happy path                          |
| :---------: | :----------: | :------: | :------: | :------------: | :----------: | :----------------------------------------------------------: |
|    POST     | `api/models` |   JSON   |   JSON   |       -        |  HabitModel  | Application saves new model to database and returns it in response. |

##### Response protocol

| code |                  message                  |      content      |
| :--: | :---------------------------------------: | :---------------: |
| XxX0 |                    OK                     | `<Created Model>` |
| HaX1 | User already has habit with the same name |       null        |
| XxX3 |               Invalid color               |       null        |

##### Sample request

```http
POST /api/habits/ HTTP/1.1
Host: 127.0.0.1:8080
Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE2Mzc4NzM4MjUsImV4cCI6MTYzNzg4MzM0NywidXNlcklkIjoyLCJ1c2VybmFtZSI6IlZpdGFtaW5Cb3kifQ.F2s8RdmAPCjKfolk3BMmEmAbDym2HpgS5aJI89Tvj3k
Content-Type: application/json
Content-Length: 117

{
            "id": 0,
            "name": "Workout",
            "color": "#fdefce",
            "user": null
}
```

##### Sample response

```json
{
    "code": "XxX0",
    "message": "OK",
    "content": {
        "id": 3,
        "name": "Workout",
        "color": "#fdefce",
        "user": {
            "id": 2,
            "username": "VitaminBoy",
            "password": "",
            "firstName": "Casimir",
            "lastName": "Funk",
            "email": "k.funk@biopoczta.pl"
        }
    }
}
```

#### `api/habits/{id}` - READ

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
GET /api/habits/2 HTTP/1.1
Host: 127.0.0.1:8080
Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE2Mzc4NzM4MjUsImV4cCI6MTYzNzg4MzM0NywidXNlcklkIjoyLCJ1c2VybmFtZSI6IlZpdGFtaW5Cb3kifQ.F2s8RdmAPCjKfolk3BMmEmAbDym2HpgS5aJI89Tvj3k
```

##### Sample response

```json
{
    "code": "XxX0",
    "message": "OK",
    "content": {
        "id": 2,
        "name": "Fast food",
        "color": "#ffffff",
        "user": {
            "id": 2,
            "username": "VitaminBoy",
            "password": "",
            "firstName": "Casimir",
            "lastName": "Funk",
            "email": "k.funk@biopoczta.pl"
        }
    }
}
```

#### `api/habits/{id}` - UPDATE

| HTTP Method |       Value       | Consumes | Produces | Path Variables | Request Body |                          Happy path                          |
| :---------: | :---------------: | :------: | :------: | :------------: | :----------: | :----------------------------------------------------------: |
|     PUT     | `api/models/{id}` |   JSON   |   JSON   |      `id`      |  HabitModel  | Application updates object with given id with new model. Then returns updated model. |

##### Response protocol

| code |                  message                  |      content      |
| :--: | :---------------------------------------: | :---------------: |
| XxX0 |                    OK                     | `<Updated model>` |
| HaX1 | User already has habit with the same name |       null        |
| XxX3 |               Invalid color               |       null        |
| XxX2 |  Object with given id not found for user  |       null        |

##### Sample request

```http
PUT /api/habits/2 HTTP/1.1
Host: 127.0.0.1:8080
Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE2Mzc4NzM4MjUsImV4cCI6MTYzNzg4MzM0NywidXNlcklkIjoyLCJ1c2VybmFtZSI6IlZpdGFtaW5Cb3kifQ.F2s8RdmAPCjKfolk3BMmEmAbDym2HpgS5aJI89Tvj3k
Content-Type: application/json
Content-Length: 105

{
        "id": 3,
        "name": "Work-out",
        "color": "#fdefce",
        "user": null
}

```

##### Sample response

```json
{
    "code": "XxX0",
    "message": "OK",
    "content": {
        "id": 2,
        "name": "Work-out",
        "color": "#fdefce",
        "user": {
            "id": 2,
            "username": "VitaminBoy",
            "password": "",
            "firstName": "Casimir",
            "lastName": "Funk",
            "email": "k.funk@biopoczta.pl"
        }
    }
}
```

#### `api/habits/{id}` - DELETE

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
DELETE /api/habits/3 HTTP/1.1
Host: 127.0.0.1:8080
Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE2Mzc4NzM4MjUsImV4cCI6MTYzNzg4MzM0NywidXNlcklkIjoyLCJ1c2VybmFtZSI6IlZpdGFtaW5Cb3kifQ.F2s8RdmAPCjKfolk3BMmEmAbDym2HpgS5aJI89Tvj3k
```

##### Sample response

```json
{
    "code": "XxX0",
    "message": "OK",
    "content": null
}
```

### Habit-Event

#### `api/habit-events/` - LIST

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
GET /api/habit-events/ HTTP/1.1
Host: 127.0.0.1:8080
Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE2Mzc4NzM4MjUsImV4cCI6MTYzNzg4MzM0NywidXNlcklkIjoyLCJ1c2VybmFtZSI6IlZpdGFtaW5Cb3kifQ.F2s8RdmAPCjKfolk3BMmEmAbDym2HpgS5aJI89Tvj3k
```

##### Sample response

```json
{
    "code": "XxX0",
    "message": "OK",
    "content": [
        {
            "id": 8,
            "habit": {
                "id": 2,
                "name": "Work-out",
                "color": "#fdefce",
                "user": {
                    "id": 2,
                    "username": "VitaminBoy",
                    "password": "",
                    "firstName": "Casimir",
                    "lastName": "Funk",
                    "email": "k.funk@biopoczta.pl"
                }
            },
            "date": "2021-11-25",
            "comment": "Pull day",
            "value": 1
        },
        {
            "id": 9,
            "habit": {
                "id": 2,
                "name": "Work-out",
                "color": "#fdefce",
                "user": {
                    "id": 2,
                    "username": "VitaminBoy",
                    "password": "",
                    "firstName": "Casimir",
                    "lastName": "Funk",
                    "email": "k.funk@biopoczta.pl"
                }
            },
            "date": "2021-11-25",
            "comment": "Push day",
            "value": 1
        }
    ]
}
```

#### `api/habit-events/` - CREATE

| HTTP Method |    Value     | Consumes | Produces | Path Variables | Request Body |                          Happy path                          |
| :---------: | :----------: | :------: | :------: | :------------: | :----------: | :----------------------------------------------------------: |
|    POST     | `api/models` |   JSON   |   JSON   |       -        |    Model     | Application saves new model to database and returns it in response. |

##### Response protocol

| code |            message            |      content      |
| :--: | :---------------------------: | :---------------: |
| XxX0 |              OK               | `<Created Model>` |
| HeX1 | Habit does not belong to user |       null        |

##### Sample request

```http
POST /api/habit-events/ HTTP/1.1
Host: 127.0.0.1:8080
Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE2Mzc4NzM4MjUsImV4cCI6MTYzNzg4MzM0NywidXNlcklkIjoyLCJ1c2VybmFtZSI6IlZpdGFtaW5Cb3kifQ.F2s8RdmAPCjKfolk3BMmEmAbDym2HpgS5aJI89Tvj3k
Content-Type: application/json
Content-Length: 194

{
   "id": 7,
   "habit": {
       "id": 2
    },
    "date": "2021-11-25",
	"comment": "Legs day",
	"value": 1
}
```

##### Sample response

```json
{
    "code": "XxX0",
    "message": "OK",
    "content": {
        "id": 10,
        "habit": {
            "id": 2,
            "name": "Work-ouit",
            "color": "#fdefce",
            "user": {
                "id": 2,
                "username": "VitaminBoy",
                "password": "",
                "firstName": "Casimir",
                "lastName": "Funk",
                "email": "k.funk@biopoczta.pl"
            }
        },
        "date": "2021-11-25",
        "comment": "Legs day",
        "value": 1
    }
}
```

#### `api/habit-events/{id}` - READ

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
GET /api/habit-events/9 HTTP/1.1
Host: 127.0.0.1:8080
Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE2Mzc4NzM4MjUsImV4cCI6MTYzNzg4MzM0NywidXNlcklkIjoyLCJ1c2VybmFtZSI6IlZpdGFtaW5Cb3kifQ.F2s8RdmAPCjKfolk3BMmEmAbDym2HpgS5aJI89Tvj3k
```

##### Sample response

```json
{
    "code": "XxX0",
    "message": "OK",
    "content": {
        "id": 9,
        "habit": {
            "id": 2,
            "name": "Work-ouit",
            "color": "#fdefce",
            "user": {
                "id": 2,
                "username": "VitaminBoy",
                "password": "",
                "firstName": "Casimir",
                "lastName": "Funk",
                "email": "k.funk@biopoczta.pl"
            }
        },
        "date": "2021-11-25",
        "comment": "Push day",
        "value": 1
    }
}
```

#### `api/habit-events/{id}` - UPDATE

| HTTP Method |       Value       | Consumes | Produces | Path Variables | Request Body |                          Happy path                          |
| :---------: | :---------------: | :------: | :------: | :------------: | :----------: | :----------------------------------------------------------: |
|     PUT     | `api/models/{id}` |   JSON   |   JSON   |      `id`      |    Model     | Application updates object with given id with new model. Then returns updated model. |

##### Response protocol

| code |                 message                 |      content      |
| :--: | :-------------------------------------: | :---------------: |
| XxX0 |                   OK                    | `<Updated model>` |
| HeX1 |      Habit does not belong to user      |       null        |
| XxX2 | Object with given id not found for user |       null        |

##### Sample request

```http
PUT /api/habit-events/9 HTTP/1.1
Host: 127.0.0.1:8080
Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE2Mzc4NzM4MjUsImV4cCI6MTYzNzg4MzM0NywidXNlcklkIjoyLCJ1c2VybmFtZSI6IlZpdGFtaW5Cb3kifQ.F2s8RdmAPCjKfolk3BMmEmAbDym2HpgS5aJI89Tvj3k
Content-Type: application/json
Content-Length: 126

{
    "id": 7,
    "habit": {
    "id": 2
    },
    "date": "2021-11-26",
    "comment": "Legs day",
    "value": 1
}
```

##### Sample response

```json
{
    "code": "XxX0",
    "message": "OK",
    "content": {
        "id": 9,
        "habit": {
            "id": 2,
            "name": "Work-out",
            "color": "#fdefce",
            "user": {
                "id": 2,
                "username": "VitaminBoy",
                "password": "",
                "firstName": "Casimir",
                "lastName": "Funk",
                "email": "k.funk@biopoczta.pl"
            }
        },
        "date": "2021-11-26",
        "comment": "Legs day",
        "value": 1
    }
}
```

#### `api/habit-events/{id}` - DELETE

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
DELETE /api/habit-events/9 HTTP/1.1
Host: 127.0.0.1:8080
Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE2Mzc4NzM4MjUsImV4cCI6MTYzNzg4MzM0NywidXNlcklkIjoyLCJ1c2VybmFtZSI6IlZpdGFtaW5Cb3kifQ.F2s8RdmAPCjKfolk3BMmEmAbDym2HpgS5aJI89Tvj3k
```

##### Sample response

```json
{
    "code": "XxX0",
    "message": "OK",
    "content": null
}
```

### Schedules

#### `api/schedules/` - LIST

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
GET /api/schedules/ HTTP/1.1
Host: 127.0.0.1:8080
Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE2Mzc4NzM4MjUsImV4cCI6MTYzNzg4MzM0NywidXNlcklkIjoyLCJ1c2VybmFtZSI6IlZpdGFtaW5Cb3kifQ.F2s8RdmAPCjKfolk3BMmEmAbDym2HpgS5aJI89Tvj3k
```

##### Sample response

```json
{
    "code": "XxX0",
    "message": "OK",
    "content": [
        {
            "id": 1,
            "name": "Leave for a train",
            "startTime": "2021-11-25T23:38:55",
            "endTime": "2021-11-25T23:38:55",
            "duration": 10,
            "user": {
                "id": 2,
                "username": "VitaminBoy",
                "password": "",
                "firstName": "Casimir",
                "lastName": "Funk",
                "email": "k.funk@biopoczta.pl"
            }
        },
        {
            "id": 2,
            "name": "Learn Confluence",
            "startTime": "2021-11-25T20:39:27",
            "endTime": "2021-11-25T22:39:27",
            "duration": 10,
            "user": {
                "id": 2,
                "username": "VitaminBoy",
                "password": "",
                "firstName": "Casimir",
                "lastName": "Funk",
                "email": "k.funk@biopoczta.pl"
            }
        }
    ]
}
```

#### `api/schedules/` - CREATE

| HTTP Method |    Value     | Consumes | Produces | Path Variables | Request Body |                          Happy path                          |
| :---------: | :----------: | :------: | :------: | :------------: | :----------: | :----------------------------------------------------------: |
|    POST     | `api/models` |   JSON   |   JSON   |       -        |    Model     | Application saves new model to database and returns it in response. |

##### Response protocol

| code |               message                |      content      |
| :--: | :----------------------------------: | :---------------: |
| XxX0 |                  OK                  | `<Created Model>` |
| XxX4 |    Start time is before end time     |       null        |
| XxX5 | Duration has to be greater than zero |       null        |

##### Sample request

```http
POST /api/schedules/ HTTP/1.1
Host: 127.0.0.1:8080
Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE2Mzc4NzM4MjUsImV4cCI6MTYzNzg4MzM0NywidXNlcklkIjoyLCJ1c2VybmFtZSI6IlZpdGFtaW5Cb3kifQ.F2s8RdmAPCjKfolk3BMmEmAbDym2HpgS5aJI89Tvj3k
Content-Type: application/json
Content-Length: 171

{
    "id": 1,
    "name": "Leave for a train",
    "startTime": "2021-11-25T23:38:55",
    "endTime": "2021-11-25T23:38:55",
    "duration": 10,
    "user": null
}
```

##### Sample response

```json
{
    "code": "XxX0",
    "message": "OK",
    "content": {
        "id": 3,
        "name": "Leave for a train",
        "startTime": "2021-11-25T23:38:55",
        "endTime": "2021-11-25T23:38:55",
        "duration": 10,
        "user": {
            "id": 2,
            "username": "VitaminBoy",
            "password": "",
            "firstName": "Casimir",
            "lastName": "Funk",
            "email": "k.funk@biopoczta.pl"
        }
    }
}
```

#### `api/schedules/{id}` - READ

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
GET /api/schedules/2 HTTP/1.1
Host: 127.0.0.1:8080
Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE2Mzc4NzM4MjUsImV4cCI6MTYzNzg4MzM0NywidXNlcklkIjoyLCJ1c2VybmFtZSI6IlZpdGFtaW5Cb3kifQ.F2s8RdmAPCjKfolk3BMmEmAbDym2HpgS5aJI89Tvj3k
```

##### Sample response

```json
{
    "code": "XxX0",
    "message": "OK",
    "content": {
        "id": 2,
        "name": "Learn Confluence",
        "startTime": "2021-11-25T23:39:27",
        "endTime": "2021-11-25T23:39:27",
        "duration": 10,
        "user": {
            "id": 2,
            "username": "VitaminBoy",
            "password": "",
            "firstName": "Casimir",
            "lastName": "Funk",
            "email": "k.funk@biopoczta.pl"
        }
    }
}
```

#### `api/schedules/{id}` - UPDATE

| HTTP Method |       Value       | Consumes | Produces | Path Variables | Request Body |                          Happy path                          |
| :---------: | :---------------: | :------: | :------: | :------------: | :----------: | :----------------------------------------------------------: |
|     PUT     | `api/models/{id}` |   JSON   |   JSON   |      `id`      |    Model     | Application updates object with given id with new model. Then returns updated model. |

##### Response protocol

| code |                 message                 |      content      |
| :--: | :-------------------------------------: | :---------------: |
| XxX0 |                   OK                    | `<Updated model>` |
| XxX4 |      Start time is before end time      |       null        |
| XxX5 |  Duration has to be greater than zero   |       null        |
| XxX2 | Object with given id not found for user |       null        |

##### Sample request

```http
PUT /api/schedules/2 HTTP/1.1
Host: 127.0.0.1:8080
Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE2Mzc4NzM4MjUsImV4cCI6MTYzNzg4MzM0NywidXNlcklkIjoyLCJ1c2VybmFtZSI6IlZpdGFtaW5Cb3kifQ.F2s8RdmAPCjKfolk3BMmEmAbDym2HpgS5aJI89Tvj3k
Content-Type: application/json
Content-Length: 195

{
    "id": 2,
    "name": "Learn Confluence and Enterprise Atchitect",
    "startTime": "2021-11-25T23:39:27",
    "endTime": "2021-11-25T23:39:27",
    "duration": 10,
    "u
```

##### Sample response

```json
{
    "code": "XxX0",
    "message": "OK",
    "content": {
        "id": 2,
        "name": "Learn Confluence and Enterprise Atchitect",
        "startTime": "2021-11-25T23:39:27",
        "endTime": "2021-11-25T23:39:27",
        "duration": 10,
        "user": {
            "id": 2,
            "username": "VitaminBoy",
            "password": "$2a$10$z5N01GRC/5Jb6eGWD..aLeY4L1hIs87mnF71pBwpNKj4vOMzWxW.a",
            "firstName": "Casimir",
            "lastName": "Funk",
            "email": "k.funk@biopoczta.pl"
        }
    }
}
```

#### `api/schedules/{id}` - DELETE

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
DELETE /api/schedules/1 HTTP/1.1
Host: 127.0.0.1:8080
Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE2Mzc4NzM4MjUsImV4cCI6MTYzNzg4MzM0NywidXNlcklkIjoyLCJ1c2VybmFtZSI6IlZpdGFtaW5Cb3kifQ.F2s8RdmAPCjKfolk3BMmEmAbDym2HpgS5aJI89Tvj3k
```

##### Sample response

```json
{
    "code": "XxX0",
    "message": "OK",
    "content": null
}
```



