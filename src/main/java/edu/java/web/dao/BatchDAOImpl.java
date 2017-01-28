package edu.java.web.dao;

import edu.java.web.beans.Batch;
import edu.java.web.status.OperationResult;
import edu.java.web.status.OperationStatus;
import edu.java.web.util.DBUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

/**
 * Created by edwin on 25/01/17.
 */
public class BatchDAOImpl implements BatchDAO {

    @Autowired
    @Qualifier(value = "sessionFactory")
    private SessionFactory sessionFactory;

    public OperationResult getAllBatches(Long userId) {
        List<Batch> batches=null;
        OperationResult result=null;
        Session session = sessionFactory.openSession();
        try {
            Query query = session.createQuery(String.format("from Batch b where b.userId='%s'", userId.toString()));
            batches = query.list();
            result= new OperationResult(OperationStatus.SUCCESS);
            result.setResultObject(batches);
        }
        catch(Exception ex) {
            result = new OperationResult(OperationStatus.FAILED);
            ex.printStackTrace();
            result.setException(ex);
        }
        finally {
            if(session!=null) {
                DBUtil.close(session);
            }
        }
        return result;
    }

    public OperationResult getBatch(String batchName, Long userId) {
        Batch batch=null;
        OperationResult result=null;
        Session session = sessionFactory.openSession();
        try {
            Query query = session.createQuery(String.format("from Batch b where b.userId='%s' and b.batchName='%s'", userId.toString(),batchName));
            batch = (Batch)query.uniqueResult();
            batch.getBatchEntries();
            result = new OperationResult(OperationStatus.SUCCESS);
            result.setResultObject(result);
        }
        catch(Exception ex) {
            result = new OperationResult(OperationStatus.FAILED);
            result.setException(ex);
        }
        finally {
            if(session!=null) {
                DBUtil.close(session);
            }
        }
        return result;
    }

    public OperationResult getBatch(Long  batchId) {
        Batch batch=null;
        OperationResult result=null;
        Session session = sessionFactory.openSession();
        try {
            Query query = session.createQuery("from Batch b where b.batchId="+batchId.toString());
            batch=(Batch)query.uniqueResult();
            System.out.println("Batch Name : "+batch.getBatchName());
            System.out.println("Batch entries : "+batch.getBatchEntries().size());
            batch.getBatchEntries().size();
            result = new OperationResult(OperationStatus.SUCCESS);
            result.setResultObject(batch);
        }
        catch(Exception ex) {
            result = new OperationResult(OperationStatus.FAILED);
            result.setException(ex);
        }
        finally {
            if(session!=null) {
                DBUtil.close(session);
            }
        }
        return result;
    }

    public OperationResult updateBatchDetails(Batch batch, Long userId) {
        OperationResult result=null;
        Session session=sessionFactory.openSession();
        try
        {
            session.getTransaction().begin();
            session.saveOrUpdate(batch);
            session.getTransaction().commit();
            result=new OperationResult(OperationStatus.SUCCESS);
            result.setResultObject(batch);
        }
        catch(Exception ex)
        {
            result=new OperationResult(OperationStatus.FAILED);
            result.setException(ex);
            session.getTransaction().rollback();
        }
        finally {
            if(session!=null) {
                session.close();
            }
        }
        return result;
    }

    public OperationResult deleteBatch(String batchName, Long userId) {
        Batch batch=null;
        OperationResult result=null;
        Session session = sessionFactory.openSession();
        try {
            Query query = session.createQuery(String.format("from Batch b where b.userId='%s' and b.batchName='%s'", userId.toString(),batchName));
            batch = (Batch)query.uniqueResult();
            session.getTransaction().begin();
            session.delete(batch);
            result = new OperationResult(OperationStatus.SUCCESS);
        }
        catch(Exception ex) {
            result = new OperationResult(OperationStatus.FAILED);
            result.setException(ex);
        }
        finally {
            if(session!=null) {
                DBUtil.close(session);
            }
        }
        return result;
    }

    public OperationResult addBatch(Batch batch, Long userId) {
        OperationResult result=null;
        Session session=sessionFactory.openSession();
        try {
            session.getTransaction().begin();
            session.save(batch);
            session.getTransaction().commit();
            result=new OperationResult(OperationStatus.SUCCESS);
        }
        catch(Exception ex) {
            result=new OperationResult(OperationStatus.FAILED);
            result.setException(ex);

        }
        finally {
            if(session!=null) {
                DBUtil.close(session);
            }
        }
        return result;
    }
}
