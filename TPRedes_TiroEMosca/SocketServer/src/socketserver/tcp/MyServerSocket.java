package socketserver.tcp;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.JOptionPane;
import socketserver.AppConstants;
import socketserver.NetworkListener;

public class MyServerSocket implements Runnable {
	
	private boolean connected = false;
	private ServerSocket serverSocket = null;
	private Socket connection = null;
	private TrataConexao trata = null;
        private NetworkListener nl;
	
        public MyServerSocket(NetworkListener nl){
            this.nl = nl;
        }       
	@Override
	public void run() {
		try {
			serverSocket = new ServerSocket(AppConstants.SERVER_PORT);
			//JOptionPane.showMessageDialog(null, "Servidor está executando");
			nl.networkMessage(NetworkListener.TCP_MESSAGE, "Servidor está executando.");
                        connected = true;
			while(connected == true){			
				System.out.println("Esperando por conexões.");
                                nl.networkMessage(NetworkListener.TCP_MESSAGE, "Esperando por conexões na porta " + AppConstants.SERVER_PORT+ " .");
				//Recebe conexão - função de espera de conexão - accept
                                connection = serverSocket.accept();
				trata = new TrataConexao(connection,nl);
				Thread trataConexao = new Thread(trata);
				trataConexao.start();
				System.out.println("Thread para tratar conexão startada");
                                nl.networkMessage(NetworkListener.TCP_MESSAGE, "Thread para tratar conexão startada.");
			}				
		} catch (IOException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Falha na conexão com o servidor!");
                        
                }
	}
	public void disconnect(){
            nl.networkMessage(NetworkListener.TCP_MESSAGE, "Vai encerrar a conexão.");	
                try{
			if(serverSocket != null){
				serverSocket.close();
				connected = false;
			}
		}
		catch(IOException ex){
			ex.printStackTrace();
			JOptionPane.showMessageDialog(null, "Falha ao fechar conexão!");
		}
                finally{
                    nl.networkMessage(NetworkListener.TCP_MESSAGE, "Conexão encerrada");
                }

	}


}
