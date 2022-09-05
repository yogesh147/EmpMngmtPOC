package com.emp.mgnt.util;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;
import org.springframework.beans.ConfigurablePropertyAccessor;
import org.springframework.beans.PropertyAccessorFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.emp.mgnt.entity.EmployeeInfo;
import lombok.extern.slf4j.Slf4j;
/**
 * Java based configuration
 * @author yogesh choudhary
 *
 */
@Slf4j
@Transactional
@EnableTransactionManagement
@Configuration
public class HibernateUtil {
	private static StandardServiceRegistry registry;
	private static SessionFactory sessionFactory;
	private static Session sessionObj;
	
	@Bean
	public static SessionFactory getSessionFactory() {
		if (sessionFactory == null) {
			try {
				// Create registry
				registry = new StandardServiceRegistryBuilder().configure().build();
				// Create MetadataSources
				MetadataSources sources = new MetadataSources(registry);
				// Create Metadata
				Metadata metadata = sources.getMetadataBuilder().build();
				// Create SessionFactory
				sessionFactory = metadata.getSessionFactoryBuilder().build();

			} catch (Exception e) {
				e.printStackTrace();
				shutdown();
			}
		}
		return sessionFactory;
	}

	@Bean
	public static void shutdown() {
		if (registry != null) {
			StandardServiceRegistryBuilder.destroy(registry);
		}
	}
	
	// Method 1: This Method Used To Create A New EmployeeInfo Record In The Database Table
	public static void createRecord() {
		int count = 0;
		try {
			// Getting Session Object From SessionFactory
			sessionObj = getSessionFactory().openSession();
			// Getting Transaction Object From Session Object
			 Transaction t = sessionObj.beginTransaction();

			// Creating Transaction Entities
			EmployeeInfo emp = new EmployeeInfo();
			
//			Field id = emp.getClass().getDeclaredField("id");
//			id.setAccessible(true);
//			int data = 11;
//			id.setInt(emp, data);
//			emp.setName("ss");
			emp.setId(1);
//			Method setter = EmployeeInfo.class.getDeclaredMethod("setId", String.class);
//			setter.invoke(emp, "11");

//			System.out.println("before: " + emp);
//			ConfigurablePropertyAccessor propertyAccessor = PropertyAccessorFactory.forBeanPropertyAccess(emp);
//			propertyAccessor.setPropertyValue("id", "Tina");
//			System.out.println("after: " + emp);
//			
			
			//
			
//			System.out.println("before: " + p);
//			Method setter = EmployeeInfo.class.getDeclaredMethod("setId", String.class);
//			setter.invoke(p, "Tina");

//			Method getter = EmployeeInfo.class.getDeclaredMethod("getId", String.class);
//			getter.invoke(p, "Tina");
			
			sessionObj.save(emp);
			// Committing The Transactions To The Database
			t.commit();
			log.info("\nSuccessfully Created '" + count + "' Records In The Database!\n");
		} catch (Exception sqlException) {
			if (null != sessionObj.getTransaction()) {
				log.info("\n.......Transaction Is Being Rolled Back.......\n");
				sessionObj.getTransaction().rollback();
			}
			sqlException.printStackTrace();
		} finally {
			if (sessionObj != null) {
				sessionObj.close();
			}
		}
	}

	// Method 2: This Method Is Used To Display The Records From The Database Table
	@SuppressWarnings("unchecked")
	public static List<EmployeeInfo> displayRecords() {
		List<EmployeeInfo> empList = new ArrayList<>();
		try {
			// Getting Session Object From SessionFactory
			sessionObj = getSessionFactory().openSession();
			// Getting Transaction Object From Session Object
			sessionObj.beginTransaction();

			empList = sessionObj.createQuery("FROM EmployeeInfo").list();
		} catch (Exception sqlException) {
			if (null != sessionObj.getTransaction()) {
				log.info("\n.......Transaction Is Being Rolled Back.......\n");
				sessionObj.getTransaction().rollback();
			}
			sqlException.printStackTrace();
		} finally {
			if (sessionObj != null) {
				sessionObj.close();
			}
		}
		return empList;
	}

	// Method 3: This Method Is Used To Update A Record In The Database Table
	public static void updateRecord(long id) {
		try {
			// Getting Session Object From SessionFactory
			sessionObj = getSessionFactory().openSession();
			// Getting Transaction Object From Session Object
			sessionObj.beginTransaction();

			// Creating Transaction Entity
			EmployeeInfo empObj = (EmployeeInfo) sessionObj.get(EmployeeInfo.class, id);
//			empObj.setName("Java Code Geek");

			// Committing The Transactions To The Database
			sessionObj.getTransaction().commit();
			log.info("\nEmployee With Id?= " + id + " Is Successfully Updated In The Database!\n");
		} catch (Exception sqlException) {
			if (null != sessionObj.getTransaction()) {
				log.info("\n.......Transaction Is Being Rolled Back.......\n");
				sessionObj.getTransaction().rollback();
			}
			sqlException.printStackTrace();
		} finally {
			if (sessionObj != null) {
				sessionObj.close();
			}
		}
	}

	// Method 4(a): This Method Is Used To Delete A Particular Record From the database Table
	public static void deleteRecord(long id) {
		try {
			// Getting Session Object From SessionFactory
			sessionObj = getSessionFactory().openSession();
			// Getting Transaction Object From Session Object
			sessionObj.beginTransaction();

			EmployeeInfo emp = findRecordById(id);
			sessionObj.delete(emp);

			// Committing The Transactions To The Database
			sessionObj.getTransaction().commit();
			log.info("\nEmployee With Id?= " + id + " Is Successfully Deleted From The Database!\n");
		} catch (Exception sqlException) {
			if (null != sessionObj.getTransaction()) {
				log.info("\n.......Transaction Is Being Rolled Back.......\n");
				sessionObj.getTransaction().rollback();
			}
			sqlException.printStackTrace();
		} finally {
			if (sessionObj != null) {
				sessionObj.close();
			}
		}
	}

	// Method 4(b): This Method To Find Particular Record In The Database Table
	public static EmployeeInfo findRecordById(long id) {
		EmployeeInfo findEmpObj = null;
		try {
			// Getting Session Object From SessionFactory
			sessionObj = getSessionFactory().openSession();
			// Getting Transaction Object From Session Object
			sessionObj.beginTransaction();

			findEmpObj = (EmployeeInfo) sessionObj.load(EmployeeInfo.class, id);
		} catch (Exception sqlException) {
			if (null != sessionObj.getTransaction()) {
				log.info("\n.......Transaction Is Being Rolled Back.......\n");
				sessionObj.getTransaction().rollback();
			}
			sqlException.printStackTrace();
		}
		return findEmpObj;
	}

	// Method 5: This Method Is Used To Delete All Records From The Database Table
	public static void deleteAllRecords() {
		try {
			// Getting Session Object From SessionFactory
			sessionObj = getSessionFactory().openSession();
			// Getting Transaction Object From Session Object
			sessionObj.beginTransaction();

			Query queryObj = sessionObj.createQuery("DELETE FROM EmployeeInfo");
			queryObj.executeUpdate();

			// Committing The Transactions To The Database
			sessionObj.getTransaction().commit();
			log.info("\nSuccessfully Deleted All Records From The Database Table!\n");
		} catch (Exception sqlException) {
			if (null != sessionObj.getTransaction()) {
				log.info("\n.......Transaction Is Being Rolled Back.......\n");
				sessionObj.getTransaction().rollback();
			}
			sqlException.printStackTrace();
		} finally {
			if (sessionObj != null) {
				sessionObj.close();
			}
		}
	}
}
