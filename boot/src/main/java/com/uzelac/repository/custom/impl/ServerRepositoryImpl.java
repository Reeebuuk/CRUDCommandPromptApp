package com.uzelac.repository.custom.impl;

import com.uzelac.model.db.QServer;
import com.uzelac.repository.custom.ServerRepositoryCustom;
import com.mysema.query.jpa.impl.JPAQuery;
import org.springframework.data.jpa.repository.support.QueryDslRepositorySupport;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigInteger;
import java.util.List;

public class ServerRepositoryImpl extends QueryDslRepositorySupport implements ServerRepositoryCustom
{
    public ServerRepositoryImpl(Class<?> domainClass)
    {
        super(domainClass);
    }

    public ServerRepositoryImpl()
    {
        this(QServer.class);
    }

    private QServer server = QServer.server;

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<String> findDistinctServerNames(String serverNamePart)
    {
        JPAQuery query = new JPAQuery(em).from(server);

        return query.where(server.name.containsIgnoreCase(serverNamePart)).distinct().list(server.name);
    }

    @Override
    public BigInteger countDistinctServerNames()
    {
        return (BigInteger) em.createNativeQuery("SELECT COUNT(DISTINCT(name)) FROM server").getSingleResult();
    }

}
