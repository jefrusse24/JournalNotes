package document;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Vector;


public class FormatEntryList implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2664991418399436332L;

	private Vector<FormatEntry> m_list = new Vector<FormatEntry>();
	
	public FormatEntryList() {
		
	}

	/**
	 * Add an entry.  There are 4 possible situations to consider
	 * 1: This new Entry does not overlap any existing entries.
	 * 2: This new Entry lies fully inside an existing entry
	 * 3: This New Entry Starts before any section, but overlaps one or more sections
	 * 4: This new Entry Starts in an existing section, and overlaps one or more sections.
	 * 
	 * @param loc
	 * @param end
	 * @param text
	 */
	public void add(int start, int end, String text) {

		FormatEntry newEntry = new FormatEntry(start,end,text);
		m_list.add(newEntry);
	}

	@SuppressWarnings("unchecked")
	private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
		Object obj = in.readObject();
		if (obj.getClass().equals(Vector.class)) {
			m_list = (Vector<FormatEntry>)obj;
		}
	}

	private void writeObject(ObjectOutputStream out) throws IOException {
		out.writeObject(m_list);
	}

	public Vector<FormatEntry> getFormatList() {
		return m_list;
	}

	public void clear() {
		m_list.clear();
	}

	
}
