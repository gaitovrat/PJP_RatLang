package vsb.gai0010;

import vsb.gai0010.exception.SyntaxException;

import java.io.IOException;
import java.nio.file.Paths;

public class Application {
	public static void main(String[] args) {
		if (args.length < 1) {
			System.err.println("Missing input file.");
			return;
		}

		try {
			RMachine machine = new RMachine(Paths.get(args[0]));
			machine.compile();
			machine.printInstructions();
			machine.execute();
		} catch (IOException e) {
			System.err.println(args[0] + " not found.");
		} catch (SyntaxException e) {
			System.err.print(e.getMessage());
		}
	}
}
