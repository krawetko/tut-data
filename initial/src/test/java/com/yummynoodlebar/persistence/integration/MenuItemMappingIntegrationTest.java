package com.yummynoodlebar.persistence.integration;

import com.mongodb.Mongo;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

import static com.yummynoodlebar.persistence.domain.fixture.MongoAssertions.usingMongo;
import static com.yummynoodlebar.persistence.domain.fixture.PersistenceFixture.standardItem;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by krawetko on 28/08/14.
 */
public class MenuItemMappingIntegrationTest {
    MongoOperations mongo;

    @Before
    public void setup() throws Exception {
        mongo = new MongoTemplate(new SimpleMongoDbFactory(new Mongo(), "yummynoodle"));

        mongo.dropCollection("menu");
    }

    @After
    public void teardown() {
        mongo.dropCollection("menu");
    }

    @Test
    public void thatItemIsInsertedIntoCollectionHasCorrectIndexes() throws Exception {

        mongo.insert(standardItem());

        assertEquals(1, mongo.getCollection("menu").count());

        assertTrue(usingMongo(mongo).collection("menu").hasIndexOn("_id"));
        assertTrue(usingMongo(mongo).collection("menu").hasIndexOn("itemName"));
    }

    @Test
    public void thatItemCustomMappingWorks() throws Exception {
        mongo.insert(standardItem());

        assertTrue(usingMongo(mongo).collection("menu").first().hasField("itemName"));
    }
}
