package pl.gawor.tayckner.taycknerbackend.web.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

/**
 * Class representing JSON that is being returned by controllers.
 * <p>
 * It contains code, message and content.
 * Code is the code number of ResponseStatus.
 * Message is the message of ResponseStatus.
 * Content is the requested content.
 * </p>
 */
public class Response {
    Map<String, Object> map = new HashMap<>();

    public ResponseEntity<Map<String, Object>> getResponseEntity() {
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    private Response(Builder builder) {
        map.put("code", builder.getResponseStatus().getCode());
        map.put("message", builder.getResponseStatus().getMessage());
        map.put("content", builder.getContent());
    }

    public static class Builder {
        ResponseStatus responseStatus = null;
        Object content = null;

        public Builder setResponseStatus(ResponseStatus responseStatus) {
            this.responseStatus = responseStatus;
            return this;
        }

        public Builder clear() {
            this.responseStatus = null;
            this.content = null;
            return this;
        }

        public Builder setContent(Object content) {
            this.content = content;
            return this;
        }

        public Response build() {
            return new Response(this);
        }

        public ResponseStatus getResponseStatus() {
            return responseStatus;
        }

        public Object getContent() {
            return content;
        }
    }

}
