package edu.java.web.dao;

import edu.java.web.beans.Batch;
import edu.java.web.status.OperationResult;

import java.util.List;

/**
 * Created by edwin on 25/01/17.
 */
public interface BatchDAO {

    public OperationResult getAllBatches(Long userId);

    public OperationResult getBatch(String batchName, Long userId);

    public OperationResult getBatch(Long batchId);

    public OperationResult updateBatchDetails(Batch batch, Long userId);

    public OperationResult deleteBatch(String batchName, Long userId);

    public OperationResult addBatch(Batch batch, Long userId);
}
