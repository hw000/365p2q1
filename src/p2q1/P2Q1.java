package p2q1;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;





public class P2Q1 {
	
	public static void main(String[] args) throws IOException {
		//int[] codes = new int[256];
		String[] codes = new String[256];
		String file = fileChooser();
		//String file = "src/wav_sample.wav";
		byte[] arrayOfBytes=getFileInfo(file);
		
		int[] frequency = new int[256];
		for(int i =0; i<256;i++){
			frequency[i]=0;
		}
		
		
		// use later
		int size_of_file=arrayOfBytes.length;
		
		
		// count frequency of symbols
		for(int i =0; i<arrayOfBytes.length;i++){
			frequency[arrayOfBytes[i]&0xff]++;

		}
		
		// init linked list
		LinkList list = new LinkList();
	
		// insert initial frequencies
		for(int i = 0;i<frequency.length;i++){
		
			Node tmp = new Node(frequency[i],i);
			list.insert(tmp);
		}
		
	
		
		// generate tree
		while(list.length>1){
			// remove nodes with lowest frequency
			Node tmp1 = list.removeFirst();
			Node tmp2 = list.removeFirst();
			
			// create parent with the two nodes as children
			Node parent = new Node(tmp1,tmp2);
			list.insert(parent);
		}
		
		
		// remove tree place in variable
		Node tree = list.removeFirst();
		tree.display();	
		Node tmp = tree;

		System.out.println(tmp.getLeft().getLeft().getLeft().getRight().getLeft().getVal());
		
		
		System.out.println("------------------post order------------------");
		Node.postOrder(tree,"");
		System.arraycopy(Node.getCodes(), 0, codes, 0, Node.getCodes().length);
		
		
		for(int i=0;i<256;i++){
			System.out.println(i+" : "+codes[i]);
		}
		
		
		// remove later
		// testing time
		long startTime=System.currentTimeMillis();
		String test="";
		for(int i=0; i<arrayOfBytes.length;i++){
			//test+=codes[arrayOfBytes[i]&0xff];
			test=test.concat(codes[arrayOfBytes[i]&0xff]);
		}
		System.out.println(test.length());

		
		byte[] out=new byte[test.length()/8];
		byte tmp_byte;
		for(int i =0; i<out.length;i++){
			tmp_byte=0;
			for(int j=i*8;j<i*8+8;j++){
				if(test.charAt(j)=='0'){
					tmp_byte=(byte) (tmp_byte<<1);
				}else{
					tmp_byte=(byte) ((tmp_byte<<1)+1);
				}
			}
			out[i]=tmp_byte;
		}
		System.out.println("done");
		System.out.println(size_of_file);
		System.out.println(out.length);
		System.out.println(out[0]&0xff);
		
		// run time testing
		long endTime=System.currentTimeMillis();
		long totalTime=endTime-startTime;
		System.out.println("time: "+totalTime);
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
