# Application Amperus

## démarrer l'application via Maven.

avec le profil "prod" activé

```bash
mvnw spring-boot:run -Dspring.profiles.active=prod
```

Cette commande est utile pour des tests locaux avec les configurations de production.

## Executer à partir d'un fichier jar

construire le JAR avec Maven

```bash
mvnw clean package
```

puis exécutez-le en spécifiant le profil.

```bash
java -jar -Dspring.profiles.active=prod target/apirnc-1.0.0-SNAPSHOT.jar
```