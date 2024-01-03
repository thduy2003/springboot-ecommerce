package com.thduy2003.ecommerce.config;

import com.thduy2003.ecommerce.entity.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.metamodel.EntityType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Configuration
public class MyDataRestConfig implements RepositoryRestConfigurer {
    private EntityManager entityManager;
    @Autowired
    public MyDataRestConfig(EntityManager theEntityManager) {
        entityManager = theEntityManager;
    }

    @Value("${allowed.origins}")
    private String[] theAllowedOrigins;
    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {
        HttpMethod[] theUnsupportedActions = {HttpMethod.PUT, HttpMethod.POST, HttpMethod.DELETE};


        disableHttpMethos(Product.class, config, theUnsupportedActions);
        disableHttpMethos(ProductCategory.class, config, theUnsupportedActions);
        disableHttpMethos(Country.class, config, theUnsupportedActions);
        disableHttpMethos(State.class, config, theUnsupportedActions);
        disableHttpMethos(Order.class, config, theUnsupportedActions);
        //call an internal helper method
        exposeIds(config);

//        configure cors mapping
        cors.addMapping(config.getBasePath() + "/**").allowedOrigins(theAllowedOrigins);

    }
    private void disableHttpMethos(Class theClass, RepositoryRestConfiguration config, HttpMethod[] theUnsupportedActions) {
        config.getExposureConfiguration()
                .forDomainType(theClass)
                .withItemExposure((metdata, httpMethods) -> httpMethods.disable(theUnsupportedActions))
                .withCollectionExposure((metdata, httpMethods) -> httpMethods.disable(theUnsupportedActions));
    }

    private void exposeIds(RepositoryRestConfiguration config) {
        //expose entity ids

        //get a list of all entity classes fromm the entity manager
        Set<EntityType<?>> entities = entityManager.getMetamodel().getEntities();

        //create an arrray of the entity types
        List<Class> entityClasses = new ArrayList<>();

        //get the entity types for the entities
        for(EntityType tempEntityType : entities) {
            entityClasses.add(tempEntityType.getJavaType());
        }
        //expose the entity ids for the arrray of entity/domain types
        Class[] domainTypes = entityClasses.toArray(new Class[0]);
        config.exposeIdsFor(domainTypes);
    }

}

