package edu.java.web.service;

import edu.java.web.beans.Batch;
import edu.java.web.dao.BatchDAO;
import edu.java.web.status.OperationResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * Created by edwin on 25/01/17.
 */
public class BatchServiceImpl implements BatchService {

    @Autowired
    @Qualifier(value = "batchDAO")
    private BatchDAO batchDAO;

    public OperationResult getAllBatches(Long userId) {
        return batchDAO.getAllBatches(userId);
    }

    public OperationResult getBatch(String batchName, Long userId) {
        return batchDAO.getBatch(batchName,userId);
    }

    public OperationResult getBatch(Long batchId) {
        return batchDAO.getBatch(batchId);
    }

    public OperationResult updateBatchDetails(Batch batch, Long userId) {
        return batchDAO.updateBatchDetails(batch,userId);
    }

    public OperationResult deleteBatch(String batchName, Long userId) {
        return batchDAO.deleteBatch(batchName,userId);
    }

    public OperationResult addBatch(Batch batch, Long userId) {
        return batchDAO.addBatch(batch,userId);
    }
}
