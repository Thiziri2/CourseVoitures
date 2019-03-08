package observeurs;

import java.util.ArrayList;

public class Simulation implements UpdateEventSender{
	ArrayList< UpdateEventListener> listeners;
	@Override
	public void add(UpdateEventListener listener) {
		listeners.add(listener);
		
	}

	@Override
	public void update() {
		for( UpdateEventListener listener : listeners) {
				listener.manageUpdate();
		}
	}

}
