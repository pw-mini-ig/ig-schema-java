package org.pw_mini_ig.models;

import org.pw_mini_ig.exceptions.InvalidIGDefinitionException;

import java.util.Collection;
import java.util.Objects;

public class ComponentWithoutPropertiesCombination implements ComponentWithoutProperties {

    private LogicalOperator logicalOperator;
    private Collection<ComponentWithoutProperties> components;

    public ComponentWithoutPropertiesCombination(LogicalOperator logicalOperator, Collection<ComponentWithoutProperties> components) throws InvalidIGDefinitionException {
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

    public Collection<ComponentWithoutProperties> getComponents() {
        return components;
    }

    public void setComponents(Collection<ComponentWithoutProperties> components) throws InvalidIGDefinitionException {
        Objects.requireNonNull(components);
        if (components.size() < 2) {
            throw new InvalidIGDefinitionException("components argument must be a collection with at least 2 elements");
        }
        this.components = components;
    }
}
