build:
	./mvnw clean verify

start-local:build
	./mvnw spring-boot:run -Dspring-boot.run.profiles=dev

build:
	mvn clean verify

stop:
	docker-compose down

up:
	docker-compose up -d

start-db: up