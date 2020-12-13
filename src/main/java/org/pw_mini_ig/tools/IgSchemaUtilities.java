package org.pw_mini_ig.tools;

import jakarta.json.stream.JsonParser;
import org.leadpony.justify.api.JsonSchema;
import org.leadpony.justify.api.JsonValidationService;
import org.leadpony.justify.api.ProblemHandler;
import org.pw_mini_ig.models.*;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.introspector.Property;
import org.yaml.snakeyaml.nodes.NodeTuple;
import org.yaml.snakeyaml.nodes.Tag;
import org.yaml.snakeyaml.representer.Representer;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Consumer;

public class IgSchemaUtilities {
    private static final JsonValidationService service = JsonValidationService.newInstance();

    /**
     * @param rootElement Root element of IG grammar
     * @return Generated YAML file
     */
    public static String generateYaml(RootElement rootElement) {
        DumperOptions options = getOptions();
        Representer representer = getCustomRepresenter();

        Yaml yaml = new Yaml(representer, options);
        return yaml.dump(rootElement);
    }

    /**
     * @param yamlFileContent Text content of yaml file
     * @return Collection of errors, if there are any
     */
    public static Collection<String> validateYaml(String yamlFileContent) {
        InputStream stream = new ByteArrayInputStream(yamlFileContent.getBytes(StandardCharsets.UTF_8));

        IgSchemaUtilities instance = new IgSchemaUtilities();
        InputStream jsonSchema = instance.getFileFromResourceAsStream("ig-schema.json");

        JsonSchema schema = service.readSchema(jsonSchema);
        return validateUsingJsonParser(schema, stream);
    }

    private static CustomRepresenter getCustomRepresenter() {
        CustomRepresenter representer = new CustomRepresenter();

        representer.addClassTag(RootElement.class, Tag.MAP);
        representer.addClassTag(TextSpan.class, Tag.MAP);
        representer.addClassTag(SimpleNode.class, Tag.MAP);
        representer.addClassTag(AbstractStatement.class, Tag.MAP);
        representer.addClassTag(AbstractAtomicStatement.class, Tag.MAP);
        representer.addClassTag(RegulativeStatement.class, Tag.MAP);
        representer.addClassTag(ConstitutiveStatement.class, Tag.MAP);
        representer.addClassTag(StatementCombination.class, Tag.MAP);

        representer.addClassTag(ComponentWithPropertiesCombination.class, Tag.MAP);
        representer.addClassTag(ComponentWithLooselyAttachedProperties.class, Tag.MAP);
        representer.addClassTag(ComponentWithoutPropertiesCombination.class, Tag.MAP);

        return representer;
    }

    private static DumperOptions getOptions() {
        DumperOptions options = new DumperOptions();
        options.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
        return options;
    }

    private static Collection<String> validateUsingJsonParser(JsonSchema schema, InputStream yamlToTest) {
        ErrorSaver errorSaver = new ErrorSaver();

        ProblemHandler handler = service.createProblemPrinter(errorSaver);
        try (JsonParser parser = service.createParser(yamlToTest, schema, handler)) {
            while (parser.hasNext()) {
                parser.next();
            }
        }

        return errorSaver.getErrors();
    }

    private InputStream getFileFromResourceAsStream(String fileName) {
        // The class loader that loaded the class
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(fileName);

        // the stream holding the file content
        if (inputStream == null) {
            throw new IllegalArgumentException("file not found! " + fileName);
        } else {
            return inputStream;
        }
    }
}

class CustomRepresenter extends Representer {
    public CustomRepresenter() {
    }

    @Override
    protected NodeTuple representJavaBeanProperty(Object javaBean, Property property, Object propertyValue, Tag customTag) {
        if (javaBean instanceof TextSpan) {
            HandlePropertyResult result = handlePropertiesOfTextSpan((TextSpan) javaBean, property, propertyValue, customTag);
            if (!result.shouldContinue) {
                return result.nodeTuple;
            }
        }

        if (propertyValue == null) {
            return null;
        }

        return super.representJavaBeanProperty(javaBean, property, propertyValue, customTag);
    }

    private HandlePropertyResult handlePropertiesOfTextSpan(TextSpan textSpan, Property property, Object propertyValue, Tag customTag) {
        if (textSpan.getWorkingMode().equals(TextSpan.TextSpanMode.TextOnly)) {
            String propName = property.getName();
            if ("begin".equals(propName) || "length".equals(propName)) {
                return new HandlePropertyResult(null, false);
            }
        }

        return new HandlePropertyResult(null, true);
    }

    private class HandlePropertyResult {
        private NodeTuple nodeTuple;

        public HandlePropertyResult(NodeTuple nodeTuple, boolean shouldContinue) {
            this.nodeTuple = nodeTuple;
            this.shouldContinue = shouldContinue;
        }

        private boolean shouldContinue;

        public NodeTuple getNodeTuple() {
            return nodeTuple;
        }

        public void setNodeTuple(NodeTuple nodeTuple) {
            this.nodeTuple = nodeTuple;
        }

        public boolean isShouldContinue() {
            return shouldContinue;
        }

        public void setShouldContinue(boolean shouldContinue) {
            this.shouldContinue = shouldContinue;
        }
    }
}

class ErrorSaver implements Consumer<String> {
    private Collection<String> errors = new ArrayList<>();

    @Override
    public void accept(String s) {
        errors.add(s);
    }

    public Collection<String> getErrors() {
        return errors;
    }
}