package org.pw_mini_ig.models;

import java.util.Objects;

public class RegulativeStatement extends AbstractAtomicStatement implements Statement, StatementOrComponentWithProperties, ComponentWithoutProperties {

    private ComponentWithProperties attribute;
    private StatementOrComponentWithProperties directObject;
    private StatementOrComponentWithProperties indirectObject;
    private ComponentWithoutProperties aim;

    public RegulativeStatement(ComponentWithProperties attribute, ComponentWithoutProperties aim, AtomicStatementType type, String text) {
        super(type, text);
        setAttribute(attribute);
        setAim(aim);
    }

    public RegulativeStatement(ComponentWithProperties attribute, ComponentWithoutProperties aim, AtomicStatementType type, String text, int begin, int length) {
        super(type, text, begin, length);
        setAttribute(attribute);
        setAim(aim);
    }

    public ComponentWithProperties getAttribute() {
        return attribute;
    }

    public void setAttribute(ComponentWithProperties attribute) {
        Objects.requireNonNull(attribute);
        this.attribute = attribute;
    }

    public StatementOrComponentWithProperties getDirectObject() {
        return directObject;
    }

    public void setDirectObject(StatementOrComponentWithProperties directObject) {
        this.directObject = directObject;
    }

    public StatementOrComponentWithProperties getIndirectObject() {
        return indirectObject;
    }

    public void setIndirectObject(StatementOrComponentWithProperties indirectObject) {
        this.indirectObject = indirectObject;
    }

    public ComponentWithoutProperties getAim() {
        return aim;
    }

    public void setAim(ComponentWithoutProperties aim) {
        Objects.requireNonNull(aim);
        this.aim = aim;
    }
}
