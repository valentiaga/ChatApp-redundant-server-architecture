package back;

/*
 * ver si hay que volarla
 */

import java.io.IOException;
import java.net.UnknownHostException;

public interface IEmisor 
{
	void conectar(String IP, int puerto) throws UnknownHostException, IOException;
}
