build-api:
	./mvnw clean verify

start-api-dev: build-api
	./mvnw spring-boot:run -Dspring-boot.run.profiles=dev

start-api-staging: build-api
	./mvnw spring-boot:run -Dspring-boot.run.profiles=staging

stop-db:
	docker-compose down

start-db:
	docker-compose up -d

start-dev:start-db build-api start-api-dev

start-staging:start-db build-api start-api-staging




