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

# Admin à insérer en bdd
INSERT INTO `user` (`is_admin`, `id`, `email`, `firstname`, `lastname`, `password`) VALUES (b'1', '1', 'admin.admin@yahoo.fr', 'Julie', 'Dupont', '$2a$10$lil0zp6eQyxTTjAVWvlZTupjDwTERFjUBCFZpqfuJp04C9svgLfK');
mot de passe = admin