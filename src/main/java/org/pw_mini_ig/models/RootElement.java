package org.pw_mini_ig.models;

import java.util.Collection;
import java.util.Objects;

public class RootElement {
    private SchemaMode mode;
    private String text;
    private Collection<Statement> statements;

    public RootElement(String text, Collection<Statement> statements) {
        this(text, statements, SchemaMode.permissive);
    }

    public RootElement(String text, Collection<Statement> statements, SchemaMode mode) {
        setText(text);
        setStatements(statements);
        setMode(mode);
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
