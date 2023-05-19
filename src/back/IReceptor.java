package back;

/*
 * ver si hay que volarla
 */

import java.io.IOException;
import java.net.Socket;

public interface IReceptor 
{
	void Conectar(int puerto) throws IOException;
	Socket getsocket();
}
