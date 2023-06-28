import java.util.Scanner;

public class Main {


    public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in);
        Consulta consulta = new Consulta();

        boolean rodando = true;
        while(rodando == true){
            System.out.println("\n┌──────────────┐");
            System.out.println("│ Consulta CEP │");
            System.out.println("└──────────────┘\n");        

            System.out.println("Escolha a forma que deseja fazer a consulta:");
            System.out.println("┌────────────────────────────────────────────────────────────┐");
            System.out.println("│[1] Pesquisar por CEP (Ex : 00000000)                       │");
            System.out.println("│[2] Pesquisar por Endereço (Ex : Estado, Cidade, Logradouro)│");
            System.out.println("│[3] Sair                                                    │");
            System.out.println("└────────────────────────────────────────────────────────────┘\n");
            String choice = scanner.nextLine();


            switch(choice){
                case "1":
                consulta.pesquisaCEP();
                break;

                case "2":
                consulta.pesquisaEnd();
                break;

                case "3":
                rodando = false;
                break;
            }
            
    }    
    scanner.close();

}
}