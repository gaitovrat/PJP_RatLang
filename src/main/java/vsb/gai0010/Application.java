package vsb.gai0010;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CodePointCharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import vsb.gai0010.listener.ErrorListener;
import vsb.gai0010.listener.StatementListener;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Application {
	public static void main(String[] args) {
		if (args.length < 1) {
			System.err.println("Missing input file.");
			return;
		}

		String content;
		try {
			content = Files.readString(Paths.get(args[0]));
		} catch (IOException e) {
			System.err.println(args[0] + " not found.");
			return;
		}

		System.out.println(content);

		CodePointCharStream charStream = CharStreams.fromString(content);
		RatLangLexer lexer = new RatLangLexer(charStream);
		CommonTokenStream tokenStream = new CommonTokenStream(lexer);
		RatLangParser parser = new RatLangParser(tokenStream);
		parser.addErrorListener(new ErrorListener());
		RatLangParser.StartContext start = parser.start();

		if (parser.getNumberOfSyntaxErrors() == 0) {
			ParseTreeWalker walker = new ParseTreeWalker();
			walker.walk(new StatementListener(), start);
		}
	}
}
