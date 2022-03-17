package dogz.bot.persistance;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashSet;
import java.util.Map;
import java.util.TreeMap;

public class LocalDB implements IDataBase{
    private Map<String,String> buttonsID;
    private HashSet<String> eventChannelID;

    private static final Logger LOG = LogManager.getLogger("DBLogger");

    LocalDB(){
        buttonsID = new TreeMap<>();
        eventChannelID = new HashSet<>();
    }

    @Override
    public void saveButtonID(String id, String desc) {
        if(!buttonsID.containsKey(id)) {
            buttonsID.put(id, desc);
            LOG.log(Level.TRACE, "ADD: Button id == {}, desc == {}", id, desc);
        }
    }

    @Override
    public Map<String,String> loadButtonID() {
        return buttonsID;
    }

    @Override
    public void saveEventChannel(String id) {
        eventChannelID.add(id);
        LOG.log(Level.TRACE,"ADD: Event ID == {}",id);
    }

    @Override
    public HashSet<String> loadEventChannel() {
        return eventChannelID;
    }
}
