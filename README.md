# Pour lancer l'application
mvn spring-boot:run

# Pour que la base de données fonctionne
Ajuster les properties dans application.properties


# Pour mettre à jour les dépendances
mvn dependency:purge-local-repository
mvn clean install

# Pour récupérer les dernières versions des dépendances
mvn versions:use-latest-versions

# Pour nettoyer le cache
mvn dependency:purge-local-repository



