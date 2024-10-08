
package Funcionarios;

import java.util.List;

import Departamentos.Departamento;

public class Horista extends Funcionario {
	  private int horas;
	  private double taxa;

	  public Horista(long id, String nome, double salario, String cargo, int horas, double taxa,Departamento departamento) {
		  super(id, nome, cargo,departamento);
	    this.horas = horas;
	    this.taxa = taxa;
	    this.salario = calcularSalario(salario);
	  }

	  
	  @Override
	    public String toString() {
	        return id + ";" + nome + ";" + salario + ";" + cargo + ";" + departamento+ ";" + horas + ";" + taxa;
	    }

	


	@Override
	public double calcularSalario(double salario) {
		return (salario * this.horas) + this.taxa;
	}
	}
