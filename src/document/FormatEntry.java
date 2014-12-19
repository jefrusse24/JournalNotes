package document;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class FormatEntry implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3488432773970905127L;
	int start;
	int end;
	String format;
	
	public FormatEntry(int start, int end, String format) {
		this.start = start;
		this.end = end;
		this.format = format;
	}
	
	private void readObject(ObjectInputStream in) throws IOException {
		format = in.readUTF();
		start = in.readInt();
		end = in.readInt();
	}

	private void writeObject(ObjectOutputStream out) throws IOException {
		out.writeUTF(format);
		out.writeInt(start);
		out.writeInt(end);
	}

}
