package dogz.bot.commands;

import dogz.bot.persistance.IDataBase;
import dogz.bot.persistance.LocalDB;

public abstract class Command implements ICommand {
    protected String name;
    protected IDataBase db;

    public Command(String _name){
        name = _name;
        db = new LocalDB();
    }
    public Command(String _name, IDataBase _db){
        name = _name;
        db = _db;
    }

    public String getName() {
        return name;
    }
}
