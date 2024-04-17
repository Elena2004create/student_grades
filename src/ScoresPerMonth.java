import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;

public class ScoresPerMonth implements Serializable {
    Map<LocalDate, List<Integer>> scoresPerMonth = new HashMap<>();
    public ScoresPerMonth generateData(){
        for(int i = 9; i < 13; i++){
            for(int j = 1; j < LocalDate.of(2023, i, 1).lengthOfMonth(); j++){
                scoresPerMonth.put(LocalDate.of(2023, i, j), new Random().ints(32, 2, 6).boxed().toList());
            }
        }
        return this;
    }
    public Collection<List<Integer>> getScoresPerMonth() {
        return scoresPerMonth.values();
    }
}


