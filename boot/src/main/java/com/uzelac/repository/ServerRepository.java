package com.uzelac.repository;

import com.uzelac.model.db.Server;
import com.uzelac.repository.custom.ServerRepositoryCustom;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServerRepository extends CrudRepository<Server, Integer>, ServerRepositoryCustom
{
}
