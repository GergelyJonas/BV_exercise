# BV_exercise

This small web application can be used to send the connected browsers messages.

## Prerequisites

- You need to have a running mysql database. The credentials and url can be configured in application.properties.
- You need to have JDK8 installed and set.

Note: The project comes with maven wrapper thus installing maven is optional.

## Usage

The server listens on port 8080. The default page is mapped to the root. After loading the page it opens a websocket to the server and listens for messages from the server.

The endpoint that can be used to send messages is /message/send. It accepts POST requests with json content type. Example:

{
	"text" : "Hello World!"
}

## Running the application

The application can be run by executing mvnw spring-boot:run
