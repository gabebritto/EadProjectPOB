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

public class TelaAlunos {
	private JFrame frame;
	private JTable table;
	private JScrollPane scrollPane;
	private JLabel label;
	private JLabel label_6;

	private JLabel label_2;

	private JTextField textField_1;
	private JButton button;
	private JButton button_1;
	private JLabel label_3;
	private JTextField textField_2;
	private JLabel label_4;
	private JTextField textField_3;

	private JButton button_2;
	private JButton button_3;
	private JButton button_4;




	/**
	 * Launch the application.
	 */
		//public static void main(String[] args) {
		//	EventQueue.invokeLater(new Runnable() {
		//		public void run() {
		//			try {
		//				TelaAlunos window = new TelaAlunos();
		//				window.frame.setVisible(true);
		//			} catch (Exception e) {
		//			e.printStackTrace();
		//		}
		//		}
		//});
		//}

	/**
	 * Create the application.
	 */
	public TelaAlunos() {
		initialize();
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setTitle("Aluno");
		frame.setBounds(100, 100, 729, 385);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				listagem();
			}
		});

		scrollPane = new JScrollPane();
		scrollPane.setBounds(21, 43, 674, 148);
		frame.getContentPane().add(scrollPane);

		table = new JTable();
		table.setGridColor(Color.BLACK);
		table.setRequestFocusEnabled(false);
		table.setFocusable(false);
		table.setBackground(Color.WHITE);
		table.setFillsViewportHeight(true);
		table.setRowSelectionAllowed(true);
		table.setFont(new Font("Tahoma", Font.PLAIN, 14));
		scrollPane.setViewportView(table);
		table.setBorder(new LineBorder(new Color(0, 0, 0)));
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setShowGrid(true);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

		label = new JLabel("");
		label.setVerticalAlignment(SwingConstants.TOP);
		label.setForeground(Color.BLUE);
		label.setBounds(21, 302, 674, 33);
		frame.getContentPane().add(label);

		label_6 = new JLabel("selecione");
		label_6.setBounds(21, 190, 431, 14);
		frame.getContentPane().add(label_6);

		label_2 = new JLabel("Nome:");
		label_2.setHorizontalAlignment(SwingConstants.LEFT);
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label_2.setBounds(21, 228, 71, 14);
		frame.getContentPane().add(label_2);

		textField_1 = new JTextField();
		textField_1.setFont(new Font("Dialog", Font.PLAIN, 12));
		textField_1.setColumns(10);
		textField_1.setBounds(68, 225, 195, 20);
		frame.getContentPane().add(textField_1);

		button_1 = new JButton("Criar");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(textField_1.getText().isEmpty() || textField_2.getText().isEmpty()
							|| textField_3.getText().isEmpty()) {
						label.setText("campo vazio");
						return;
					}
					String nome = textField_1.getText();
					String cpf = textField_2.getText();
					String matricula = textField_3.getText();
					Aluno a = Fachada.criarAluno(nome,cpf,matricula);

					label.setText("Aluno criado: "+a.getNome());
					listagem();
				}
				catch(Exception ex) {
					label.setText(ex.getMessage());
				}
			}
		});
		button_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		button_1.setBounds(273, 273, 86, 23);
		frame.getContentPane().add(button_1);

		button = new JButton("Listar");
		button.setFont(new Font("Tahoma", Font.PLAIN, 12));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listagem();
			}
		});
		button.setBounds(18, 11, 89, 23);
		frame.getContentPane().add(button);

		label_3 = new JLabel("Cpf:");
		label_3.setHorizontalAlignment(SwingConstants.LEFT);
		label_3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label_3.setBounds(21, 277, 63, 14);
		frame.getContentPane().add(label_3);

		textField_2 = new JTextField();
		textField_2.setFont(new Font("Dialog", Font.PLAIN, 12));
		textField_2.setColumns(10);
		textField_2.setBounds(50, 274, 213, 20);
		frame.getContentPane().add(textField_2);

		label_4 = new JLabel("Matricula:");
		label_4.setHorizontalAlignment(SwingConstants.LEFT);
		label_4.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label_4.setBounds(21, 253, 71, 14);
		frame.getContentPane().add(label_4);

		textField_3 = new JTextField();
		textField_3.setFont(new Font("Dialog", Font.PLAIN, 12));
		textField_3.setColumns(10);
		textField_3.setBounds(78, 250, 185, 20);
		frame.getContentPane().add(textField_3);

		button_2 = new JButton("Apagar");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					if (table.getSelectedRow() >= 0){
						int id = (int) table.getValueAt( table.getSelectedRow(), 0);
						Fachada.apagarAluno(id);
						label.setText("deletou Aluno " + id);
						listagem();
					}
					else
						label.setText("Aluno não selecionado");
				}
				catch(Exception ex) {
					label.setText(ex.getMessage());
				}
			}
		});
		button_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		button_2.setBounds(273, 249, 86, 23);
		frame.getContentPane().add(button_2);
		
		button_3 = new JButton("Adicionar Curso");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					if (table.getSelectedRow() >= 0){
						int id = (int) table.getValueAt( table.getSelectedRow(), 0);
						String nome = JOptionPane.showInputDialog(frame, "Digite o nome do Curso");
						Curso c = Fachada.localizarCurso(nome);
						Aluno a = Fachada.localizarAluno(id);
						
						JOptionPane.showMessageDialog(frame, " Aluno a ser matriculado =" + a.getNome());
						
						Object[] options = { "Confirmar", "Desistir" };
						int escolha = JOptionPane.showOptionDialog(null, "Confirma inscricao do Curso "+ nome, "Alerta",
										JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[1]);
						if(escolha == 0) {
							Fachada.adicionarAlunoCurso(nome, id);
							label.setText(a.getNome()+ " recebera boleto de pagamento de " + c.getPreco());
							listagem();
						}
						else
							label.setText("Não adicionou no curso " + nome );

					}
					else
						label.setText("Aluno não selecionado");
				}
				catch(NumberFormatException ex) {
					label.setText("Formato do id inválido");
				}
				catch(Exception ex) {
					label.setText(ex.getMessage());
				}
			}
		});
		button_3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		button_3.setBounds(552, 244, 143, 23);
		frame.getContentPane().add(button_3);
		
		button_4 = new JButton("Remover Curso");
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					if (table.getSelectedRow() >= 0){
						int id = (int) table.getValueAt( table.getSelectedRow(), 0);
						String nome = JOptionPane.showInputDialog(frame, "Digite o nome");
						Curso c = Fachada.localizarCurso(nome);
						Aluno a = Fachada.localizarAluno(id);
						
						JOptionPane.showMessageDialog(frame, "Preco do curso=" + c.getPreco()+ " - Aluno matriculado =" + a.getNome());
						
						Object[] options = { "Confirmar", "Desistir" };
						int escolha = JOptionPane.showOptionDialog(null, "Confirma desmatricula de " + nome, "Alerta",
										JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[1]);
						if(escolha == 0) {
							Fachada.removerAlunoCurso(nome, id);
							label.setText("O aluno foi desmatriculado de " + nome);
							listagem();
						}
						else
							label.setText("Não desmatriculou de " + nome );

					}
					else
						label.setText("Aluno não selecionado");
				}
				catch(NumberFormatException ex) {
					label.setText("Formato do id é inválido");
				}
				catch(Exception ex) {
					label.setText(ex.getMessage());
				}
			}
		});
		button_4.setFont(new Font("Tahoma", Font.PLAIN, 12));
		button_4.setBounds(552, 219, 143, 23);
		frame.getContentPane().add(button_4);
		
	}

	public void listagem() {
		try{
			List<Aluno> lista = Fachada.listarAlunos();
			
			//model contem todas as linhas e colunas da tabela
			DefaultTableModel model = new DefaultTableModel();

			//colunas
			model.addColumn("ID");
			model.addColumn("nome");
			model.addColumn("Cpf");
			model.addColumn("Matricula");
			model.addColumn("Cursos");

			//linhas
			String texto;
			for(Aluno a : lista) {
				texto=" ";
				for(Curso c : a.getCursos()) 
					texto += c.getNome()+ " " ;

				model.addRow(new Object[]{a.getId(), a.getNome(), a.getCpf(), a.getMatricula(), texto});

			}
			
			table.setModel(model);
			label_6.setText("resultados: "+lista.size()+ " Alunos  - selecione uma linha");
		}
		catch(Exception erro){
			label.setText(erro.getMessage());
		}
	}
	

}
