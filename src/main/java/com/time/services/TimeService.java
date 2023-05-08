package com.time.services;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import org.springframework.stereotype.Service;

@Service
public class TimeService {

  private static final String[] HOURS = {
    "", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten", "eleven", "twelve"
  };

  private static final String[] TENS = {
    "", "", "twenty", "thirty", "forty", "fifty"
  };

  private static final String[] ONES = {
    "", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten",
    "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen",
    "eighteen", "nineteen"
  };

  public String convertToWords(String time) {
    try {
      LocalTime localTime = LocalTime.parse(time, DateTimeFormatter.ofPattern("HH:mm"));
      int hour = localTime.getHour();
      int minute = localTime.getMinute();
      
      String hourWord = HOURS[hour % 12 == 0 ? 12 : hour % 12];
      String minuteWord;
      if (minute == 0) {
        minuteWord = "o'clock";
      } else if (minute < 20) {
        minuteWord = ONES[minute];
      } else {
        minuteWord = TENS[minute / 10] + (minute % 10 == 0 ? "" : " " + ONES[minute % 10]);
      }
      
      String result = "It's " + hourWord;
      if (!minuteWord.equals("o'clock")) {
        result += " " + minuteWord;
      }
      return result;
    } catch (DateTimeParseException e) {
      throw new IllegalArgumentException("Invalid time format, expected HH:mm");
    }
  }
}
