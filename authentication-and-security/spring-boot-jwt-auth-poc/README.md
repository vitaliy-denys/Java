# Spring Security JWT Authentication

**JSON Web Token (JWT)** is used to send information that can be verified and trusted by means of a digital signature. 
It comprises a compact and URL-safe JSON object, which is cryptographically signed to verify its authenticity, 
and which can also be encrypted if the payload contains sensitive information.

JWT needs to be attached to every HTTP request so the server can authorize your users. There are some options how to send the token. 
For example, as an URL parameter or in HTTP Authorization header using the Bearer schema:
```sh
Authorization: Bearer <token string>
```

#### Main Components:
**JwtAuthenticationFilter** will be used directly for user authentication. It’ll check for username and password parameters 
from URL and calls Spring’s authentication manager to verify them.
If username and password is correct, then filter will create a JWT token and returns it in HTTP Authorization header.

**JwtAuthorizationFilter** handles all HTTP requests and checks if there is an Authorization header with correct token. 
For example, if token is not expired or if signature key is correct.
If the token is valid then filter will add authentication data into Spring’s security context.