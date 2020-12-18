package org.pw_mini_ig.models;

import java.util.Collection;
import java.util.Objects;

public class ComponentWithLooselyAttachedProperties implements ComponentWithProperties {

    private SimpleNode element;
    private Collection<StatementOrComponentWithProperties> properties;

    public ComponentWithLooselyAttachedProperties(SimpleNode element, Collection<StatementOrComponentWithProperties> properties) {
        setElement(element);
        setProperties(properties);
    }

    public SimpleNode getElement() {
        return element;
    }

    public void setElement(SimpleNode element) {
        Objects.requireNonNull(element);
        this.element = element;
    }

    public Collection<StatementOrComponentWithProperties> getProperties() {
        return properties;
    }

    public void setProperties(Collection<StatementOrComponentWithProperties> properties) {
        Objects.requireNonNull(properties);
        if (properties.isEmpty()) {
            throw new IllegalArgumentException("properties argument must be a non empty collection");
        }
        this.properties = properties;
    }
}
