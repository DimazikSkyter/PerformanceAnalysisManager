package ru.performancetool.analysis.controllers;


import lombok.Data;

import java.util.zip.ZipFile;

@Data
public class UserRequest {

    private ZipFile zipFile;
}
