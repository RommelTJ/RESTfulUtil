package edu.sandiego.restfulUtil.hibernate;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static SessionFactory basicSessionFactory;
	public static Log log = LogFactory.getLog(HibernateUtil.class);

    // hibernate uses org.hibernate.Configuration object to wraps all the
	// configuration settings and is used to build sessionfactory object
	@SuppressWarnings("deprecation")
	public static SessionFactory getBasicSessionFactory() {
        if (basicSessionFactory == null) {
			/*
			 * NOTE:IMP Configure config = new Configure(); config.configure();
			 * OR config.configure("com/go/anyname.cfg.xml"); if it does NOT
			 * exist in the root classpath this method will search for
			 * hibernate.cfg.xml file in the root and if it finds it then it
			 * will load the configuration from the file into configuration
			 * object
			 */

			// this automatically searches hibernate.properties in the root
			// classpath.
			// Configuration config = new Configuration();

			Configuration config = new Configuration()
					.addResource("USD_Util.hbm.xml")
					.addResource("restfulUtil/beans/ParentInfo.hbm.xml")
					.addResource("restfulUtil/beans/ChildInfo.hbm.xml")
					.addResource("restfulUtil/beans/ParentPinUN.hbm.xml");
			try {
				basicSessionFactory = config.buildSessionFactory();
			}
			catch (Throwable e) {
				// please log this exception
				//System.err.println("Session##Factory creat_ion FaI_lier" + e);
				log.error("Session##Factory creat_ion FaI_lier" + e.getMessage());
				log.error(e.toString());
				throw new ExceptionInInitializerError(e);
			}
			if (basicSessionFactory == null) {
				log.error("not able to initialize session");
			}
        }
        return basicSessionFactory;
    }

}