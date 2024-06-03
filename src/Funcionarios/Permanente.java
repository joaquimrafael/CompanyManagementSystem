/*Lucca Blumer/10416985
* Vinicius Uliana/10418646
* Guilherme Olimpio/10417809
* Andrey Antzuk Camargo 10399782
* Projeto POO
*/

package Funcionarios;

import Departamentos.Departamento;

public class Permanente extends Funcionario {
	  public Permanente(long id, String nome, double salario, String cargo, Departamento departamento) {
	    super(id, nome, cargo,departamento);
	    this.salario = calcularSalario(salario);
	  }

	
	  
	  @Override
	    public String toString() {
	        return id + ";" + nome + ";" + salario + ";" + cargo + ";" + departamento;
	    }



	@Override
	public double calcularSalario(double salario) {
		// TODO Auto-generated method stub
		return salario;
	}
	}