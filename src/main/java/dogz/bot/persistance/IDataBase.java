package dogz.bot.persistance;

import java.util.HashSet;
import java.util.Map;

public interface IDataBase {
    public void saveButtonID(String id, String desc);
    public Map<String,String> loadButtonID();

    public void saveEventChannel(String id);
    public HashSet<String> loadEventChannel();
}
