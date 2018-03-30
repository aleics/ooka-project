package com.senacor.code.fullstack.chat.exceptions;

/**
 * Created by Nikolas Glaser on 26.01.18.
 */

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No such channel")
public class ChannelNotFoundException extends RuntimeException {
}
