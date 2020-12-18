package org.pw_mini_ig.models;

public abstract class AbstractStatement extends TextSpan implements Statement {

    protected boolean inversion = false;
    protected Statement orElse;

    public AbstractStatement(String text) {
        super(text);
    }

    public AbstractStatement(String text, int begin, int length) {
        super(text, begin, length);
    }

    public Statement getOrElse() {
        return orElse;
    }

    public void setOrElse(Statement orElse) {
        this.orElse = orElse;
    }

    public boolean isInversion() {
        return inversion;
    }

    public void setInversion(boolean inversion) {
        this.inversion = inversion;
    }
}
