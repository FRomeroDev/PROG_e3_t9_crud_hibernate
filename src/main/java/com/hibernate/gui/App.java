package com.hibernate.gui;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import com.hibernate.dao.SerieDAO;
import com.hibernate.model.Serie;

public class App {

	private JFrame frame;
	private DefaultTableModel modelSerieDB = new DefaultTableModel();
	private JScrollPane scrollPanSerie;
	private JTable tableSerieDB;
	private JTextField txtFid;
	private JTextField txtFnom;
	private JTextField txtFnumTem;
	private JTextField txtFcap;
	private JButton btnGuardar;
	private JButton btnActualizarBD;
	private JButton btnBorrar;

	private SerieDAO serieDAO = new SerieDAO();
	private List<Serie> series = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		try {
			UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					App window = new App();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public App() {
		initialize();

	}

	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings("serial")
	private void initialize() {

		frame = new JFrame();
		frame.setBounds(100, 100, 800, 650);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		modelSerieDB = new DefaultTableModel() {
			public boolean isCellEditable(int row, int column) {

				return false; // No seleccionable la celdas
			}
		};
		modelSerieDB.addColumn("ID");
		modelSerieDB.addColumn("Título");
		modelSerieDB.addColumn("NºTemporadas");
		modelSerieDB.addColumn("Total episodios");

		tableSerieDB = new JTable(modelSerieDB);
		tableSerieDB.setBounds(48, 239, 695, -205);
		tableSerieDB.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				int index = tableSerieDB.getSelectedRow();
				TableModel modelSerieDB = tableSerieDB.getModel();
				txtFid.setText(modelSerieDB.getValueAt(index, 0).toString());
				txtFnom.setText(modelSerieDB.getValueAt(index, 1).toString());
				txtFnumTem.setText(modelSerieDB.getValueAt(index, 2).toString());
				txtFcap.setText(modelSerieDB.getValueAt(index, 3).toString());

			}
		});
		tableSerieDB.setBounds(67, 81, 425, 144);
		tableSerieDB.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		// tableSerieDB.setModel(tableModel);
		frame.getContentPane().add(tableSerieDB);
		
		/*
		 * Centra los registros de la tabla
		 */
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment( JLabel.CENTER );
		tableSerieDB.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
		tableSerieDB.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
		tableSerieDB.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
		tableSerieDB.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);

		scrollPanSerie = new JScrollPane(tableSerieDB);
		scrollPanSerie.setBounds(63, 36, 676, 262);
		frame.getContentPane().add(scrollPanSerie);

		JLabel lblID = new JLabel("Id:");
		lblID.setFont(new Font("Dialog", Font.BOLD, 14));
		lblID.setBounds(63, 335, 120, 15);
		frame.getContentPane().add(lblID);

		JLabel lblNombre = new JLabel("Serie:");
		lblNombre.setFont(new Font("Dialog", Font.BOLD, 14));
		lblNombre.setBounds(63, 385, 120, 15);
		frame.getContentPane().add(lblNombre);

		JLabel lblNumTem = new JLabel("Temporadas:");
		lblNumTem.setFont(new Font("Dialog", Font.BOLD, 14));
		lblNumTem.setBounds(63, 435, 120, 15);
		frame.getContentPane().add(lblNumTem);

		JLabel lblCap = new JLabel("Capítulos:");
		lblCap.setFont(new Font("Dialog", Font.BOLD, 14));
		lblCap.setBounds(63, 485, 120, 15);
		frame.getContentPane().add(lblCap);

		txtFid = new JTextField();
		txtFid.setEnabled(false);
		txtFid.setFont(new Font("Dialog", Font.PLAIN, 14));
		txtFid.setBounds(185, 329, 114, 27);
		frame.getContentPane().add(txtFid);
		txtFid.setColumns(10);

		txtFnom = new JTextField();
		txtFnom.setFont(new Font("Dialog", Font.PLAIN, 14));
		txtFnom.setColumns(10);
		txtFnom.setBounds(185, 379, 324, 27);
		frame.getContentPane().add(txtFnom);

		txtFnumTem = new JTextField();
		txtFnumTem.setFont(new Font("Dialog", Font.PLAIN, 14));
		txtFnumTem.setColumns(10);
		txtFnumTem.setBounds(185, 429, 114, 27);
		frame.getContentPane().add(txtFnumTem);

		txtFcap = new JTextField();
		txtFcap.setFont(new Font("Dialog", Font.PLAIN, 14));
		txtFcap.setColumns(10);
		txtFcap.setBounds(185, 479, 114, 27);
		frame.getContentPane().add(txtFcap);

		btnActualizarBD = new JButton("ActualizarBD");
		btnActualizarBD.setFont(new Font("Dialog", Font.BOLD, 15));
		btnActualizarBD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {

					series = serieDAO.selectAllSeries();
					modelSerieDB.setRowCount(0);
					for (Serie s : series) {

						Object[] row = new Object[4];
						row[0] = s.getId();
						row[1] = s.getNombre();
						row[2] = s.getNumTemp();
						row[3] = s.getNumCapi();
						modelSerieDB.addRow(row);
					}

				} catch (Exception e1) {
					e1.printStackTrace();
					System.out.println("Error: bd no cargada");
				}
			}
		});
		btnActualizarBD.setBounds(337, 574, 135, 27);
		frame.getContentPane().add(btnActualizarBD);
		btnActualizarBD.setVisible(false);
		btnActualizarBD.doClick();

		btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {

					String nombre = txtFnom.getText();
					int numTemp = Integer.parseInt(txtFnumTem.getText());
					int numCapi = Integer.parseInt(txtFcap.getText());

					Serie s = new Serie(nombre, numTemp, numCapi);

					serieDAO.insertSerie(s);
					txtFid.setText(null);
					txtFnom.setText(null);
					txtFnumTem.setText(null);
					txtFcap.setText(null);
					btnActualizarBD.doClick();
					JOptionPane.showMessageDialog(null, "Serie creada");

				} catch (Exception e1) {
					e1.printStackTrace();
					System.out.println("Error: serie no guardada");
				}
			}
		});
		btnGuardar.setFont(new Font("Dialog", Font.BOLD, 15));
		btnGuardar.setBounds(120, 537, 135, 27);
		frame.getContentPane().add(btnGuardar);

		btnBorrar = new JButton("Borrar");
		btnBorrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {

					serieDAO.deleteSerie(Integer.parseInt(txtFid.getText()));
					btnActualizarBD.doClick();
					txtFid.setText(null);
					txtFnom.setText(null);
					txtFnumTem.setText(null);
					txtFcap.setText(null);
					JOptionPane.showMessageDialog(null, "Serie borrada");

				} catch (Exception e1) {
					e1.printStackTrace();
					System.out.println("Error: seria no borrada");
				}
			}
		});
		btnBorrar.setFont(new Font("Dialog", Font.BOLD, 15));
		btnBorrar.setBounds(557, 537, 135, 27);
		frame.getContentPane().add(btnBorrar);

		JButton btnActualizarCam = new JButton("Actualizar");
		btnActualizarCam.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				try {
					Serie s = new Serie();

					s.setId(Integer.parseInt(txtFid.getText()));
					s.setNombre(txtFnom.getText());
					s.setNumTemp(Integer.parseInt(txtFnumTem.getText()));
					s.setNumCapi(Integer.parseInt(txtFcap.getText()));
					serieDAO.updateSerie(s);
					txtFid.setText(null);
					txtFnom.setText(null);
					txtFnumTem.setText(null);
					txtFcap.setText(null);
					btnActualizarBD.doClick();
					JOptionPane.showMessageDialog(null, "Serie actualizada");

				} catch (Exception e1) {
					e1.printStackTrace();
					System.out.println("Error: serie no guardada");
				}

			}
		});
		btnActualizarCam.setFont(new Font("Dialog", Font.BOLD, 15));
		btnActualizarCam.setBounds(337, 537, 135, 27);
		frame.getContentPane().add(btnActualizarCam);
	}
}
