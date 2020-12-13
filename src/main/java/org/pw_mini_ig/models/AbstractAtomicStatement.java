package org.pw_mini_ig.models;

import java.util.Objects;

public class AbstractAtomicStatement extends AbstractStatement {

    protected AtomicStatementType type;
    protected SimpleNode deontic;
    protected ComponentWithoutProperties activationCondition;
    protected ComponentWithoutProperties executionConstraint;

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
            return;
        }
        this.deontic = deontic;
    }

    public ComponentWithoutProperties getActivationCondition() {
        return activationCondition;
    }

    public void setActivationCondition(ComponentWithoutProperties activationCondition) {
        this.activationCondition = activationCondition;
    }

    public ComponentWithoutProperties getExecutionConstraint() {
        return executionConstraint;
    }

    public void setExecutionConstraint(ComponentWithoutProperties executionConstraint) {
        this.executionConstraint = executionConstraint;
    }

    @Override
    public void setOrElse(Statement orElse) {
        if (type.equals(AtomicStatementType.statementOfFact)) {
            return;
        }
        super.setOrElse(orElse);
    }
}
