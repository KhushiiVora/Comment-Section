package NewsFeed.Utils;

public class IdGenerator {
    static int id = 0;

    public static int generateId() {
        id++;
        return id;
    }

}
