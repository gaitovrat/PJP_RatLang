package vsb.gai0010;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CodePointCharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import vsb.gai0010.compiler.Compiler;
import vsb.gai0010.compiler.CompilerMod;
import vsb.gai0010.compiler.REPL;
import vsb.gai0010.listener.ErrorListener;
import vsb.gai0010.visitor.Listener;

import java.util.Scanner;

public class Application {
	public static void main(String[] args) {
		Compiler compiler = new Compiler();

		if (compiler.getCompilerMod() == CompilerMod.REPL) {
			new REPL(compiler).run();
		}
	}
}
