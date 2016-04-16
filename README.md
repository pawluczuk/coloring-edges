# coloring-edges
Web app for coloring edges visualization

# how to run
to run application use command

    mvn clean spring-boot:run

or use Spring Boot builder

after build go to
    http://localhost:8080/upload
and upload txt file filed with edges made of source node and destination node
divided by white space and new edges divided by new line as such:

    1 2

    2 3

    3 1

    2 4

# unit tests
    unit tests stand for user stories to be executed on graph object