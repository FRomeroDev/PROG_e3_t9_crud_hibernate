package com.hibernate.util;

import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.AvailableSettings;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import com.hibernate.model.Serie;

public class HibernateUtil {

	private static SessionFactory sessionFactory;

	public static SessionFactory getSessionFactory() {

		if (sessionFactory == null) {

			try {
				/*
				 * Crea y rellena un objeto Properties con los parámetros de configuración de la
				 * BD
				 */
				Properties settings = new Properties();
				settings.put(AvailableSettings.DRIVER, "com.mysql.cj.jdbc.Driver");
				settings.put(AvailableSettings.URL, "jdbc:mysql://127.0.0.1:3307/prog_t9_ejer3_db?useSSL=false");
				settings.put(AvailableSettings.USER, "fernando");
				settings.put(AvailableSettings.PASS, "fernando");
				settings.put(AvailableSettings.SHOW_SQL, "false");
				settings.put(AvailableSettings.CURRENT_SESSION_CONTEXT_CLASS, "thread");
				settings.put(AvailableSettings.HBM2DDL_AUTO, "update");

				/* Asocia un objeto Configuration con las propiedades */
				Configuration configuration = new Configuration();
				configuration.setProperties(settings);
				configuration.addAnnotatedClass(Serie.class);

				/*
				 * Crea un servicio con la configuración (que será el encargado de realizar la
				 * traducción ORM)
				 */
				ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
						.applySettings(configuration.getProperties()).build();

				/*
				 * Crea un objeto SessionFactory (similar al ConnectionSingleton de la unidad
				 * anterior), que será el objeto que devuelva nuestra función. Esta función
				 * estática será invocada cada vez que queramos realizar una operación en la BD
				 */
				sessionFactory = configuration.buildSessionFactory(serviceRegistry);

			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Error: bd no cargada");

			}

		}
		return sessionFactory;

	}

}
