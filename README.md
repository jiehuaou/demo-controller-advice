## Environment:
- Java version: 1.8
- Maven version: 3.*
- Spring Boot version: 2.2.1.RELEASE

## Read-Only Files:
- src/test/*

## Requirements:

use **@RestControllerAdvice** to customize the exception output.
```java
@RestControllerAdvice
public class FizzBuzzExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(FizzException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    protected GlobalError handleFizzException(FizzException ex, WebRequest request) {
        return new GlobalError("Fizz Exception has been thrown",
                "Internal Server Error");
    }
    @ExceptionHandler(BuzzException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    protected GlobalError handlebuzzException(BuzzException ex, WebRequest request){
        return new GlobalError("Buzz Exception has been thrown",
                "Bad Request");
    }
}
```

Given a rest controller class `FizzBuzzController.java`, there is a `GET` API endpoint to be modified, which can throw three types of runtime exceptions:
1. FizzException
2. BuzzException
3. FizzBuzzException 

Below is the endpoint for the `GET` API endpoint with a single path parameter.

`GET /controller_advice/{code}`

Exceptions are to be thrown based on the value of the path param `{code}` passed to the rest API.

Here is a series of requests and their corresponding expected responses:

`GET /controller_advice/fizz`:

Response Code: 500

Response Body:
```json
{
    "message": "Fizz Exception has been thrown",
    "errorReason" : "Internal Server Error"
}
```

`GET /controller_advice/buzz`:

Response Code: 400

Response Body:
```json
{
    "message": "Buzz Exception has been thrown",
    "errorReason" : "Bad Request"
}
```

`GET /controller_advice/fizzbuzz`:

Response Code: 507

Response Body:
```json
{
    "message": "FizzBuzz Exception has been thrown",
    "errorReason" : "Insufficient Storage"
}
```

`GET /controller_advice/success`:

Response Code: 200

Response Body:
```json
{
    "message": "Successfully completed fizzbuzz test",
    "statusCode": "200"
}
```

Your task is to complete the given project so that it passes all the test cases when running the provided unit tests. 
For that, you have to implement a controller advice global exception handler, intercept the runtime exceptions thrown from the GET API endpoint, and return a response entity wrapped in the `GlobalError.java` class.

The runtime exceptions and error response classes are already predefined in the project. You just need to complete the implementation `controller` GET API endpoint and the `controller advice` classes.

## Commands
- run: 
```bash
mvn clean package; java -jar target/RestControllerAdvice-1.0.jar
```
- install: 
```bash
mvn clean install
```
- test: 
```bash
mvn clean test
```

# heavy way to test controller
the full Spring application context is started but without the server.
```java
@SpringBootTest
@AutoConfigureMockMvc
public class HelloControllerHeavyTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldReturnDefaultMessage()  {
        this.mockMvc.perform(get("/hello/333"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Hello, 333")));
    }
}
```

# light way to test controller
only load web layer, but you need to inject the dependency bean.
```java
@WebMvcTest(controllers = {HelloController.class})
public class HelloControllerLightTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private HelloService helloService; // controller depends on this service

    @Test
    public void shouldReturnDefaultMessage()  {
        // when
        Mockito.when(helloService.greet("333")).thenReturn("Hello, 333");
        // then
        this.mockMvc.perform(get("/hello/333"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Hello, 333")));
    }
}
```