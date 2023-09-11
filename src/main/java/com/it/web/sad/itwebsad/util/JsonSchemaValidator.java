package com.it.web.sad.itwebsad.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonschema.core.report.ProcessingReport;
import com.github.fge.jsonschema.main.JsonSchema;
import com.github.fge.jsonschema.main.JsonSchemaFactory;
import com.it.web.sad.itwebsad.dto.CommentDTO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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

    public String getJsonSchemaFromFile() {
        try (InputStreamReader inputStreamReader = new InputStreamReader(getClass().getResourceAsStream("/comment-schema.json"));
             BufferedReader reader = new BufferedReader(inputStreamReader)) {
            return reader.lines().collect(Collectors.joining(System.lineSeparator()));
        } catch (IOException e) {
            e.printStackTrace(); // 예외 처리를 적절하게 수정하세요.
            return "";
        }
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