package vsb.gai0010;

import lombok.Getter;
import lombok.Setter;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CodePointCharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import vsb.gai0010.exception.SyntaxException;
import vsb.gai0010.instruction.IInstruction;
import vsb.gai0010.listener.ErrorListener;
import vsb.gai0010.listener.StatementListener;
import vsb.gai0010.stack.Element;

import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class RMachine {
    private final StatementListener listener;
    private final RatLangParser parser;
    @Getter
    private final Stack<Element> stack;
    @Getter
    private final List<IInstruction> instructions;
    @Getter
    private final Map<String, Integer> labels;
    @Getter
    private final Map<String, Element> ids;
    @Getter
    @Setter
    private int pc;

    public RMachine(Path input) throws IOException {
        String content = Files.readString(input);
        CodePointCharStream charStream = CharStreams.fromString(content);
        RatLangLexer lexer = new RatLangLexer(charStream);
        CommonTokenStream tokenStream = new CommonTokenStream(lexer);

        this.parser = new RatLangParser(tokenStream);
        parser.addErrorListener(new ErrorListener());

        this.listener = new StatementListener(this);
        this.stack = new Stack<>();
        this.instructions = new ArrayList<>();
        this.labels = new HashMap<>();
        this.ids = new HashMap<>();
        this.pc = 0;
    }

    public void compile() throws SyntaxException {
        if (parser.getNumberOfSyntaxErrors() != 0) {
            throw new SyntaxException();
        }

        ParseTreeWalker walker = new ParseTreeWalker();
        walker.walk(this.listener, this.parser.start());
    }

    public void printInstructions() {
        printInstructions(System.out);
    }

    public void printInstructions(PrintStream printStream) {
        for (IInstruction instruction : instructions) {
            printStream.println(instruction.toString());
        }
    }

    public void execute() {
        for (; pc < instructions.size(); pc++) {
            this.instructions.get(pc).execute();
        }
    }
}
