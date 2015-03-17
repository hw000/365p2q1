package p2q1;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;

// uncomment for file chooser
//import java.nio.ByteBuffer;
//import java.nio.ByteOrder;
//import java.nio.ShortBuffer;
//import java.util.LinkedList;
//
//import javax.swing.JButton;
//import javax.swing.JFileChooser;

public class P2Q1 {
	public static void main(String[] args) throws IOException {
		
		//file chooser///////////////////////////////////////////
		
//		
//		JButton open = new JButton();
//		JFileChooser fc = new JFileChooser();
//		fc.setCurrentDirectory(new java.io.File("."));
//		fc.setDialogTitle("");
//		
//		
//		if(fc.showOpenDialog(open)==JFileChooser.APPROVE_OPTION){
//			//
//		}
		
		
		////////////////////////////////////////////////////////
		


		//String file = fc.getSelectedFile().getAbsolutePath();
		String file = "src/wav_sample.wav";
		byte[] arrayOfBytes=getFileInfo(file);
		
		int[] test = new int[256];
		for(int i =0; i<256;i++){
			test[i]=0;
		}
		
		
		// use later
		//int size_of_file=arrayOfBytes.length;
		
		// count frequency of symbols
		for(int i =0; i<arrayOfBytes.length;i++){
			test[arrayOfBytes[i]&0xff]++;

		}
		LinkList list = new LinkList();
	
		// insert initial values in order;
		//list.insertFirstNode(test[0], 0);
		for(int i = 0;i<test.length;i++){
		//for(int i = 1;i<6;i++){
			Node tmp = new Node(test[i],i);
			list.insert(tmp);
		}
		
		// remove later
		System.out.println("-------------------------------------------------------------");
		System.out.println("initial list");
		System.out.println("-------------------------------------------------------------");
		list.display();

		System.out.println();
		System.out.println(list.length);
		
		// generate tree
		while(list.length>1){
			Node tmp1 = list.removeFirst();
			Node tmp2 = list.removeFirst();
			Node parent = new Node(tmp1,tmp2);
			list.insert(parent);
		}
		
		
		//remove  later
		System.out.println(list.length);
		

		//list.display();
		
		Node tree = list.removeFirst();
		tree.display();

		
		
		
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
    
    //don't think i need this anymore
//    public static float[] toFloatArray(byte[] arr_of_bytes){
//    
//
//	    	ShortBuffer sbuf = 
//	    	ByteBuffer.wrap(arr_of_bytes).order(ByteOrder.LITTLE_ENDIAN).asShortBuffer();	
//	    	
//	    	short[] audioShorts = new short[sbuf.capacity()];
//	    	
//	    	sbuf.get(audioShorts);
//	    	
//	    	float[] audioFloats = new float[audioShorts.length];
//	    	
//	    	for (int i = 0; i <audioShorts.length;i++) {
//	    		audioFloats[i] = ((float) audioShorts[i]);
//	    		
//	    	}
//
//	    	return audioFloats;
//	    	
//
//
//    	
//    }
//    
    
	
}
