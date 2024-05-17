package src.Consult;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Register {
    private List<History> historyList = new ArrayList<>();

    public void addHistory(String operation, LocalDateTime timestamp) {
        historyList.add(new History(operation, timestamp));
    }

    public List<History> getHistoryList() {
        return historyList;
    }


    @Override
    public String toString() {
        return "Register{" +
                "historyList=" + historyList +
                '}';
    }
}
