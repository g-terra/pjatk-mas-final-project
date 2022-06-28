build:
	./mvnw clean verify

start-local:build
	./mvnw spring-boot:run -Dspring-boot.run.profiles=dev

build:
	mvn clean verify

stop-db:
	docker-compose down

start-db:
	docker-compose up -d
