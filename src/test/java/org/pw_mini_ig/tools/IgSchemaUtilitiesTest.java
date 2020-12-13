package org.pw_mini_ig.tools;

import org.junit.jupiter.api.Test;
import org.pw_mini_ig.models.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class IgSchemaUtilitiesTest {
    @Test
    public void ShouldProperlyGenerateYaml() {
        RootElement root = prepareTestCase1();
        String yaml = IgSchemaUtilities.generateYaml(root);
        Collection<String> errors = IgSchemaUtilities.validateYaml(yaml);

        assertTrue(errors.isEmpty());
    }

    private RootElement prepareTestCase1() {
        Collection<Statement> statements = new ArrayList<>();
        RootElement rootElement = new RootElement("The State requires employers to provide notification to employees of the availability of " +
                "unemployment compensation at the time of separation from employment.", statements);

        ComponentWithProperties regAttribute = new SimpleNode("The State", 0, 9);
        ComponentWithoutProperties regAim = new SimpleNode("requires", 10, 8);

        RegulativeStatement regulativeStatement = new RegulativeStatement(regAttribute, regAim, AtomicStatementType.institutionalStatement, "The State requires employers to provide notification to employees of the availability of " +
                " unemployment compensation at the time of separation from employment.", 0, 156);
        statements.add(regulativeStatement);


        ComponentWithProperties dirRegAttribute = new SimpleNode("employers", 19, 9);
        ComponentWithoutProperties dirRegAim = new SimpleNode("requires", 29, 10);

        RegulativeStatement directObjectStatement = new RegulativeStatement(dirRegAttribute, dirRegAim, AtomicStatementType.institutionalStatement,
                "employers to provide notification to employees of the availability of " +
                        " unemployment compensation at the time of separation from employment.", 19, 137);
        regulativeStatement.setDirectObject(directObjectStatement);


        StatementOrComponentWithProperties dirDirObject = new ComponentWithLooselyAttachedProperties(
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
}