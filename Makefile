
test:
	./gradlew test

prepareLocal:
	docker-compose -f mongo-compose.yml up -d

runLocal: prepareLocal
	./gradlew bootRun
