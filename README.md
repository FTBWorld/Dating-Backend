# Dating app backend (Java Spring)

## Development

### Java version

Use Java 11, 64-bit.

### Postgres

To setup the database (do this when setting up the project for the first time or after pulling changes. This will also reset the DB):

`psql -U postgres -f dating_db.sql`

### Maven

Make sure to sync Maven dependencies by opening `pom.xml` and clicking the sync button in IntelliJ.

## Info

### How likes work

A like is represented with a `user_id` (user who liked someone) and `liked_user` (the "someone").

If user A likes user B and user B likes user A, there is a "match" between user A and B. 