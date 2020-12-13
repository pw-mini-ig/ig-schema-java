package org.pw_mini_ig.models;

import java.util.Collection;
import java.util.Objects;

public class RootElement {
    private SchemaMode mode = SchemaMode.strict;
    private String text;
    private Collection<Statement> statements;

    public RootElement(String text, Collection<Statement> statements) {
        setText(text);
        setStatements(statements);
    }

    public SchemaMode getMode() {
        return mode;
    }

    public void setMode(SchemaMode mode) {
        this.mode = mode;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Collection<Statement> getStatements() {
        return statements;
    }

    public void setStatements(Collection<Statement> statements) {
        Objects.requireNonNull(statements);
        this.statements = statements;
    }
}
