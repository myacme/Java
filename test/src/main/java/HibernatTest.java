/**
 * Copyright (C), 2015-2020, XXX有限公司
 * <p>
 * FileName: HibernatTest
 * <p>
 * Author:   MyAcme
 * <p>
 * Date:     2020/8/17 12:25
 * <p>
 * Description:
 * <p>
 * History:
 *
 * <author>          <time>          <version>          <desc>
 * <p>
 * 作者姓名           修改时间           版本号              描述
 */


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author MyAcme

 * @create 2020/8/17

 * @since 1.0.0

 */

public class HibernatTest {
    public static void main(String[] args) {
        SessionFactory sessionFactory = null;
        Configuration configuration = new Configuration().configure();
        ServiceRegistry serviceRegistry = configuration.getStandardServiceRegistryBuilder().build();
        sessionFactory = new MetadataSources(serviceRegistry).buildMetadata().buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        person p = new person();
        p.setName("zhangsan1");
        session.update(p);
        transaction.commit();
        session.close();
        sessionFactory.close();
    }


}