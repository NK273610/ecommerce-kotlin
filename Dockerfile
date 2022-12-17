#Runner
FROM openjdk:17-alpine
ADD build/libs/order-ecommerce.jar /app.jar

ENTRYPOINT java \
              $JAVA_OPTS \
              -Dspring.datasource.orderecommercedb.url= $spring_datasource_orderecommercedb_url \
              -Dspring.datasource.orderecommercedb.name= $spring_datasource_orderecommercedb_name \
              -Dspring.datasource.orderecommercedb.password= $spring_datasource_orderecommercedb_password \
              -Dspring.datasource.orderecommercedb.hikari.connectiontimeout= $spring_datasource_orderecommercedb_hikari_connectiontimeout \
              -Dspring.datasource.orderecommercedb.hikari.maximumpoolsize= $spring_datasource_orderecommercedb_hikari_maximumpoolsize \
              -Dspring.datasource.orderecommercedb.hikari.poolname= $spring_datasource_orderecommercedb_hikari_poolname \
              -Dspring.datasource.orderecommercedb.hikari.autocommit= $spring_datasource_orderecommercedb_hikari_autocommit \
              -Dspring_application_context_path= $spring_application_context_path \
             -jar \
             app.jar

EXPOSE 8080
