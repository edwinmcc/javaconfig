package edu.java.web.dao;

import edu.java.web.beans.User;
import edu.java.web.status.OperationResult;
import edu.java.web.status.OperationStatus;
import edu.java.web.util.DBUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.beanvalidation.GroupsPerOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

/**
 * Created by edwin on 22/01/17.
 */

public class UserDAOImpl implements UserDAO {

    @Autowired
    @Qualifier(value = "sessionFactory")
    public SessionFactory sessionFactory;

    public OperationResult getAllUsers() {
        OperationResult result;
        Session session=sessionFactory.openSession();
        List<User> userList=null;
        try {
            Query query=session.createQuery("from User");
            userList=query.list();
            result=new OperationResult(OperationStatus.SUCCESS);
            result.setResultObject(userList);
        }
        catch(Exception ex) {
            ex.printStackTrace();
            result=new OperationResult(OperationStatus.FAILED);
            result.setException(ex);
        }
        finally {
            if(session!=null) {
                session.close();
            }
        }
        return result;
    }

    public OperationResult getUser(String userName) {
        OperationResult result=null;
        User user=null;
        Session session=null;
        session=sessionFactory.openSession();
        Query query=session.createQuery(String.format("from User as user where user.username='%s'",userName));
        try {
            List<User> resultList = query.list();
            if (resultList.size() > 0) {
                user = resultList.get(0);
                result=new OperationResult(OperationStatus.SUCCESS);
                result.setResultObject(user);
            }
            else if(resultList.size()==0) {
                result=new OperationResult(OperationStatus.NOT_FOUND);
            }
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

    public OperationResult addUser(User user) {
        OperationResult result=null;
        Session session=sessionFactory.openSession();
        try {
            session.getTransaction().begin();
            session.save(user);
            session.getTransaction().commit();
            result=new OperationResult(OperationStatus.SUCCESS);
        }
        catch(Exception ex) {
            ex.printStackTrace();
            session.getTransaction().rollback();
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

    public OperationResult editUser(User user) {
        Session session=null;
        OperationResult result=null;
        try
        {
            session=sessionFactory.openSession();
            session.getTransaction().begin();
            session.update(user);
            session.getTransaction().commit();
            result=new OperationResult(OperationStatus.SUCCESS);
        }
        catch(Exception ex) {
            ex.printStackTrace();;
            session.getTransaction().rollback();
            result=new OperationResult(OperationStatus.FAILED);
            result.setException(ex);
        }
        finally {
            DBUtil.close(session);
        }
        return result;
    }

    public OperationResult deleteUser(User user) {
        Session session=null;
        OperationResult result=null;
        try
        {
            session=sessionFactory.openSession();
            session.getTransaction().begin();
            session.delete(user);
            session.getTransaction().commit();
            result=new OperationResult(OperationStatus.SUCCESS);
        }
        catch(Exception ex) {
            ex.printStackTrace();;
            result = new OperationResult(OperationStatus.FAILED);
            result.setException(ex);
        }
        finally {
            DBUtil.close(session);
        }
        return result;
    }
}
