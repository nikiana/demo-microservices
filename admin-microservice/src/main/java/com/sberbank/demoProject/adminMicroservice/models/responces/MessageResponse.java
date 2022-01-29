package com.sberbank.demoProject.adminMicroservice.models.responces;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.FieldError;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MessageResponse {
	private Integer errorCode;
	private String message;

	public static MessageResponseBuilder builder() {
		return new MessageResponseBuilder();
	}

	@NoArgsConstructor
	public static class MessageResponseBuilder {
		private Integer errorCode;
		private String message;

		public MessageResponseBuilder errorCode(Integer errorCode) {
			this.errorCode = errorCode;
			return this;
		}

		public MessageResponseBuilder message(String message) {
			this.message = message;
			return this;
		}

		public MessageResponseBuilder message(List<FieldError> fieldErrors) {
			StringBuilder builder = new StringBuilder("{");
			fieldErrors.forEach(error -> builder
					.append(error.getField())
					.append(": '")
					.append(error.getDefaultMessage())
					.append("', "));

			this.message = builder.substring(0, builder.length() - 2) + "}";
			return this;
		}

		public MessageResponseBuilder message(String key, String value) {
			this.message = "{" + key + ": '" + value + "'}";
			return this;
		}

		public MessageResponse build() {
			return new MessageResponse(errorCode, message);
		}
	}
}