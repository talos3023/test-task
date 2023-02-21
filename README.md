The project contains:
<br />&emsp;storages/auth server:
<br />&emsp;&emsp;keycloak:9080 - auth server for user login, token generation and verification
<br />&emsp;&emsp;redis_db:6379 - redis pub/sub used for queue
<br />&emsp;&emsp;products_db:3200(on localhost) - products-service database
<br />&emsp;&emsp;orders_db:3100(on localhost) - order-service database
<br />&emsp;microservices
<br />&emsp;&emsp;naming-server(eureka):8761 - naming server for microservices registration and discovery
<br />&emsp;&emsp;api-gateway:8765 - api gateway with routes and security for our edge service
<br />&emsp;&emsp;notifications-service:8300 - listens to redis and sends notifications(email,sms) to clients
<br />&emsp;&emsp;order-service:8000 - publishes to redis client order
<br />&emsp;&emsp;package-and-delivery-service:8100 - orders crud, listens to redis
<br />&emsp;&emsp;products-service:8200 - categories and products crud, listens to redis

<br />each microservice containes Dockerfile and can be build by running maven install -> docker build -t name:tag . commands from microservices root directory
<br />project can be launched by running the command docker compose up from the projects root directory
<br />database scripts are located in DBScripts directory
<br />swagger is on microName:port/swagger-ui.html