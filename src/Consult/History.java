package src.Consult;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public record History(String operation, LocalDateTime timestamp) {
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return "History{ \n" +
                "operation=' \n" + operation + '\'' +
                ",\n \n timestamp = " + timestamp.format(formatter) + "\n" +
                '}';
    }
}
