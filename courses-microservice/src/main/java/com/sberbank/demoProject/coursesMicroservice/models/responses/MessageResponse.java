package com.sberbank.demoProject.coursesMicroservice.models.responses;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * Кастомный респонс, который сервис возвращает при обрабатываемых исключениях
 */
@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MessageResponse {
	private Integer errorCode;
	private String message;
	private List<String> errors;
}