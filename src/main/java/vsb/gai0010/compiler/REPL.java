package vsb.gai0010.compiler;

import lombok.AllArgsConstructor;
import vsb.gai0010.Command;
import vsb.gai0010.RatLangParser;

import java.util.Scanner;

public class REPL {
    private final Compiler compiler;
    private final Scanner scanner;

    public REPL(Compiler compiler) {
        this.compiler = compiler;
        this.scanner = new Scanner(System.in);
    }

    public void run() {
        while (true) {
            System.out.print(">> ");
            String line = this.scanner.nextLine();

            if (this.checkCommand(line)) {
                break;
            }

            if (line.charAt(line.length() - 1) != ';') {
                line += ';';
            }
            RatLangParser parser = this.compiler.parse(line);
            this.compiler.compile(parser);
        }
    }

    private boolean checkCommand(String command) {
        return command.equals(Command.QUIT);
    }
}
