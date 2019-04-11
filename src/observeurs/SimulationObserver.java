package observeurs;

import java.util.ArrayList;

public class SimulationObserver implements UpdateEventSender{
	ArrayList< UpdateEventListener> listeners;
	
	public void add(UpdateEventListener listener) {
		listeners.add(listener);
		
	}

	public void update() {
		for( UpdateEventListener listener : listeners) {
				listener.manageUpdate();
		}
	}

}
