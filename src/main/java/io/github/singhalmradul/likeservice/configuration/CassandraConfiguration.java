package io.github.singhalmradul.likeservice.configuration;

import static java.util.Arrays.asList;
import static org.springframework.data.cassandra.config.SchemaAction.CREATE_IF_NOT_EXISTS;
import static org.springframework.data.cassandra.core.cql.keyspace.CreateKeyspaceSpecification.createKeyspace;
import static org.springframework.data.cassandra.core.cql.keyspace.KeyspaceOption.DURABLE_WRITES;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.cassandra.config.AbstractCassandraConfiguration;
import org.springframework.data.cassandra.config.SchemaAction;
import org.springframework.data.cassandra.core.cql.keyspace.CreateKeyspaceSpecification;
import org.springframework.data.cassandra.core.cql.session.init.KeyspacePopulator;
import org.springframework.data.cassandra.core.cql.session.init.ResourceKeyspacePopulator;

@Configuration
public class CassandraConfiguration extends AbstractCassandraConfiguration {

    @Value("${cassandra.contactpoints:127.0.0.1}")
    private String contactPoints;

    @Value("${cassandra.port:9042}")
    private int port;

    @Override
    public SchemaAction getSchemaAction() {

        return CREATE_IF_NOT_EXISTS;
    }

    @Override
    protected String getKeyspaceName() {

        return "high_five";
    }

    @Override
    protected String getContactPoints() {

        return contactPoints;
    }

    @Override
    protected int getPort() {

        return port;
    }

    @Override
    protected List<CreateKeyspaceSpecification> getKeyspaceCreations() {

        return asList(
            createKeyspace("high_five").ifNotExists()
            .with(DURABLE_WRITES, true)
            .withSimpleReplication()
        );
    }

    @Override
    protected KeyspacePopulator keyspacePopulator() {

        return new ResourceKeyspacePopulator(new ClassPathResource("data.cql"));
    }

}