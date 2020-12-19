package org.pw_mini_ig.models;

import org.pw_mini_ig.exceptions.InvalidIGDefinitionException;

import java.util.Collection;
import java.util.Objects;

public class StatementCombination extends AbstractStatement {

    private LogicalOperator logicalOperator;
    private Collection<Statement> statements;

    public StatementCombination(LogicalOperator logicalOperator, Collection<Statement> statements, String text) throws InvalidIGDefinitionException {
        super(text);
        setLogicalOperator(logicalOperator);
        setStatements(statements);
    }

    public StatementCombination(LogicalOperator logicalOperator, Collection<Statement> statements, String text, int begin, int length) throws InvalidIGDefinitionException {
        super(text, begin, length);
        setLogicalOperator(logicalOperator);
        setStatements(statements);
    }

    public LogicalOperator getLogicalOperator() {
        return logicalOperator;
    }

    public void setLogicalOperator(LogicalOperator logicalOperator) {
        Objects.requireNonNull(logicalOperator);
        this.logicalOperator = logicalOperator;
    }

    public Collection<Statement> getStatements() {
        return statements;
    }

    public void setStatements(Collection<Statement> statements) throws InvalidIGDefinitionException {
        Objects.requireNonNull(statements);
        if (statements.size() < 2) {
            throw new InvalidIGDefinitionException("statements parameter must be collection with at least 2 items");
        }
        this.statements = statements;
    }
}
