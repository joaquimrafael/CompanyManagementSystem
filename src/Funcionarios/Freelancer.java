
package Funcionarios;

import Departamentos.Departamento;

public class Freelancer extends Funcionario {
	  private int projetos;
	  private double taxa;

	  public Freelancer(long id, String nome, double salario, String cargo, int projetos, double taxa,Departamento departamento) {
		  super(id, nome, cargo,departamento);
	    this.projetos = projetos;
	    this.taxa = taxa;
	    this.salario = calcularSalario(salario);
	  }

	 
	  
	  @Override
	    public String toString() {
	        return id + ";" + nome + ";" + salario + ";" + cargo + ";" + departamento+ ";" + projetos + ";" + taxa;
	    }



	@Override
	public double calcularSalario(double salario) {
		return salario + (this.projetos * this.taxa);
	}
	}
