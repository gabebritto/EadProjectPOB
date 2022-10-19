/**********************************
 * IFPB - Curso Superior de Tec. em Sist. para Internet
 * POO
 * Prof. Fausto Maranh�o Ayres
 **********************************/
package appswing;

import java.awt.Color;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import java.util.List;

import javax.swing.JButton;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;


import modelo.Aluno;
import modelo.Curso;
import regras_negocio.Fachada;

public class TelaCursos {
	private JFrame frame;
	private JTable table;
	private JScrollPane scrollPane;
	private JButton button;
	private JButton button_1;
	private JButton button_3;
	private JButton button_4;
	private JButton button_2;
	private JTextField textField_1;
	private JLabel label;
	private JLabel lblNome;
	private JLabel lblPreo;
	private JLabel label_8;
	private JButton button_5;
	private JTextField textField;



	/**
	 * Launch the application.
	 */
	//	public static void main(String[] args) {
	//		EventQueue.invokeLater(new Runnable() {
	//			public void run() {
	//				try {
	//					TelaCursos window = new TelaCursos();
		//				window.frame.setVisible(true);
	//				} catch (Exception e) {
		//				e.printStackTrace();
		//			}
		//		}
	//		});
	//	}

	/**
	 * Create the application.
	 */
	public TelaCursos() {
		initialize();
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 12));
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				listagem();
			}
		});
		frame.setTitle("Cursos");
		frame.setBounds(100, 100, 912, 351);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(26, 42, 844, 120);
		frame.getContentPane().add(scrollPane);

		table = new JTable();
		table.setGridColor(Color.BLACK);
		table.setRequestFocusEnabled(false);
		table.setFocusable(false);
		table.setBackground(Color.WHITE);
		table.setFillsViewportHeight(true);
		table.setRowSelectionAllowed(true);
		table.setFont(new Font("Tahoma", Font.PLAIN, 12));
		scrollPane.setViewportView(table);
		table.setBorder(new LineBorder(new Color(0, 0, 0)));
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setShowGrid(true);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);


		button = new JButton("Criar");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(textField.getText().isEmpty() || 
							textField_1.getText().isEmpty())
					{
						label.setText("campo vazio");
						return;
					}

					String nome = textField.getText();
					String preco = textField_1.getText();
					Curso c = Fachada.criarCurso(nome, Integer.parseInt(preco));
					label.setText("Curso criado: " + c.getNome());
					listagem();
				}
				catch(Exception ex) {
					label.setText(ex.getMessage());
				}
			}
		});
		button.setFont(new Font("Tahoma", Font.PLAIN, 12));
		button.setBounds(26, 278, 95, 23);
		frame.getContentPane().add(button);
		
		button_3 = new JButton("Cursos c/ n Alunos");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					
					String qnt = JOptionPane.showInputDialog(frame,"Digite a quantidade de alunos");
					if (qnt != null) { 
						List<Curso> cursosConsultados = Fachada.consultarCursoNAlunos(Integer.parseInt(qnt));
						String nomes= "Nomes dos cursos:";
						for(Curso c : cursosConsultados)
							nomes+="\n"+c.getNome() + " - " + c.getAlunos().size();
	
						JOptionPane.showMessageDialog(frame, nomes);
					}

				}
				catch(NumberFormatException ex) {
					label.setText("Valor inválido. Digite apenas números");
				}
				catch(Exception erro) {
					label.setText(erro.getMessage());
				}
			}
		});
		
		button_3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		button_3.setBounds(435, 180, 140, 23);
		frame.getContentPane().add(button_3);

		button_1 = new JButton("Apagar");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (table.getSelectedRow() >= 0){
						String nome = (String) table.getValueAt( table.getSelectedRow(), 0);

						Object[] options = { "Confirmar", "Cancelar" };
						int escolha = JOptionPane.showOptionDialog(null, "Confirma cancelamento do Curso "+nome, "Alerta",
								JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[1]);
						if(escolha == 0) {
							Fachada.apagarCurso(nome);
							label.setText("Curso cancelado e Alunos removidos: "+ nome);
							listagem();
						}
						else
							label.setText("nao cancelou evento " + nome );
					}
					else
						label.setText("selecione uma linha");
				}
				catch(Exception erro) {
					label.setText(erro.getMessage());
				}
			}
		});
		button_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		button_1.setBounds(131, 278, 95, 23);
		frame.getContentPane().add(button_1);

		label = new JLabel("");
		label.setForeground(Color.BLUE);
		label.setBackground(Color.RED);
		label.setBounds(263, 278, 607, 23);
		frame.getContentPane().add(label);

		lblNome = new JLabel("Nome");
		lblNome.setHorizontalAlignment(SwingConstants.LEFT);
		lblNome.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblNome.setBounds(26, 225, 71, 14);
		frame.getContentPane().add(lblNome);

		lblPreo = new JLabel("Preço");
		lblPreo.setHorizontalAlignment(SwingConstants.LEFT);
		lblPreo.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblPreo.setBounds(26, 253, 71, 14);
		frame.getContentPane().add(lblPreo);

		textField_1 = new JTextField();
		textField_1.setFont(new Font("Dialog", Font.PLAIN, 12));
		textField_1.setColumns(10);
		textField_1.setBounds(67, 250, 169, 20);
		frame.getContentPane().add(textField_1);

		label_8 = new JLabel("selecione");
		label_8.setBounds(26, 163, 561, 14);
		frame.getContentPane().add(label_8);

		button_4 = new JButton("Listar");
		button_4.setFont(new Font("Tahoma", Font.PLAIN, 12));
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listagem();
			}
		});
		button_4.setBounds(26, 11, 135, 23);
		frame.getContentPane().add(button_4);

		button_2 = new JButton("Atualizar Preço");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (table.getSelectedRow() >= 0){
						String nome = (String) table.getValueAt( table.getSelectedRow(), 0);
						String preco = JOptionPane.showInputDialog(frame,"Digite o novo Preço");
						Fachada.ajustarPrecoCurso(nome, Integer.parseInt(preco));
						label.setText("Preço alterado para : "+ preco);
						listagem();
					}
					else
						label.setText("selecione uma linha");
				}
				catch(Exception erro) {
					label.setText(erro.getMessage());
				}
			}
		});
		button_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		button_2.setBounds(730, 180, 140, 23);
		frame.getContentPane().add(button_2);

		button_5 = new JButton("Ver Alunos");
		button_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (table.getSelectedRow() >= 0){
						String nome = (String) table.getValueAt( table.getSelectedRow(), 0);
						Curso c = Fachada.localizarCurso(nome);
						String nomes= "Nomes dos alunos:";
						for(Aluno a : c.getAlunos())
							nomes+="\n"+a.getNome();

						JOptionPane.showMessageDialog(frame, nomes);
					}
					else
						label.setText("selecione uma linha");
				}
				catch(Exception erro) {
					label.setText(erro.getMessage());
				}
			}
		});
		button_5.setFont(new Font("Tahoma", Font.PLAIN, 12));
		button_5.setBounds(585, 180, 135, 23);
		frame.getContentPane().add(button_5);
		
		textField = new JTextField();
		textField.setFont(new Font("Dialog", Font.PLAIN, 12));
		textField.setColumns(10);
		textField.setBounds(67, 222, 169, 20);
		frame.getContentPane().add(textField);

	

	}

	//*****************************
	public void listagem () {
		try{
			List<Curso> lista = Fachada.listarCursos();

			//model contem todas as linhas e colunas da tabela
			DefaultTableModel model = new DefaultTableModel();
			//colunas
			model.addColumn("nome");
			model.addColumn("preco");
			model.addColumn("total ganho");
			//linhas
			for(Curso c : lista) {
				model.addRow(new Object[]{c.getNome()+"", c.getPreco(), c.totalGanho()});
			}

			table.setModel(model);
			label_8.setText("resultados: "+lista.size()+ " cursos  - selecione uma linha");
		}
		catch(Exception erro){
			label.setText(erro.getMessage());
		}

	}
}
