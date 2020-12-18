package org.pw_mini_ig.models;

import java.util.Objects;

public class ConstitutiveStatement extends AbstractAtomicStatement {

    private ComponentWithProperties constitutedEntity;
    private ComponentWithoutProperties constitutiveFunction;
    private ComponentWithProperties constitutingProperty;

    public ConstitutiveStatement(ComponentWithProperties constitutedEntity, ComponentWithoutProperties constitutiveFunction, AtomicStatementType type, String text) {
        super(type, text);
        setConstitutedEntity(constitutedEntity);
        setConstitutiveFunction(constitutiveFunction);
    }

    public ConstitutiveStatement(ComponentWithProperties constitutedEntity, ComponentWithoutProperties constitutiveFunction, AtomicStatementType type, String text, int begin, int length) {
        super(type, text, begin, length);
        setConstitutedEntity(constitutedEntity);
        setConstitutiveFunction(constitutiveFunction);
    }

    public ComponentWithProperties getConstitutedEntity() {
        return constitutedEntity;
    }

    public void setConstitutedEntity(ComponentWithProperties constitutedEntity) {
        Objects.requireNonNull(constitutedEntity);
        this.constitutedEntity = constitutedEntity;
    }

    public ComponentWithoutProperties getConstitutiveFunction() {
        return constitutiveFunction;
    }

    public void setConstitutiveFunction(ComponentWithoutProperties constitutiveFunction) {
        Objects.requireNonNull(constitutiveFunction);
        this.constitutiveFunction = constitutiveFunction;
    }

    public ComponentWithProperties getConstitutingProperty() {
        return constitutingProperty;
    }

    public void setConstitutingProperty(ComponentWithProperties constitutingProperty) {
        this.constitutingProperty = constitutingProperty;
    }
}
