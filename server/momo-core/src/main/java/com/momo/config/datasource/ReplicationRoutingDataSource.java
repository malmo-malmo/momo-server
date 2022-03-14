package com.momo.config.datasource;

import static java.util.stream.Collectors.toList;

import java.util.List;
import java.util.Map;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.transaction.support.TransactionSynchronizationManager;

public class ReplicationRoutingDataSource extends AbstractRoutingDataSource {

    private CircularList<String> dataSourceNameList;

    @Override
    public void setTargetDataSources(Map<Object, Object> targetDataSources) {
        super.setTargetDataSources(targetDataSources);

        List<String> slaveNames = targetDataSources.keySet()
            .stream()
            .map(Object::toString)
            .filter(string -> string.contains("slave"))
            .collect(toList());

        dataSourceNameList = new CircularList<>(slaveNames);
    }

    @Override
    protected Object determineCurrentLookupKey() {
        boolean isReadOnly = TransactionSynchronizationManager.isCurrentTransactionReadOnly();
        if (isReadOnly) {
            logger.info("Connection Slave");
            return dataSourceNameList.getOne();
        } else {
            logger.info("Connection Master");
            return "master";
        }
    }
}