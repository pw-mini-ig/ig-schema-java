package org.pw_mini_ig.tools;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.pw_mini_ig.models.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class IgSchemaUtilitiesTest {

    // Unfortunately, the test runner reports the cases with rather unhelpful, garbled
    // names, can we set a name for each Arguments object?
    @ParameterizedTest
    @MethodSource
    public void ShouldProperlyGenerateYaml(RootElement root) {
        String yaml = IgSchemaUtilities.generateYaml(root);
        Collection<String> errors = IgSchemaUtilities.validateYaml(yaml);

        assertTrue(errors.isEmpty());
    }

    private static Stream<Arguments> ShouldProperlyGenerateYaml() {
        return Stream.of(
                Arguments.of(prepareTestCaseStrict1()),
                Arguments.of(prepareTestCasePermissive1())
        );
    }

    private static RootElement prepareTestCaseStrict1() {
        var statements = new ArrayList<Statement>();
        var rootElement = new RootElement(
                "The State requires employers to provide notification to employees of the availability of " +
                "unemployment compensation at the time of separation from employment.",
                statements,
                SchemaMode.strict
        );

        var regAttribute = new SimpleNode("The State", 0, 9);
        var regAim = new SimpleNode("requires", 10, 8);

        var regulativeStatement = new RegulativeStatement(regAttribute, regAim, AtomicStatementType.institutionalStatement,
                "The State requires employers to provide notification to employees of the availability of " +
                "unemployment compensation at the time of separation from employment.", 0, 156);
        statements.add(regulativeStatement);

        var dirRegAttribute = new SimpleNode("employers", 19, 9);
        var dirRegAim = new SimpleNode("requires", 29, 10);

        var directObjectStatement = new RegulativeStatement(dirRegAttribute, dirRegAim, AtomicStatementType.institutionalStatement,
                "employers to provide notification to employees of the availability of " +
                        " unemployment compensation at the time of separation from employment.", 19, 137);
        regulativeStatement.setDirectObject(directObjectStatement);


        var dirDirObject = new ComponentWithLooselyAttachedProperties(
                new SimpleNode("notification", 40, 12),
                new ArrayList<>(
                        List.of(new SimpleNode("of the availability of unemployment compensation", 66, 48))
                ));

        directObjectStatement.setDirectObject(dirDirObject);
        directObjectStatement.setIndirectObject(
                new SimpleNode("to employees", 53, 12));
        directObjectStatement.setActivationCondition(
                new SimpleNode("at the time of separation from employment.", 115, 41));

        return rootElement;
    }

    private static RootElement prepareTestCasePermissive1() {
        var statements = new ArrayList<Statement>();
        var rootElement = new RootElement("The State requires employers to provide notification to employees of the availability of " +
                "unemployment compensation at the time of separation from employment.", statements);

        var regAttribute = new SimpleNode("The State");
        var regAim = new SimpleNode("requires");

        var regulativeStatement = new RegulativeStatement(regAttribute, regAim, AtomicStatementType.institutionalStatement,
                "The State requires employers to provide notification to employees of the availability of " +
                        "unemployment compensation at the time of separation from employment.");
        statements.add(regulativeStatement);

        var dirRegAttribute = new SimpleNode("employers");
        var dirRegAim = new SimpleNode("requires");

        var directObjectStatement = new RegulativeStatement(dirRegAttribute, dirRegAim, AtomicStatementType.institutionalStatement,
                "employers to provide notification to employees of the availability of " +
                        " unemployment compensation at the time of separation from employment.");
        regulativeStatement.setDirectObject(directObjectStatement);


        var dirDirObject = new ComponentWithLooselyAttachedProperties(
                new SimpleNode("notification"),
                new ArrayList<>(
                        List.of(new SimpleNode("of the availability of unemployment compensation"))
                ));

        directObjectStatement.setDirectObject(dirDirObject);
        directObjectStatement.setIndirectObject(
                new SimpleNode("to employees"));
        directObjectStatement.setActivationCondition(
                new SimpleNode("at the time of separation from employment."));

        return rootElement;
    }
}