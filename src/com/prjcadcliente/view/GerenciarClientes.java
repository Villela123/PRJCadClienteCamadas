package com.prjcadcliente.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.prjcadcliente.dominio.Cliente;
import com.prjcadcliente.persistencia.CRUDCliente;

public class GerenciarClientes extends JFrame {

	private JPanel contentPane;
	private JTable tbClientesCadastrados;
	private JTextField txtNome;
	private JTextField txtTelefone;
	private JTextField txtIdade;
	private JTextField txtEmail;
	private Cliente cliente;
	private CRUDCliente crud;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GerenciarClientes frame = new GerenciarClientes();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GerenciarClientes() {
	//vamos instanciar as classes Cliente e CRUD
		cliente = new Cliente();
		crud = new CRUDCliente();
		setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 460, 547);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.setBounds(26, 208, 89, 23);
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
	//passar os dados do formulário para o objeto cliente
				cliente.setNome(txtNome.getText());
				cliente.setEmail(txtEmail.getText());
				cliente.setTelefone(txtTelefone.getText());
				cliente.setIdade(Integer.parseInt(txtIdade.getText()));
				String retorno = crud.cadastrar(cliente);
				JOptionPane.showMessageDialog(null, retorno);
				txtNome.setText("");
				txtEmail.setText("");
				txtTelefone.setText("");
				txtIdade.setText("");
			}
		});
		contentPane.setLayout(null);
		contentPane.add(btnCadastrar);
		
		JButton btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.setBounds(323, 208, 89, 23);
		contentPane.add(btnPesquisar);
		
		JButton btnAtualizar = new JButton("Atualizar");
		btnAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = JOptionPane.showInputDialog("Digite o Id do cliente");				
				cliente.setNome(txtNome.getText());
				cliente.setEmail(txtEmail.getText());
				cliente.setTelefone(txtTelefone.getText());
				cliente.setIdade(Integer.parseInt(txtIdade.getText()));
				cliente.setId(Integer.parseInt(id));
				String retorno = crud.atualizar(cliente);
				JOptionPane.showMessageDialog(null, retorno);
				txtNome.setText("");
				txtEmail.setText("");
				txtTelefone.setText("");
				txtIdade.setText("");
			}
		});
		btnAtualizar.setBounds(125, 208, 89, 23);
		contentPane.add(btnAtualizar);
		
		JButton btnDeletar = new JButton("Deletar");
		btnDeletar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = JOptionPane.showInputDialog("Digite o Id do cliente para apagar");
				cliente.setId(Integer.parseInt(id));
				JOptionPane.showMessageDialog(null, crud.deletar(cliente));
			}
		});
		btnDeletar.setBounds(224, 208, 89, 23);
		btnDeletar.setBackground(Color.WHITE);
		contentPane.add(btnDeletar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(26, 275, 386, 222);
		contentPane.add(scrollPane);
		
		String[] colunas = {"Id","Nome","Email","Telefone","Idade"};
		
		Object[][] dados = {
				{15,"Roberto","roberto@gmail.com","11111111",12},
				{16,"Roberta","roberta@gmail.com","11111111",12},
				{17,"Robertinho","robertinho@gmail.com","11111111",12},
				{18,"Robertinha","robertinha@gmail.com","11111111",12},
				{19,"Robertao","robertao@gmail.com","11111111",12},
				{20,"Robertona","robertona@gmail.com","11111111",12},
		};
		
	//vamos construir o modelo de dados para exibir na tabela
		
		DefaultTableModel modelo = new DefaultTableModel(dados,colunas);
		
		tbClientesCadastrados = new JTable(modelo);
		scrollPane.setViewportView(tbClientesCadastrados);
		
		
		
		txtNome = new JTextField();
		txtNome.setBounds(26, 43, 199, 20);
		contentPane.add(txtNome);
		txtNome.setColumns(10);
		
		txtTelefone = new JTextField();
		txtTelefone.setBounds(26, 126, 96, 20);
		contentPane.add(txtTelefone);
		txtTelefone.setColumns(10);
		
		txtIdade = new JTextField();
		txtIdade.setBounds(251, 126, 29, 20);
		contentPane.add(txtIdade);
		txtIdade.setColumns(10);
		
		txtEmail = new JTextField();
		txtEmail.setBounds(251, 43, 163, 20);
		contentPane.add(txtEmail);
		txtEmail.setColumns(10);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(26, 18, 48, 14);
		contentPane.add(lblNome);
		
		JLabel lblTelefone = new JLabel("Telefone:");
		lblTelefone.setBounds(26, 101, 48, 14);
		contentPane.add(lblTelefone);
		
		JLabel lblIdade = new JLabel("Idade:");
		lblIdade.setBounds(251, 101, 48, 14);
		contentPane.add(lblIdade);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setBounds(251, 18, 48, 14);
		contentPane.add(lblEmail);
	}
}
