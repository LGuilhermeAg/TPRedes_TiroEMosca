package socketserver.tcp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import socketserver.NetworkListener;

public class TrataConexao implements Runnable {

	private Socket connection;
	private DataInputStream dis = null;
	private DataOutputStream dos = null;
        private NetworkListener nl;
	
	public TrataConexao(Socket conn, NetworkListener nl){
		this.connection = conn;
                this.nl = nl;
	}
	
	@Override
	public void run() {
		try {			
			//Obtem os fluxos
			dos = new DataOutputStream(connection.getOutputStream());
			dis = new DataInputStream(connection.getInputStream());
			
			System.out.println("Obtendo dos streams");
                        System.out.flush();
                        nl.networkMessage(NetworkListener.TCP_MESSAGE, "Obtendo dos streams");
			//recebe um inteiro do cliente
			int code = dis.readInt();
			System.out.println("O cliente enviou o inteiro " + code);
                        System.out.flush();
                        nl.networkMessage(NetworkListener.TCP_MESSAGE, "O cliente enviou o inteiro " + code);
			//envia uma string para o cliente
			dos.writeUTF("Olá Cliente");
			dos.flush();
			System.out.println("O servidor enviou a String Olá Cliente");
                        System.out.flush();
                        nl.networkMessage(NetworkListener.TCP_MESSAGE, "O servidor enviou a String Olá Cliente");
			//recebe uma string para o cliente
			String receivedString = dis.readUTF();
			System.out.println("O cliente enviou a String " + receivedString);			
			System.out.flush();
                        nl.networkMessage(NetworkListener.TCP_MESSAGE, "O cliente enviou a String " + receivedString);
			
                        //Finalização dos Streams de comunicação e da conexão
                        if(dis != null){
				dis.close();
			}
			if(dos != null){
				dos.close();
			}
			if(connection != null){
				connection.close();
			}
		}//fim do try 
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//fim do catch
	}//fim do run
}//fim da classe TrataConexao
