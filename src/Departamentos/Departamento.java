/*Lucca Blumer/10416985
* Vinicius Uliana/10418646
* Guilherme Olimpio/10417809
* Andrey Antzuk Camargo 10399782
* Projeto POO
*/

package Departamentos;

import java.util.List;

import Funcionarios.Funcionario;

public class Departamento {
  private String nome;
  private List<Funcionario> funcionarios;
  
 
  public Departamento(String nome) {
    this.nome = nome;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }
  // link com os funcionarios
  public List<Funcionario> getFuncionarios() {
    return funcionarios;
  }

  public void addFuncionario(Funcionario funcionario) {
    this.funcionarios.add(funcionario);
  }

  public void removerFuncionario(Funcionario funcionario) {
    this.funcionarios.remove(funcionario);
  }
  
  @Override
  public String toString() {
	  return nome;
  }
  
}