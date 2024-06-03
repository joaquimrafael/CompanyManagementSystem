/*Lucca Blumer/10416985
* Vinicius Uliana/10418646
* Guilherme Olimpio/10417809
* Andrey Antzuk Camargo 10399782
* Projeto POO
*/

package Funcionarios;


import Departamentos.Departamento;

public class Temporario extends Funcionario {
	  private int horas;
	  public Temporario(long id, String nome, double salario, String cargo, int horas,Departamento departamento) {
		super(id, nome, cargo,departamento);
		this.horas = horas;
	    this.salario = calcularSalario(salario);
	  }
	  
	  @Override
	  public String toString() {
	      return id + ";" + nome + ";" + salario + ";" + cargo + ";" + departamento+ ";" + horas;
	   }

	@Override
	public double calcularSalario(double salario) {
		return (salario * this.horas);
	}
}