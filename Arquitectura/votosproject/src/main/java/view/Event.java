package view;

public class Event {
    private String request;
    private Object content;

    public Event(String request, Object content) {
        this.request = request;
        this.content = content;
    }

    public String getRequest() {
        return request;
    }

    public Object getContent() {
        return content;
    }
}
