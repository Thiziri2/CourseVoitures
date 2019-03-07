package observeurs;

public interface UpdateEventSender {  
        public void add(UpdateEventListener listener);
        public void update();
 }
