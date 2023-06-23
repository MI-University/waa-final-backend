package com.waa.backend.dtos;

public class EmailDto {
    String message;
    String[] to;
    String from;
    String[] cc;
    String[] bcc;
    String[] files; // links
}
