# Adobe & AEM Engineering Test

This project is meant to serve as a coding assessment. The challenge of this assignment is to create a basic web api that takes in an Arabic Number and convert it to a Roman Numeral. 

## Code Layout

The main parts of the template are:

* src/main/java/com/vkersey/App - spins up a basic webserver that accepts requests. Default requests are sent to the RootHandler, while requests made to /stop are sent to the StopHandler, and requests made to /romannumeral are sent to RomanNumeralHandler.
* src/main/java/com/vkersey/views - location to store all the different Handler files.
* src/main/java/com/vkersey/models - location to store the class that actually converts arabic numbers to roman numerals.

## How to build

### Maven/Java

To build all the files into a single jar, in the project root directory run the following command with Maven 3:

    mvn clean package

To run the webserver, in the project root directory run the following command using Java 8:

    java -jar target/romannumeral-1.0-SNAPSHOT-jar-with-dependencies.jar

### Docker

To build a docker image, in the project root directory run the following command to build a docker image:
    
    docker build -t roman_numeral_webserver .

To run the newly created docker image run the following command:

    docker run -d -p 8080:8080 roman_numeral_webserver

## Unit tests

This show-cases classic unit testing of the code contained in the bundle. To
test, execute:

    mvn clean test

## Methodology

I considered node originally for setting up the web server but chose to stick just with Java because I haven't had an opportunity to set up a pure Java web server before. 
This was pretty simple using [HttpServer](https://docs.oracle.com/javase/8/docs/jre/api/net/httpserver/spec/com/sun/net/httpserver/HttpServer.html).
HttpServer requires setting up [HttpHandler](https://docs.oracle.com/javase/8/docs/jre/api/net/httpserver/spec/com/sun/net/httpserver/HttpHandler.html) to handle the request. I decided to add 
some basic html to the RootHandler to add a friendly ui to reach the other two endpoints. I also felt it was necessary to set up an endpoint to stop the running server without having to kill it through the terminal.
I restricted the functionality within RomanNumeralHandler to just handle the request and the response,
while shifting the logic for converting the Arabic Number into Roman Numerals to a RomanNumeral class. The algorithm I used to convert Arabic Numbers to Roman Numerals is similar to how change
is figured on a monetary transaction where you start by handing back the maximum number of the largest domination of currency and work your way to the smallest. 
    
    Example: $7.15 in change would require: 
    1x$5.00 
    2x$1.00 
    0x$0.25 
    1x$0.10 
    1x$0.05 
    0x$0.01

For unit testing I used junit, jupiter, and mockito, this is what I've used before and was pretty simple to reimplement here and gave me the freedom to mock the 
HttpExchange and monitor what data is being set. I strived to find all the edge cases in the unit testing for the RomanNumeral class because this file holds all the logic around the conversion. I would have liked to explore
a way to use integrated testing to test the different handlers better and do more black-box-testing on the implementation.

## Maven Dependencies
* gson - Easily create and modify Json
* log4j - Better logging
* junit
* junit-jupiter-api
* mockito-core
* mockito-junit-jupiter

## Reference Material
https://byjus.com/maths/roman-numerals/ - Specification for Roman numerals  
https://www.codeproject.com/Tips/1040097/Create-a-Simple-Web-Server-in-Java-HTTP-Server - Simple Java Web Server  
https://www.calculatorsoup.com/calculators/conversions/roman-numeral-converter.php - Used to test the Conversions  

## Author
[Victor Kersey](https://www.linkedin.com/in/vkersey/) 

