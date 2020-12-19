package org.pw_mini_ig.models;

import org.pw_mini_ig.exceptions.InvalidIGDefinitionException;

import java.util.Collection;
import java.util.Objects;

public class ComponentWithPropertiesCombination implements ComponentWithProperties {
    private LogicalOperator logicalOperator;
    private Collection<ComponentWithProperties> components;

    public ComponentWithPropertiesCombination(LogicalOperator logicalOperator, Collection<ComponentWithProperties> components) throws InvalidIGDefinitionException {
        setLogicalOperator(logicalOperator);
        setComponents(components);
    }

    public LogicalOperator getLogicalOperator() {
        return logicalOperator;
    }

    public void setLogicalOperator(LogicalOperator logicalOperator) {
        Objects.requireNonNull(logicalOperator);
        this.logicalOperator = logicalOperator;
    }

    public Collection<ComponentWithProperties> getComponents() {
        return components;
    }

    public void setComponents(Collection<ComponentWithProperties> components) throws InvalidIGDefinitionException {
        Objects.requireNonNull(components);
        if (components.size() < 2) {
            throw new InvalidIGDefinitionException("components argument must be a collection with at least 2 elements");
        }
        this.components = components;
    }
}
