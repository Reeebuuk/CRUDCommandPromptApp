package com.uzelac.model.db;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QServer is a Querydsl query type for Server
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QServer extends EntityPathBase<Server> {

    private static final long serialVersionUID = 1759769283L;

    public static final QServer server = new QServer("server");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath name = createString("name");

    public QServer(String variable) {
        super(Server.class, forVariable(variable));
    }

    public QServer(Path<? extends Server> path) {
        super(path.getType(), path.getMetadata());
    }

    public QServer(PathMetadata<?> metadata) {
        super(Server.class, metadata);
    }

}

