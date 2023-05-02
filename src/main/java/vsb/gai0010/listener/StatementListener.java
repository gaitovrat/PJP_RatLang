package vsb.gai0010.listener;

import org.antlr.v4.runtime.ParserRuleContext;
import vsb.gai0010.RatLangBaseListener;
import vsb.gai0010.RatLangParser;

public class StatementListener extends RatLangBaseListener {
    @Override
    public void enterEveryRule(ParserRuleContext ctx) {
        String ruleName = RatLangParser.ruleNames[ctx.getRuleIndex()];
        int depth = ctx.depth();
        String indent = "  ".repeat(depth);
        System.out.printf("%s%s%n", indent, ruleName);
    }
}
