package com.hibernate.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.hibernate.model.Serie;
import com.hibernate.util.HibernateUtil;

/* 3 CREACIÓN DE LA CLASE DAO. Clase Data Access Object */
/* Operaciones básicas para interactuar con la BD */
public class SerieDAO {

	/* UPDATE */
	/*
	 * Esta función requiere que le pasemos como parámetro el objeto persona cuyos
	 * datos han cambiado y queremos actualizar. Se crea una sesión, después una
	 * transacción y se invoca a la función merge que será la que actualice la tabla
	 * con la persona pasada como parámetro. Después se confirma la transacción (o
	 * se anula si hubiera alguna excepción). Al igual que la función anterior, esta
	 * función no necesita devolver nada
	 */
	public void updateSerie(Serie s) { // Reutilización código - Cambio Serie
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			session.merge(s);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
			System.err.println("Error al actualizar " + e.getMessage());
		}
	}

	/* DELETE */
	/*
	 * En este caso, le pasamos a la función el identificador de la persona que
	 * queremos borrar. En primer lugar, se crea la sesión y la transacción y con
	 * get se obtiene la persona con el id especificado. Esa persona será la que se
	 * elimine de la tabla con remove. Finalmente, se confirma o anula la
	 * transacción. Esta función tampoco devuelve nada.
	 */
	public void deleteSerie(int id) { // Reutilización código - Cambio Serie / Parámetro que es el mismo de la
										// clase
		Transaction transaction = null;
		Serie s = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			s = session.get(Serie.class, id); // Cógeme de la tabla ciudad la ciudad con código y guardalo en c
			session.remove(s); // Borra la ciudad que hayas seleccionado de la tabla
			transaction.commit(); // Ejecuta la transacción
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			System.err.println("Error al borrar " + e.getMessage());

		}
	}

	/* SELECT ALL */
	/*
	 * Esta función no necesita parámetros y devuelve todas las filas de la tabla,
	 * transformándolas en una lista de objetos. En primer lugar, se crean la sesión
	 * y la transacción. Después se crea una consulta o query donde especificamos en
	 * HQL la clase de la cual queremos las filas, y la convertimos a lista con
	 * getResultList. La lista será devuelta por la función. Inicialmente tenemos
	 * que asignarle el valor null para controlar los posibles errores.
	 */
	public List<Serie> selectAllSeries() { // Reutilización código - Cambio Ciudads
		Transaction transaction = null;
		List<Serie> series = null; // Reutilización código - Cambio Ciudads
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			series = session.createQuery("FROM Serie", Serie.class).getResultList(); // Reutilización código -
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			System.err.println("Error al seleccionar " + e.getMessage());

		}
		return series; // Reutilización código - Cambio Ciudads
	}

}
