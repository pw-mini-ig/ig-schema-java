package org.pw_mini_ig.models;

public class SimpleNode extends TextSpan implements ComponentWithProperties, ComponentWithoutProperties {
    public SimpleNode(String text) {
        super(text);
    }

    public SimpleNode(String text, int begin, int length) {
        super(text, begin, length);
    }
}
