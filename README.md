# Agregio Sale Technical Test

## Test context

Une partie du métier d'Agregio est de vendre de l'énergie sur plusieurs marchés, il y a 3 principaux marchés, celui de
la Réserve Primaire, la Réserve Secondaire et la Réserve Rapide. Sur chacun de ces marchés une offre est composée d'une
quantité d'énergie (en MW) qui sera livrée sur un "bloc" horaire (une journée de 24h pourrait contenir 8 blocs de 3
heures), et d'un prix plancher pour ce bloc horaire au-dessous duquel on ne vendra pas.

Les parcs producteurs d'électricité, de différents types (solaires, éoliens ou hydrauliques), sont capables de fournir
un certain nombre de MégaWatt pendant un bloc horaire. Pour permettre la traçabilité de la production électrique (
garantie d'origine), on doit pouvoir connaître le parc qui va produire l'électricité d'une offre.

Nous vous demandons d'implémenter les APIs permettant de créer une offre, de créer un parc, de lister les offres
proposées par Agregio pour chaque marché et d'obtenir la liste des parcs qui vendent sur un marché.

Nous attendons comme livrable, le code source du service qui réalise ces APIs et de tous les éléments que vous pourriez
considérer nécessaire. Vous avez carte blanche sur la partie technique tout en restant sur une technologie compatible
avec la JVM. Vous ne devez pas consacrer plus de 3h à cet exercice, ce qui est déjà, nous en avons conscience, un fort
investissement personnel !
Nous savons aussi que la limite de temps ne vous permettra pas de terminer l'exercice, donc nous n'attendons rien de
fini mais plutôt que cela reflète votre approche du développement.

## Technical context

### Language, frameworks and libraries

- Java 17
- Lombok to delegate boilerplate code due to java verbosity
- Spring boot 2.7.0
- Vavr for functional programming
- Hexagonal Architecture in a package implementation to save time (module implementation is better but longer)
- AssertJ for fluent test assertions
- Testcontainer for integration tests

### Database

For simplicity, the project database is wrapped in the `docker-compose.yml` file at the root of the project.

To start the database, start the docker daemon on your computer and then at the root of the project type:

```bash
docker-compose up -d
```    

In a real context, it would have been relevant to use a database migration tool like Liquibase or Flyway. However, in
order to respect the given instructions, we will exceptionally let Hibernate manage the database schema to save time.