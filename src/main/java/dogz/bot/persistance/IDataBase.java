package dogz.bot.persistance;

public interface IDataBase {
    public void saveID(String id, String desc);
    public long loadID(String id, String desc);

    public void saveEventChannel(String id);
    public long loadEventChannel(String id);
}
