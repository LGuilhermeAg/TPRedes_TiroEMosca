import java.io.Console;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.IOException;

public class App {
    // Transforma o valor da tentativa em um array para comparação de posições (tiros e moscas)
    public static ArrayList<Integer> intToArray(ArrayList<Integer> shotArray, int num){
        if(num != 0){
            int temp = num%10;
            num/=10;
            intToArray(shotArray, num);
            shotArray.add(temp);
        }
        return shotArray;
    }
    // Classe de interação com o console para limpar a tela
    public static void clearConsole() throws IOException, InterruptedException {
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
    }

    public static void main(String[] args) throws Exception {
        // Inicialização
        Scanner scan = new Scanner(System.in);
        int[] target = new int[3];
        int palpite=0;
        int tiros;
        int moscas;
        int tries = 0;
        String historico="";                                               // String para armazenar o histórico de tentativas

        // Geração do número aleatório e seu array de posições para comparação posterior de tiros e moscas
        target[0] = (int) Math.floor(Math.random()*(9)+1);
        do target[1] = (int) Math.floor(Math.random()*(9)+1); while(target[1] == target[0]);
        do target[2] = (int) Math.floor(Math.random()*(9)+1); while(target[2] == target[0] || target[2] == target[1]);
        int fullTarget = target[0]*100 + target[1]*10 + target[2];
        
        // Início do jogo
        do{
            moscas=0;tiros=0;
            System.out.println("Insira o número do "+(++tries)+"º palpite:\t\t\t(0 para desistir)");
            palpite = scan.nextInt();

            System.out.println("Voce digitou:"+palpite);
            if(palpite == 0){
                System.out.println("Você desistiu! O número era "+fullTarget);
                break;
            } 
            System.out.println("O número gerado foi:"+fullTarget);              //APAGAAAAR - APENAS DEBUG
            if(palpite == fullTarget){
                clearConsole();
                System.out.println("Parabéns, você acertou os três dígitos!");
                moscas=3;
                historico+="\nPalpite "+tries+": "+palpite+" - "+tiros+"T"+moscas+"M";
                System.out.println(historico);
                break;
            }else{
                ArrayList<Integer> shotArray = new ArrayList<>();
                shotArray = intToArray(shotArray,palpite);
                System.out.println("O array gerado foi:"+shotArray);         
                if(palpite<100 || palpite>999){
                    clearConsole();
                    System.err.println("Você deve informar um número de 3 dígitos!");
                }else{
                    for(int i = 0; i < shotArray.size(); i++){
                        for(int j = 0; j < target.length; j++){
                            if(shotArray.get(i) == target[j]){
                                if(i == j){
                                    moscas++;
                                }else{
                                    tiros++;
                                }
                            }
                        }
                    }
                }
                historico+="\nPalpite "+tries+": "+palpite+" - "+tiros+"T"+moscas+"M";
                clearConsole();
                System.out.println(historico);
            }
        }while(palpite!=0||palpite!=fullTarget);
        scan.close();
    }
}
