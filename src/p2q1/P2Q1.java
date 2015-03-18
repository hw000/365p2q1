package p2q1;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;



public class P2Q1 {
	public static void main(String[] args) throws IOException {
		



		String file = fileChooser();
		//String file = "src/wav_sample.wav";
		byte[] arrayOfBytes=getFileInfo(file);
		
		int[] frequency = new int[256];
		for(int i =0; i<256;i++){
			frequency[i]=0;
		}
		
		
		// use later
		//int size_of_file=arrayOfBytes.length;
		
		// count frequency of symbols
		for(int i =0; i<arrayOfBytes.length;i++){
			frequency[arrayOfBytes[i]&0xff]++;

		}
		LinkList list = new LinkList();
	
		// insert initial values in order;

		for(int i = 0;i<frequency.length;i++){
		//for(int i = 1;i<6;i++){
			Node tmp = new Node(frequency[i],i);
			list.insert(tmp);
		}
		
	
		
		// generate tree
		while(list.length>1){
			Node tmp1 = list.removeFirst();
			Node tmp2 = list.removeFirst();
			Node parent = new Node(tmp1,tmp2);
			list.insert(parent);
		}
		
		
		// remove tree place in variable
		Node tree = list.removeFirst();
		tree.display();

		
		
		
	}
	
	
	// file chooser
	public static String fileChooser(){
		
		JButton open = new JButton();
		JFileChooser fc = new JFileChooser();
		fc.setCurrentDirectory(new java.io.File("."));
		fc.setDialogTitle("");
		
		
		if(fc.showOpenDialog(open)==JFileChooser.APPROVE_OPTION){
			//
		}
		String file = fc.getSelectedFile().getAbsolutePath();
		return file;
	}
	
	
	// places file contents in array
    public static byte[] getFileInfo(String fileName) throws IOException {
        
        int read;
        
        ByteArrayOutputStream out =new ByteArrayOutputStream();
        @SuppressWarnings("resource")
		BufferedInputStream in = new BufferedInputStream(
                                 new FileInputStream(fileName));
        
        byte[] buffer = new byte[10000]; //maybe alter to maxfilesize provided in assignment description
            
        while((read = in.read(buffer))>0) { //.read gets length of file
            out.write(buffer,0,read);
        }
        out.flush();
        byte[] fileBytes = out.toByteArray();
        
        return fileBytes;
        
    }
    

    
	
}
