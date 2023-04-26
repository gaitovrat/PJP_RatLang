package vsb.gai0010;

import vsb.gai0010.compiler.Compiler;
import vsb.gai0010.compiler.CompilerMod;
import vsb.gai0010.compiler.REPL;

public class Application {
	public static void main(String[] args) {
		Compiler compiler = new Compiler();

		if (compiler.getCompilerMod() == CompilerMod.REPL) {
			new REPL(compiler).run();
		}
	}
}
