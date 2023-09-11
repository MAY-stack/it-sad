package com.it.web.sad.itwebsad.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonschema.core.report.ProcessingReport;
import com.github.fge.jsonschema.main.JsonSchema;
import com.github.fge.jsonschema.main.JsonSchemaFactory;
import com.it.web.sad.itwebsad.dto.CommentDTO;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;

public class JsonSchemaValidator {
    final ObjectMapper mapper = new ObjectMapper();

    private JsonNode convertObjToJsonNode(Object object) {
        try {
            return mapper.valueToTree(object);
        } catch (Exception e) {
            //
        }
        return null;
    }

    private JsonNode convertCardRuleToJsonNode(String cardRule) {
        try {
            return mapper.readTree(cardRule);
        } catch (Exception e) {
            //
        }
        return null;
    }

    private String getJsonSchemaFromFile() {
        try {
            Path path = Paths.get(ClassLoader.getSystemResource("comment-schema.json").toURI());
            return Files.readAllLines(path).stream().collect(Collectors.joining());
        } catch (IOException | URISyntaxException e) {
            //
        }
        return "";
    }

    public boolean validate(CommentDTO commentDTO) {
        // 문자열 스키마를 기반으로 JsonNode 생성
        JsonNode schemaNode = convertCardRuleToJsonNode(getJsonSchemaFromFile());

        // 스키마 validator 초기화
        JsonSchemaFactory factory = JsonSchemaFactory.byDefault();

        ProcessingReport report = null;
        try {

            // 스키마 객체 생성
            JsonSchema schema = factory.getJsonSchema(schemaNode);

            // 테스트 데이터를 JsonNode 타입으로 변환한다.
            JsonNode data = convertObjToJsonNode(commentDTO);

            // 검증
            report = schema.validate(data);

        } catch (Exception e) {
            //
        }

        if (report.isSuccess()) {
            //System.out.println("Json data is valid :)");
            return true;
        } else {
            //System.out.println("Json data is not valid :( => " + report);
            return false;
        }
    }
}