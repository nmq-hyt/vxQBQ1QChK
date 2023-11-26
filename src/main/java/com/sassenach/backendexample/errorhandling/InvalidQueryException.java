package com.sassenach.backendexample.errorhandling;

public class InvalidQueryException extends RuntimeException {
    
    public InvalidQueryException() {
        super("Invalid query!\n" + 
        "Please ensure your query's body text conforms to the query schema:\n" +
        "{\n" + //
                "  \"$schema\": \"http://json-schema.org/draft-07/schema#\",\n" + //
                "  \"title\": \"Generated schema for Root\",\n" + //
                "  \"type\": \"object\",\n" + //
                "  \"properties\": {\n" + //
                "    \"sensors\": {\n" + //
                "      \"type\": \"array\",\n" + //
                "      \"items\": {\n" + //
                "        \"type\": \"string\"\n" + //
                "      }\n" + //
                "    },\n" + //
                "    \"metrics\": {\n" + //
                "      \"type\": \"array\",\n" + //
                "      \"items\": {\n" + //
                "        \"type\": \"string\"\n" + //
                "      }\n" + //
                "    },\n" + //
                "    \"statistics\": {\n" + //
                "      \"type\": \"string\"\n" + //
                "    },\n" + //
                "    \"date_range\": {\n" + //
                "      \"type\": \"array\",\n" + //
                "      \"items\": {\n" + //
                "        \"type\": \"string\"\n" + //
                "      }\n" + //
                "    }\n" + //
                "  },\n" + //
                "  \"required\": [\n" + //
                "    \"sensors\",\n" + //
                "    \"metrics\",\n" + //
                "    \"statistics\",\n" + //
                "    \"date_range\"\n" + //
                "  ]\n" + //
                "}" 
                );
      }
}
