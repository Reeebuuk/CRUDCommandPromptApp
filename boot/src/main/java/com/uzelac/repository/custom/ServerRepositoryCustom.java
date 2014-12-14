package com.uzelac.repository.custom;

import java.math.BigInteger;
import java.util.List;

public interface ServerRepositoryCustom
{
    public List<String> findDistinctServerNames(String serverNamePart);

    public BigInteger countDistinctServerNames();
}
