package client.messages.commands;

public class ConsoleCommandObject
{
    private final String command;
    private final ConsoleCommandExecute exe;
    
    public ConsoleCommandObject(final String com, final ConsoleCommandExecute c) {
        this.command = com;
        this.exe = c;
    }
    
    public int execute(final String[] splitted) {
        return this.exe.execute(splitted);
    }
}
