# Spring Security Basic Authentication

**Basic authentication** is a simple authentication scheme built into the HTTP protocol. The client sends HTTP requests with 
the Authorization header that contains the word Basic, word followed by a space and a base64-encoded string username:password. 
For example, to authorize as demo / p@55w0rd the client would send: Authorization: Basic ZGVtbzpwQDU1dzByZA== 

### Note: 
Because base64 is easily decoded, Basic authentication should only be used together with other security mechanisms such as HTTPS/SSL.

##### Credentials:
GET: [localhost:8787/api/public](http://localhost:8787/api/public) - public endpoint

GET: [localhost:8787/api/private](http://localhost:8787/api/private) - username: user, password: user

GET: [localhost:8787/api/admin](http://localhost:8787/api/admin) - username: admin, password: admin