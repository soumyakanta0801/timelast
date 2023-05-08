package com.time.controller;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.time.services.TimeService;

@RestController
@RequestMapping("/time")
public class TimeController {

  @Autowired
  private TimeService timeService;

  @GetMapping("/current")
  public String getCurrentTime() {
    String currentTime = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm"));
    return timeService.convertToWords(currentTime);
  }

  @GetMapping("/{time}")
  public String convertToWords(@PathVariable @Valid @Pattern(regexp = "^([01][0-9]|2[0-3]):([0-5][0-9])$") String time) {
    return timeService.convertToWords(time);
  }

  @ExceptionHandler
  //@ResponseStatus(HttpStatus.BAD_REQUEST)
  public String handleIllegalArgumentException(IllegalArgumentException e) {
    return e.getMessage();
  }
}
