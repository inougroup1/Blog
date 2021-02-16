import java.io.File;

public class RunJava {

	public static void main(String[] args) {
		System.out.println("Hello walk action");
		walk(".docs\\");
	}
	
	private static void walk( String path ) {

        File root = new File( path );
        File[] list = root.listFiles();

        if (list == null) {
        	return;
        }

        for ( File f : list ) {
            if ( f.isDirectory() ) {
                walk( f.getAbsolutePath() );
            }
            else {
            	String fullPath = f.getAbsoluteFile().toString();
                System.out.println(fullPath);
            }
        }
    }
}
