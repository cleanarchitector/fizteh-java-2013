package ru.fizteh.fivt.students.nadezhdakaratsapova.shell;

import java.io.IOException;

public interface Command {
    public String getName();
    public void execute(CurrentDirectory currentDirectory, String[] args) throws IOException;
    public int getArgsCount();
}
