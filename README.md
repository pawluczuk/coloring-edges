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

providing a line such as
    
    Color: 234

within the upload file will define the number of colors to be used while
coloring the graph. if such line will not be encountered within file, the
assumed number of colors used will be the degree of a node with the highest one

# unit tests
    unit tests stand for user stories to be executed on graph object