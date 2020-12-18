package org.pw_mini_ig.models;

import java.util.Objects;

public abstract class AbstractAtomicStatement extends AbstractStatement {

    protected AtomicStatementType type;
    protected SimpleNode deontic;
    protected StatementOrComponentWithoutProperties activationCondition;
    protected StatementOrComponentWithoutProperties executionConstraint;

    public AbstractAtomicStatement(AtomicStatementType type, String text) {
        super(text);
        setType(type);
    }

    public AbstractAtomicStatement(AtomicStatementType type, String text, int begin, int length) {
        super(text, begin, length);
        setType(type);
    }

    public AtomicStatementType getType() {
        return type;
    }

    public void setType(AtomicStatementType type) {
        if (this.type != null) {
            // Cannot change type
            // TODO: throw an exception
            return;
        }
        Objects.requireNonNull(type);
        this.type = type;
    }

    public SimpleNode getDeontic() {
        return deontic;
    }

    public void setDeontic(SimpleNode deontic) {
        if (type.equals(AtomicStatementType.statementOfFact)) {
            // TODO: operations like this should throw an exception
            return;
        }
        this.deontic = deontic;
    }

    public StatementOrComponentWithoutProperties getActivationCondition() {
        return activationCondition;
    }

    public void setActivationCondition(StatementOrComponentWithoutProperties activationCondition) {
        this.activationCondition = activationCondition;
    }

    public StatementOrComponentWithoutProperties getExecutionConstraint() {
        return executionConstraint;
    }

    public void setExecutionConstraint(StatementOrComponentWithoutProperties executionConstraint) {
        this.executionConstraint = executionConstraint;
    }

    @Override
    public void setOrElse(Statement orElse) {
        if (type.equals(AtomicStatementType.statementOfFact)) {
            // TODO: should throw an exception
            return;
        }
        super.setOrElse(orElse);
    }
}
