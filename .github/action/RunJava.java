import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class RunJava {
	public static void main(String[] args) {
		System.out.println("Hello walk action");
		walk("/home/runner/work/blog/blog/./docs/");
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
            	String fileName = f.getName();
            	if(fileName.contains("index.html")) {
            		System.out.println(fullPath);
            		changeTxt(fullPath);
            	}
            }
        }
    }
	
	private static void changeTxt( String path ) {
		try {
			boolean isChanged = false;
			
			StringBuilder sb = new StringBuilder();
	        File file = new File(path);
	        FileInputStream fis = new FileInputStream(file);
	        InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
	        BufferedReader br = new BufferedReader(isr);
	        String line = "";
	        
	        while((line = br.readLine()) != null){
	            if(line.contains("test01")) {
	            	isChanged = true;
	            	line = line.replace("test01", "taaa01");
	            }
	        	sb.append(line);
	            sb.append(System.getProperty("line.separator"));
	        }
	        
	        br.close();
	        
	        if(isChanged) {
	        	System.out.println("changed");
	        	file.delete();
        		file.createNewFile();

    			BufferedWriter output = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file.getPath()), "UTF8"));

    			output.write(sb.toString());
    			output.close();
	        }
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
		}
	}

}
