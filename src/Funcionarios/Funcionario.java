/*Lucca Blumer/10416985
* Vinicius Uliana/10418646
* Guilherme Olimpio/10417809
* Andrey Antzuk Camargo 10399782
* Projeto POO
*/

package Funcionarios;
import Departamentos.*;

public abstract class Funcionario {
    protected long id;
    protected String nome;
    protected double salario;
    protected String cargo;
    protected Departamento departamento;

    public Funcionario(long id, String nome,  String cargo, Departamento departamento) {
        this.id = id;
        this.nome = nome;
        this.cargo = cargo;
        this.departamento = departamento;
    }
    

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamentos) {
        this.departamento = departamentos;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(id).append(";").append(nome).append(";").append(salario).append(";").append(cargo).append(";").append(departamento);
        return sb.toString();
    }

    public static Funcionario fromString(String str) { // função de tokenização simplificada para ler os dados do arquivo
        String[] parts = str.split(";");
        long id = Long.parseLong(parts[0]);
        String nome = parts[1];
        double salario = Double.parseDouble(parts[2]);
        String cargo = parts[3];
        Departamento departamento = new Departamento(parts[4]);

        switch (cargo.toLowerCase()) {
            case "permanente":
                return new Permanente(id, nome, salario, cargo, departamento);
            case "horista":
                int horas = Integer.parseInt(parts[5]);
                double taxa = Double.parseDouble(parts[6]);
                return new Horista(id, nome, salario, cargo, horas, taxa, departamento);
            case "temporario":
                horas = Integer.parseInt(parts[5]);
                return new Temporario(id, nome, salario, cargo, horas, departamento);
            case "freelancer":
                int projetos = Integer.parseInt(parts[5]);
                taxa = Double.parseDouble(parts[6]);
                return new Freelancer(id, nome, salario, cargo, projetos, taxa, departamento);
            default:
                return null;
        }
    }


	public abstract double calcularSalario(double salario); // metodo abstrato a ser implementado por cada tipo de cargo diferente
}
