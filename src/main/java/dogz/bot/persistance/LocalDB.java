package dogz.bot.persistance;

import dogz.bot.commands.EventAdventiser;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

public class LocalDB implements IDataBase{
    private final Map<String,String> buttonsID;
    private final HashSet<String> eventChannelID;
    private final LinkedList<EventAdventiser.EventParameter> eventParameters;

    private static final Logger LOG = LogManager.getLogger("DBLogger");

    public LocalDB(){
        eventParameters = new LinkedList<>();
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

    @Override
    public void saveEventParameter(EventAdventiser.EventParameter parameter) {
        eventParameters.add(parameter);
    }

    @Override
    public List<EventAdventiser.EventParameter> loadEventParameter() {
        return eventParameters;
    }
}
