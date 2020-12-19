package org.pw_mini_ig.models;

import org.pw_mini_ig.exceptions.InvalidIGDefinitionException;

import java.util.Objects;

public abstract class AbstractAtomicStatement extends AbstractStatement {

    protected AtomicStatementType type;
    protected SimpleNode deontic;
    protected StatementOrComponentWithoutProperties activationCondition;
    protected StatementOrComponentWithoutProperties executionConstraint;

    public AbstractAtomicStatement(AtomicStatementType type, String text) {
        super(text);
        try {
            setType(type);
        } catch (InvalidIGDefinitionException e) {
           // Cannot happen
        }
    }

    public AbstractAtomicStatement(AtomicStatementType type, String text, int begin, int length) {
        super(text, begin, length);
        try {
            setType(type);
        } catch (InvalidIGDefinitionException e) {
            // Cannot happen
        }
    }

    public AtomicStatementType getType() {
        return type;
    }

    public void setType(AtomicStatementType type) throws InvalidIGDefinitionException {
        if (this.type != null) {
            throw new InvalidIGDefinitionException("Cannot change type. You have to create another object.");
        }
        Objects.requireNonNull(type);
        this.type = type;
    }

    public SimpleNode getDeontic() {
        return deontic;
    }

    public void setDeontic(SimpleNode deontic) throws InvalidIGDefinitionException {
        if (type.equals(AtomicStatementType.statementOfFact)) {
            throw new InvalidIGDefinitionException(String.format("Deontic cannot have value for %s type", AtomicStatementType.statementOfFact.toString()));
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
    public void setOrElse(Statement orElse) throws InvalidIGDefinitionException {
        if (type.equals(AtomicStatementType.statementOfFact)) {
            throw new InvalidIGDefinitionException(String.format("SetOrElse cannot have value for %s type", AtomicStatementType.statementOfFact.toString()));
        }
        super.setOrElse(orElse);
    }
}
