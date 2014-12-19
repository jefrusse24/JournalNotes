package menubar;
import com.apple.eawt.AppEvent.PreferencesEvent;
import com.apple.eawt.PreferencesHandler;

/**
 * This is a Mac OSX unique preferences handler.  This calls the more generic perferences method
 * if a user selects on the "Preferences" option on a Mac OSX.
 * 
 * @author jeffrus
 *
 */
@SuppressWarnings("restriction")
public class MacPreferencesHandler implements PreferencesHandler {

	@Override
	public void handlePreferences(PreferencesEvent arg0) {
		System.out.println("Prefernces!!!");
		
		Preferences pref = new Preferences();
		pref.showPrefs();

	}
	
}
