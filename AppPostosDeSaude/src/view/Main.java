package view;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import model.*;
import dao.*;
import model.Endereco;
import model.Equipamento;
import model.EquipamentoDoPosto;
import model.Paciente;
import model.PostoDeSaude;
import model.TipoEquipamento;

public class Main {
	public static void main(String[] args) {

		TipoEquipamentoDAO tipoEquipamentoDAO = new TipoEquipamentoDAO();
		EquipamentoDAO equipamentoDAO = new EquipamentoDAO();
		EnderecoDAO enderecoDAO = new EnderecoDAO();
		PacienteDAO pacienteDAO = new PacienteDAO();
		PostoDeSaudeDAO postoDeSaudeDAO = new PostoDeSaudeDAO();
		EquipamentoDoPostoDAO equipamentoDoPostoDAO = new EquipamentoDoPostoDAO();

		System.out.println("Digite a operação:");
		Scanner scanner = new Scanner(System.in);

		boolean loop = true;
		while (loop) {

			System.out.println("Bem vindo ao portal saude com transparencia");
			System.out.println("Digite:");
			System.out.println("[ 1 ] Realizar Cadastros");
			System.out.println("[ 2 ] Realizar Alterações");
			System.out.println("[ 3 ] Realizar Remoções");
			System.out.println("[ 4 ] Realizar Consultas");
			System.out.println("[ 0 ] Sair do Sistema");

			int comando = scanner.nextInt();

			switch (comando) {
			case 1:

				boolean cadLoop = true;
				while (cadLoop) {

					System.out.println("[ 1 ] Realizar Cadastro de Tipo de Equipamento");
					System.out.println("[ 2 ] Realizar Cadastro de Equipamentos");
					System.out.println("[ 3 ] Realizar Cadastro de Pacientes");
					System.out.println("[ 4 ] Realizar Cadastro de Posto de Saúde");
					System.out.println("[ 5 ] Realizar Cadastro de Equipamentos do Posto");
					System.out.println("[ 0 ] Sair da Aba Cadastro");

					int cadComando = scanner.nextInt();

					switch (cadComando) {
					case 1:
						System.out.println("Digite o nome do Tipo de Equipamento: ");				
						String nomeTipoEqui = scanner.next(); 
						
						TipoEquipamento tipoEquipamento = new TipoEquipamento(nomeTipoEqui);
						
						if(tipoEquipamentoDAO.addTipoEquipamento(tipoEquipamento)) {
							System.out.println("cadastrado com sucesso!");
							break;
						}else {
							System.out.println("Falha ao tenter realizar cadastrado");
						}
						break;
					case 2:
						ArrayList<TipoEquipamento> listaTipoEquipamento = new ArrayList<TipoEquipamento>();
						listaTipoEquipamento = tipoEquipamentoDAO.getListTipoEquipamento();
						if(listaTipoEquipamento.size()>0) {
							System.out.println("Escolha qual é o Tipo desse Equipamento: ");				
							
							for (TipoEquipamento tipoEquipamento2 : listaTipoEquipamento) {
								System.out.println("["+tipoEquipamento2.getIdTipoEquipamento()+"]:"+ tipoEquipamento2.getTipoEquiNome() );
								
							}
							
							System.out.println("Digite o numero do Tipo desse Equipamento: ");									
							int idTipoEquipamento = scanner.nextInt(); 
							
							System.out.println("Digite a descrição do Equipamento: ");				
							String descricao = scanner.next(); 
							
							
							Equipamento equipamento = new Equipamento(idTipoEquipamento, descricao);
							
							if(equipamentoDAO.addEquipamento(equipamento) ) {
								System.out.println("cadastrado com sucesso!");
							}else {
								System.out.println("Falha ao tenter realizar cadastrado");
							}
						}else {
							System.out.println("è preciso cadastrar primeiro um tipo de Equipamento!");
						}
						break;
					case 3:
						ArrayList<PostoDeSaude> listaPostoDeSaude = new ArrayList<PostoDeSaude>();
						listaPostoDeSaude = postoDeSaudeDAO.getListPostoDeSaude();
						if(listaPostoDeSaude.size()>0) {
						
							System.out.println("Digite o nome do Paciente:\n ");
							String pacNome = scanner.next();
							System.out.println("Digite o id do enderenco do Paciente:\n ");
							int idEndereco = scanner.nextInt();
							
							System.out.println("Listas dos Postos de Saúde:\n ");
							for (PostoDeSaude postoDeSaude : listaPostoDeSaude) {
								System.out.println("["+postoDeSaude.getIdPosto() +"]: Nome = "+postoDeSaude.getpNome()+"Endereco = "+postoDeSaude.getIdEndereco());
							}
							System.out.println("Escolha o numero do Posto do Paciente:\n ");
							
							int idPosto = scanner.nextInt();
							
							Paciente paciente = new Paciente(pacNome, idEndereco, idPosto);
							
							if(pacienteDAO.addPaciente(paciente)) {	
								System.out.println("cadastrado com sucesso!");
								break;
							}else {
								System.out.println("Falha ao tenter realizar cadastrado");
							}
						}else {
							System.out.println("Primeiro é necessario ter algun Posto de Saúde cadastrado!");
						}
						
						break;
					case 4:
						try {
							int numero;
							String pNome, rua, bairro, cep, estado;
							
							System.out.println("Digite o nome do Posto: ");
							pNome = scanner.next();
							
							System.out.println("Digite o nome da Rua: ");
							rua = scanner.next();
							
							System.out.println("Digite o número da Casa: ");
							numero = scanner.nextInt();
							
							System.out.println("Digite o nome do Bairro: ");
							bairro = scanner.next();
							
							System.out.println("Digite o número do Cep: ");
							cep = scanner.next();
							// System.out.println("Digite o nome da Cidade: ");
							// cidade = scanner.nextLine();
							System.out.println("Digite o nome do Estado: ");
							estado = scanner.next();
	
							Endereco end = new Endereco(rua, numero, bairro, cep, estado);
							if (enderecoDAO.adicionarEndereco(end)) {
								int idEndereco = enderecoDAO.getIdByObjeto(end);
								if(idEndereco > 0) {
									PostoDeSaude posto = new PostoDeSaude(pNome, idEndereco);
									if (postoDeSaudeDAO.adicionarPostoDeSaude(posto)) {
										System.out.println("Posto Cadastrado com sucesso!!!");
									}
								}else{
									System.out.println("Ocorreu Algum erro com seu endereco!!!");
								}
									
							}
						} catch (Exception e) {
							System.out.println(e.getMessage());
						}
							

						break;
					case 5:
						// int vetorIds[] = equipamentoDoPostoDAO.getIdByObjeto(equiDoPosto);
						
						boolean cadEqPo = true;
						while (cadEqPo) {
							
							
							System.out.println("Selecione o Posto degejado: ");
							ArrayList<PostoDeSaude> postos = postoDeSaudeDAO.getListPostoDeSaude();
							
							for (PostoDeSaude postoDeSaude : postos) {
								System.out.println("[ " +postoDeSaude.getIdPosto()+ " ]" + "Nome do Posto: " + postoDeSaude.getpNome());
							}
							System.out.println("[ 0 ] Para Sair");
							int escolhaPosto = scanner.nextInt();
							if(escolhaPosto > 0) {
								int idPosto = postos.get(escolhaPosto).getIdPosto();
								
								boolean cadEqPoEqui = true;
								while(cadEqPoEqui) {
									
								
									System.out.println("Selecione o Equipamento degejado: ");
									ArrayList<Equipamento> equipamentos = equipamentoDAO.getListEquipamento();
									//ArrayList<EquipamentoDoPosto> equipamentosDoPosto = equipamentoDoPostoDAO.getListEquipamentoDoPosto(idPosto);
									
//									ArrayList<Equipamento> equipamentosQueNaoEstaoNoPosto = new ArrayList<>();
//									
//									for (EquipamentoDoPosto equipamentoDoPosto : equipamentosDoPosto) {
//										for (Equipamento equipamento : equipamentos) {
//											equipamentosQueNaoEstaoNoPosto = equipamentoDAO.getListEquipamentosQueNaoEstaoNoPosto(equipamento.getIdEquipamento(), equipamentoDoPosto.getIdPosto());
//										}
//										
//										
//									}
									
									
									
									
									for (Equipamento equipamento : equipamentos) {
										System.out.println("[ " + equipamento.getIdEquipamento() + " ] " + "Tipo: "+equipamento.getIdTipoEquipamento() + " Descrição: "
									    + equipamento.getDescricao());
									}
									System.out.println("[ 0 ] Para Sair");
									
									int escolhaEquipamento = scanner.nextInt();
									
									if(escolhaEquipamento > 0) {
										int idEquipamento = equipamentos.get(escolhaEquipamento).getIdEquipamento();
										
										System.out.println("Digite a quantidade de equipamentos");
										int qtdEquipamento = scanner.nextInt();
										
										System.out.println("Digite a data de Entrega ou que foi entregue");
										String dataDeEntrega = scanner.nextLine();
										
										EquipamentoDoPosto equipDoPosto = new EquipamentoDoPosto(idPosto, idEquipamento, qtdEquipamento, dataDeEntrega);
										
										if(equipamentoDoPostoDAO.adicionarEquipamentoNoPosto(equipDoPosto)) {
											System.out.println("Equipamento cadastrado com sucesso!!!");
										}else {
											System.out.println("Ocorreu algum erro no percurso, tente novamente!!!");
										}
									}
								
								}
								
							}else {
								System.out.println("Selecione um número válido!!!");
							}
							
						}

						break;
					case 0:
						cadLoop = false;
						break;

					default:
						System.out.println("Comando Inválido!!! Tente um novo comando");
						break;
					}
				}

				break;
			case 2:

				boolean altLoop = true;
				while (altLoop) {

					System.out.println("[ 1 ] Realizar Alteração de Tipo de Equipamento");
					System.out.println("[ 2 ] Realizar Alteração de Equipamentos");
					System.out.println("[ 3 ] Realizar Alteração de Pacientes");
					System.out.println("[ 4 ] Realizar Alteração de Posto de Saúde");
					System.out.println("[ 5 ] Realizar Alteração de Equipamentos do Posto");
					System.out.println("[ 0 ] Sair da Aba Alterações");

					int altComando = scanner.nextInt();

					switch (altComando) {
					case 1:

						break;
					case 2:

						break;
					case 3:

						break;
					case 4:

						break;
					case 5:

						break;
					case 0:
						altLoop = false;
						break;

					default:
						System.out.println("Comando Inválido!!! Tente um novo comando");
						break;
					}
				}

				break;
			case 3:

				boolean remLoop = true;
				while (remLoop) {

					System.out.println("[ 1 ] Realizar Remover de Tipo de Equipamento");
					System.out.println("[ 2 ] Realizar Remover de Equipamentos");
					System.out.println("[ 3 ] Realizar Remover de Pacientes");
					System.out.println("[ 4 ] Realizar Remover de Posto de Saúde");
					System.out.println("[ 5 ] Realizar Remover de Equipamentos do Posto");
					System.out.println("[ 0 ] Sair da Aba Remoções");

					int remComando = scanner.nextInt();

					switch (remComando) {
					case 1:
						ArrayList<TipoEquipamento> listaTipoEquipamento = new ArrayList<TipoEquipamento>();
						listaTipoEquipamento = tipoEquipamentoDAO.getListTipoEquipamento();
						if(listaTipoEquipamento.size()>0) {
							System.out.println("Escolha qual é o Tipo desse Equipamento: ");				
							
							for (TipoEquipamento tipoEquipamento2 : listaTipoEquipamento) {
								System.out.println("["+tipoEquipamento2.getIdTipoEquipamento()+"]:"+ tipoEquipamento2.getTipoEquiNome() );
								
							}
							
							System.out.println("Digite o numero do Tipo de Equipamento que será removido:");
							int idTipoEquipamento = scanner.nextInt();
							
							if(tipoEquipamentoDAO.deleteEquipamento(idTipoEquipamento)) {
								System.out.println("Tipo de equipamento removido com Sucesso!");
							}else {
								System.out.println("Tipo de equipamento não encontrado com esse ID!");
							}
						}else{
							System.out.println("Não há nenhum tipo de Equipamento cadastrado!");
						}
						break;
					case 2:
						ArrayList<Equipamento> listaEquipamentos = new ArrayList<Equipamento>();
						listaEquipamentos = equipamentoDAO.getListEquipamento();
						if(listaEquipamentos.size()>0) {
							for (Equipamento equipamento2 : listaEquipamentos) {
								System.out.println("["+equipamento2.getIdEquipamento() +"]: Descrição = "+equipamento2.getDescricao());
							}
							System.out.println("Digite o numero do Equipamento que será removido:");
							int idEquipamento = scanner.nextInt();
							
							if(equipamentoDAO.deleteEquipamento(idEquipamento)) {
								System.out.println("Equipamento removido com Sucesso!");
							}else {
								System.out.println("Equipamento não encontrado com esse ID!");
							}
						}else {
							System.out.println("Não há equipamento cadastrado no sistema!");
						}
						break;
					case 3:
						ArrayList<Paciente> listaPacientes = new ArrayList<Paciente>();
						listaPacientes = pacienteDAO.getListPaciente();
						if(listaPacientes.size()>0) {
							
							try {
								System.out.println("Lista dos Pacientes cadastrado no sistema!");
								for (Paciente paciente : listaPacientes) {
									System.out.println("id ["+paciente.getIdPaciente()+"]: Nome = "+paciente.getPacNome());
								}
								System.out.println("Digite o id do Paciente que será removido:");
								int idPaciente = scanner.nextInt();
								
								if(pacienteDAO.deletePaciente(idPaciente)) {
									System.out.println("Paciente removido com Sucesso!");
								}else {
									System.out.println("Paciente não encontrado com esse ID!");
								}
							}catch (InputMismatchException e) {
								System.out.println("Deve ser inserido numero não uma letra!");
							}	
						}else {
							System.out.println("Não há Paciente cadastrado no sistema!");
						}
					
						break;
					case 4:

						break;
					case 5:

						break;
					case 0:
						remLoop = false;
						break;

					default:
						System.out.println("Comando Inválido!!! Tente um novo comando");
						break;
					}
				}

				break;
			case 4:

				boolean conLoop = true;
				while (conLoop) {

					System.out.println("[ 1 ] Realizar Consulta de Tipo de Equipamento");
					System.out.println("[ 2 ] Realizar Consulta de Equipamentos");
					System.out.println("[ 3 ] Realizar Consulta de Pacientes");
					System.out.println("[ 4 ] Realizar Consulta de Posto de Saúde");
					System.out.println("[ 5 ] Realizar Consulta de Equipamentos do Posto");
					System.out.println("[ 0 ] Sair da Aba Consultas");

					int conComando = scanner.nextInt();

					switch (conComando) {
					case 1:
						ArrayList<TipoEquipamento> listaTipoEquipamento = new ArrayList<TipoEquipamento>();
						listaTipoEquipamento = tipoEquipamentoDAO.getListTipoEquipamento();
						if(listaTipoEquipamento.size()>0) {
							System.out.println("Digite o id do Tipo do Equipamento: ");
							int idTipoEquipamento = scanner.nextInt();
							TipoEquipamento tipoEquipamento = tipoEquipamentoDAO.getTipoEquipamentoById(idTipoEquipamento);
							
							System.out.println("Nome: "+tipoEquipamento.getTipoEquiNome());
						}else {
							System.out.println("Não há tipo de Equipamento cadastrado no sistema!");
						}
						break;
					case 2:
						ArrayList<Equipamento> listaEquipamentos = new ArrayList<Equipamento>();
						listaEquipamentos = equipamentoDAO.getListEquipamento();
						if(listaEquipamentos.size()>0) {
							System.out.println("Digite o id do Equipamento: ");
							int idEquipamento = scanner.nextInt();
							Equipamento equipamento = equipamentoDAO.getEquipamentoByID(idEquipamento);
							
							System.out.println("Descrição do Equipamento: "+equipamento.getDescricao());
							
						}else {
							System.out.println("Não há equipamento cadastrado no sistema!");
						}
						break;
					case 3:

						break;
					case 4:

						break;
					case 5:

						break;
					case 0:
						conLoop = false;
						break;

					default:
						System.out.println("Comando Inválido!!! Tente um novo comando");
						break;
					}
				}

				break;
			case 0:
				loop = false;
				break;

			default:
				System.out.println("Comando Inválido!!! Tente um novo comando");
				break;
			}

		}

	}
}


//package view;
//
//import java.util.ArrayList;
//import java.util.InputMismatchException;
//import java.util.Scanner;
//
//import dao.*;
//import jdbc.Conexao;
//import model.*;
//
//public class Main {
//	public static void main(String[] args) {
//		Conexao conexao = new Conexao();
//		
//		TipoEquipamentoDAO tipoEquipamentoDAO = new TipoEquipamentoDAO();
//		EquipamentoDAO equipamentoDAO = new EquipamentoDAO();
//		EnderecoDAO enderecoDAO = new EnderecoDAO();
//		PacienteDAO pacienteDAO = new PacienteDAO();
//		PostoDeSaudeDAO postoDeSaudeDAO = new PostoDeSaudeDAO();
//		EquipamentoDoPostoDAO equipamentoDoPosto = new EquipamentoDoPostoDAO();
//		
//		
//		System.out.println("Digite a operação:");
//		Scanner scanner = new Scanner(System.in);
//		int comando = scanner.nextInt();
//		
//
////		
////		
//////		enderecoDAO.adicionarEndereco(endereco);
//////		
//////		postoDeSaudeDAO.adicionarPostoDeSaude(posto);
//////		
////	//	pacienteDAO.addPaciente(paciente);
////		
////		TipoEquipamento  tipoEquipamentoTeste = new TipoEquipamento(3,"ss"); 
////		if(tipoEquipamentoDAO.atualizarTipoEquipamento(tipoEquipamentoTeste))
////			System.out.println("Oko");
////		
////		Equipamento  Equipamento = new Equipamento(1,3,"combate ");
////		if(equipamentoDAO.atualizarEquipamento(Equipamento))
////			System.out.println("Oko");
////		
//		
////		
////			switch (comando) {
////			//cadastrar
////			case 1://ok
////				System.out.println("Digite o nome do Tipo de Equipamento: ");				
////				String nomeTipoEqui = scanner.nextLine(); 
////				
////				TipoEquipamento tipoEquipamento = new TipoEquipamento(nomeTipoEqui);
////				
////				if(tipoEquipamentoDAO.addTipoEquipamento(tipoEquipamento)) {
////					System.out.println("cadastrado com sucesso!");
////					break;
////				}else {
////					System.out.println("Falha ao tenter realizar cadastrado");
////				}
////				break;
////			case 2://ok
////				ArrayList<TipoEquipamento> listaTipoEquipamento = new ArrayList<TipoEquipamento>();
////				listaTipoEquipamento = tipoEquipamentoDAO.getListTipoEquipamento();
////				if(listaTipoEquipamento.size()>0) {
////					System.out.println("Escolha qual é o Tipo desse Equipamento: ");				
////					
////					for (TipoEquipamento tipoEquipamento2 : listaTipoEquipamento) {
////						System.out.println("["+tipoEquipamento2.getIdTipoEquipamento()+"]:"+ tipoEquipamento2.getTipoEquiNome() );
////						count++;
////					}
////					
////					System.out.println("Digite o numero do Tipo desse Equipamento: ");									
////					int idTipoEquipamento = scanner.nextInt(); 
////					
////					System.out.println("Digite a descrição do Equipamento: ");				
////					String descricao = scanner.nextLine(); 
////					
////					
////					Equipamento equipamento = new Equipamento(idTipoEquipamento, descricao);
////					
////					if(equipamentoDAO.addEquipamento(equipamento) ) {
////						System.out.println("cadastrado com sucesso!");
////						//break;
////					}else {
////						System.out.println("Falha ao tenter realizar cadastrado");
////					}
////					//break;
////				}else {
////					System.out.println("è preciso cadastrar primeiro um tipo de Equipamento!");
////				}
////			case 3://quase
////				ArrayList<PostoDeSaude> listaPostoDeSaude = new ArrayList<PostoDeSaude>();
////				// falta a lista posto postoDeSaudeDAO.;
////				if(listaPostoDeSaude.size()>0) {
////				
////					System.out.println("Digite o nome do Paciente:\n ");
////					String pacNome = scanner.nextLine();
////					System.out.println("Digite o id do enderenco do Paciente:\n ");
////					int idEndereco = scanner.nextInt();
////					
////					System.out.println("Listas dos Postos de Saúde:\n ");
////					for (PostoDeSaude postoDeSaude : listaPostoDeSaude) {
////						System.out.println("["+postoDeSaude.getIdPosto() +"]: Nome = "+postoDeSaude.getpNome()+"Endereco = "+postoDeSaude.getIdEndereco());
////					}
////					System.out.println("Escolha o numero do Posto do Paciente:\n ");
////					
////					int idPosto = scanner.nextInt();
////					
////					Paciente paciente = new Paciente(pacNome, idEndereco, idPosto);
////					
////					if(pacienteDAO.addPaciente(paciente)) {	
////						System.out.println("cadastrado com sucesso!");
////					}else {
////						System.out.println("Falha ao tenter realizar cadastrado");
////					}
////				}else {
////					System.out.println("Primeiro é necessario ter algun Posto de Saúde cadastrado!");
////				}
////				System.out.println("Tamanho : "+ listaPostoDeSaude.size());
////				break;			
////				
////			default:
////				break;
////			}
//////			
////			int Remover = 1;
////			switch (Remover) {
////			case 1://ok
////				ArrayList<TipoEquipamento> listaTipoEquipamento = new ArrayList<TipoEquipamento>();
////				listaTipoEquipamento = tipoEquipamentoDAO.getListTipoEquipamento();
////				if(listaTipoEquipamento.size()>0) {
////					System.out.println("Escolha qual é o Tipo desse Equipamento: ");				
////					
////					for (TipoEquipamento tipoEquipamento2 : listaTipoEquipamento) {
////						System.out.println("["+tipoEquipamento2.getIdTipoEquipamento()+"]:"+ tipoEquipamento2.getTipoEquiNome() );
////						
////					}
////					
////					System.out.println("Digite o numero do Tipo de Equipamento que será removido:");
////					int idTipoEquipamento = scanner.nextInt();
////					
////					if(tipoEquipamentoDAO.deleteEquipamento(idTipoEquipamento)) {
////						System.out.println("Tipo de equipamento removido com Sucesso!");
////					}else {
////						System.out.println("Tipo de equipamento não encontrado com esse ID!");
////					}
////				}else{
////					System.out.println("Não há nenhum tipo de Equipamento cadastrado!");
////				}
//			//	break;
////			case 2://ok
////				ArrayList<Equipamento> listaEquipamentos = new ArrayList<Equipamento>();
////				listaEquipamentos = equipamentoDAO.getListEquipamento();
////				if(listaEquipamentos.size()>0) {
////					for (Equipamento equipamento2 : listaEquipamentos) {
////						System.out.println("["+equipamento2.getIdEquipamento() +"]: Descrição = "+equipamento2.getDescricao());
////					}
////					System.out.println("Digite o numero do Equipamento que será removido:");
////					int idEquipamento = scanner.nextInt();
////					
////					if(equipamentoDAO.deleteEquipamento(idEquipamento)) {
////						System.out.println("Equipamento removido com Sucesso!");
////					}else {
////						System.out.println("Equipamento não encontrado com esse ID!");
////					}
//////					break;
////				}else {
////					System.out.println("Não há equipamento cadastrado no sistema!");
////				}
////			case 3://ok
////				ArrayList<Paciente> listaPacientes = new ArrayList<Paciente>();
////				listaPacientes = pacienteDAO.getListPaciente();
////				if(listaPacientes.size()>0) {
////					
////					try {
////						System.out.println("Lista dos Pacientes cadastrado no sistema!");
////						for (Paciente paciente : listaPacientes) {
////							System.out.println("id ["+paciente.getIdPaciente()+"]: Nome = "+paciente.getPacNome());
////						}
////						System.out.println("Digite o id do Paciente que será removido:");
////						int idPaciente = scanner.nextInt();
////						
////						if(pacienteDAO.deletePaciente(idPaciente)) {
////							System.out.println("Paciente removido com Sucesso!");
////						}else {
////							System.out.println("Paciente não encontrado com esse ID!");
////						}
////					}catch (InputMismatchException e) {
////						System.out.println("Deve ser inserido numero não uma letra!");
////					}	
////				}else {
////					System.out.println("Não há Paciente cadastrado no sistema!");
////				}
////				break;
////			default:
////				break;
////			}
//			
////			//Buscar
////			int buscar = 1;
////			
////			switch (buscar) {
////			case 1:
//				System.out.println("Digite o id do Tipo do Equipamento: ");
//				int idTipoEquipamento = scanner.nextInt();
//				TipoEquipamento tipoEquipamento = tipoEquipamentoDAO.getTipoEquipamentoById(idTipoEquipamento);
//				
//				System.out.println("Nome: "+tipoEquipamento.getTipoEquiNome());
//				break;
////			case 2:
//				System.out.println("Digite o id do Equipamento: ");
//				int idEquipamento = scanner.nextInt();
//				Equipamento equipamento = equipamentoDAO.getEquipamentoByID(idEquipamento);
//				
//				System.out.println("Descrição do Equipamento: "+equipamento.getDescricao());
//				
////				break;
////			case 3:
////				System.out.println("Digite o id do Paciente: ");
////				int idPaciente = scanner.nextInt();
////				
////				Paciente paciente = pacienteDAO.getPacienteById(idPaciente);
////				System.out.println("Nome do Paciente: "+paciente.getPacNome());
////			default:
////				break;
////			}
////			//listaTodos
////			int listaTodos =1;
////			switch (listaTodos) {
////			case 1://ok
////				ArrayList<TipoEquipamento> listaTipoEquipamentos = new ArrayList<TipoEquipamento>();
////				listaTipoEquipamentos = tipoEquipamentoDAO.getListTipoEquipamento();
////				if(listaTipoEquipamentos.size()>0) {
////					System.out.println("todos os tipos de Equipamento: ");
////					for (TipoEquipamento tipoEquipamento : listaTipoEquipamentos) {
////						System.out.println("Nome: "+ tipoEquipamento.getTipoEquiNome());
////					}
////				}else {
////					System.out.println("Não há tipo de equipamento cadastrado no sistema!");
////				}
////				break;
////			case 2://ok
////				ArrayList<Equipamento> listaEquipamentos = new ArrayList<Equipamento>();
////				listaEquipamentos = equipamentoDAO.getListEquipamento();
////				
////				if(listaEquipamentos.size()>0) {
////					System.out.println("todos os Equipamento: ");
////					for (Equipamento equipamento : listaEquipamentos) {
////						System.out.println("Descrição: "+ equipamento.getDescricao());
////					}
////				}else {
////					System.out.println("Não há equipamento cadastrado no sistema!");
////				}
////				break;
////			case 3://ok
////				ArrayList<Paciente> listaPacientes = new ArrayList<Paciente>();
////				listaPacientes = pacienteDAO.getListPaciente();
////				if(listaPacientes.size()>0) {
////					System.out.println("todos os pacientes: ");
////					for (Paciente paciente : listaPacientes) {
////						System.out.println("Nome do Paciente: "+ paciente.getPacNome());
////					}
////				}else {
////					System.out.println("Não há paciente cadastrado no sistema");
////				}
////				break;
////			default:
////				break;
////			}
////			
////			
////			//Altera 
//		
//			// [1]altera todos
//			// [2] altera descricao
//			// [3] altera tipo do equipamento
////			int alterar =1;
////			
////			switch (alterar) {		
////			
////			case 1://ok talvez lista
////				System.out.println("Digite o id do Tipo de Equipamento para ser atualizado:");
////				int idTipoEquipamento = scanner.nextInt();
////				System.out.println("Digite o novo nome do Tipo de Equipamento:");
////				String tipoEquiNome = scanner.nextLine();
////				TipoEquipamento tipoEquipamento = new TipoEquipamento(idTipoEquipamento,tipoEquiNome);
////				
////				if(tipoEquipamentoDAO.atualizarTipoEquipamento(tipoEquipamento)) {
////					System.out.println("Atualizado com sucesso!!");
////				}else {
////					System.out.println("Tipo de Equipamento não encontrado!!");
////				}
////				
////				break;
////			case 2:
////				System.out.println("Digite o numero do campo que será atualizado");
////				System.out.println("[1]altera todos:");
////				System.out.println("[2] altera descricao:");
////				System.out.println("[3] altera tipo do equipamento:");
////				
////				break;	
////			default:
////				break;
////			}
// 
//			
//	}
//}
//
//
