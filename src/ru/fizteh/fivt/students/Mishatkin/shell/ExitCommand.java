package ru.fizteh.fivt.students.mishatkin.shell;

/**
 * ExitCommand.java
 * Created by Vladimir Mishatkin on 9/25/13
 */
public class ExitCommand extends Command {
	ExitCommand(CommandReceiver receiver) {
		super(receiver);
		type = COMMAND_TYPE.EXIT;
	}

	@Override
	public void execute() throws ShellException {
		receiver.exitCommand();
	}
}
