# Problem Statement

You have a proxy service (a simple spring boot application) that interacts with a bunch of files.

## Part 1
Implement a metrics service that calculates the number of rows in each file repository every five minutes. Only keep the latest gathered metrics. These metrics also need to be made available through a REST endpoint. Since counting the lines in a file could be an expensive operation, the REST endpoint should use the results of the metric service.

## Part 2
Introduce a new repository that keeps track of service owner. The format of the file is:

    service1, user1
    service2, user2
    service3, user3
    service4, user4

Using the existing model class ServiceOwner, create a new repository and update metrics collection to include metrics for the new repository. You only need to implement enough of the new repository to allow for metrics collection using the service you created in Part 1.

## Part 3
Add appropriate unit tests wherever applicable.