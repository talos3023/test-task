The project contains:
	storages/auth server:
		keycloak:9080 - auth server for user login, token generation and verification
		redis_db:6379 - redis pub/sub used for queue
		products_db:3200(on localhost) - products-service database
		orders_db:3100(on localhost) - order-service database
	microservices
		naming-server(eureka):8761 - naming server for microservices registration and discovery
		api-gateway:8765 - api gateway with routes and security for our edge service
		notifications-service:8300 - listens to redis and sends notifications(email,sms) to clients
		order-service:8000 - publishes to redis client order
		package-and-delivery-service:8100 - orders crud, listens to redis
		products-service:8200 - categories and products crud, listens to redis
	
each microservice containes Dockerfile and can be build by running maven install -> docker build -t name:tag . commands from microservices root directory
project can be launched by running the command docker compose up from the projects root directory
database scripts are located in DBScripts directory
swagger is on microName:port/swagger-ui.html