package dogz.bot.persistance;

import dogz.bot.commands.EventAdventiser;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface IDataBase {
    public void saveButtonID(String id, String desc);
    public Map<String,String> loadButtonID();

    public void saveEventChannel(String id);
    public Set<String> loadEventChannel();

    public void saveEventParameter(EventAdventiser.EventParameter parameter);
    public List<EventAdventiser.EventParameter> loadEventParameter();
}
