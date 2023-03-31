package vsb.gai0010;

import org.antlr.v4.runtime.CodePointCharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.CharStreams;

import vsb.gai0010.ArrayInitParser.InitContext;

public class Application {
	public static void main(String[] args) {
		CodePointCharStream codePointCharStream = CharStreams.fromString("{ 1, 2, { 3, 6 } }");
		ArrayInitLexer arrayInitLexer = new ArrayInitLexer(codePointCharStream);
		CommonTokenStream commonTokenStream = new CommonTokenStream(arrayInitLexer);
		ArrayInitParser parser = new ArrayInitParser(commonTokenStream);
		
		InitContext context = parser.init();
		
		System.out.println(context.toStringTree());
	}
}
