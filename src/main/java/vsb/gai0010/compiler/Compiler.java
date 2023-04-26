package vsb.gai0010.compiler;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CodePointCharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import vsb.gai0010.RatLangLexer;
import vsb.gai0010.RatLangParser;
import vsb.gai0010.listener.ErrorListener;
import vsb.gai0010.visitor.Listener;

@AllArgsConstructor
@Getter
@Setter
public class Compiler {
    private CompilerMod compilerMod;

    public Compiler() {
        this.compilerMod = CompilerMod.REPL;
    }

    public RatLangParser parse(String program) {
        CodePointCharStream charStream = CharStreams.fromString(program);
        RatLangLexer lexer = new RatLangLexer(charStream);
        CommonTokenStream tokenStream = new CommonTokenStream(lexer);

        RatLangParser parser = new RatLangParser(tokenStream);
        parser.addErrorListener(new ErrorListener());

        return parser;
    }

    public void compile(RatLangParser parser) {
        if (parser.getNumberOfSyntaxErrors() == 0) {
            ParseTreeWalker walker = new ParseTreeWalker();
            walker.walk(new Listener(), parser.prog());
        }
    }
}
