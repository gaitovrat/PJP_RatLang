package vsb.gai0010;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CodePointCharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import vsb.gai0010.listener.ErrorListener;
import vsb.gai0010.visitor.Listener;

import java.util.Scanner;

public class Application {
	public static void main(String[] args) {
		while (true) {
			// Get input
			System.out.print(">> ");
			Scanner scanner = new Scanner(System.in);
			String line = scanner.nextLine();

			// Check commands
			if (line.equals(Command.QUIT)) {
				break;
			}

			// Load expression
			if (line.charAt(line.length() - 1) != ';') {
				line += ';';
			}
			CodePointCharStream charStream = CharStreams.fromString(line);
			RatLangLexer lexer = new RatLangLexer(charStream);
			CommonTokenStream tokenStream = new CommonTokenStream(lexer);

			// Parse expression
			RatLangParser parser = new RatLangParser(tokenStream);
			parser.addErrorListener(new ErrorListener());
			RatLangParser.ProgContext prog = parser.prog();

			// Execute expression
			if (parser.getNumberOfSyntaxErrors() == 0) {
				ParseTreeWalker walker = new ParseTreeWalker();
				walker.walk(new Listener(), prog);
			}
		}

	}
}
