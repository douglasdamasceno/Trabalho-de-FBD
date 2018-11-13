package view;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

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

		try {

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
							String nomeTipoEqui = scannerStrings.nextLine();

							TipoEquipamento tipoEquipamento = new TipoEquipamento(nomeTipoEqui);

							if (tipoEquipamentoDAO.addTipoEquipamento(tipoEquipamento)) {
								System.out.println("cadastrado com sucesso!");
								break;
							} else {
								System.out.println("Falha ao tenter realizar cadastrado");
							}

							break;
						case 2:

							ArrayList<TipoEquipamento> listaTipoEquipamento = new ArrayList<TipoEquipamento>();
//							if(listaTipoEquipamento.size() == 0) {
//								System.out.println("Primeiro é necessario ter algun Tipo de Equipamento cadastrado!");
//								break;
//							}

							listaTipoEquipamento = tipoEquipamentoDAO.getListTipoEquipamento();
							if (listaTipoEquipamento.size() > 0) {
								System.out.println("Escolha qual é o Tipo desse Equipamento: ");

								for (TipoEquipamento tipoEquipamento2 : listaTipoEquipamento) {
									System.out.println("[ " + tipoEquipamento2.getIdTipoEquipamento() + " ] "
											+ tipoEquipamento2.getTipoEquiNome());
									// count++;
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
										// break;
									} else {
										System.out.println("Falha ao tenter realizar cadastrado");
									}
								} else if (idTipoEquipamento == 0) {
									break;
								} else {
									System.out.println("Digite apenas números que estão entre as opções.");
								}

								// break;
							} else {
								System.out.println("è preciso cadastrar primeiro um tipo de Equipamento!");
							}

							break;
						case 3:

							ArrayList<PostoDeSaude> listaPostoDeSaude = postoDeSaudeDAO.getListPostoDeSaude();

							// falta a lista posto postoDeSaudeDAO.;
							if (listaPostoDeSaude.size() > 0) {

								System.out.println("Listas dos Postos de Saúde:\n ");
								for (PostoDeSaude postoDeSaude : listaPostoDeSaude) {
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
											Paciente paciente = new Paciente(pacNome, idEndereco, idPosto);

											if (pacienteDAO.addPaciente(paciente)) {
												System.out.println("cadastrado com sucesso!");
											} else {
												System.out.println("Falha ao tenter realizar cadastrado");
											}
										} else {
											System.out.println("Erro ao cadastrar endereço. Tente novamente.");
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
							// System.out.println("Digite o nome da Cidade: ");
							// cidade = scanner.nextLine();
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
							// int vetorIds[] = equipamentoDoPostoDAO.getIdByObjeto(equiDoPosto);
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
										// ArrayList<EquipamentoDoPosto> equipamentosDoPosto =
										// equipamentoDoPostoDAO.getListEquipamentoDoPosto(idPosto);

										// ArrayList<Equipamento> equipamentosQueNaoEstaoNoPosto = new ArrayList<>();
										//
										// for (EquipamentoDoPosto equipamentoDoPosto : equipamentosDoPosto) {
										// for (Equipamento equipamento : equipamentos) {
										// equipamentosQueNaoEstaoNoPosto =
										// equipamentoDAO.getListEquipamentosQueNaoEstaoNoPosto(equipamento.getIdEquipamento(),
										// equipamentoDoPosto.getIdPosto());
										// }
										//
										//
										// }

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
							
							
							System.out.println("Degeja alterar o Nome do Tipo de Equipamento cadastrado?");
							System.out.println("[ 1 ] Para sim [ 2 ] para não");
							
							
							
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
							// Talvez fazer trigger para excluir suas dependencias
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
							// Talvez fazer trigger para excluir suas dependencias
							ArrayList<Equipamento> listaEquipamentos = new ArrayList<Equipamento>();
							listaEquipamentos = equipamentoDAO.getListEquipamento();
							System.out.println("lista de Equipamentos cadastrados");
							if (listaEquipamentos.size() > 0) {
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
										System.out.println("Selecione o equipamento que degeja excluir do posto.");

										for (EquipamentoDoPosto equipamentoDoPosto : equipamentosDoPosto) {
											Equipamento equi = equipamentoDAO
													.getEquipamentoByID(equipamentoDoPosto.getIdEquiDoPosto());
											TipoEquipamento tipo = tipoEquipamentoDAO
													.getTipoEquipamentoById(equi.getIdTipoEquipamento());
											System.out.println("[ " + equipamentoDoPosto.getIdEquiDoPosto() + " ]"
													+ " Tipo: " + tipo.getTipoEquiNome() + " Descrição: "
													+ equi.getDescricao() + "Quantidade: "
													+ equipamentoDoPosto.getQtdEquipamento() + " Data de entrega: "
													+ equipamentoDoPosto.getDataDeEntrega());
										}
										System.out.println("[ 0 ] Para sair");

										int escolhaEqui = scanner.nextInt();

										if (escolhaEqui > 0 && escolhaEqui <= equipamentoDoPostoDAO.getIdMax()) {

//											if (equipamentoDoPostoDAO.removerEquipamentoDoPosto(escolhaEqui)) {
//												System.out.println("Equipamento removido do posto com Sucesso!");
//											} else {
//												System.out.println("Ocorreu algum erro na remoção. Tente novamente.");
//											}

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
								System.out.println("Escolha qual é o Tipo desse Equipamento: ");

								for (TipoEquipamento tipoEquipamento2 : listaTipoEquipamento) {
									System.out.println("[ " + tipoEquipamento2.getIdTipoEquipamento() + " ] "
											+ tipoEquipamento2.getTipoEquiNome());

								}
								System.out.println("[ 0 ] Para sair");
								int escolhaTipo = scanner.nextInt();
								if(escolhaTipo > 0 && escolhaTipo < postoDeSaudeDAO.getIdMax()) {
									
								}else if(escolhaTipo == 0) {
									break;
								}else {
									System.out.println("insira apenas os numeros listarados");
								}
							} else {
								System.out.println("Nenhum Tipo de equipamento cadastrado.");
							}

							break;
						case 2:
							ArrayList<Equipamento> listaEquipamentos = new ArrayList<Equipamento>();
							listaEquipamentos = equipamentoDAO.getListEquipamento();
							System.out.println("lista de Equipamentos cadastrados");
							if (listaEquipamentos.size() > 0) {
								for (Equipamento equipamento2 : listaEquipamentos) {
									TipoEquipamento tipo = tipoEquipamentoDAO.getTipoEquipamentoById(equipamento2.getIdTipoEquipamento());
									System.out.println("[ " + equipamento2.getIdEquipamento() + " ] Tipo: "+ tipo.getTipoEquiNome() +" Descrição: "
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
								System.out.println("Selecione o Posto degejado: ");

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
							ArrayList<PostoDeSaude> listaPostosDeSaude = postoDeSaudeDAO.getListPostoDeSaude();

							if (listaPostosDeSaude.size() > 0) {
								System.out.println("Selecione o Posto degejado: ");

								for (PostoDeSaude postoDeSaude : listaPostosDeSaude) {
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
													.getEquipamentoByID(equipamentoDoPosto.getIdEquiDoPosto());
											TipoEquipamento tipo = tipoEquipamentoDAO
													.getTipoEquipamentoById(equi.getIdTipoEquipamento());
											System.out.println("[ " + equipamentoDoPosto.getIdEquiDoPosto() + " ]"
													+ " Tipo: " + tipo.getTipoEquiNome() + " Descrição: "
													+ equi.getDescricao() + "Quantidade: "
													+ equipamentoDoPosto.getQtdEquipamento() + " Data de entrega: "
													+ equipamentoDoPosto.getDataDeEntrega());
										}
										System.out.println("[ 0 ] Para sair");

										int escolhaEqui = scanner.nextInt();

										if (escolhaEqui > 0 && escolhaEqui <= equipamentoDoPostoDAO.getIdMax()) {

//											if (equipamentoDoPostoDAO.removerEquipamentoDoPosto(escolhaEqui)) {
//												System.out.println("Equipamento removido do posto com Sucesso!");
//											} else {
//												System.out.println("Ocorreu algum erro na remoção. Tente novamente.");
//											}

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
					break;

				default:
					System.out.println("Comando Inválido!!! Tente um novo comando");
					break;
				}

			}
		} catch (InputMismatchException e) {
			System.out.println("Deve ser inserido numero não uma letra!");
		} catch (IndexOutOfBoundsException e) {
			System.out.println("Digite apenas números que estão entre as opções.");
		}

	}
}