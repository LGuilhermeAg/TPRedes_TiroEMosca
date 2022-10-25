import java.io.Console;
import java.util.ArrayList;
import java.util.Scanner;

public class App {
    public static ArrayList<Integer> intToArray(int num){
        ArrayList<Integer> shotArray = new ArrayList<>();
        if(num != 0){
            int temp = num%10;
            num/=10;
            intToArray(num);
            shotArray.add(temp);
        }
        return shotArray;
    }
    public static void main(String[] args) throws Exception {
        // Inicialização
        Scanner scan = new Scanner(System.in);
        int[] target = new int[3];
        int palpite=0;
        int tiros;
        int moscas;
        ArrayList<Integer> shotArray = new ArrayList<>();
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
            if(palpite == 0) break;
            System.out.println("O número gerado foi:"+target);              //APAGAAAAR - APENAS DEBUG
            if(palpite == fullTarget){
                System.out.println("Parabéns, você acertou os três dígitos!");
                historico+="\nPalpite "+tries+": "+palpite+" - "+tiros+"T"+moscas+"M";
            }else{
                shotArray = intToArray(palpite);
                if(shotArray.get(0)==null||shotArray.get(1)==null||shotArray.get(2)==null){
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
            }
            System.out.println(historico);
        }while(palpite!=0||palpite!=fullTarget);
        scan.close();
    }
}
