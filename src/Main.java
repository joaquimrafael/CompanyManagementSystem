
import java.io.*;
import java.util.*;

import Departamentos.*;
import Funcionarios.*;

public class Main {
    // Scanner para entrada de dados do usuário
    private Scanner scanner = new Scanner(System.in);
    
    public void menuPrincipal() {
    	System.out.println("===== Menu Principal =====");
        System.out.println("1. Criar Funcionario");
        System.out.println("2. Mostrar Funcionarios");
        System.out.println("3. Alterar Funcionario");
        System.out.println("4. Apagar Funcionario");
        System.out.println("5. Criar Departamento");
        System.out.println("6. Mostrar Departamentos");
        System.out.println("7. Mostrar Funcionarios por Departamento");
        System.out.println("8. Excluir Departamento");
        System.out.println("9. Sair");
        System.out.print("Escolha uma opção: ");

        int opcao = scanner.nextInt();
        scanner.nextLine();

        // Switch para executar a operação correspondente à opção escolhida
        switch (opcao) {
            case 1:
                Random gerador = new Random();
                int count = gerador.nextInt();
                criarFuncionario(count);
                break;
            case 2:
                mostrarFuncionarios();
                break;
            case 3:
                alterarFuncionario();
                break;
            case 4:
                apagarFuncionario();
                break;
            case 5:
                criarDepartamento();
                break;
            case 6:
                mostrarDepartamentos();
                break;
            case 7:
                mostrarFuncionariosPorDepartamento();
                break;
            case 8:
                excluirDepartamento();
                break;
            case 9:
                System.out.println("Saindo...");
                System.exit(0);
                break;
            default:
                System.out.println("Opção inválida!");
        }
    }

    // Método para solicitar operações do usuário repetidamente
    public void solicitarOperacao() {
        while (true) {
            menuPrincipal();
        }
    }

    public void criarFuncionario(int count) {
        System.out.println("Informe o nome do funcionário: ");
        String nome = scanner.nextLine();
        System.out.println("Informe o salário do funcionário: ");
        double salario = scanner.nextDouble();
        scanner.nextLine();
        System.out.println("Informe o cargo do funcionário (permanente, horista, temporario, freelancer): ");
        String cargo = scanner.nextLine();
        System.out.println("Informe o departamento do funcionário: ");
        String departamento = scanner.nextLine();

        Funcionario funcionario = null;
        // Cria um objeto Funcionario baseado no tipo de cargo
        if (cargo.equalsIgnoreCase("permanente")) {
            funcionario = new Permanente(count, nome, salario, cargo, new Departamento(departamento));
        } else if (cargo.equalsIgnoreCase("horista")) {
            System.out.println("Informe as horas trabalhadas: ");
            int horas = scanner.nextInt();
            System.out.println("Informe a taxa por hora: ");
            double taxa = scanner.nextDouble();
            scanner.nextLine();
            funcionario = new Horista(count, nome, salario, cargo, horas, taxa, new Departamento(departamento));
        } else if (cargo.equalsIgnoreCase("temporario")) {
            System.out.println("Informe as horas trabalhadas: ");
            int horas = scanner.nextInt();
            scanner.nextLine();
            funcionario = new Temporario(count, nome, salario, cargo, horas, new Departamento(departamento));
        } else if (cargo.equalsIgnoreCase("freelancer")) {
            System.out.println("Informe o número de projetos concluídos: ");
            int projetos = scanner.nextInt();
            System.out.println("Informe a taxa por projeto: ");
            double taxa = scanner.nextDouble();
            scanner.nextLine();
            funcionario = new Freelancer(count, nome, salario, cargo, projetos, taxa, new Departamento(departamento));
        } else {
            System.out.println("Cargo desconhecido: " + cargo);
            return;
        }

        // Tenta cadastrar o funcionário e informa o usuário sobre o resultado
        if (cadastrarFuncionario(funcionario)) {
            System.out.println("Funcionário criado com sucesso! Pressione enter para continuar...");
            scanner.nextLine();
        } else {
            System.out.println("Erro ao criar o funcionário.");
        }
    }

    public boolean cadastrarFuncionario(Funcionario funcionario) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("funcionarios.txt", true))) { // criação de variavel buffer pra escrita no arquivo de funcionarios
            writer.write(funcionario.toString());
            writer.newLine();
            return true;
        } catch (IOException e) {
            System.out.println("Erro ao salvar funcionário: " + e.getMessage());
            return false;
        }
    }

    // Método para exibir todos os funcionários
    public void mostrarFuncionarios() {
        try (BufferedReader reader = new BufferedReader(new FileReader("funcionarios.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler funcionários: " + e.getMessage());
        }
    }

    public void alterarFuncionario() {
        System.out.println("Informe o ID do funcionário que deseja alterar: ");
        long id = scanner.nextLong();
        scanner.nextLine();  // Consome a nova linha pendente
        Funcionario funcionario = obterFuncionarioPorId(id);
        if (funcionario != null) {
            System.out.println("Informe o novo nome do funcionário (atual: " + funcionario.getNome() + "): ");
            String nome = scanner.nextLine();
            System.out.println("Informe o novo salário do funcionário (atual: " + funcionario.getSalario() + "): ");
            double salario = scanner.nextDouble();
            scanner.nextLine();  // Consome a nova linha pendente
            System.out.println("Informe o novo cargo do funcionário (atual: " + funcionario.getCargo() + "): ");
            String cargo = scanner.nextLine();
            funcionario.setNome(nome);
            funcionario.setSalario(salario);
            funcionario.setCargo(cargo);
            if (atualizarFuncionario(funcionario)) {
                System.out.println("Funcionário atualizado com sucesso!");
            } else {
                System.out.println("Erro ao atualizar o funcionário.");
            }
        } else {
            System.out.println("Funcionário não encontrado.");
        }
    }

    // Método para atualizar os dados do funcionário no arquivo
    public boolean atualizarFuncionario(Funcionario funcionario) {
        List<Funcionario> funcionarios = obterTodosFuncionarios();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("funcionarios.txt"))) {
            for (Funcionario f : funcionarios) {
                if (f.getId() == funcionario.getId()) {
                    writer.write(funcionario.toString());
                } else {
                    writer.write(f.toString());
                }
                writer.newLine();
            }
            return true;
        } catch (IOException e) {
            System.out.println("Erro ao atualizar funcionário: " + e.getMessage());
            return false;
        }
    }

    public void apagarFuncionario() {
        System.out.println("Informe o ID do funcionário que deseja apagar: ");
        long id = scanner.nextLong();
        scanner.nextLine();
        if (excluirFuncionario(id)) {
            System.out.println("Funcionário apagado com sucesso!");
        } else {
            System.out.println("Erro ao apagar o funcionário.");
        }
    }

    // Método para remover um funcionário do arquivo
    public boolean excluirFuncionario(long id) {
        List<Funcionario> funcionarios = obterTodosFuncionarios();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("funcionarios.txt"))) {
            for (Funcionario f : funcionarios) {
                if (f.getId() != id) {
                    writer.write(f.toString());
                    writer.newLine();
                }
            }
            return true;
        } catch (IOException e) {
            System.out.println("Erro ao apagar funcionário: " + e.getMessage());
            return false;
        }
    }

    public Funcionario obterFuncionarioPorId(long id) {
        List<Funcionario> funcionarios = obterTodosFuncionarios();
        for (Funcionario f : funcionarios) {
            if (f.getId() == id) {
                return f;
            }
        }
        return null;
    }

    // Método para obter todos os funcionários do arquivo
    public List<Funcionario> obterTodosFuncionarios() {
        List<Funcionario> funcionarios = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("funcionarios.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Funcionario funcionario = Funcionario.fromString(line);
                funcionarios.add(funcionario);
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler funcionários: " + e.getMessage());
        }
        return funcionarios;
    }

    // Método para criar um novo departamento no programa
    public void criarDepartamento() {
        System.out.println("Informe o nome do departamento: ");
        String nome = scanner.nextLine();
        Departamento departamento = new Departamento(nome);
        if (cadastrarDepartamento(departamento)) {
            System.out.println("Departamento criado com sucesso! Pressione enter para continuar...");
            scanner.nextLine();
        } else {
            System.out.println("Erro ao criar o departamento.");
        }
    }

    // Método para salvar o departamento no arquivo
    public boolean cadastrarDepartamento(Departamento departamento) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("departamentos.txt", true))) {
            writer.write(departamento.toString());
            writer.newLine();
            return true;
        } catch (IOException e) {
            System.out.println("Erro ao salvar departamento: " + e.getMessage());
            return false;
        }
    }

    public void mostrarDepartamentos() {
        try (BufferedReader reader = new BufferedReader(new FileReader("departamentos.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler departamentos: " + e.getMessage());
        }
    }

    public void mostrarFuncionariosPorDepartamento() {
        System.out.println("Informe o nome do departamento: ");
        String nome = scanner.nextLine();
        List<Funcionario> funcionarios = obterFuncionariosPorDepartamento(nome);
        for (Funcionario funcionario : funcionarios) {
            System.out.println("ID: " + funcionario.getId() + ", Nome: " + funcionario.getNome() + ", Salário: "
                    + funcionario.getSalario() + ", Cargo: " + funcionario.getCargo());
        }
    }

    public List<Funcionario> obterFuncionariosPorDepartamento(String nomeDepartamento) {
        List<Funcionario> funcionariosPorDepartamento = new ArrayList<>();
        List<Funcionario> funcionarios = obterTodosFuncionarios();
        for (Funcionario f : funcionarios) {
            if (f.getDepartamento().getNome().equalsIgnoreCase(nomeDepartamento)) {
                funcionariosPorDepartamento.add(f);
            }
        }
        return funcionariosPorDepartamento;
    }
    
    public void excluirDepartamento() {
        System.out.println("Informe o nome do departamento que deseja excluir: ");
        String nome = scanner.nextLine();
        List<Departamento> departamentos = obterTodosDepartamentos();
        
        boolean departamentoRemovido = false;
        Iterator<Departamento> iterator = departamentos.iterator();
        
        // Percorre a lista de departamentos e remove o departamento desejado
        while (iterator.hasNext()) {
            Departamento departamento = iterator.next();
            if (departamento.getNome().equalsIgnoreCase(nome)) {
                iterator.remove();
                departamentoRemovido = true;
            }
        }
        
        // Atualiza o arquivo de departamentos se o departamento foi removido
        if (departamentoRemovido) {
            if (atualizarDepartamentos(departamentos)) {
                System.out.println("Departamento excluído com sucesso!");
            } else {
                System.out.println("Erro ao atualizar o arquivo de departamentos.");
            }
        } else {
            System.out.println("Departamento não encontrado.");
        }
    }

    // Método para atualizar os departamentos no arquivo
    public boolean atualizarDepartamentos(List<Departamento> departamentos) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("departamentos.txt"))) {
            for (Departamento departamento : departamentos) {
                writer.write(departamento.toString());
                writer.newLine();
            }
            return true;
        } catch (IOException e) {
            System.out.println("Erro ao atualizar departamentos: " + e.getMessage());
            return false;
        }
    }
    
    public List<Departamento> obterTodosDepartamentos() {
        List<Departamento> departamentos = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("departamentos.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                departamentos.add(new Departamento(line));
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler departamentos: " + e.getMessage());
        }
        return departamentos;
    }

    public static void main(String[] args) {
        Main app = new Main();
        app.solicitarOperacao();
    }
}
