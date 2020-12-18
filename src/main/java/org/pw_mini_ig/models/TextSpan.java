package org.pw_mini_ig.models;

import java.util.Objects;

public abstract class TextSpan {
    public enum TextSpanMode {
        TextOnly,
        WithBeginAndLength
    }

    public TextSpan(String text) {
        workingMode = TextSpanMode.TextOnly;
        setText(text);
    }

    public TextSpan(String text, int begin, int length) {
        workingMode = TextSpanMode.WithBeginAndLength;
        setText(text);
        setBegin(begin);
        setLength(length);
    }

    protected TextSpanMode workingMode;
    protected String text;
    protected int begin;
    protected int length;

    public TextSpanMode getWorkingMode() {
        return workingMode;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        Objects.requireNonNull(text);
        this.text = text;
    }

    public int getBegin() {
        return begin;
    }

    public void setBegin(int begin) {
        this.begin = begin;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }
}
