# Codetest application for H&M

Sorting integers has never been this easy. Well kind of. Only handles integers
between 0 and 100 (not inclusive). Because we know this about the input
integers we can be smarter in sorting and are using a radix sort with fixed
number of iterations and buckets. This gives us a time complexity of O(n + k)
which is pretty cool!

This application developed as a code test for H&M and was timeboxed to around two hours.

## Building the application
```
./gradlew build
```

## Running the application

The application was developed on a linux environment and had a requirement for
persistent storage without using an external databse. This was solved using a
H2 database which is writing to a file. If this application is to run on a
Windows computer the file which the database is stored at might need to be
updated with a path which works on Windows. This configuration can be found
here `src/main/resources/application.properties` in the property
`spring.datasource.url`.

```
./gradlew bootRun
```
