# Spring Security JWT Authentication

**JSON Web Token (JWT)** is used to send information that can be verified and trusted by means of a digital signature. 
It comprises a compact and URL-safe JSON object, which is cryptographically signed to verify its authenticity, 
and which can also be encrypted if the payload contains sensitive information.

JWT needs to be attached to every HTTP request so the server can authorize your users. There are some options how to send the token. 
For example, as an URL parameter or in HTTP Authorization header using the Bearer schema:
```sh
Authorization: Bearer <token string>
```