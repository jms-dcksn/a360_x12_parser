package com.automationanywhere.botcommand.samples.commands.basic;
/*
 * Copyright (c) 2020 Automation Anywhere.
 * All rights reserved.
 *
 * This software is the proprietary information of Automation Anywhere.
 * You shall use it only in accordance with the terms of the license agreement
 * you entered into with Automation Anywhere.
 */

/**
 * @author James Dickson
 */

import static com.automationanywhere.commandsdk.model.AttributeType.*;
import static com.automationanywhere.commandsdk.model.DataType.STRING;

import com.automationanywhere.botcommand.exception.BotCommandException;
import com.automationanywhere.botcommand.samples.Utils.BackendServer;
import com.automationanywhere.commandsdk.annotations.*;
import com.automationanywhere.commandsdk.annotations.rules.NotEmpty;
import com.imsweb.x12.reader.X12Reader;

import java.io.File;
import java.io.IOException;
import java.util.Map;

@BotCommand
@CommandPkg(label = "Start Session",
        description = "Starts Session with File or Text Input",
        icon = "x12_logo.svg",
        name = "startx12session",
        node_label = "Start Session {{sessionName}}",
        group_label="Admin",
        comment = true
)

public class A_StartSession {
    @Sessions
    private Map<String, Object> sessionMap;
    @Execute
    public void execute(
            @Idx(index = "1", type = TEXT)
            @Pkg(label = "Start Session", default_value_type = STRING, default_value = "Default")
            @NotEmpty String sessionName,
            @Idx(index = "2", type = FILE) @Pkg(label = "ANSI X12 File") @NotEmpty String path,
            @Idx(index = "3", type = SELECT, options = {
                    @Idx.Option(index ="3.1", pkg = @Pkg(label = "ANSI270_4010_X092", value = "ANSI270_4010_X092")),
                    @Idx.Option(index ="3.2", pkg = @Pkg(label = "ANSI271_4010_X092", value = "ANSI271_4010_X092")),
                    @Idx.Option(index ="3.3", pkg = @Pkg(label = "ANSI835_5010_X221", value = "ANSI835_5010_X221")),
                    @Idx.Option(index ="3.4", pkg = @Pkg(label = "ANSI835_4010_X091", value = "ANSI835_4010_X091")),
                    @Idx.Option(index ="3.5", pkg = @Pkg(label = "ANSI837_4010_X096", value = "ANSI837_4010_X096")),
                    @Idx.Option(index ="3.6", pkg = @Pkg(label = "ANSI837_4010_X097", value = "ANSI837_4010_X097")),
                    @Idx.Option(index ="3.7", pkg = @Pkg(label = "ANSI837_4010_X098", value = "ANSI837_4010_X098")),
                    @Idx.Option(index ="3.8", pkg = @Pkg(label = "ANSI837_5010_X222", value = "ANSI837_5010_X222")),
                    @Idx.Option(index ="3.9", pkg = @Pkg(label = "ANSI837_5010_X223", value = "ANSI837_5010_X223")),
                    @Idx.Option(index ="3.10", pkg = @Pkg(label = "ANSI277_5010_X214", value = "ANSI277_5010_X214")),
                    @Idx.Option(index ="3.11", pkg = @Pkg(label = "ANSI277_5010_X212", value = "ANSI277_5010_X212"))
            }) @Pkg(label = "X12 Version", default_value_type = STRING, default_value = "ANSI271_4010_X092") @NotEmpty String version
    ) {
        X12Reader reader;
        try {
            switch(version) {
                case "ANSI270_4010_X092":
                    reader = new X12Reader(X12Reader.FileType.ANSI270_4010_X092, new File(path));
                    break;
                case "ANSI271_4010_X092":
                    reader = new X12Reader(X12Reader.FileType.ANSI271_4010_X092, new File(path));
                    break;
                case "ANSI835_5010_X221":
                    reader = new X12Reader(X12Reader.FileType.ANSI835_5010_X221, new File(path));
                    break;
                case "ANSI835_4010_X091":
                    reader = new X12Reader(X12Reader.FileType.ANSI835_4010_X091, new File(path));
                    break;
                case "ANSI837_4010_X096":
                    reader = new X12Reader(X12Reader.FileType.ANSI837_4010_X096, new File(path));
                    break;
                case "ANSI837_4010_X097":
                    reader = new X12Reader(X12Reader.FileType.ANSI837_4010_X097, new File(path));
                    break;
                case "ANSI837_4010_X098":
                    reader = new X12Reader(X12Reader.FileType.ANSI837_4010_X098, new File(path));
                    break;
                case "ANSI837_5010_X222":
                    reader = new X12Reader(X12Reader.FileType.ANSI837_5010_X222, new File(path));
                    break;
                case "ANSI837_5010_X223":
                    reader = new X12Reader(X12Reader.FileType.ANSI837_5010_X223, new File(path));
                    break;
                case "ANSI277_5010_X214":
                    reader = new X12Reader(X12Reader.FileType.ANSI277_5010_X214, new File(path));
                    break;
                case "ANSI277_5010_X212":
                    reader = new X12Reader(X12Reader.FileType.ANSI277_5010_X212, new File(path));
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + version);
            }
        } catch (Exception e) {throw new BotCommandException("Error:" + e);}
        BackendServer server = new BackendServer(reader);
        sessionMap.put(sessionName, server);
    }
    public void setSessionMap(Map<String, Object> sessionMap) {
        this.sessionMap = sessionMap;
    }
}
