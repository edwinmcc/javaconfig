package edu.java.web.util;

import org.hibernate.Session;

/**
 * Created by edwin on 22/01/17.
 */
public class DBUtil {
    public static void close(Session session) {
        if(session!=null) {
            session.close();
        }
    }
}
