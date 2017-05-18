package sendimage;

import java.io.IOException;

/**
 *
 * @author Marzorati Stefano
 */
public class SendImage {

   
    public static void main(String[] args) throws IOException {
        FileServer fs = new FileServer(1988, "D:\\ciao.jpg");
        fs.start();
	FileClient fc = new FileClient("localhost", 1988);
    }
    
}
