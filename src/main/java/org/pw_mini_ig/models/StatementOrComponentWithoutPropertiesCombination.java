package org.pw_mini_ig.models;

import org.pw_mini_ig.exceptions.InvalidIGDefinitionException;

import java.util.Collection;
import java.util.Objects;

public class StatementOrComponentWithoutPropertiesCombination implements StatementOrComponentWithoutProperties {

    private LogicalOperator logicalOperator;
    private Collection<StatementOrComponentWithoutProperties> components;

    public StatementOrComponentWithoutPropertiesCombination(LogicalOperator logicalOperator, Collection<StatementOrComponentWithoutProperties> components) throws InvalidIGDefinitionException {
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

    public Collection<StatementOrComponentWithoutProperties> getComponents() {
        return components;
    }

    public void setComponents(Collection<StatementOrComponentWithoutProperties> components) throws InvalidIGDefinitionException {
        Objects.requireNonNull(components);
        if (components.size() < 2) {
            throw new InvalidIGDefinitionException("components argument must be a collection with at least 2 elements");
        }
        this.components = components;
    }
}
