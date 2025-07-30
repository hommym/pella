package com.pellanotes.pella.features.tge.dtos;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class EventDto {
    


    @NotBlank(message="No Value passed for title")
    public String title;


    @NotNull(message="No Value passed for eventDate")
    public  LocalDate eventDate;


    public  LocalTime eventTime;

    public String description;

    public Boolean repeat;

}
