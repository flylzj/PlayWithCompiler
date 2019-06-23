package script;

import java.util.HashMap;
import java.util.Map;

import script.PlayScriptParser.AdditiveExpressionContext;
import script.PlayScriptParser.ArgumentExpressionListContext;
import script.PlayScriptParser.AssignmentExpressionContext;
import script.PlayScriptParser.AssignmentOperatorContext;
import script.PlayScriptParser.BlockItemContext;
import script.PlayScriptParser.BlockItemListContext;
import script.PlayScriptParser.CompoundStatementContext;
import script.PlayScriptParser.DeclarationContext;
import script.PlayScriptParser.ExpressionContext;
import script.PlayScriptParser.ExpressionStatementContext;
import script.PlayScriptParser.InitializerContext;
import script.PlayScriptParser.LiteralContext;
import script.PlayScriptParser.MultiplicativeExpressionContext;
import script.PlayScriptParser.PrimaryExpressionContext;
import script.PlayScriptParser.PrimitiveTypeContext;
import script.PlayScriptParser.StatementContext;

public class CalcVisitor extends PlayScriptBaseVisitor<Integer> {
    Map<String, Integer> memory = new HashMap<String, Integer>();

    @Override
    public Integer visitAdditiveExpression(AdditiveExpressionContext ctx) {
        if (ctx.ADD() != null || ctx.SUB() != null) {
            Integer left = visitAdditiveExpression(ctx.additiveExpression());
            Integer right = visitMultiplicativeExpression(ctx.multiplicativeExpression());
            if (ctx.ADD() != null) {
                return left + right;
            } else {
                return left - right;
            }
        } else {
            return visitMultiplicativeExpression(ctx.multiplicativeExpression());
        }
    }

    @Override
    public Integer visitArgumentExpressionList(ArgumentExpressionListContext ctx) {
        return super.visitArgumentExpressionList(ctx);
    }

    @Override
    public Integer visitAssignmentExpression(AssignmentExpressionContext ctx) {
        String id = ctx.Identifier().getText();
        System.out.println(id);
        return super.visitAssignmentExpression(ctx);
    }

    @Override
    public Integer visitBlockItem(BlockItemContext ctx) {
        return super.visitBlockItem(ctx);
    }

    @Override
    public Integer visitBlockItemList(BlockItemListContext ctx) {
        return super.visitBlockItemList(ctx);
    }

    @Override
    public Integer visitCompoundStatement(CompoundStatementContext ctx) {
        return super.visitCompoundStatement(ctx);
    }

    @Override
    public Integer visitDeclaration(DeclarationContext ctx) {
        return super.visitDeclaration(ctx);
    }

    @Override
    public Integer visitExpression(ExpressionContext ctx) {
        return super.visitExpression(ctx);
    }

    @Override
    public Integer visitExpressionStatement(ExpressionStatementContext ctx) {
        return super.visitExpressionStatement(ctx);
    }

    @Override
    public Integer visitInitializer(InitializerContext ctx) {
        return super.visitInitializer(ctx);
    }

    @Override
    public Integer visitMultiplicativeExpression(MultiplicativeExpressionContext ctx) {
        if (ctx.MUL() != null || ctx.DIV() != null || ctx.MOD() != null) {
            Integer left = visitMultiplicativeExpression(ctx.multiplicativeExpression());
            Integer right = visitPrimaryExpression(ctx.primaryExpression());
            if (ctx.MUL() != null) {
                return left * right;
            } else if (ctx.DIV() != null) {
                return left / right;
            } else {
                return left % right;
            }
        } else {
            return visitPrimaryExpression(ctx.primaryExpression());
        }
    }

    @Override
    public Integer visitPrimaryExpression(PrimaryExpressionContext ctx) {
        if (ctx.literal() != null) {
            return visitLiteral(ctx.literal());
        }
        return 0;
    }

    @Override
    public Integer visitStatement(StatementContext ctx) {
        return super.visitStatement(ctx);
    }

    @Override
    public Integer visitAssignmentOperator(AssignmentOperatorContext ctx) {
        return super.visitAssignmentOperator(ctx);
    }

    @Override
    public Integer visitLiteral(LiteralContext ctx) {
        if (ctx.IntegerLiteral() !=null){
            return Integer.valueOf(ctx.IntegerLiteral().getText());
        }
        return 0;
    }

    @Override
    public Integer visitPrimitiveType(PrimitiveTypeContext ctx) {
        return super.visitPrimitiveType(ctx);
    }

}
