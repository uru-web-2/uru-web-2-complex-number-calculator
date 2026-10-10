# uru-web-2-complex-number-calculator

Complex number calculator built with Java servlets for the Web 2 course at Universidad Rafael Urdaneta. It offers a JSON API and three JSP pages (sign in, sign up, dashboard).

**Note:** This repository is archived and read-only, and it is coursework.

## Features

- **Calculator API** — add, subtract, multiply and divide, for numbers in rectangular form (`/api/rectangular/...`) and polar form (`/api/polar/...`). The classes are `RectangularComplexNumber` and `PolarComplexNumber`.
- **Accounts** — `/api/sign-up`, `/api/sign-in` and `/api/sign-out`. Users are kept in an in-memory `HashMap`, so they are lost when the server restarts.
- **Pages** — `/sign-up`, `/sign-in` and `/dashboard`, served from `src/main/webapp/` with their own CSS and JavaScript.

## Build

Maven project (`complex-number-calculator`, version `1.0-SNAPSHOT`, packaged as a WAR) using Jakarta Servlet 5.0 and `org.json`, compiled for Java 8.

```
mvn clean install
```

Deploy the resulting WAR from `target/` to a Jakarta EE 9 servlet container such as Tomcat 10.

## Known issue

`ApiPolarMultiplyServlet` and `ApiPolarAddServlet` both declare the URL pattern `/api/polar/add`. Most containers refuse to deploy two servlets with the same mapping, so the multiply servlet probably needs `/api/polar/multiply`.

## License

GNU General Public License v3.0.
