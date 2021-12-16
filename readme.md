## Datasource settings in application.properties

#### Important: Set to your own local settings!!

Current settings:
* postgresql database on //localhost:5432
* database: springboot
* user/owner: springboot
* password: springboot

#### Seeded users

* admin - password
* user - password

### Endpoints

#### books
* GET /api/v1/books
* POST /api/v1/books
* DELETE /api/v1/books/{id}
* GET /api/v1/books/{id}
* GET /api/v1/books/{id}/copies
* PATCH /api/v1/books/{id}/copies
* PATCH /api/v1/books/{id}/description

#### borrowedCopies
* GET /api/v1/borrowedCopies
* POST /api/v1/borrowedCopies
* DELETE /api/v1/borrowedCopies/{id}
* GET /api/v1/borrowedCopies/{id}

#### copies
* GET /api/v1/copies
* POST /api/v1/copies
* DELETE /api/v1/copies/{id}
* GET /api/v1/copies/{id}

#### members
* GET /api/v1/members
* POST /api/v1/members
* DELETE /api/v1/members/{id}
* GET /api/v1/members/{id}
* GET /api/v1/members/{id}/borrowed
* PATCH /api/v1/members/{id}/borrowed
* DELETE /api/v1/members/{id}/borrowed/{borrowedCopyId}

#### users
* GET /api/v1/users
* POST /api/v1/users
* DELETE /api/v1/users/{username}
* GET /api/v1/users/{username}
* PUT /api/v1/users/{username}
* GET /api/v1/users/{username}/authorities
* POST /api/v1/users/{username}/authorities
* DELETE /api/v1/users/{username}/authorities/{authority}
* PATCH /api/v1/users/{username}/password

#### A Postman export has been included in the documentation directory.

