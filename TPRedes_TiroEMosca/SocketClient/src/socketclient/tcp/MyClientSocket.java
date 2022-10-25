package socketclient.tcp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import socketclient.NetworkListener;


public class MyClientSocket implements Runnable{

	private DataInputStream dis = null;
	private DataOutputStream dos = null;
        private NetworkListener nl;
	private Socket connection;
        
	public MyClientSocket(String serverIp, int port, NetworkListener nl) {
                this.nl = nl;
		try {
			connection = new Socket(serverIp, port);			
                        nl.networkMessage(NetworkListener.TCP_MESSAGE, "Conectado ao servidor!");                       
			dos = new DataOutputStream(connection.getOutputStream());
			dis = new DataInputStream(connection.getInputStream());
                        nl.networkMessage(NetworkListener.TCP_MESSAGE, "Streams obtidos!");
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public void run() {
		try {		
			dos.writeInt(10);
			dos.flush();
			nl.networkMessage(NetworkListener.TCP_MESSAGE, "Int enviado ao servidor: 10.");			
			String stringFromServer = dis.readUTF();
			nl.networkMessage(NetworkListener.TCP_MESSAGE,"String recebido do Servidor: " + stringFromServer+ ".");
			
			dos.writeUTF("Tchau!");
			nl.networkMessage(NetworkListener.TCP_MESSAGE,"String enviado ao Servidor: Tchau.");	
			dos.flush();
                        
                        dos.close();
                        dis.close();
                        connection.close();
		}//fim do try  
		catch (IOException e) {
			e.printStackTrace();
		}//fim do catch	
                finally{
                    nl.networkMessage(NetworkListener.TCP_MESSAGE,"Fim da conexão TCP.");
                }
	}//fim do run
}