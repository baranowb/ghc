# Undertow demo app.
This is Undertow demo app. Its goals are as follow:
 - highlight how to easily jump start servlet application
 - allow participants to familiarize with API - analyze code
 - highlight different methods of HTTP request handling( forward/include ) and how those affect outcome
     - HINT: careful analysis of content and response code is warranted
 - allow participants to experiment and potentially complete tasks set forth by this example(boot) 

# Application design

This project sets up simple application with three servlets, which respond in different way to requests. Servlet names/context path are descriptive on purpose to hint function of each class.

Note that each task is more complex than previous and as such, might pose challenge that might not be feasible to finish during live session.

# Boot up

## Linux 

./gradlew clean build run

## Windows

./gradlew.bat clean build run

# Invoke

curl -v localhost:8080/ghc/[target_servlet]
