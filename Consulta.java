import java.util.Scanner;

public class Consulta {
    private static final String busca = "https://viacep.com.br/ws/";
    Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        Consulta consulta = new Consulta();
        consulta.pesquisaCEP();
        consulta.pesquisaEnd();
    }

    public void pesquisaCEP() {

        System.out.println("Informe o CEP que você deseja");
        String cep = scanner.nextLine();

        String buscaCompleta = busca + cep + "/json/";
        System.out.println(buscaCompleta);
    }

    public void pesquisaEnd() {
    }
}
