import java.io.File;
import java.io.IOException;

public class DriverCensor {

	public static void main(String[] args) throws IOException {
		
		File source = null;
		File destination = null;
		File badlist = null;
		Censor.censor(source, destination, badlist);
		
	}

}
