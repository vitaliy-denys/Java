# Spring Security Digest Authentication

**Digest authentication** is one of the agreed-upon methods a web server can use to negotiate credentials, 
such as username or password, with a user's web browser. This can be used to confirm the identity of a user before sending sensitive information, 
such as online banking transaction history. It applies a hash function to the username and password before sending them over the network. 
In contrast, basic access authentication uses the easily reversible Base64 encoding instead of encryption, making it non-secure unless used in conjunction with TLS.
Technically, digest authentication is an application of MD5 cryptographic hashing with usage of nonce values to prevent replay attacks. It uses the HTTP protocol.
                          
                          
##### Credentials:
GET: [localhost:8787/api/public](http://localhost:8787/api/public) - public endpoint

GET: [localhost:8787/api/private](http://localhost:8787/api/private) - username: user, password: user

GET: [localhost:8787/api/admin](http://localhost:8787/api/admin) - username: admin, password: admin