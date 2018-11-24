package view;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import org.json.simple.JSONObject;

import dao.*;
import model.*;

public class Main {
	private static Scanner scanner;
	private static Scanner scannerStrings;

	public static void main(String[] args) {

		TipoEquipamentoDAO tipoEquipamentoDAO = new TipoEquipamentoDAO();
		EquipamentoDAO equipamentoDAO = new EquipamentoDAO();
		EnderecoDAO enderecoDAO = new EnderecoDAO();
		PacienteDAO pacienteDAO = new PacienteDAO();
		PostoDeSaudeDAO postoDeSaudeDAO = new PostoDeSaudeDAO();
		EquipamentoDoPostoDAO equipamentoDoPostoDAO = new EquipamentoDoPostoDAO();

		System.out.println("Digite a operação:");
		scanner = new Scanner(System.in);
		scannerStrings = new Scanner(System.in);

		

			boolean loop = true;
			while (loop) {
				menuInicial();
				int comando = scanner.nextInt();

				switch (comando) {
				case 1:

					boolean cadLoop = true;
					while (cadLoop) {
						meuCadastro();
						int cadComando = scanner.nextInt();

						switch (cadComando) {
						case 1:

							System.out.println("Digite o nome do Tipo de Equipamento: ");
							String nomeTipoEqui = scannerStrings.nextLine();

							TipoEquipamento tipoEquipamento = new TipoEquipamento(nomeTipoEqui);

							if (tipoEquipamentoDAO.addTipoEquipamento(tipoEquipamento)) {
								System.out.println("cadastrado com sucesso!");
								break;
							} else {
								System.out.println("Falha ao tentar realizar cadastrado");
							}

							break;
						case 2:

							ArrayList<TipoEquipamento> listaTipoEquipamento = new ArrayList<TipoEquipamento>();
							listaTipoEquipamento = tipoEquipamentoDAO.getListTipoEquipamento();
							if (listaTipoEquipamento.size() > 0) {
								System.out.println("Escolha qual é o Tipo desse Equipamento: ");

								for (TipoEquipamento tipoEquipamento2 : listaTipoEquipamento) {
									System.out.println("[ " + tipoEquipamento2.getIdTipoEquipamento() + " ] "
											+ tipoEquipamento2.getTipoEquiNome());
								}
								System.out.println("[ 0 ] Para sair");
								System.out.println("Digite o numero do Tipo desse Equipamento: ");
								int idTipoEquipamento = scanner.nextInt();

								if (idTipoEquipamento > 0 && idTipoEquipamento <= tipoEquipamentoDAO.getIdMax()) {
									System.out.println("Digite a descrição do Equipamento: ");
									String descricao = scannerStrings.nextLine();

									Equipamento equipamento = new Equipamento(descricao, idTipoEquipamento);

									if (equipamentoDAO.addEquipamento(equipamento)) {
										System.out.println("cadastrado com sucesso!");
									} else {
										System.out.println("Falha ao tenter realizar cadastrado");
									}
								} else if (idTipoEquipamento == 0) {
									break;
								} else {
									System.out.println("Digite apenas números que estão entre as opções.");
								}
							} else {
								System.out.println("è preciso cadastrar primeiro um tipo de Equipamento!");
							}

							break;
						case 3:
							ArrayList<PostoDeSaude> listaPostoDeSaudes = new ArrayList<PostoDeSaude>();
							listaPostoDeSaudes = postoDeSaudeDAO.getListPostoDeSaude();

							if (listaPostoDeSaudes.size() > 0) {
								System.out.println("Listas dos Postos de Saúde:\n ");
								for (PostoDeSaude postoDeSaude : listaPostoDeSaudes) {
									Endereco endereco = enderecoDAO.getEnderecoById(postoDeSaude.getIdEndereco());
									System.out.print("[ " + postoDeSaude.getIdPosto() + " ] " + "Nome do Posto: "
											+ postoDeSaude.getpNome());
									System.out.println(" Rua: " + endereco.getRua() + " Numero: " + endereco.getNumero()
											+ " Bairro: " + endereco.getBairro() + " CEP: " + endereco.getCep()
											+ " Estado: " + endereco.getEstado());

								}
								System.out.println("[ 0 ] Para sair");

								System.out.println("Escolha o numero do Posto do Paciente:\n ");
								int idPosto = scanner.nextInt();

								if (idPosto > 0 && idPosto <= postoDeSaudeDAO.getIdMax()) {

									System.out.println("Digite o nome do Paciente:\n ");
									String pacNome = scannerStrings.nextLine();

									int numero;
									String rua, bairro, cep, estado;

									System.out.println("Digite o nome da Rua: ");
									rua = scannerStrings.nextLine();

									System.out.println("Digite o número da Casa: ");
									numero = scanner.nextInt();

									System.out.println("Digite o nome do Bairro: ");
									bairro = scannerStrings.nextLine();

									System.out.println("Digite o número do Cep: ");
									cep = scannerStrings.nextLine();
									// System.out.println("Digite o nome da Cidade: ");
									// cidade = scanner.nextLine();
									System.out.println("Digite o nome do Estado: ");
									estado = scannerStrings.nextLine();

									Endereco end = new Endereco(rua, numero, bairro, cep, estado);
									if (enderecoDAO.adicionarEndereco(end)) {
										int idEndereco = enderecoDAO.getIdByObjeto(end);

										if (idEndereco > 0) {
											Paciente paciente = new Paciente(pacNome,idPosto,idEndereco);
											if (pacienteDAO.addPaciente(paciente)) {
												System.out.println("cadastrado com sucesso!");
											} else {
												System.out.println("Falha ao tenter realizar cadastrado");
											}
										} else {
											System.out.println("Erro ao cadastrar 1 endereço. Tente novamente.");
										}
									} else {
										System.out.println("Erro ao cadastrar endereço. Tente novamente.");
									}

								} else if (idPosto == 0) {
									break;
								} else {
									System.out.println("Digite apenas números que estão entre as opções.");
								}

							} else {
								System.out.println("Primeiro é necessario ter algun Posto de Saúde cadastrado!");
							}

							break;
						case 4:

							int numero;
							String pNome, rua, bairro, cep, estado;

							System.out.println("Digite o nome do Posto: ");
							pNome = scannerStrings.nextLine();

							System.out.println("Digite o nome da Rua: ");
							rua = scannerStrings.nextLine();

							System.out.println("Digite o número da Casa: ");
							numero = scanner.nextInt();

							System.out.println("Digite o nome do Bairro: ");
							bairro = scannerStrings.nextLine();

							System.out.println("Digite o número do Cep: ");
							cep = scannerStrings.nextLine();
							System.out.println("Digite o nome do Estado: ");
							estado = scannerStrings.nextLine();

							Endereco end = new Endereco(rua, numero, bairro, cep, estado);
							if (enderecoDAO.adicionarEndereco(end)) {
								int idEndereco = enderecoDAO.getIdByObjeto(end);
								if (idEndereco > 0) {
									PostoDeSaude posto = new PostoDeSaude(pNome, idEndereco);
									if (postoDeSaudeDAO.adicionarPostoDeSaude(posto)) {
										System.out.println("Posto Cadastrado com sucesso!!!");
									}
								} else {
									System.out.println("Ocorreu Algum erro com seu endereco!!!");
								}

							}

							break;
						case 5:
							ArrayList<PostoDeSaude> postos = postoDeSaudeDAO.getListPostoDeSaude();

							if (postos.size() == 0) {
								System.out.println("Primeiro é necessario ter algun Posto de Saúde cadastrado!!!");
								break;
							}

							boolean cadEqPo = true;
							while (cadEqPo) {

								System.out.println("Selecione o Posto degejado: ");

								for (PostoDeSaude postoDeSaude : postos) {
									System.out.println("[ " + postoDeSaude.getIdPosto() + " ] " + "Nome do Posto: "
											+ postoDeSaude.getpNome());

								}
								System.out.println("[ 0 ] Para Sair");
								int escolhaPosto = scanner.nextInt();
								if (escolhaPosto > 0 && escolhaPosto <= postoDeSaudeDAO.getIdMax()) {
									int idPosto = escolhaPosto;

									boolean cadEqPoEqui = true;
									while (cadEqPoEqui) {

										System.out.println("Selecione o Equipamento degejado: ");
										ArrayList<Equipamento> equipamentos = equipamentoDAO.getListEquipamento();
										ArrayList<TipoEquipamento> tipoEquipamentos = tipoEquipamentoDAO
												.getListTipoEquipamento();
								
										for (Equipamento equipamento : equipamentos) {
											System.out.print("[ " + equipamento.getIdEquipamento() + " ] " + "Tipo: ");

											for (TipoEquipamento tipoEquipamento2 : tipoEquipamentos) {
												if (tipoEquipamento2.getIdTipoEquipamento() == equipamento
														.getIdTipoEquipamento()) {
													System.out.print(tipoEquipamento2.getTipoEquiNome());
												}
											}

											System.out.println(" Descrição: " + equipamento.getDescricao());
										}
										System.out.println("[ 0 ] Para Sair");

										int escolhaEquipamento = scanner.nextInt();

										if (escolhaEquipamento > 0 && escolhaEquipamento < equipamentoDAO.getIdMax()) {
											int idEquipamento = escolhaEquipamento;

											System.out.println("Digite a quantidade de equipamentos");
											int qtdEquipamento = scanner.nextInt();

											System.out.println("Digite a data de Entrega ou que foi entregue");
											String dataDeEntrega = scannerStrings.nextLine();

											EquipamentoDoPosto equipDoPosto = new EquipamentoDoPosto(idPosto,
													idEquipamento, qtdEquipamento, dataDeEntrega);

											if (equipamentoDoPostoDAO.adicionarEquipamentoNoPosto(equipDoPosto)) {
												System.out.println("Equipamento cadastrado com sucesso!!!");
											} else {
												System.out
														.println("Ocorreu algum erro no percurso, tente novamente!!!");
											}
										} else if (escolhaEquipamento == 0) {
											cadEqPoEqui = false;
										} else {
											System.out.println("Digite apenas números que estão entre as opções.");
										}
									}

								} else if (escolhaPosto == 0) {
									cadEqPo = false;
								} else {
									System.out.println("Selecione um número válido!!!");
								}
							}

							break;
						case 0:
							cadLoop = false;
							break;

						default:
							System.out.println("Comando Inválido!!! Tente um novo comando");
							cadLoop = false;
							break;
						}
					}

					break;
				case 2:

					boolean altLoop = true;
					while (altLoop) {
						System.out.println("Digite :");	
						System.out.println("[ 1 ] Realizar Alteração de Tipo de Equipamento");
						System.out.println("[ 2 ] Realizar Alteração de Equipamentos");
						System.out.println("[ 3 ] Realizar Alteração de Pacientes");
						System.out.println("[ 4 ] Realizar Alteração de Posto de Saúde");
						System.out.println("[ 5 ] Realizar Alteração de Equipamentos do Posto");
						System.out.println("[ 0 ] Sair da Aba Alterações");

						int altComando = scanner.nextInt();

						switch (altComando) {
						case 1:
							
							ArrayList<TipoEquipamento> listaTipoEquipamento = new ArrayList<TipoEquipamento>();
							listaTipoEquipamento = tipoEquipamentoDAO.getListTipoEquipamento();
							if(listaTipoEquipamento.size()>0) {
								System.out.println("Digite o id do Tipo de Equipamento que será alterado!");
								for (TipoEquipamento tipoEquipamento : listaTipoEquipamento) {
									System.out.println("["+tipoEquipamento.getIdTipoEquipamento()+"] Nome: "+ tipoEquipamento.getTipoEquiNome());
								}
								System.out.println("[ 0 ] para sair");
								int idTipoEquipamento = scanner.nextInt();
								if(idTipoEquipamento==0) {
									break;
								}
								System.out.println("Digite o novo nome do Tipo de Equipamento cadastrado?");
								String tipoEquiNome = scannerStrings.nextLine();
								if(tipoEquipamentoDAO.atualizarTipoEquipamento(idTipoEquipamento, tipoEquiNome)) {
										System.out.println("Nome do Tipo de Equipamento Alterado com sucesso!");
								}else {
										System.out.println("Tipo de Equipamento não Alterado");
								}
							}else {
								System.out.println("Não nenhum tipo de Equipamento cadastrado!");
							}
						case 2:
							boolean equipamentoLoop = true;
							while(equipamentoLoop) {
								System.out.println("Digite :");
								System.out.println("[ 1 ] Alterar todo o Equipamento");
								System.out.println("[ 2 ] Alterar o tipo do Equipamentos");
								System.out.println("[ 3 ] Alterar a descrição do Equipamento");
								System.out.println("[ 0 ] Sair da Aba Alterar Equipamento");
								int equipamentoComando =  scanner.nextInt();;
								switch (equipamentoComando) {
								case 1:
									ArrayList<TipoEquipamento> listaTipoEquipamentos = new ArrayList<TipoEquipamento>();
									listaTipoEquipamentos = tipoEquipamentoDAO.getListTipoEquipamento();
									ArrayList<Equipamento>listaEquipamentos111 = new ArrayList<Equipamento>();
									listaEquipamentos111 = equipamentoDAO.getListEquipamento();

									if(listaTipoEquipamentos.size()>0 && listaEquipamentos111.size()>0) {
										System.out.println("Digite o id do Equipamento que será alterado");
										for (Equipamento equipamento : listaEquipamentos111) {
											TipoEquipamento tipoEqui = tipoEquipamentoDAO.getTipoEquipamentoById(equipamento.getIdTipoEquipamento());
											System.out.println("["+equipamento.getIdEquipamento() +"] Nome :"
										+ tipoEqui.getTipoEquiNome()+" Descrição : "+ equipamento.getDescricao());
										}
										System.out.println("[ 0 ] para sair.");
										int idEquipamento = scanner.nextInt();
										if(idEquipamento==0)
											break;
										System.out.println("Digite o numero do Tipo do Equipamento");
										for (TipoEquipamento tipoEquipamento : listaTipoEquipamentos) {
											System.out.println("["+tipoEquipamento.getIdTipoEquipamento()+"] Nome: "+ tipoEquipamento.getTipoEquiNome());
										}
										int idTipoEquipamento = scanner.nextInt();
										System.out.println("Digite a nova descrição do Equipamento");
										String descricao = scannerStrings.nextLine();
										
										Equipamento equipamento = new Equipamento(idEquipamento,descricao, idTipoEquipamento);
										if(equipamentoDAO.alterarEquipamento(equipamento)) {
											System.out.println("Equipamento Alterado com Sucesso!!");
										}else {
											System.out.println("Erro ao tentar alterar o Equipamento!");
										}
										
									}else {
										System.out.println("Não há equipamento cadastrado no sistema");
									}
									break;
								case 2:
									ArrayList<TipoEquipamento> listaTipoEquipamento1 = new ArrayList<TipoEquipamento>();
									listaTipoEquipamento1 = tipoEquipamentoDAO.getListTipoEquipamento();
									ArrayList<Equipamento>listaEquipamentoss = new ArrayList<Equipamento>();
									listaEquipamentoss = equipamentoDAO.getListEquipamento();

									if(listaTipoEquipamento1.size()>0 && listaEquipamentoss.size()>0) {

										System.out.println("Digite o id do Equipamento que será alterado");
										for (Equipamento equipamento : listaEquipamentoss) {
											TipoEquipamento tipoEqui = tipoEquipamentoDAO.getTipoEquipamentoById(equipamento.getIdTipoEquipamento());
											System.out.println("["+equipamento.getIdEquipamento() +"] Nome :"
										+ tipoEqui.getTipoEquiNome()+" Descrição : "+ equipamento.getDescricao());
										}
										System.out.println("[ 0 ] para sair.");
										int idEquipamento = scanner.nextInt();
										if(idEquipamento==0)
											break;
										System.out.println("Digite o numero do Tipo do Equipamento");
										for (TipoEquipamento tipoEquipamento : listaTipoEquipamento1) {
											System.out.println("["+tipoEquipamento.getIdTipoEquipamento()+"] Nome: "+ tipoEquipamento.getTipoEquiNome());
										}
										int idTipoEquipamento = scanner.nextInt();
										
										if(equipamentoDAO.alterarTipoDoEquipamento(idEquipamento, idTipoEquipamento)) {
											System.out.println("Tipo do Equipamento Alterado com Sucesso!!");
										}else {
											System.out.println("Erro ao tentar alterar o id do Tipo do Equipamento!");
										}
										
									}else {
										System.out.println("Não há equipamento cadastrado no sistema");
									}		
									break;
								case 3:
									ArrayList<TipoEquipamento> listaTipoEquipamento11 = new ArrayList<TipoEquipamento>();
									listaTipoEquipamento11 = tipoEquipamentoDAO.getListTipoEquipamento();
									ArrayList<Equipamento>listaEquipamentos11 = new ArrayList<Equipamento>();
									listaEquipamentos11 = equipamentoDAO.getListEquipamento();
									System.out.println("Digite o id do Equipamento que será alterado");
									if(listaTipoEquipamento11.size()>0 && listaEquipamentos11.size()>0) {
										for (Equipamento equipamento : listaEquipamentos11) {
											TipoEquipamento tipoEqui = tipoEquipamentoDAO.getTipoEquipamentoById(equipamento.getIdTipoEquipamento());
											System.out.println("["+equipamento.getIdEquipamento() +"] Nome :"
										+ tipoEqui.getTipoEquiNome()+" Descrição : "+ equipamento.getDescricao());
										}
										System.out.println("[ 0 ] para sair.");
										int idEquipamento = scanner.nextInt();
										if(idEquipamento==0)
											break;

										System.out.println("Digite a nova descrição do Equipamento");
										String descricao = scannerStrings.nextLine();
										
										if(equipamentoDAO.alterarDescricao(idEquipamento, descricao)) {
											System.out.println("Descrição do Equipamento Alterado com Sucesso!!");
										}else {
											System.out.println("Erro ao tentar alterar a descrição do Equipamento!");
										}
										
									}else {
										System.out.println("Não há equipamento cadastrado no sistema");
									}		
									break;	
								case 0:
									equipamentoLoop = false;
									break;
		
								default:
									System.out.println("Comando Inválido!!! Tente um novo comando");
									break;
								}
							}	
							break;
						case 3:
							boolean pacienteLoop = true;
							while(pacienteLoop) {
								System.out.println("Digite :");
								System.out.println("[ 1 ] Alterar todo o Paciente");
								System.out.println("[ 2 ] Alterar o Nome do Paciente");
								System.out.println("[ 3 ] Alterar o Posto de saúde do Paciente");
								System.out.println("[ 4 ] Alterar o endereco do Paciente");								
								System.out.println("[ 0 ] Sair da Aba Alterar Paciente");
								int pacienteComando =  scanner.nextInt();
								switch (pacienteComando) {
								case 1:
									ArrayList<PostoDeSaude> listaPostoDeSaudes = new ArrayList<PostoDeSaude>();
									listaPostoDeSaudes = postoDeSaudeDAO.getListPostoDeSaude();

									ArrayList<Paciente> listaPacientes = new ArrayList<Paciente>();
									listaPacientes = pacienteDAO.getListPaciente();
		
									if(listaPostoDeSaudes.size()>0 && listaPacientes.size()>0) {
										System.out.println("Escolha o novo Posto de saúde do Paciente:");
										System.out.println("Listas dos Postos de Saúde:");
										for (PostoDeSaude postoDeSaude : listaPostoDeSaudes) {
											Endereco endereco = enderecoDAO.getEnderecoById(postoDeSaude.getIdEndereco());
											System.out.print("[ " + postoDeSaude.getIdPosto() + " ] " + "Nome do Posto: "
													+ postoDeSaude.getpNome());
											System.out.println(" Rua: " + endereco.getRua() + " Numero: " + endereco.getNumero()
													+ " Bairro: " + endereco.getBairro() + " CEP: " + endereco.getCep()
													+ " Estado: " + endereco.getEstado());
										}
										System.out.println("[ 0 ] Para sair");
										
										int idPosto = scanner.nextInt();
										if(idPosto==0)
											break;
										
										if (idPosto > 0 && idPosto <= postoDeSaudeDAO.getIdMax()) {
											System.out.println("Lista de Pacientes :");
											for (Paciente paciente : listaPacientes) {
												PostoDeSaude posto = postoDeSaudeDAO.getPostoById(paciente.getIdPosto());
												System.out.println("["+paciente.getIdPaciente()+"] Nome : "+paciente.getPacNome()+
														" Posto de saude Nome: "+posto.getpNome()) ;
											}	
											System.out.println("Digite o id do Paciente que será alterado:\n ");
											int idPaciente = scanner.nextInt();
											System.out.println("Digite o novo nome do Paciente:\n ");
											String pacNome = scannerStrings.nextLine();
											
											int idEndereco = pacienteDAO.getPacienteById(idPaciente).getIdEndereco();
												
											int numero;
											String rua, bairro, cep, estado;
											System.out.println("Digite o nome da Rua: ");
											rua = scannerStrings.nextLine();
											System.out.println("Digite o número da Casa: ");
											numero = scanner.nextInt();
											System.out.println("Digite o nome do Bairro: ");
											bairro = scannerStrings.nextLine();
											System.out.println("Digite o número do Cep: ");
											cep = scannerStrings.nextLine();
											System.out.println("Digite o nome do Estado: ");
											estado = scannerStrings.nextLine();
											System.out.println("Endereco "+ idEndereco);
											Endereco endereco = new Endereco(idEndereco,rua, numero, bairro, cep, estado);
											
											Paciente paciente = new Paciente(idPaciente,pacNome,idPosto,idEndereco);
											if(enderecoDAO.alterarEndereco(endereco)) {
												if (pacienteDAO.alterarPaciente(paciente, endereco)) {
													System.out.println("Paciente Alterado com sucesso!");
												} else {
													System.out.println("Falha ao tenter ao tentar Alterar");
												}
											}else {
												System.out.println("Falha ao tentar alterar o endereço!");
											}

										} else if (idPosto == 0) {
											break;
										} else {
											System.out.println("Digite apenas números que estão entre as opções.");
										}

									}
									break;
								case 2:
									ArrayList<Paciente> listaPacientes1 = new ArrayList<Paciente>();
									listaPacientes1 = pacienteDAO.getListPaciente();
									if(listaPacientes1.size()>0) {
										System.out.println("Lista de Pacientes :");
										System.out.println("Digite o id Paciente que será alterado");										
										for (Paciente paciente : listaPacientes1) {
											System.out.println("["+paciente.getIdPaciente()+"] Nome : "+paciente.getPacNome()+" Posto de saude id: "+paciente.getIdPosto());
										}
										System.out.println("[ 0 ] Para sair");
										
										int idPaciente = scanner.nextInt();
										if(idPaciente==0)
											break;
										
										System.out.println("Digite o novo nome do Paciente:\n ");
										String pacNome = scannerStrings.nextLine();
										if(pacienteDAO.alterarPacNome(idPaciente, pacNome)) {
											System.out.println("Nome do Paciente alterado com sucesso!!\n");
										}else {
											System.out.println("Nome do Paciente Não foi alterado!");
										}
									}else {
										System.out.println("Não há Paciente cadastrado no sistema");
									}
									break;
								case 3:
									ArrayList<Paciente> listaPacientes11 = new ArrayList<Paciente>();
									listaPacientes11 = pacienteDAO.getListPaciente();
									ArrayList<PostoDeSaude> listaPostoDeSaudes1 = new ArrayList<PostoDeSaude>();
									listaPostoDeSaudes1 = postoDeSaudeDAO.getListPostoDeSaude();

									if(listaPacientes11.size()>0 && listaPostoDeSaudes1.size()>0) {
										System.out.println("Lista de Pacientes :");
										System.out.println("Digite o id Paciente que será alterado");
										
										for (Paciente paciente : listaPacientes11) {
											System.out.println("["+paciente.getIdPaciente()+"] Nome : "+paciente.getPacNome()+" Posto de saude id: "+paciente.getIdPosto());
										}
										System.out.println("[ 0 ] Para sair");
										
										int idPaciente = scanner.nextInt();
										if(idPaciente==0)
											break;
										
										System.out.println("Lista dos Posto de saúde do Pacientes :");
										for (PostoDeSaude postoDeSaude : listaPostoDeSaudes1) {
											System.out.println("["+postoDeSaude.getIdPosto()+"] Nome : "+postoDeSaude.getpNome());
										}
										System.out.println("Digite o numero do Posto de Saúde do Paciente");
										int idPosto = scanner.nextInt();
										if(pacienteDAO.alterarIdPosto(idPaciente, idPosto)) {
											System.out.println("Posto de saúde do Paciente alterado com sucesso!!\n");
										}else {
											System.out.println("Posto de saúde do Paciente Não foi alterado!");
										}
									}else {
										System.out.println("Não há Paciente cadastrado no sistema");
									}
									break;
								case 4:
									ArrayList<Paciente> listaPacientes111 = new ArrayList<Paciente>();
									listaPacientes111 = pacienteDAO.getListPaciente();				
									System.out.println("Lista de Pacientes :");
									for (Paciente paciente : listaPacientes111) {
										PostoDeSaude posto = postoDeSaudeDAO.getPostoById(paciente.getIdPosto());
										System.out.println("["+paciente.getIdPaciente()+"] Nome : "+paciente.getPacNome()+
												" Posto de saude Nome: "+posto.getpNome()) ;
									}	
									System.out.println("Digite o id do Paciente que será alterado:\n ");
									int idPaciente = scanner.nextInt();
									System.out.println("[ 0 ] para sair");
									if(idPaciente==0) {
										break;
									}
									int idEndereco = pacienteDAO.getPacienteById(idPaciente).getIdEndereco();
										
									int numero;
									String rua, bairro, cep, estado;
									System.out.println("Digite o nome da Rua: ");
									rua = scannerStrings.nextLine();
									System.out.println("Digite o número da Casa: ");
									numero = scanner.nextInt();
									System.out.println("Digite o nome do Bairro: ");
									bairro = scannerStrings.nextLine();
									System.out.println("Digite o número do Cep: ");
									cep = scannerStrings.nextLine();
									System.out.println("Digite o nome do Estado: ");
									estado = scannerStrings.nextLine();
									System.out.println("Endereco "+ idEndereco);
									Endereco endereco = new Endereco(idEndereco,rua, numero, bairro, cep, estado);
									
									if(enderecoDAO.alterarEndereco(endereco)) {
										if(pacienteDAO.alterarEndereco(idPaciente, endereco)) {
											System.out.println("Endereço do Paciente alterado com sucesso");
										}else {
											System.out.println("Falha ao alterar o Paciente");
										}
									}else {
										System.out.println("Falha ao alterar enderenço");
									}
									
									break;
								case 0:
									pacienteLoop = false;
									break;
								default:
									break;
								}
								
							}
							break;
						case 4:
							boolean postoLoop = true;
							while(postoLoop) {
								System.out.println("Digite :");
								System.out.println("[ 1 ] Alterar todo o Posto de Saúde");
								System.out.println("[ 2 ] Alterar o Nome do Posto de Saúde");
								System.out.println("[ 3 ] Alterar o endereco do Posto de Saúde");
								System.out.println("[ 4 ] Alterar os equipamentos do Posto de Saúde");								
								System.out.println("[ 0 ] Sair da Aba Alterar Posto de Saúde");
								int postoComando =  scanner.nextInt();
								switch (postoComando) {
								case 1:
									ArrayList<PostoDeSaude> listaPostoDeSaudes = new ArrayList<PostoDeSaude>();
									listaPostoDeSaudes = postoDeSaudeDAO.getListPostoDeSaude();

			
									if(listaPostoDeSaudes.size()>0 ) {
										System.out.println("Listas dos Postos de Saúde:");
										System.out.println("Escolha o numero do Posto de saúde:\n ");										
										for (PostoDeSaude postoDeSaude : listaPostoDeSaudes) {
											Endereco endereco = enderecoDAO.getEnderecoById(postoDeSaude.getIdEndereco());
											System.out.print("[ " + postoDeSaude.getIdPosto() + " ] " + "Nome do Posto: "
													+ postoDeSaude.getpNome());
											System.out.println(" Rua: " + endereco.getRua() + " Numero: " + endereco.getNumero()
													+ " Bairro: " + endereco.getBairro() + " CEP: " + endereco.getCep()
													+ " Estado: " + endereco.getEstado());
										}
										System.out.println("[ 0 ] Para sair");
										int idPosto = scanner.nextInt();
										if(idPosto==0) {
											break;
										}
										
										System.out.println("Digite o novo nome do Posto de saúde:");
										String pNome = scannerStrings.nextLine();

										int idEndereco = postoDeSaudeDAO.getPostoById(idPosto).getIdEndereco();
										PostoDeSaude postoDeSaude = new PostoDeSaude(idPosto,pNome, idEndereco);
										
										System.out.println("Digite as novas informações de endereco!");
										int numero;
										String rua, bairro, cep, estado;
										System.out.println("Digite o nome da Rua: ");
										rua = scannerStrings.nextLine();
										System.out.println("Digite o número da Casa: ");
										numero = scanner.nextInt();
										System.out.println("Digite o nome do Bairro: ");
										bairro = scannerStrings.nextLine();
										System.out.println("Digite o número do Cep: ");
										cep = scannerStrings.nextLine();
										System.out.println("Digite o nome do Estado: ");
										estado = scannerStrings.nextLine();
										
										
										Endereco endereco = new Endereco(idEndereco,rua, numero, bairro, cep, estado);
										if(enderecoDAO.alterarEndereco(endereco)) {
											
											if (idPosto > 0 && idPosto <= postoDeSaudeDAO.getIdMax()) {
												
												if(postoDeSaudeDAO.alterarPostoDeSaude(postoDeSaude)) {
													System.out.println("Alterado com sucesso!");
												}else{
													System.out.println("Falha ao tentar Alterar!");
												}
												
											} else if (idPosto == 0) {
												System.out.println("Não há Posto com esse id");
											} else {
												System.out.println("Digite apenas números que estão entre as opções.");
											}
										}else {
											System.out.println("Falha ao cadastra o endereco");
										}
									}	
									break;
								case 2:
									ArrayList<PostoDeSaude> listaPostoDeSaudes1 = new ArrayList<PostoDeSaude>();
									listaPostoDeSaudes1 = postoDeSaudeDAO.getListPostoDeSaude();

			
									if(listaPostoDeSaudes1.size()>0 ) {
										System.out.println("Listas dos Postos de Saúde:");
										System.out.println("Digite o id do Posto de Saúde que será alterado!");
										
										for (PostoDeSaude postoDeSaude : listaPostoDeSaudes1) {
											Endereco endereco = enderecoDAO.getEnderecoById(postoDeSaude.getIdEndereco());
											System.out.print("[ " + postoDeSaude.getIdPosto() + " ] " + "Nome do Posto: "
													+ postoDeSaude.getpNome());
											System.out.println(" Rua: " + endereco.getRua() + " Numero: " + endereco.getNumero()
													+ " Bairro: " + endereco.getBairro() + " CEP: " + endereco.getCep()
													+ " Estado: " + endereco.getEstado());
										}

										System.out.println("Digite:");
										System.out.println("[ 0 ] Para sair");
										int idPosto = scanner.nextInt();
										
										if(idPosto==0) {
											break;
										}
										System.out.println("Digite o novo nome do Posto de saúde!");
										String pNome = scannerStrings.nextLine();
										if (idPosto > 0 && idPosto <= postoDeSaudeDAO.getIdMax()) {
											
											if(postoDeSaudeDAO.alterarNomePosto(idPosto, pNome)) {
												System.out.println("Nome do Posto de saúde Alterado com sucesso!");
											}else{
												System.out.println("Falha ao tentar Alterar o nome do Posto!");
											}
											
										} else if (idPosto == 0) {
											System.out.println("Não há Posto com esse id");
										} else {
											System.out.println("Digite apenas números que estão entre as opções.");
										}

									}else {
										System.out.println("Não há Posto cadastrado no sistema!");
									}
									break;
								case 3:
									ArrayList<PostoDeSaude> listaPostoDeSaudes11 = new ArrayList<PostoDeSaude>();
									listaPostoDeSaudes11 = postoDeSaudeDAO.getListPostoDeSaude();			
									if(listaPostoDeSaudes11.size()>0 ) {
										System.out.println("Listas dos Postos de Saúde:");
										System.out.println("Digite o id do Posto de Saúde que será alterado!");
										
										for (PostoDeSaude postoDeSaude : listaPostoDeSaudes11) {
											Endereco endereco = enderecoDAO.getEnderecoById(postoDeSaude.getIdEndereco());
											System.out.print("[ " + postoDeSaude.getIdPosto() + " ] " + "Nome do Posto: "
													+ postoDeSaude.getpNome());
											System.out.println(" Rua: " + endereco.getRua() + " Numero: " + endereco.getNumero()
													+ " Bairro: " + endereco.getBairro() + " CEP: " + endereco.getCep()
													+ " Estado: " + endereco.getEstado());
										}

										System.out.println("Digite:");
										System.out.println("[ 0 ] Para sair");
										int idPosto = scanner.nextInt();
										
										if(idPosto==0) {
											break;
										}
										System.out.println("Digite as novas informações de endereco!");
										int numero;
										String rua, bairro, cep, estado;
										System.out.println("Digite o nome da Rua: ");
										rua = scannerStrings.nextLine();
										System.out.println("Digite o número da Casa: ");
										numero = scanner.nextInt();
										System.out.println("Digite o nome do Bairro: ");
										bairro = scannerStrings.nextLine();
										System.out.println("Digite o número do Cep: ");
										cep = scannerStrings.nextLine();
										System.out.println("Digite o nome do Estado: ");
										estado = scannerStrings.nextLine();
										
										int idEndereco = postoDeSaudeDAO.getPostoById(idPosto).getIdEndereco();
										Endereco endereco = new Endereco(idEndereco,rua, numero, bairro, cep, estado);
										if(enderecoDAO.alterarEndereco(endereco)) {
											if(postoDeSaudeDAO.alterarEnderecoPosto(idPosto, idEndereco)) {
												System.out.println("Endereço do Posto de saúde alterado com sucesso!");
											}else {
												System.out.println("Falha ao alterar endereço");
											}
										}else{
											System.out.println("Falha ao alterar endereço");
										}
									}else {
										System.out.println("Não há Posto de Saúde cadastrado!");
									}
									break;
								case 4:
									
								case 0:
									postoLoop = false;
									break;
								default:
									break;
								}
							}
							break;
						case 5:
							
							boolean equiDoPostoLoop = true;
							while(equiDoPostoLoop ) {
								System.out.println("Digite :");
								System.out.println("[ 1 ] Alterar o Posto de Saúde Equipamento");
								System.out.println("[ 2 ] Alterar a quatidade de Saúde Equipamento");
								System.out.println("[ 0 ] Sair da Aba Alterar Posto de Saúde");
								int equiDoPostoComando =  scanner.nextInt();
								switch (equiDoPostoComando) {
								case 1:
									ArrayList<PostoDeSaude> listaPostoDeSaudes = new ArrayList<PostoDeSaude>();
									listaPostoDeSaudes = postoDeSaudeDAO.getListPostoDeSaude();			
									if(listaPostoDeSaudes.size()>0 ) {
										System.out.println("Listas dos Postos de Saúde:");
										System.out.println("Escolha o numero do Posto de saúde:\n ");										
										for (PostoDeSaude postoDeSaude : listaPostoDeSaudes) {
											Endereco endereco = enderecoDAO.getEnderecoById(postoDeSaude.getIdEndereco());
											System.out.print("[ " + postoDeSaude.getIdPosto() + " ] " + "Nome do Posto: "
													+ postoDeSaude.getpNome());
											System.out.println(" Rua: " + endereco.getRua() + " Numero: " + endereco.getNumero()
													+ " Bairro: " + endereco.getBairro() + " CEP: " + endereco.getCep()
													+ " Estado: " + endereco.getEstado());
										}
										System.out.println("[ 0 ] Para sair");
										int idPosto = scanner.nextInt();
										if(idPosto==0) 
											break;

										ArrayList<EquipamentoDoPosto> listaEquiDoPosto = new ArrayList<EquipamentoDoPosto>();
										listaEquiDoPosto = equipamentoDoPostoDAO.getListEquipamentosDoPosto(idPosto);
											
										System.out.println("Lista de Equipamentos do Posto "+ postoDeSaudeDAO.getPostoById(idPosto).getpNome() );
										System.out.println("Escolha o numero do Posto de saúde:\n ");												
										for (EquipamentoDoPosto equipamentoDoPosto : listaEquiDoPosto) {
											Equipamento equi = equipamentoDAO.getEquipamentoByID(equipamentoDoPosto.getIdEquipamento());
											TipoEquipamento tipoEqui = tipoEquipamentoDAO.getTipoEquipamentoById(equi.getIdTipoEquipamento());
											System.out.println("[ "+equi.getIdEquipamento() +" ]  Nome : "+tipoEqui.getTipoEquiNome()+ " Descrição: " + equi.getDescricao() );
										}
										System.out.println("[ 0 ] Para sair");
										int idEquiDoPosto = scanner.nextInt();
										if(idEquiDoPosto==0) 
											break;
										System.out.println("Digite a nova quantidade do Equipamento: ");
										int qtdEqui = scanner.nextInt();
										System.out.println("Digite a data de entrega do Equipamento: ");
										String dataEntrega = scannerStrings.nextLine();
										
										int idEquipamento =0;
										for (EquipamentoDoPosto equipamentoDoPosto : listaEquiDoPosto) {
											Equipamento equi = equipamentoDAO.getEquipamentoByID(equipamentoDoPosto.getIdEquipamento());
											if(equipamentoDoPosto.getIdEquipamento() ==idEquiDoPosto) {
												idEquipamento = equi.getIdEquipamento();
												break;
											}
										}
										
										EquipamentoDoPosto equiDoPosto = new EquipamentoDoPosto(idPosto, idEquipamento, qtdEqui, dataEntrega);
										
										if(equipamentoDoPostoDAO.alterarEquipamentoDoPosto(equiDoPosto)) {
											System.out.println("Equipamento do Posto Alterado com sucesso!!");
										}else {
											System.out.println("Falha ao alterar Equipamento do Posto");
										}
									}else {
										System.out.println("");
									}
									break;
								case 0:
									equiDoPostoLoop =false;
									break;
								default:
									break;
								}
							}
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
							if (listaTipoEquipamento.size() > 0) {
								System.out.println("Escolha qual é o Tipo desse Equipamento: ");

								for (TipoEquipamento tipoEquipamento2 : listaTipoEquipamento) {
									System.out.println("[ " + tipoEquipamento2.getIdTipoEquipamento() + " ] "
											+ tipoEquipamento2.getTipoEquiNome());

								}
								System.out.println("[ 0 ] Para sair");

								System.out.println("Digite o numero do Tipo de Equipamento que será removido:");
								int idTipoEquipamento = scanner.nextInt();
								if (idTipoEquipamento > 0 && idTipoEquipamento <= tipoEquipamentoDAO.getIdMax()) {
									if (tipoEquipamentoDAO.deleteEquipamento(idTipoEquipamento)) {
										System.out.println("Tipo de equipamento removido com Sucesso!");
									} else {
										System.out.println(
												"Verifique se não tem algum equipamento com esse tipo e Tente novamente.");
									}
								} else if (idTipoEquipamento == 0) {
									break;
								} else {
									System.out.println("Tipo de equipamento não encontrado com esse ID!");
								}

							} else {
								System.out.println("Não há nenhum tipo de Equipamento cadastrado!");
							}

							break;
						case 2:
							ArrayList<Equipamento>listaEquipamentos = new ArrayList<Equipamento>();
							listaEquipamentos = equipamentoDAO.getListEquipamento();

							if (listaEquipamentos.size() > 0) {
								System.out.println("lista de Equipamentos cadastrados");
								for (Equipamento equipamento2 : listaEquipamentos) {
									System.out.println("[ " + equipamento2.getIdEquipamento() + " ] Descrição = "
											+ equipamento2.getDescricao());
								}
								System.out.println("[ 0 ] para sair");

								System.out.println("Digite o numero do Equipamento que será removido:");
								int idEquipamento = scanner.nextInt();
								if (idEquipamento > 0 && idEquipamento <= equipamentoDAO.getIdMax()) {
									if (equipamentoDAO.deleteEquipamento(idEquipamento)) {
										System.out.println("Equipamento removido com Sucesso!");
									} else {
										System.out.println(
												"Verifique se esse equipamento não está em nenhum posto e Tente novamente.");
									}
								} else if (idEquipamento == 0) {
									break;
								} else {
									System.out.println("Equipamento não encontrado com esse ID!");
								}
							} else {
								System.out.println("Não há equipamento cadastrado no sistema!");
							}

							break;
						case 3:
							ArrayList<Paciente> listaPacientes = new ArrayList<Paciente>();
							listaPacientes = pacienteDAO.getListPaciente();
							if (listaPacientes.size() > 0) {
								System.out.println("Lista dos Pacientes cadastrado no sistema!");
								for (Paciente paciente : listaPacientes) {
									System.out.println(
											"[ " + paciente.getIdPaciente() + " ] Nome = " + paciente.getPacNome());
								}
								System.out.println("[ 0 ] Para sair");

								System.out.println("Digite o id do Paciente que será removido:");
								int idPaciente = scanner.nextInt();

								if (idPaciente > 0 && idPaciente <= pacienteDAO.getIdMax()) {
									Paciente pac = pacienteDAO.getPacienteById(idPaciente);
									int idEndereco = pac.getIdEndereco();
									System.out.println();
									if (pacienteDAO.deletePaciente(idPaciente)) {
										if (enderecoDAO.removerEndereco(idEndereco)) {
											System.out.println("Paciente removido com Sucesso!");
										} else {
											System.out.println("Ocorreu algum erro na remoção do endereço.");
										}
									} else {
										System.out.println("Ocorreu algum erro na remoção");
									}
								} else if (idPaciente == 0) {
									break;
								} else {
									System.out.println("Paciente não encontrado com esse ID!");
								}

							} else {
								System.out.println("Não há Paciente cadastrado no sistema!");
							}

							break;
						case 4:
							ArrayList<PostoDeSaude> postos = postoDeSaudeDAO.getListPostoDeSaude();

							if (postos.size() > 0) {
								System.out.println("Selecione o Posto degejado: ");

								for (PostoDeSaude postoDeSaude : postos) {
									System.out.println("[ " + postoDeSaude.getIdPosto() + " ] " + "Nome do Posto: "
											+ postoDeSaude.getpNome());

								}
								System.out.println("[ 0 ] Para Sair");

								int escolhaPosto = scanner.nextInt();
								if (escolhaPosto > 0 && escolhaPosto <= postoDeSaudeDAO.getIdMax()) {
									PostoDeSaude posto = postoDeSaudeDAO.getPostoById(escolhaPosto);
									int idEndereco = posto.getIdEndereco();
									System.out.println();
									if (postoDeSaudeDAO.removerPostoDeSaude(escolhaPosto)) {
										if (enderecoDAO.removerEndereco(idEndereco)) {
											System.out.println("Posto removido com Sucesso!");
										} else {
											System.out.println("Ocorreu algum erro na remoção do endereço.");
										}
									} else {
										System.out
												.println("Verifique se o posto não possui paciente e Tente novamente.");
									}

								} else if (escolhaPosto == 0) {
									break;
								} else {
									System.out.println("Posto não encontrado com esse ID!");
								}
							} else {
								System.out.println("Nenhum posto cadastrado");
							}

							break;
						case 5:
							ArrayList<PostoDeSaude> postosDeSaude = postoDeSaudeDAO.getListPostoDeSaude();
							if (postosDeSaude.size() > 0) {
								System.out.println("Selecione o Posto degejado: ");
								for (PostoDeSaude postoDeSaude : postosDeSaude) {
									System.out.println("[ " + postoDeSaude.getIdPosto() + " ] " + "Nome do Posto: "
											+ postoDeSaude.getpNome());
								}
								System.out.println("[ 0 ] Para Sair");

								int escolhaPosto = scanner.nextInt();
								if (escolhaPosto > 0 && escolhaPosto <= postoDeSaudeDAO.getIdMax()) {

									ArrayList<EquipamentoDoPosto> equipamentosDoPosto = equipamentoDoPostoDAO
											.getListEquipamentosDoPosto(escolhaPosto);

									if (equipamentosDoPosto.size() > 0) {
										System.out.println("Selecione o equipamento que deseja excluir do posto.");
										for (EquipamentoDoPosto equipamentoDoPosto : equipamentosDoPosto) {
											Equipamento equi = equipamentoDAO
													.getEquipamentoByID(equipamentoDoPosto.getIdEquipamento());
											TipoEquipamento tipo = tipoEquipamentoDAO
													.getTipoEquipamentoById(equi.getIdTipoEquipamento());
											System.out.println("[ " + equipamentoDoPosto.getIdEquipamento() + " ]"
													+ " Tipo: " + tipo.getTipoEquiNome() + " Descrição: "
													+ equi.getDescricao() + "Quantidade: "
													+ equipamentoDoPosto.getQtdEquipamento() + " Data de entrega: "
													+ equipamentoDoPosto.getDataDeEntrega());
										}
										System.out.println("[ 0 ] Para sair");

										int escolhaEqui = scanner.nextInt();
										if (escolhaEqui == 0)
											break;
										
										if (equipamentoDoPostoDAO.removerEquipamentoDoPosto(escolhaPosto,escolhaEqui)) {
												System.out.println("Equipamento removido do posto com Sucesso!");
										} else {
												System.out.println("Ocorreu algum erro na remoção. Tente novamente.");
										}
									} else {
										System.out.println("Nenhum Equipamento cadastrado no posto Escolhido");
									}

								} else if (escolhaPosto == 0) {
									break;
								} else {
									System.out.println("Posto não encontrado com esse ID!");
								}
							} else {
								System.out.println("Nenhum posto cadastrado");
							}

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
							if (listaTipoEquipamento.size() > 0) {
								System.out.println("Lista de todos os Tipos de Equipamento cadastado no sistema: ");

								for (TipoEquipamento tipoEquipamento2 : listaTipoEquipamento) {
									System.out.println(" " + tipoEquipamento2.getIdTipoEquipamento() + " Nome : "
											+ tipoEquipamento2.getTipoEquiNome());

								}
								System.out.println("Digite:");
								System.out.println("[ 0 ] Para sair");
								int escolhaTipo = scanner.nextInt();
								if(escolhaTipo > 0 && escolhaTipo < postoDeSaudeDAO.getIdMax()) {
									
								}else if(escolhaTipo == 0) {
									break;
								}else {
									System.out.println("insira apenas os numeros listarados");
								}
							}else {
								System.out.println("è necessario tem Tipo de Equipamento cadastrado");
							}

							break;
						case 2:
							ArrayList<Equipamento>listaEquipamentos = new ArrayList<Equipamento>();
							listaEquipamentos = equipamentoDAO.getListEquipamento();

							if (listaEquipamentos.size() > 0) {
								System.out.println("lista de Equipamentos cadastrados");
								for (Equipamento equipamento2 : listaEquipamentos) {
									TipoEquipamento tipo = tipoEquipamentoDAO.getTipoEquipamentoById(equipamento2.getIdTipoEquipamento());
									System.out.println(" id:" + equipamento2.getIdEquipamento() + " Tipo: "+ tipo.getTipoEquiNome() +" Descrição: "
											+ equipamento2.getDescricao());
								}
								System.out.println("[ 0 ] para sair");
								int escolhaEqui = scanner.nextInt();
								if(escolhaEqui > 0 && escolhaEqui < postoDeSaudeDAO.getIdMax()) {
									
								}else if(escolhaEqui == 0) {
									break;
								}else {
									System.out.println("insira apenas os numeros listarados");
								}
							}else {
								System.out.println("Nenhum equipamento cadastrado.");
							}
							break;
						case 3:
							
							ArrayList<PostoDeSaude> postos = postoDeSaudeDAO.getListPostoDeSaude();

							if (postos.size() > 0) {
								System.out.println("Deseja lista paciente de qual posto de saúde ");
								for (PostoDeSaude postoDeSaude : postos) {
									System.out.println("[ " + postoDeSaude.getIdPosto() + " ] " + "Nome do Posto: "
											+ postoDeSaude.getpNome());

								}
								System.out.println("[ 0 ] Para Sair");

								int escolhaPosto = scanner.nextInt();
								if (escolhaPosto > 0 && escolhaPosto <= postoDeSaudeDAO.getIdMax()) {
									ArrayList<Paciente> listaPacientes = new ArrayList<Paciente>();
									listaPacientes = pacienteDAO.getListPaciente();
									if (listaPacientes.size() > 0) {

										System.out.println("Lista dos Pacientes cadastrado no sistema!");
										for (Paciente paciente : listaPacientes) {
											System.out.println(
													"[ " + paciente.getIdPaciente() + " ] Nome = " + paciente.getPacNome());
										}
										System.out.println("[ 0 ] Para sair");
										int escolhaPaciente = scanner.nextInt();
										if(escolhaPaciente > 0 && escolhaPaciente < postoDeSaudeDAO.getIdMax()) {
											
										}else if(escolhaPaciente == 0) {
											break;
										}else {
											System.out.println("Posto não encontrado com esse numero!");
										}
									}else {
										System.out.println("Nenhum paciente cadastrado nesse posto.");
									}

								} else if (escolhaPosto == 0) {
									break;
								} else {
									System.out.println("Posto não encontrado com esse ID!");
								}
							} else {
								System.out.println("Nenhum posto cadastrado");
							}
							
							break;
						case 4:
							
							ArrayList<PostoDeSaude> postosDeSaude = postoDeSaudeDAO.getListPostoDeSaude();

							// falta a lista posto postoDeSaudeDAO.;
							if (postosDeSaude.size() > 0) {

								System.out.println("Listas dos Postos de Saúde:\n ");
								for (PostoDeSaude postoDeSaude : postosDeSaude) {
									Endereco endereco = enderecoDAO.getEnderecoById(postoDeSaude.getIdEndereco());
									System.out.print("[ " + postoDeSaude.getIdPosto() + " ] " + "Nome do Posto: "
											+ postoDeSaude.getpNome());
									System.out.println(" Rua: " + endereco.getRua() + " Numero: " + endereco.getNumero()
											+ " Bairro: " + endereco.getBairro() + " CEP: " + endereco.getCep()
											+ " Estado: " + endereco.getEstado());

								}
								System.out.println("[ 0 ] Para sair");
								int escolhaPosto = scanner.nextInt();
								if(escolhaPosto > 0 && escolhaPosto <= postoDeSaudeDAO.getIdMax()) {
									
								}else if(escolhaPosto == 0) {
									break;
								}else {
									System.out.println("Posto não encontrado com esse numero!");
								}
							}
							
							break;
						case 5:
							ArrayList<PostoDeSaude> listaPostoDeSaudes = new ArrayList<PostoDeSaude>();
							listaPostoDeSaudes = postoDeSaudeDAO.getListPostoDeSaude();

							if (listaPostoDeSaudes.size() > 0) {
								System.out.println("Selecione o Posto degejado: ");

								for (PostoDeSaude postoDeSaude : listaPostoDeSaudes) {
									System.out.println("[ " + postoDeSaude.getIdPosto() + " ] " + "Nome do Posto: "
											+ postoDeSaude.getpNome());

								}
								System.out.println("[ 0 ] Para Sair");

								int escolhaPosto = scanner.nextInt();
								if (escolhaPosto > 0 && escolhaPosto <= postoDeSaudeDAO.getIdMax()) {

									ArrayList<EquipamentoDoPosto> equipamentosDoPosto = equipamentoDoPostoDAO
											.getListEquipamentosDoPosto(escolhaPosto);

									if (equipamentosDoPosto.size() > 0) {
										System.out.println("Selecione o equipamento que degeja excluir do posto.");

										for (EquipamentoDoPosto equipamentoDoPosto : equipamentosDoPosto) {
											Equipamento equi = equipamentoDAO
													.getEquipamentoByID(equipamentoDoPosto.getIdEquipamento());
											TipoEquipamento tipo = tipoEquipamentoDAO
													.getTipoEquipamentoById(equi.getIdTipoEquipamento());
											System.out.println("[ " + equipamentoDoPosto.getIdEquipamento() + " ]"
													+ " Tipo: " + tipo.getTipoEquiNome() + " Descrição: "
													+ equi.getDescricao() + "Quantidade: "
													+ equipamentoDoPosto.getQtdEquipamento() + " Data de entrega: "
													+ equipamentoDoPosto.getDataDeEntrega());
										}
										System.out.println("[ 0 ] Para sair");

										int escolhaEqui = scanner.nextInt();

										if (escolhaEqui > 0 ) {

											if (equipamentoDoPostoDAO.removerEquipamentoDoPosto(escolhaPosto,escolhaEqui)) {
												System.out.println("Equipamento removido do posto com Sucesso!");
											} else {
												System.out.println("Ocorreu algum erro na remoção. Tente novamente.");
											}

										} else if (escolhaEqui == 0) {
											break;
										} else {
											System.out.println("Equipamento não encontrado no Posto com esse numero!");
										}

									} else {
										System.out.println("Nenhum Equipamento cadastrado no posto Escolhido");
									}

								} else if (escolhaPosto == 0) {
									break;
								} else {
									System.out.println("Posto não encontrado com esse ID!");
								}
							} else {
								System.out.println("Nenhum posto cadastrado");
							}
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
					System.out.println("Sistema finalizado!");
					break;

				default:
					System.out.println("Comando Inválido!!! Tente um novo comando");
					break;
				}
				
//				} catch (InputMismatchException e) {
//					System.out.println("Deve ser inserido numero não uma letra!");
//					
//				} catch (IndexOutOfBoundsException e) {
//					System.out.println("Digite apenas números que estão entre as opções.");
//				}
			}
		

	}

	private static void meuCadastro() {
		System.out.println("Digite :");
		System.out.println("[ 1 ] Realizar Cadastro de Tipo de Equipamento");
		System.out.println("[ 2 ] Realizar Cadastro de Equipamentos");
		System.out.println("[ 3 ] Realizar Cadastro de Pacientes");
		System.out.println("[ 4 ] Realizar Cadastro de Posto de Saúde");
		System.out.println("[ 5 ] Realizar Cadastro de Equipamentos do Posto");
		System.out.println("[ 0 ] Sair da Aba Cadastro");
	}

	private static void menuInicial() {
		System.out.println("Portal Saúde com transparencia");
		System.out.println("Digite:");
		System.out.println("[ 1 ] Realizar Cadastros");
		System.out.println("[ 2 ] Realizar Alterações");
		System.out.println("[ 3 ] Realizar Remoções");
		System.out.println("[ 4 ] Realizar Consultas");
		System.out.println("[ 0 ] Sair do Sistema");

	}

	//trocar o try 
			
}